package com.project.library.library_backend.repository;

import com.project.library.library_backend.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository-Interface für den Zugriff auf Medien.
 * Ermöglicht Suche nach Titel oder ID.
 * Erweiterung von JpaRepository stellt Standardmethoden wie save, deleteById etc. bereit.
 * 
 * @author Basil Ramseyer
 * @version 1.0
 */
public interface MediaRepository extends JpaRepository<Media, Long> {

    /**
     * Gibt alle gespeicherten Medien zurück.
     *
     * @return Liste aller Medien
     */
    List<Media> findAll();

    /**
     * Sucht Medien mit einem bestimmten Titel.
     *
     * @param title Titel des Mediums
     * @return Liste aller passenden Medien
     */
    List<Media> findByTitle(String title);

    /**
     * Sucht ein Medium anhand seiner ID.
     *
     * @param id ID des Mediums
     * @return Optional mit Medium, falls vorhanden
     */
    Optional<Media> findById(Long id);
}
