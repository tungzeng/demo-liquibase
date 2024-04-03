package com.tungzeng.liquibasedemo.repository;

import com.tungzeng.liquibasedemo.model.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

    @Query("SELECT p.name FROM Person p WHERE p.name LIKE %:personName%")
    String findByName(String personName);

    @Modifying
    @Transactional
    @Query("DELETE FROM Person p WHERE p.name = :personName")
    void deleteByName(@Param("personName") String personName);

}
