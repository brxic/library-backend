package com.project.library.library_backend.repository;

import com.project.library.library_backend.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repository-Interface für den Zugriff auf Adressen.
 * Ermöglicht Suche nach Stadt oder PLZ.
 * Erweiterung von JpaRepository bietet alle Standardmethoden wie save, deleteById, findById.
 * 
 * @author Basil Ramseyer
 * @version 1.0
 */
public interface AddressRepository extends JpaRepository<Address, Long> {

    /**
     * Gibt alle gespeicherten Adressen zurück.
     *
     * @return Liste aller Adressen
     */
    List<Address> findAll();

    /**
     * Sucht Adressen in einer bestimmten Stadt.
     *
     * @param city Stadtname
     * @return Liste aller passenden Adressen
     */
    List<Address> findByCity(String city);

    /**
     * Sucht Adressen mit einer bestimmten Postleitzahl.
     *
     * @param plz Postleitzahl
     * @return Liste aller passenden Adressen
     */
    List<Address> findByPlz(String plz);
    
    /**
     * Findet eine Adresse anhand Straße + Stadt (z.B. für Duplikatsvermeidung).
     *
     * @param street Straße und Hausnummer
     * @param city   Stadtname
     * @return Optional mit Adresse, falls vorhanden
     */
    @Query("SELECT a FROM Address a WHERE a.streetAndNum = :street AND a.city = :city")
    Optional<Address> findByStreetAndCity(@Param("streetandnum") String streetandnum, @Param("city") String city);

}
