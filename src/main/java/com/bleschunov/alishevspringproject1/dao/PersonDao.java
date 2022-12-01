package com.bleschunov.alishevspringproject1.dao;

import com.bleschunov.alishevspringproject1.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Types;
import java.util.List;

/**
 * @author Bleschunov Dmitry
 */
@Component
public class PersonDao {

    private final JdbcTemplate jdbcTemplate;

    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> selectAllPeople() {
        return jdbcTemplate.query(
                "SELECT * FROM person",
                new BeanPropertyRowMapper<Person>(Person.class));
    }

    public Person selectById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM person WHERE id = ?",
                new Object[]{id},
                new int[]{Types.INTEGER},
                new BeanPropertyRowMapper<Person>(Person.class));
    }

    public Person selectHolderOfBook(int holder_id) {
        return jdbcTemplate.query(
                "SELECT * FROM person WHERE id = ?",
                new Object[]{holder_id},
                new int[]{Types.INTEGER},
                new BeanPropertyRowMapper<Person>(Person.class)
        ).stream().findFirst().orElse(null);
    }

    public void insertOne(Person person) {
        jdbcTemplate.update(
                "INSERT INTO person(full_name, year_of_birth) VALUES(?, ?)",
                person.getFullName(),
                person.getYearOfBirth());
    }

    public void updateOne(Person person) {
        jdbcTemplate.update(
                "UPDATE person SET full_name = ?, year_of_birth = ? WHERE id = ?",
                person.getFullName(),
                person.getYearOfBirth(),
                person.getId()
        );
    }

    public void deleteById(int id) {
        jdbcTemplate.update(
                "DELETE FROM person WHERE id = ?",
                id
        );
    }
}
