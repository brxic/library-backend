package com.project.library.library_backend.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.*;

/**
 * Ist eine Adresse in der Bibliothek.
 *
 * Diese Klasse speichert Informationen über eine Adresse und wird von Kunden verwendet.
 * Sie ist über eine Beziehung mit der Entity {@link Customer} verknüpft.
 *
 * API-Endpunkte:
 * - POST /api/addresses → erstellt eine neue Adresse
 * - GET /api/addresses → gibt alle Adressen zurück
 * - GET /api/addresses/{id} → gibt eine Adresse zurück
 * - PUT /api/addresses/{id} → aktualisiert eine Adresse
 * - DELETE /api/addresses/{id} → löscht eine Adresse
 *
 * @author Basil Ramseyer
 * @version 1.0
 */
@Entity
@JsonInclude(Include.NON_NULL)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"streetandnum", "city"}))
public class Address {

    /**
     * Die eindeutige ID der Adresse (automatisch generiert).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Strasse und Hausnummer.
     */
    private String streetandnum;

    /**
     * Name der Stadt.
     */
    private String city;

    /**
     * Postleitzahl der Adresse.
     */
    private String plz;

    public Address() {}

    public Address(String streetandnum, String city, String plz) {
        this.streetandnum = streetandnum;
        this.city = city;
        this.plz = plz;
    }

    // Getter und Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetandnum() {
        return streetandnum;
    }

    public void setStreetandnum(String streetandnum) {
        this.streetandnum = streetandnum;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }
}
