package com.project.library.library_backend.repository;

import com.project.library.library_backend.model.Address;
import com.project.library.library_backend.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testklasse für das CustomerRepository.
 *
 * Testet die vier CRUD-Funktionen (Create, Read, Update, Delete) anhand eines einzigen Kundenobjekts.
 * Die Tests laufen auf einer In-Memory-Datenbank (H2) mit Spring Boot JPA.
 * Ziel: Am Ende des Tests existiert der Kunde nicht mehr.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private AddressRepository addressRepository;

    private Customer customer;

    /**
     * Initialisiert ein Customer-Objekt vor jedem Test.
     */
    @BeforeEach
    void setup() {
    	Address address = addressRepository.save(new Address("Hauptstrasse 1", "Zürich", "8000"));
        customer = new Customer("Max", "Mustermann", LocalDate.of(1985, 1, 1), "max@example.com");
        customer.setAddress(address);
        customer = customerRepository.save(customer);    }

    /**
     * Testet das Erstellen und Speichern eines Kunden.
     */
    @Test
    void testCreateCustomer() {
        Customer saved = customerRepository.save(customer);
        assertNotNull(saved.getId());
    }

    /**
     * Testet das Lesen eines zuvor gespeicherten Kunden.
     */
    @Test
    void testReadCustomer() {
        Customer saved = customerRepository.save(customer);
        Optional<Customer> found = customerRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("Max", found.get().getFirstname());
    }

    /**
     * Testet das Aktualisieren eines Kunden.
     */
    @Test
    void testUpdateCustomer() {
        Customer saved = customerRepository.save(customer);
        saved.setEmail("luca.neu@lang.ch");
        Customer updated = customerRepository.save(saved);
        assertEquals("luca.neu@lang.ch", updated.getEmail());
    }

    /**
     * Testet das Löschen eines Kunden.
     */
    @Test
    void testDeleteCustomer() {
        Customer saved = customerRepository.save(customer);
        customerRepository.deleteById(saved.getId());
        assertFalse(customerRepository.findById(saved.getId()).isPresent());
    }
}
