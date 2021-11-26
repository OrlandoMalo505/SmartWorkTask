package com.orlando.smartworktask.controllers;

import com.orlando.smartworktask.domain.PhoneBook;
import com.orlando.smartworktask.domain.Type;
import com.orlando.smartworktask.services.NameService;
import com.orlando.smartworktask.services.PhoneBookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
public class IndexController {

    private final PhoneBookService phoneBookService;
    private final NameService nameService;

    public IndexController(PhoneBookService phoneBookService, NameService nameService) {
        this.phoneBookService = phoneBookService;
        this.nameService = nameService;
    }

    @RequestMapping({"", "/", "/index", "/phoneBooks/"})
    public String getIndexPage(Model model) {
        model.addAttribute("phoneBooks", phoneBookService.getPhoneBooks());
        return "index";
    }


    @GetMapping
    @RequestMapping("phone/{id}/delete")
    public String deleteById(@PathVariable Long id) {

        phoneBookService.deletePhoneBookById(id);
        return "redirect:/";
    }

    @GetMapping
    @RequestMapping("phone/{id}/edit")
    public String updatePhone(@PathVariable Long id, Model model){
        model.addAttribute("phoneBooks", phoneBookService.findPhoneBookById(id));
        return  "new";
    }

    @GetMapping("/phonebook/new")
    public String initCreationForm(Model model) {
        model.addAttribute("phoneBooks", PhoneBook.builder().build());
        return "new";
    }

    @PostMapping("/phonebook/new")
    public String createNewPhoneBook(
            @Validated PhoneBook phoneBook, BindingResult result, @RequestParam String enumTypes) {

        if (result.hasErrors()) {
            return "new";
        }
        PhoneBook editedPhoneBook = null;

        try {
            editedPhoneBook = phoneBookService.findPhoneBookById(phoneBook.getId());
        }
        catch (Exception e ){

            phoneBook.setType(Type.valueOf(enumTypes));
            phoneBookService.savePhoneBook(phoneBook);
            return "redirect:/";
        }
        try {
            if(!editedPhoneBook.equals(null)){
                editedPhoneBook.setId(phoneBook.getId());
                editedPhoneBook.setName(phoneBook.getName());
                editedPhoneBook.setNumber(phoneBook.getNumber());
                editedPhoneBook.setType(Type.valueOf(enumTypes));
                phoneBookService.savePhoneBook(editedPhoneBook);
            }
        }catch (Exception e){

            e.getMessage();
        }

        return "redirect:/";
    }

}
