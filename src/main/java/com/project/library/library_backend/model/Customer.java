package com.project.library.library_backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Ist ein Kunde in der Bibliothek.
 *
 * Diese Klasse wird in der Datenbank als Tabelle "customer" gespeichert und enthält
 * alle relevanten Informationen zur Identifikation eines Kunden.
 *
 * Wird verwendet durch die REST-API zur Verwaltung von Kunden (CRUD).
 *
 * API-Endpunkte:
 * - POST /api/customers → erstellt einen neuen Kunden
 * - GET /api/customers → gibt alle Kunden zurück
 * - GET /api/customers/{id} → gibt einen bestimmten Kunden zurück
 * - PUT /api/customers/{id} → aktualisiert einen bestehenden Kunden
 * - DELETE /api/customers/{id} → löscht einen Kunden
 *
 * @author Basil Ramseyer
 * @version 1.0
 */
@Entity
@JsonInclude(Include.NON_NULL)
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames = {"firstname", "lastname", "birthdate"})
)
public class Customer {

    /**
     * Die eindeutige ID des Kunden (wird automatisch generiert).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Der Vorname des Kunden.
     */
    private String firstname;

    /**
     * Der Nachname des Kunden.
     */
    private String lastname;

    /**
     * Das Geburtsdatum des Kunden.
     */
    private LocalDate birthdate;

    /**
     * Die E-Mail-Adresse des Kunden.
     */
    private String email;

    /**
     * Die Adresse des Kunden (Referenz zur Address-Entity).
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "addressid", nullable = false)
    private Address address;

    public Customer() {}

    public Customer(String firstname, String lastname, LocalDate birthdate, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.email = email;
    }

    // Getter und Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
