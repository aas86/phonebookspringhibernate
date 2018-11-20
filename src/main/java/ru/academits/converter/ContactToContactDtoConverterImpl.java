package ru.academits.converter;

import org.springframework.stereotype.Service;
import ru.academits.dto.ContactDto;
import ru.academits.model.Contact;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ContactToContactDtoConverterImpl implements ContactToContactDtoConverter {
    @Override
    public List<ContactDto> convert(List<Contact> list) {
        List<ContactDto> dtoList = list.stream().map(this::convert).collect(Collectors.toList());
        for (int i = 0; i < dtoList.size(); i++) {
            dtoList.get(i).setNumber(i + 1);
        }
        return dtoList;
    }

    @Override
    public ContactDto convert(Contact contact) {
        ContactDto contactDto = new ContactDto();
        contactDto.setId(contact.getId());
        contactDto.setFirstName(contact.getFirstName());
        contactDto.setLastName(contact.getLastName());
        contactDto.setPhone(contact.getPhone());
        return contactDto;
    }

}
