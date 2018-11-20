package ru.academits.converter;


import ru.academits.dto.ContactDto;
import ru.academits.model.Contact;

import java.util.List;

/**
 * Created by 437-5 on 15.11.2018.
 */
public interface ContactToContactDtoConverter {
    List<ContactDto> convert(List<Contact> list);
    ContactDto convert(Contact contact);
}
