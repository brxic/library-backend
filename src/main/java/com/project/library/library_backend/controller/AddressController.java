package com.project.library.library_backend.controller;

import com.project.library.library_backend.model.Address;
import com.project.library.library_backend.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST-Controller zur Verwaltung von Adressen.
 *
 * Diese Klasse stellt Endpunkte zur Verfügung, um Adressen zu erstellen,
 * abzurufen, zu bearbeiten oder zu löschen.
 *
 * API-Endpunkte:
 * - POST /api/addresses → erstellt eine neue Adresse
 * - GET /api/addresses → gibt alle Adressen zurück
 * - GET /api/addresses/{id} → gibt eine bestimmte Adresse zurück
 * - PUT /api/addresses/{id} → aktualisiert eine Adresse
 * - DELETE /api/addresses/{id} → löscht eine Adresse
 * 
 * @author Basil Ramseyer
 * @version 1.0
 */
@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    /**
     * Gibt alle Adressen aus der Datenbank zurück.
     *
     * @return Liste aller Adressen
     */
    @GetMapping
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    /**
     * Gibt eine Adresse anhand der ID zurück.
     *
     * @param id ID der Adresse
     * @return Optional mit Adresse (falls vorhanden)
     */
    @GetMapping("/{id}")
    public Optional<Address> getAddressById(@PathVariable Long id) {
        return addressRepository.findById(id);
    }

    /**
     * Erstellt eine neue Adresse.
     *
     * @param address Address-Objekt aus dem Request-Body
     * @return Gespeicherte Adresse
     */
    @PostMapping
    public Address createAddress(@RequestBody Address address) {
        return addressRepository.save(address);
    }

    /**
     * Aktualisiert eine bestehende Adresse oder erstellt eine neue mit dieser ID.
     *
     * @param id ID der Adresse
     * @param updatedAddress Neue Adressdaten
     * @return Aktualisierte oder neu erstellte Adresse
     */
    @PutMapping("/{id}")
    public Address updateAddress(@PathVariable Long id, @RequestBody Address updatedAddress) {
        return addressRepository.findById(id).map(address -> {
            address.setStreetandnum(updatedAddress.getStreetandnum());
            address.setCity(updatedAddress.getCity());
            address.setPlz(updatedAddress.getPlz());
            return addressRepository.save(address);
        }).orElseGet(() -> {
            updatedAddress.setId(id);
            return addressRepository.save(updatedAddress);
        });
    }

    /**
     * Löscht eine Adresse anhand der ID.
     *
     * @param id ID der zu löschenden Adresse
     */
    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Long id) {
        addressRepository.deleteById(id);
    }
}
