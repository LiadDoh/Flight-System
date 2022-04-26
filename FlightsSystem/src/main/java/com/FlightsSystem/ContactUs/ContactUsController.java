package com.FlightsSystem.ContactUs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contactus")
public class ContactUsController {

    @Autowired
    ContactService contactService;

    @GetMapping("/")
    public List<ContactUsDTO> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/{id}")
    public ContactUsDTO getContactById(int id) {
        return contactService.getContactById(id);
    }

    @PutMapping("/")
    public void addContact(@RequestBody ContactUsDTO contactUsDTO) {
        contactService.addContact(contactUsDTO);
    }
}
