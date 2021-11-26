package com.orlando.smartworktask.repositories;

import com.orlando.smartworktask.domain.Name;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NameRepository extends JpaRepository<Name, Long> {

    Name findByNameId(Long nameId);
}
