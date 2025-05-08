package com.project.library.library_backend.repository;

import com.project.library.library_backend.model.Customer;
import com.project.library.library_backend.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository-Interface für den Zugriff auf Kunden.
 *
 * Dieses Interface ermöglicht den Zugriff auf die Customer-Tabelle der Datenbank
 * und stellt benutzerdefinierte sowie Standard-Suchabfragen zur Verfügung.
 *
 * Standardmethoden (von JpaRepository):
 * - findAll()
 * - findById(Long)
 * - save(Customer)
 * - deleteById(Long)
 *
 * Zusätzliche Query-Methoden:
 * - findByLastname(String)
 * - findByAddress(Address)
 * 
 * @author Basil
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Sucht alle Kunden mit dem gegebenen Nachnamen.
     *
     * @param lastname Nachname der Kunden
     * @return Liste aller Kunden mit übereinstimmendem Nachnamen
     */
    List<Customer> findByLastname(String lastname);

    /**
     * Sucht alle Kunden, die an einer bestimmten Adresse wohnen.
     *
     * @param address Address-Objekt
     * @return Liste aller Kunden mit dieser Adresse
     */
    List<Customer> findByAddress(Address address);

    /**
     * Sucht einen Kunden anhand seiner ID.
     * (Hinweis: wird auch automatisch von JpaRepository bereitgestellt)
     *
     * @param id Eindeutige ID des Kunden
     * @return Optional mit gefundenem Kunden oder leer
     */
    Optional<Customer> findById(Long id);
}
