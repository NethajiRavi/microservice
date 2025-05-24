package com.eazybytes.card.repository;

import com.eazybytes.card.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CardsRepository extends JpaRepository<Cards,Long> {


    Optional<Cards> findByMobileNumber(String mobileNumber);
}
