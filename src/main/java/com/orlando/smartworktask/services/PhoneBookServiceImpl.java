package com.orlando.smartworktask.services;

import com.orlando.smartworktask.domain.PhoneBook;
import com.orlando.smartworktask.repositories.PhoneBookRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PhoneBookServiceImpl implements PhoneBookService {

    PhoneBookRepository phoneBookRepository;

    public PhoneBookServiceImpl(PhoneBookRepository phoneBookRepository) {
        this.phoneBookRepository = phoneBookRepository;
    }

    @Override
    public Set<PhoneBook> getPhoneBooks() {
        Set<PhoneBook> phoneBookSet=new HashSet<>();
         phoneBookRepository.findAll().iterator().forEachRemaining(phoneBookSet::add);
         return phoneBookSet;
    }

    @Override
    public PhoneBook savePhoneBook(PhoneBook phoneBook) {
       return  phoneBookRepository.saveAndFlush(phoneBook);

    }

    @Override
    public void deletePhoneBookById(Long id) {
        phoneBookRepository.deleteById(id);

    }

    @Override
    public PhoneBook findPhoneBookById(Long id) {
        return phoneBookRepository.findById(id).orElse(null);
    }
}
