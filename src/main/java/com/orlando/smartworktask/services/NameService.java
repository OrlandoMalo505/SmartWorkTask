package com.orlando.smartworktask.services;

import com.orlando.smartworktask.domain.Name;
import com.orlando.smartworktask.domain.PhoneBook;

public interface NameService {

    void saveName(PhoneBook phoneBook);

    Name findNameById(Long nameId);

}
