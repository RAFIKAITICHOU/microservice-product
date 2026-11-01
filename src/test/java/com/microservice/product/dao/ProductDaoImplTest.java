package com.microservice.product.dao;

import com.microservice.product.config.AppConfig;
import com.microservice.product.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/** Chaque test est transactionnel : rollback automatique à la fin. */
@SpringJUnitConfig(AppConfig.class)
@Transactional
class ProductDaoImplTest {

    @Autowired
    private IDao<Product> productDao;

    @Test
    void saveAssignsId() {
        Product p = new Product("Clavier", 25.0);
        productDao.save(p);
        assertNotNull(p.getId());
    }

    @Test
    void findByIdReturnsSavedProduct() {
        Product p = new Product("Souris", 15.5);
        productDao.save(p);

        Product found = productDao.findById(p.getId());
        assertNotNull(found);
        assertEquals("Souris", found.getName());
        assertEquals(15.5, found.getPrice(), 0.001);
    }

    @Test
    void findAllReturnsAllProducts() {
        int before = productDao.findAll().size();
        productDao.save(new Product("A", 1));
        productDao.save(new Product("B", 2));
        assertEquals(before + 2, productDao.findAll().size());
    }

    @Test
    void updateChangesValues() {
        Product p = new Product("Ecran", 100);
        productDao.save(p);

        p.setPrice(120);
        productDao.update(p);

        assertEquals(120, productDao.findById(p.getId()).getPrice(), 0.001);
    }

    @Test
    void deleteRemovesProduct() {
        Product p = new Product("Casque", 40);
        productDao.save(p);
        Long id = p.getId();

        productDao.delete(p);
        assertNull(productDao.findById(id));
    }
}
