package ru.academits.dao;

import ru.academits.model.Contact;

import java.util.List;

/**
 * Created by Anna on 26.07.2017.
 */
public interface ContactDao {
    List<Contact> getAllContacts();

    void add(Contact contact);

    void delete(Contact contact);

    List<Contact> findByPhone(String phone);
}
