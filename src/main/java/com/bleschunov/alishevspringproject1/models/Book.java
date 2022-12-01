package com.bleschunov.alishevspringproject1.models;

/**
 * @author Bleschunov Dmitry
 */
public class Book {
    private int id;
    private String title;
    private String author;
    private int publishingYear;
    private int personId;

    public Book() {
    }

    public Book(int id, String title, String author, int publishingYear, Integer personId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishingYear = publishingYear;
        this.personId = personId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public int getPersonId() {
        return personId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publishingYear=" + publishingYear +
                ", personId=" + personId +
                '}';
    }
}
