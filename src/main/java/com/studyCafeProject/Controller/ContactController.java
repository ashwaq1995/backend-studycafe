package com.studyCafeProject.Controller;

import com.studyCafeProject.DTO.Api;
import com.studyCafeProject.Model.Contact;
import com.studyCafeProject.Model.User;
import com.studyCafeProject.Service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/contact")
public class ContactController {

    private final ContactService contactService;

    @GetMapping("/all/contacts")
    public ResponseEntity<List<Contact>> getContacts(@AuthenticationPrincipal User user){
        return ResponseEntity.status(HttpStatus.OK).body(contactService.getContactsByUserId(user.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity<Api> addContact(@AuthenticationPrincipal User user, @RequestBody @Valid Contact contact){
        contactService.addContact(user,contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Api("New contact added",201));
    }

    @PutMapping("/{contactId}")
    public ResponseEntity<Api> updateContact(@AuthenticationPrincipal User user,@PathVariable String contactId,@RequestBody @Valid Contact contact){
        contactService.updateContact(user.getId(),contactId,contact);
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Contact updated",200));
    }

    @DeleteMapping("/delete/{contactId}")
    public ResponseEntity<Api> deleteContact(@AuthenticationPrincipal User user,@PathVariable String contactId){
        contactService.deleteContact(user.getId(),contactId);
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Contact deleted",200));
    }
}
