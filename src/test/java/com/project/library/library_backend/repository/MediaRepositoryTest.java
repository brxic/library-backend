package com.project.library.library_backend.repository;

import com.project.library.library_backend.model.Media;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testklasse für das MediaRepository.
 *
 * Testet die vier CRUD-Funktionen (Create, Read, Update, Delete) anhand eines einzigen Mediums.
 * Verwendet eine In-Memory-Datenbank (H2).
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MediaRepositoryTest {

    @Autowired
    private MediaRepository mediaRepository;

    private Media media;

    /**
     * Initialisiert ein Media-Objekt vor jedem Test.
     */
    @BeforeEach
    void setup() {
        media = new Media("Testbuch", "Autor X", "Roman", 4, 1234567890123L, "A1", "12+");
    }

    /**
     * Testet das Erstellen und Speichern eines Mediums.
     */
    @Test
    void testCreateMedia() {
        Media saved = mediaRepository.save(media);
        assertNotNull(saved.getId());
    }

    /**
     * Testet das Lesen eines Mediums aus der Datenbank.
     */
    @Test
    void testReadMedia() {
        Media saved = mediaRepository.save(media);
        Optional<Media> found = mediaRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("Testbuch", found.get().getTitle());
    }

    /**
     * Testet das Aktualisieren eines Mediums.
     */
    @Test
    void testUpdateMedia() {
        Media saved = mediaRepository.save(media);
        saved.setGenre("Sachbuch");
        Media updated = mediaRepository.save(saved);
        assertEquals("Sachbuch", updated.getGenre());
    }

    /**
     * Testet das Löschen eines Mediums.
     */
    @Test
    void testDeleteMedia() {
        Media saved = mediaRepository.save(media);
        mediaRepository.deleteById(saved.getId());
        assertFalse(mediaRepository.findById(saved.getId()).isPresent());
    }
}
