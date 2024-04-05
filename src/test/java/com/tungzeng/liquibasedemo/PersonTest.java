package com.tungzeng.liquibasedemo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class PersonTest {



    @BeforeEach
    void setUp() {
        //connect database
    }

    @AfterEach
    void tearDown() {
        //close,release db connection
    }

    @Test
    void getName() {

        //query db

    }

    @Test
    void setName() {
    }

    @Test
    void getHeight() {
    }

    @Test
    void setHeight() {
    }

    @Test
    void getAddress() {
    }

    @Test
    void setAddress() {
    }
}