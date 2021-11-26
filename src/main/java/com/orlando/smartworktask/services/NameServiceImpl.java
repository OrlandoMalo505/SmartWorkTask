package com.orlando.smartworktask.services;

import com.orlando.smartworktask.domain.Name;
import com.orlando.smartworktask.domain.PhoneBook;
import com.orlando.smartworktask.repositories.NameRepository;
import org.springframework.stereotype.Service;

@Service
public class NameServiceImpl implements NameService {

    NameRepository nameRepository;

    public NameServiceImpl(NameRepository nameRepository) {
        this.nameRepository = nameRepository;
    }

    @Override
    public void saveName(PhoneBook phoneBook) {
        Name name=new Name();
        name.setFirstName(phoneBook.getName().getFirstName());
        name.setLastName(phoneBook.getName().getLastName());
        nameRepository.saveAndFlush(name);
    }

    @Override
    public Name findNameById(Long nameId) {
        return nameRepository.findByNameId(nameId);
    }
}
