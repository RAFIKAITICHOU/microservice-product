package com.microservice.product;

import com.microservice.product.config.AppConfig;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(AppConfig.class)
class SpringContextTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void contextLoadsAndSessionFactoryIsOpen() {
        assertNotNull(sessionFactory);
        assertFalse(sessionFactory.isClosed());
    }
}
