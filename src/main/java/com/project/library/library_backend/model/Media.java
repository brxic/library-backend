package com.project.library.library_backend.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.*;

/**
 * Ist ein Medium in der Bibliothek, also z.B. ein Buch.
 *
 * Jedes Medium besitzt Informationen wie Titel, Autor, Genre und ist über Borrowing ausleihbar.
 *
 * API-Endpunkte:
 * - POST /api/media → erstellt ein neues Medium
 * - GET /api/media → gibt alle Medien zurück
 * - GET /api/media/{id} → gibt ein Medium anhand der ID zurück
 * - PUT /api/media/{id} → aktualisiert ein Medium
 * - DELETE /api/media/{id} → löscht ein Medium
 * 
 * Zusätzlich gilt: ein Medium darf nur einmal gleichzeitig ausgeliehen sein.
 * 
 * @author Basil Ramseyer
 * @version 1.0
 */
@Entity
@JsonInclude(Include.NON_NULL)
public class Media {

    /**
     * eindeutige ID des Mediums (wird automatisch generiert).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Titel des Mediums.
     */
    private String title;

    /**
     * Autor oder Ersteller des Mediums.
     */
    private String author;

    /**
     * Genre des Mediums (z. B. "Fantasy", "Sachbuch").
     */
    private String genre;

    /**
     * Bewertung des Mediums (z. B. 1–5 Sterne).
     */
    private int rating;

    /**
     * ISBN-Nummer (optional).
     */
    private Long isbn;

    /**
     * Standort bzw. Regalcode in der Bibliothek.
     */
    private String shelfCode;

    /**
     * Altersfreigabe (FSK).
     */
    private String fsk;

    public Media() {}

    public Media(String title, String author, String genre, int rating, Long isbn, String shelfCode, String fsk) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.rating = rating;
        this.isbn = isbn;
        this.shelfCode = shelfCode;
        this.fsk = fsk;
    }

    // Getter und Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getShelfCode() {
        return shelfCode;
    }

    public void setShelfCode(String shelfCode) {
        this.shelfCode = shelfCode;
    }

    public String getFsk() {
        return fsk;
    }

    public void setFsk(String fsk) {
        this.fsk = fsk;
    }
}
