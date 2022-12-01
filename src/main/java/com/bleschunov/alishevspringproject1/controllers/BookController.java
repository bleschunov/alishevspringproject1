package com.bleschunov.alishevspringproject1.controllers;

import com.bleschunov.alishevspringproject1.dao.BookDao;
import com.bleschunov.alishevspringproject1.dao.PersonDao;
import com.bleschunov.alishevspringproject1.models.Book;
import com.bleschunov.alishevspringproject1.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Bleschunov Dmitry
 *
 * get /books
 * get /books/{id}
 *
 * get /books/add
 * post /books
 *
 * get /books/{id}/edit
 * patch /books
 *
 * delete /books/{id}
 */
@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDao bookDao;
    private final PersonDao personDao;

    public BookController(BookDao bookDao, PersonDao personDao) {
        this.bookDao = bookDao;
        this.personDao = personDao;
    }

    @GetMapping("")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookDao.selectAllBooks());
        return "books/books";
    }

    @GetMapping("/{id}")
    public String getBookById(
            @ModelAttribute("newPerson") Person newPerson,
            @PathVariable int id, Model model) {
        Book book = bookDao.selectById(id);
        model.addAttribute("book", book);

        List<Person> people = personDao.selectAllPeople();
        Person person = personDao.selectHolderOfBook(book.getPersonId());

        model.addAttribute("people", people);
        model.addAttribute("person", person);

        return "books/book";
    }

    @GetMapping("/add")
    public String getBookAddForm(@ModelAttribute("book") Book book) {
        return "books/add";
    }

    @PostMapping("")
    public String createNewBook(@ModelAttribute("book") Book book) {
        bookDao.insertOne(book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/give")
    public String giveBook(
            @PathVariable int id,
            @ModelAttribute("newPerson") Person person) {
        bookDao.giveBook(id, person.getId());
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/take_back")
    public String takeBookBack(@PathVariable int id) {
        bookDao.returnOne(id);
        return "redirect:/books/" + id;
    }

    @GetMapping("/{id}/edit")
    public String getBookEditForm(
            @PathVariable int id,
            Model model) {
        model.addAttribute("book", bookDao.selectById(id));
        return "books/edit";
    }

    @PutMapping("/{id}")
    public String editBook(@ModelAttribute("book") Book book) {
        bookDao.updateOne(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        bookDao.deleteById(id);
        return "redirect:/books";
    }
}
