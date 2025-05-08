package com.project.library.library_backend.repository;

import com.project.library.library_backend.model.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository-Interface für den Zugriff auf Ausleihen.
 * Enthält Methoden zur Suche nach Medium oder Kunde.
 * Erweiterung von JpaRepository bietet Standardmethoden wie save, deleteById etc.
 * 
 * @author Basil Ramseyer
 * @version 1.0
 */
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {

    /**
     * Gibt alle gespeicherten Ausleihen zurück.
     *
     * @return Liste aller Ausleihen
     */
    List<Borrowing> findAll();

    /**
     * Sucht eine Ausleihe anhand der Media-ID.
     *
     * @param mediaId ID des Mediums
     * @return Optional mit Ausleihe, falls vorhanden
     */
    Optional<Borrowing> findByMedia_Id(Long mediaId);

    /**
     * Sucht alle Ausleihen eines bestimmten Kunden.
     *
     * @param customerId ID des Kunden
     * @return Liste aller passenden Ausleihen
     */
    List<Borrowing> findByCustomer_Id(Long customerId);
}
