package com.FlightsSystem.ContactUs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {

    @Autowired
    ContactUsRepo contactService;

    public List<ContactUsDTO> getAllContacts(){
        List<ContactUsDTO> contacts = new ArrayList<>();
        contactService.findAll().forEach(contacts::add);
        return contacts;
    }

    public ContactUsDTO getContactById(int id) {
        var res = contactService.findById(id);
        return res.get();

    }

    public void addContact(ContactUsDTO contact) {
        contactService.save(contact);
    }

    public void updateContact(ContactUsDTO contact, int id) {
        contactService.save(contact);
    }

    public void deleteContact(int id) {
        contactService.deleteById(id);
    }
}

