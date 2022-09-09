package com.example.fuckdaka.dao;

import com.example.fuckdaka.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardDao extends JpaRepository<Card, Long> {

}
