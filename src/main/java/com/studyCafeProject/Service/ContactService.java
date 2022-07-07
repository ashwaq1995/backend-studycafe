package com.studyCafeProject.Service;

import com.studyCafeProject.DTO.Api;
import com.studyCafeProject.Exception.ApiException;
import com.studyCafeProject.Model.Contact;
import com.studyCafeProject.Model.User;
import com.studyCafeProject.Repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    public void addContact(User user, Contact contact){
        contact.setUser(user);
        contactRepository.save(contact);
    }

    public void updateContact(String userId, String contactId, Contact contact){
        Contact updateContact=contactRepository.findById(contactId).orElseThrow(()->new ApiException("There is not contact with this id"));
        if(!updateContact.getUser().getId().equals(userId)){
            throw new ApiException("You don't own this contact to update it !");
        }
        updateContact.setName(contact.getName());
        updateContact.setPhoneNo(contact.getPhoneNo());
        contactRepository.save(updateContact);
    }

    public void deleteContact(String userId,String contactId){
        Contact deletedContact=contactRepository.findById(contactId).orElseThrow(()->new ApiException("There is not contact with this id"));;
        if(!deletedContact.getUser().getId().equals(userId)){
            throw new ApiException("You don't own this contact to delete it !");
        }
        contactRepository.delete(deletedContact);
    }


    public  List<Contact> getContactsByUserId(String userId) {
        List<Contact> contacts= contactRepository.findAllByUser(userId);
        return contacts;
    }
}
