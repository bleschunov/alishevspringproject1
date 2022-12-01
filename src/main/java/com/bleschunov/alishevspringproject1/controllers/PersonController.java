package com.bleschunov.alishevspringproject1.controllers;

import com.bleschunov.alishevspringproject1.dao.BookDao;
import com.bleschunov.alishevspringproject1.dao.PersonDao;
import com.bleschunov.alishevspringproject1.models.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Bleschunov Dmitry
 *
 * get /people
 * get /people/{id}
 *
 * get /people/add
 * post /people
 *
 * get /people/{id}/edit
 * patch /people
 *
 * delete /people/{id}
 */

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonDao personDao;
    private final BookDao bookDao;

    public PersonController(PersonDao personDao, BookDao bookDao) {
        this.personDao = personDao;
        this.bookDao = bookDao;
    }

    @GetMapping("")
    public String getAllPeople(Model model) {
        model.addAttribute("people", personDao.selectAllPeople());
        return "people/people";
    }

    @GetMapping("/{id}")
    public String getPersonById(@PathVariable int id, Model model) {
        model.addAttribute("person", personDao.selectById(id));
        model.addAttribute("books", bookDao.selectBooksOfPerson(id));
        return "people/person";
    }

    @GetMapping("/add")
    public String getPersonAddForm(
            @ModelAttribute("person") Person person,
            Model model) {
        model.addAttribute("person", person);
        return "people/add";
    }

    @PostMapping("")
    public String createNewPerson(@ModelAttribute("person") Person person) {
        personDao.insertOne(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String getPersonEditForm(
            @PathVariable int id,
            Model model) {
        Person person = personDao.selectById(id);
        model.addAttribute("person", person);
        return "people/edit";
    }

    @PutMapping("/{id}")
    public String editPerson(@ModelAttribute("person") Person person) {
        personDao.updateOne(person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable int id) {
        personDao.deleteById(id);
        return "redirect:/people";
    }
}
