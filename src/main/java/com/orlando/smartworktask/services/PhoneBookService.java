package com.orlando.smartworktask.services;

import com.orlando.smartworktask.domain.PhoneBook;

import java.util.Set;

public interface PhoneBookService {

    Set<PhoneBook> getPhoneBooks();
    PhoneBook savePhoneBook(PhoneBook phoneBook);
    void deletePhoneBookById( Long id);
     PhoneBook findPhoneBookById(Long id);
}
