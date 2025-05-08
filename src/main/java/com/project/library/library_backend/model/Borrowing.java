package com.project.library.library_backend.model;

import jakarta.persistence.*;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Repräsentiert eine Ausleihe eines Mediums durch einen Kunden.
 *
 * Diese Klasse speichert, wann ein Medium ausgeliehen wurde, wann es fällig ist,
 * ob es verlängert wurde und zu welchem Kunden und Medium sie gehört.
 *
 * API-Endpunkte:
 * - POST /api/borrowings → erstellt eine neue Ausleihe
 * - GET /api/borrowings → gibt alle Ausleihen zurück
 * - GET /api/borrowings/{id} → gibt eine Ausleihe anhand der ID zurück
 * - PUT /api/borrowings/{id} → aktualisiert eine Ausleihe
 * - DELETE /api/borrowings/{id} → löscht eine Ausleihe
 *
 * Zusätzlich existiert eine View "overdue", die überfällige Ausleihen anzeigt.
 * 
 * Ein Medium kann nur einmal gleichzeitig ausgeliehen sein (unique mediaId).
 * 
 * @author Basil Ramseyer
 * @version 1.0
 */
@Entity
@JsonInclude(Include.NON_NULL)
public class Borrowing {

    /**
     * Die eindeutige ID der Ausleihe.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Datum, an dem das Medium ausgeliehen wurde.
     */
    private LocalDate dateborrowed = LocalDate.now();

    /**
     * Fälligkeitsdatum der Rückgabe.
     */
    private LocalDate duedate;

    /**
     * Datum, an dem die Ausleihe verlängert wurde (optional).
     */
    private LocalDate extended_on;

    /**
     * Der Kunde, der das Medium ausgeliehen hat.
     */
    @ManyToOne
    @JoinColumn(name = "customerid", nullable = false)
    private Customer customer;

    /**
     * Das ausgeliehene Medium.
     */
    @OneToOne
    @JoinColumn(name = "mediaid", nullable = false, unique = true)
    private Media media;

    public Borrowing() {}

    public Borrowing(LocalDate duedate, LocalDate dateborrowed, LocalDate extended_on, Customer customer, Media media) {
        this.duedate = duedate;
        this.dateborrowed = dateborrowed;
        this.extended_on = extended_on;
        this.customer = customer;
        this.media = media;
    }

    // Getter und Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateborrowed() {
        return dateborrowed;
    }

    public void setDateborrowed(LocalDate dateborrowed) {
        this.dateborrowed = dateborrowed;
    }

    public LocalDate getDuedate() {
        return duedate;
    }

    public void setDuedate(LocalDate duedate) {
        this.duedate = duedate;
    }

    public LocalDate getExtended_on() {
        return extended_on;
    }

    public void setExtended_on(LocalDate extended_on) {
        this.extended_on = extended_on;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }
}
