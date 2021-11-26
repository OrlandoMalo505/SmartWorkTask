package com.orlando.smartworktask.repositories;

import com.orlando.smartworktask.domain.PhoneBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneBookRepository extends JpaRepository<PhoneBook, Long> {


}
