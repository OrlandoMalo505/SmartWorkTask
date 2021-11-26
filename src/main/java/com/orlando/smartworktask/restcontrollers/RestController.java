package com.orlando.smartworktask.restcontrollers;


import com.orlando.smartworktask.domain.PhoneBook;
import com.orlando.smartworktask.domain.Type;
import com.orlando.smartworktask.services.NameService;
import com.orlando.smartworktask.services.PhoneBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@Api(description = "This is the Controller")
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/phoneBooks")
public class RestController {

    private final PhoneBookService phoneBookService;
    private final  NameService nameService;

    public RestController(PhoneBookService phoneBookService, NameService nameService) {
        this.phoneBookService = phoneBookService;
        this.nameService = nameService;
    }



    @ApiOperation(value = "This will return all phone books." ,notes="It uses GetMapping.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<PhoneBook> getPhoneBooks(){
        Set<PhoneBook> set=phoneBookService.getPhoneBooks();
        return set;
    }

    @ApiOperation(value = "This will create a new phone book." ,notes="It uses PostMapping.")
    @PostMapping("/createNewPhoneBook")
    public ResponseEntity<PhoneBook> createPhoneBook (@RequestBody PhoneBook phoneBook) throws Exception {

        if(phoneBook.getType().equals(Type.Home) ||
                phoneBook.getType().equals(Type.Work) || phoneBook.getType().equals(Type.Cellphone)){
            phoneBook.setType(phoneBook.getType());
        }else {
            return new ResponseEntity("Type must be: Work or Cellphone or Home}",HttpStatus.BAD_REQUEST);
        }

        nameService.saveName(phoneBook);
        PhoneBook newPhoneBook = new PhoneBook();
        newPhoneBook.setName(phoneBook.getName());
        newPhoneBook.setNumber(phoneBook.getNumber());
        newPhoneBook.setType(phoneBook.getType());
        phoneBookService.savePhoneBook(newPhoneBook);
        return new ResponseEntity(newPhoneBook, HttpStatus.CREATED);
    }

    @ApiOperation(value = "This will delete a new phone book." ,notes="It uses DeleteMapping.")
    @DeleteMapping("/deletePhoneBookById")
    public ResponseEntity<PhoneBook> deletePhoneBookById(@RequestBody PhoneBook phoneBook){
        PhoneBook deletePhoneBook = phoneBookService.findPhoneBookById(phoneBook.getId());
        if(deletePhoneBook == null) {
            return new ResponseEntity("Phone book does not exist.",HttpStatus.BAD_REQUEST);
        }
        phoneBookService.deletePhoneBookById(deletePhoneBook.getId());
        return new ResponseEntity(phoneBook, HttpStatus.OK);
    }

    @ApiOperation(value = "This will update a new phone book." ,notes="It uses PutMapping.")
    @PutMapping("/editPhoneBookById")
    public ResponseEntity<PhoneBook> editPhoneBookById(@RequestBody PhoneBook phoneBook){
        if(phoneBook.getType().equals(Type.Home) ||
                phoneBook.getType().equals(Type.Cellphone) || phoneBook.getType().equals(Type.Work)){
            PhoneBook editPhoneBook = phoneBookService.findPhoneBookById(phoneBook.getId());
            if(editPhoneBook == null) {
                return new ResponseEntity("Phone book is not found",HttpStatus.BAD_REQUEST);
            }
            editPhoneBook.setName(phoneBook.getName());
            editPhoneBook.setType(phoneBook.getType());
            editPhoneBook.setNumber(phoneBook.getNumber());
            phoneBookService.savePhoneBook(editPhoneBook);
            return new ResponseEntity<>(editPhoneBook, HttpStatus.OK);
        }else {
            return new ResponseEntity("Type must be Work or Cellphone or Home}",HttpStatus.BAD_REQUEST);
        }
    }

}
