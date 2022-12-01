package com.bleschunov.alishevspringproject1.dao;

import com.bleschunov.alishevspringproject1.models.Book;
import com.bleschunov.alishevspringproject1.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bleschunov Dmitry
 */
@Component
public class BookDao {
    private final JdbcTemplate jdbcTemplate;

    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> selectAllBooks() {
        return jdbcTemplate.query(
                "SELECT * FROM book",
                (rs, rowNum) -> new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("publishing_year"),
                        rs.getInt("person_id")
                ));
    }

    public Book selectById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM book WHERE id = ?",
                new Object[]{id},
                new int[]{Types.INTEGER},
                (rs, rowNum) -> new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("publishing_year"),
                        rs.getInt("person_id")
                ));
    }

    public List<Book> selectBooksOfPerson(int person_id) {
        return jdbcTemplate.query(
                "SELECT * FROM book WHERE person_id = ?",
                new Object[]{person_id},
                new int[]{Types.INTEGER},
                new BeanPropertyRowMapper<Book>(Book.class)
        );
    }

    public void insertOne(Book book) {
        jdbcTemplate.update(
                "INSERT INTO book(title, author, publishing_year) VALUES(?, ?, ?)",
                book.getTitle(),
                book.getAuthor(),
                book.getPublishingYear()
        );
    }

    public void updateOne(Book book) {
        jdbcTemplate.update(
                "UPDATE book SET title = ?, author = ?, publishing_year = ? WHERE id = ?",
                book.getTitle(),
                book.getAuthor(),
                book.getPublishingYear(),
                book.getId()
        );
    }

    public void giveBook(int book_id, int person_id) {
        jdbcTemplate.update(
                "UPDATE book SET person_id = ? WHERE id = ?",
                person_id,
                book_id
        );
    }

    public void returnOne(int id) {
        jdbcTemplate.update(
                "UPDATE book SET person_id = NULL WHERE id = ?",
                id
        );
    }

    public void deleteById(int id) {
        jdbcTemplate.update(
                "DELETE FROM book WHERE id = ?",
                id
        );
    }
}
