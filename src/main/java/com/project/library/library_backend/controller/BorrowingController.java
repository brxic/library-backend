package com.project.library.library_backend.controller;

import com.project.library.library_backend.model.Borrowing;
import com.project.library.library_backend.model.Customer;
import com.project.library.library_backend.model.Media;
import com.project.library.library_backend.repository.BorrowingRepository;
import com.project.library.library_backend.repository.CustomerRepository;
import com.project.library.library_backend.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST-Controller zur Verwaltung von Ausleihen.
 *
 * Diese Klasse stellt Endpunkte zur Verfügung, um Ausleihen zu erstellen,
 * abzurufen, zu bearbeiten, zu löschen oder nach bestimmten Kriterien zu suchen.
 *
 * API-Endpunkte:
 * - POST /api/borrowings → erstellt eine neue Ausleihe
 * - GET /api/borrowings → gibt alle Ausleihen zurück
 * - GET /api/borrowings/{id} → gibt eine bestimmte Ausleihe zurück
 * - PUT /api/borrowings/{id} → aktualisiert eine Ausleihe
 * - DELETE /api/borrowings/{id} → löscht eine Ausleihe
 * - GET /api/borrowings/search/media?id=... → findet Ausleihe zu einem Medium
 * - GET /api/borrowings/search/customer?id=... → findet alle Ausleihen eines Kunden
 * 
 * @author Basil
 * @version 1.0
 */
@RestController
@RequestMapping("/api/borrowings")
public class BorrowingController {

    @Autowired
    private BorrowingRepository borrowingRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MediaRepository mediaRepository;

    /**
     * Gibt alle Ausleihen zurück.
     *
     * @return Liste aller Ausleihen
     */
    @GetMapping
    public List<Borrowing> getAllBorrowings() {
        return borrowingRepository.findAll();
    }

    /**
     * Gibt eine Ausleihe anhand ihrer ID zurück.
     *
     * @param id ID der Ausleihe
     * @return Optional mit Ausleihe
     */
    @GetMapping("/{id}")
    public Optional<Borrowing> getBorrowingById(@PathVariable Long id) {
        return borrowingRepository.findById(id);
    }

    /**
     * Erstellt eine neue Ausleihe.
     *
     * @param borrowing Ausleihe-Objekt aus dem Request
     * @return Gespeicherte Ausleihe
     */
    @PostMapping
    public Borrowing createBorrowing(@RequestBody Borrowing borrowing) {
        return borrowingRepository.save(borrowing);
    }

    /**
     * Aktualisiert eine Ausleihe oder erstellt sie neu, wenn sie nicht existiert.
     *
     * @param id ID der Ausleihe
     * @param updatedBorrowing Neue Daten der Ausleihe
     * @return Aktualisierte oder neu gespeicherte Ausleihe
     */
    @PutMapping("/{id}")
    public Borrowing updateBorrowing(@PathVariable Long id, @RequestBody Borrowing updatedBorrowing) {
        return borrowingRepository.findById(id).map(borrowing -> {
            borrowing.setDateborrowed(updatedBorrowing.getDateborrowed());
            borrowing.setDuedate(updatedBorrowing.getDuedate());
            borrowing.setExtended_on(updatedBorrowing.getExtended_on());
            borrowing.setCustomer(updatedBorrowing.getCustomer());
            borrowing.setMedia(updatedBorrowing.getMedia());
            return borrowingRepository.save(borrowing);
        }).orElseGet(() -> {
            updatedBorrowing.setId(id);
            return borrowingRepository.save(updatedBorrowing);
        });
    }

    /**
     * Löscht eine Ausleihe anhand der ID.
     *
     * @param id ID der zu löschenden Ausleihe
     */
    @DeleteMapping("/{id}")
    public void deleteBorrowing(@PathVariable Long id) {
        borrowingRepository.deleteById(id);
    }

    /**
     * Sucht eine Ausleihe zu einem bestimmten Medium.
     *
     * @param id ID des Mediums
     * @return Optional mit Ausleihe, falls vorhanden
     */
    @GetMapping("/search/media")
    public Optional<Borrowing> findByMedia(@RequestParam Long id) {
        return (borrowingRepository.findByMedia_Id(id));
    }

    /**
     * Sucht alle Ausleihen eines bestimmten Kunden.
     *
     * @param id ID des Kunden
     * @return Liste aller Ausleihen dieses Kunden
     */
    @GetMapping("/search/customer")
    public List<Borrowing> findByCustomer(@RequestParam Long id) {
        return borrowingRepository.findByCustomer_Id(id);
    }
}
