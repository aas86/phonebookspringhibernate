package ru.academits.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.academits.converter.ContactToContactDtoConverter;
import ru.academits.dto.ContactDto;
import ru.academits.model.Contact;
import ru.academits.model.ContactValidation;
import ru.academits.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
@RequestMapping("/phoneBook/rcp/api/v1")
public class PhoneBookController {

    private static final Logger logger = LoggerFactory.getLogger(PhoneBookController.class);

    @Autowired
    private ContactToContactDtoConverter contactToContactDtoConverter;

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "getAllContacts", method = RequestMethod.GET)
    @ResponseBody
    public List<ContactDto> getAllContacts() {
        //TODO можно вернуть контроллером List<ContactDto> и немного переделать клиентскую часть
        logger.info("called method getAllContacts");
        List<Contact> list = contactService.getAllContacts();
        List<ContactDto> list1 = contactToContactDtoConverter.convert(list);
//        System.out.println(list1.toString());
        return list1;
    }

    @RequestMapping(value = "addContact", method = RequestMethod.POST)
    @ResponseBody
    public ContactValidation addContact(@RequestBody Contact contact) {
        return contactService.addContact(contact);
    }


    @RequestMapping(value = "deleteContact", method = RequestMethod.POST)
    @ResponseBody
    public void deleteContact(@RequestBody Contact contact) {
        contactService.deleteContact(contact);
    }
}


