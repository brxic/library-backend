package com.project.library.library_backend.repository;

import com.project.library.library_backend.model.Address;
import com.project.library.library_backend.model.Borrowing;
import com.project.library.library_backend.model.Customer;
import com.project.library.library_backend.model.Media;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testklasse für das {@link BorrowingRepository}.
 * 
 * Ziel: Sicherstellen, dass grundlegende CRUD-Operationen auf Ausleihen korrekt funktionieren.
 * 
 * Tests:
 * - Create: Eine Ausleihe soll erfolgreich gespeichert werden.
 * - Read: Eine gespeicherte Ausleihe soll abrufbar sein.
 * - Update: Eine vorhandene Ausleihe soll aktualisierbar sein.
 * - Delete: Eine Ausleihe soll löschbar sein.
 * 
 * Erwartung: Alle Operationen sollen ohne Fehler durchführbar sein und die gespeicherten Daten korrekt widerspiegeln.
 * 
 * @author Basil Ramseyer
 * @version 1.0
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BorrowingRepositoryTest {

    @Autowired
    private BorrowingRepository borrowingRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MediaRepository mediaRepository;

    private Borrowing borrowing;

    @BeforeEach
    void setup() {
        Address address = addressRepository.save(new Address("Testweg 5", "Bern", "3000"));

        Customer customer = new Customer("Sarah", "Schmid", LocalDate.of(1990, 5, 10), "sarah@schmid.ch");
        customer.setAddress(address);
        customer = customerRepository.save(customer);

        Media media = mediaRepository.save(new Media("Testbuch", "Max Mustermann", "Roman", 5, 9781234567890L, "R1", "12"));

        borrowing = new Borrowing(LocalDate.now().plusDays(14), LocalDate.now(), null, customer, media);
        borrowing = borrowingRepository.save(borrowing);
    }

    @Test
    void testCreateBorrowing() {
        assertNotNull(borrowing.getId(), "Borrowing ID should not be null after saving.");
    }

    @Test
    void testReadBorrowing() {
        Optional<Borrowing> found = borrowingRepository.findById(borrowing.getId());
        assertTrue(found.isPresent(), "Borrowing should be found by ID.");
    }

    @Test
    void testUpdateBorrowing() {
        borrowing.setExtended_on(LocalDate.now().plusDays(7));
        Borrowing updated = borrowingRepository.save(borrowing);
        assertEquals(LocalDate.now().plusDays(7), updated.getExtended_on(), "Borrowing extension date should match.");
    }

    @Test
    void testDeleteBorrowing() {
        borrowingRepository.delete(borrowing);
        assertFalse(borrowingRepository.findById(borrowing.getId()).isPresent(), "Borrowing should no longer exist.");
    }
}
