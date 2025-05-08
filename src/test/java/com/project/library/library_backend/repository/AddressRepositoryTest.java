package com.project.library.library_backend.repository;

import com.project.library.library_backend.model.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testklasse für das AddressRepository.
 *
 * Testet die vier CRUD-Funktionen (Create, Read, Update, Delete) anhand eines einzigen Adressobjekts.
 * Die Tests verwenden eine In-Memory-Datenbank (H2).
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    private Address address;

    /**
     * Erstellt vor jedem Test eine Testadresse.
     */
    @BeforeEach
    void setup() {
        address = new Address("Hauptstrasse 1", "Zürich", "8000");
    }

    /**
     * Testet das Erstellen und Speichern einer Adresse.
     */
    @Test
    void testCreateAddress() {
        Address saved = addressRepository.save(address);
        assertNotNull(saved.getId());
    }

    /**
     * Testet das Lesen einer Adresse aus der Datenbank.
     */
    @Test
    void testReadAddress() {
        Address saved = addressRepository.save(address);
        Optional<Address> found = addressRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("Zürich", found.get().getCity());
    }

    /**
     * Testet das Aktualisieren einer Adresse.
     */
    @Test
    void testUpdateAddress() {
        Address saved = addressRepository.save(address);
        saved.setPlz("8001");
        Address updated = addressRepository.save(saved);
        assertEquals("8001", updated.getPlz());
    }

    /**
     * Testet das Löschen einer Adresse.
     */
    @Test
    void testDeleteAddress() {
        Address saved = addressRepository.save(address);
        addressRepository.deleteById(saved.getId());
        assertFalse(addressRepository.findById(saved.getId()).isPresent());
    }
}
