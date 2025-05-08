package com.project.library.library_backend.controller;

import com.project.library.library_backend.model.Media;
import com.project.library.library_backend.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST-Controller zur Verwaltung von Medien.
 *
 * Diese Klasse stellt Endpunkte zur Verfügung, um Medien zu erstellen,
 * abzurufen, zu bearbeiten, zu löschen oder nach bestimmten Kriterien zu suchen.
 *
 * API-Endpunkte:
 * - POST /api/media → erstellt ein neues Medium
 * - GET /api/media → gibt alle Medien zurück
 * - GET /api/media/{id} → gibt ein bestimmtes Medium zurück
 * - PUT /api/media/{id} → aktualisiert ein Medium
 * - DELETE /api/media/{id} → löscht ein Medium
 * - GET /api/media/search/title?title=... → findet Medien anhand des Titels
 * - GET /api/media/search/id?id=... → gibt ein Medium anhand der ID zurück
 * 
 * @author Basil Ramseyer
 * @version 1.0
 */
@RestController
@RequestMapping("/api/media")
public class MediaController {

    @Autowired
    private MediaRepository mediaRepository;

    /**
     * Gibt alle Medien zurück.
     *
     * @return Liste aller Medien
     */
    @GetMapping
    public List<Media> getAllMedia() {
        return mediaRepository.findAll();
    }

    /**
     * Gibt ein Medium anhand der ID zurück.
     *
     * @param id ID des Mediums
     * @return Optional mit Medium, falls vorhanden
     */
    @GetMapping("/{id}")
    public Optional<Media> getMediaById(@PathVariable Long id) {
        return mediaRepository.findById(id);
    }

    /**
     * Erstellt ein neues Medium.
     *
     * @param media Media-Objekt aus dem Request-Body
     * @return Gespeichertes Medium
     */
    @PostMapping
    public Media createMedia(@RequestBody Media media) {
        return mediaRepository.save(media);
    }

    /**
     * Aktualisiert ein bestehendes Medium oder erstellt ein neues mit dieser ID.
     *
     * @param id ID des Mediums
     * @param updatedMedia Neue Mediadaten
     * @return Aktualisiertes oder neu erstelltes Medium
     */
    @PutMapping("/{id}")
    public Media updateMedia(@PathVariable Long id, @RequestBody Media updatedMedia) {
        return mediaRepository.findById(id).map(media -> {
            media.setTitle(updatedMedia.getTitle());
            media.setAuthor(updatedMedia.getAuthor());
            media.setGenre(updatedMedia.getGenre());
            media.setRating(updatedMedia.getRating());
            media.setIsbn(updatedMedia.getIsbn());
            media.setShelfCode(updatedMedia.getShelfCode());
            media.setFsk(updatedMedia.getFsk());
            return mediaRepository.save(media);
        }).orElseGet(() -> {
            updatedMedia.setId(id);
            return mediaRepository.save(updatedMedia);
        });
    }

    /**
     * Löscht ein Medium anhand der ID.
     *
     * @param id ID des zu löschenden Mediums
     */
    @DeleteMapping("/{id}")
    public void deleteMedia(@PathVariable Long id) {
        mediaRepository.deleteById(id);
    }

    /**
     * Sucht Medien anhand eines Titels.
     *
     * @param title Titel des Mediums
     * @return Liste mit passenden Medien
     */
    @GetMapping("/search/title")
    public List<Media> findByTitle(@RequestParam String title) {
        return mediaRepository.findByTitle(title);
    }

    /**
     * Sucht ein Medium anhand der ID (alternative zum PathVariable-Endpunkt).
     *
     * @param id ID des Mediums
     * @return Optional mit Medium
     */
    @GetMapping("/search/id")
    public Optional<Media> findById(@RequestParam Long id) {
        return mediaRepository.findById(id);
    }
}
