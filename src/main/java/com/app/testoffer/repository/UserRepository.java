package com.app.testoffer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.testoffer.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

}

