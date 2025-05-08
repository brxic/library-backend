package com.project.library.library_backend.controller;

import com.project.library.library_backend.model.Customer;
import com.project.library.library_backend.model.Address;
import com.project.library.library_backend.repository.CustomerRepository;
import com.project.library.library_backend.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST-Controller zur Verwaltung von Kunden.
 *
 * Diese Klasse stellt Endpunkte zur Verfügung, um Kunden zu erstellen,
 * abzurufen, zu bearbeiten, zu löschen oder nach bestimmten Kriterien zu suchen.
 *
 * API-Endpunkte:
 * - POST /api/customers → erstellt einen neuen Kunden
 * - GET /api/customers → gibt alle Kunden zurück
 * - GET /api/customers/{id} → gibt einen bestimmten Kunden zurück
 * - PUT /api/customers/{id} → aktualisiert einen bestehenden Kunden
 * - DELETE /api/customers/{id} → löscht einen Kunden
 * - GET /api/customers/search/lastname?name=... → findet Kunden anhand des Nachnamens
 * - GET /api/customers/search/address?id=... → findet Kunden anhand der Address-ID
 * 
 * @author Basil Ramseyer
 * @version 1.0
 */
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    /**
     * Gibt alle Kunden zurück.
     *
     * @return Liste aller Kunden
     */
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * Gibt einen Kunden anhand seiner ID zurück.
     *
     * @param id ID des Kunden
     * @return Optional mit Kunde, falls vorhanden
     */
    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id);
    }

    /**
     * Erstellt einen neuen Kunden.
     *
     * @param customer Kundenobjekt aus dem Request-Body
     * @return Der gespeicherte Kunde
     */
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * Aktualisiert einen bestehenden Kunden anhand seiner ID.
     * Wenn kein Kunde mit der ID existiert, wird ein neuer erstellt.
     *
     * @param id ID des Kunden
     * @param updatedCustomer Neue Kundendaten
     * @return Aktualisierter oder neu erstellter Kunde
     */
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        return customerRepository.findById(id).map(customer -> {
            customer.setFirstname(updatedCustomer.getFirstname());
            customer.setLastname(updatedCustomer.getLastname());
            customer.setBirthdate(updatedCustomer.getBirthdate());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setAddress(updatedCustomer.getAddress());
            return customerRepository.save(customer);
        }).orElseGet(() -> {
            updatedCustomer.setId(id);
            return customerRepository.save(updatedCustomer);
        });
    }

    /**
     * Löscht einen Kunden anhand seiner ID.
     *
     * @param id ID des zu löschenden Kunden
     */
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
    }

    /**
     * Sucht Kunden anhand des Nachnamens.
     *
     * @param name Nachname des Kunden
     * @return Liste von Kunden mit übereinstimmendem Nachnamen
     */
    @GetMapping("/search/lastname")
    public List<Customer> findByLastname(@RequestParam String name) {
        return customerRepository.findByLastname(name);
    }

    /**
     * Sucht Kunden, die an einer bestimmten Adresse wohnen.
     *
     * @param id ID der Adresse
     * @return Liste der Kunden an dieser Adresse
     */
    @GetMapping("/search/address")
    public List<Customer> findByAddress(@RequestParam Long id) {
        Optional<Address> address = addressRepository.findById(id);
        return address.map(customerRepository::findByAddress).orElse(List.of());
    }
}
