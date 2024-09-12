package org.adam.springrestfulapi.controller;

import org.adam.springrestfulapi.entity.User;
import org.adam.springrestfulapi.model.*;
import org.adam.springrestfulapi.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping(
            value = "/api/contacts",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ContactResponse> create(User user, @RequestBody CreateContactRequest request) {
        ContactResponse response = contactService.create(user, request);
        return WebResponse.<ContactResponse>builder().data(response).build();
    }

    @GetMapping(
            value = "/api/contacts/{idContact}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ContactResponse> get(User user, @PathVariable(name = "idContact", required = true) String id) {
        ContactResponse contactResponse = contactService.get(user, id);
        return WebResponse.<ContactResponse>builder().data(contactResponse).build();
    }

    @PutMapping(
            value = "/api/contacts/{contactId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ContactResponse> create(User user,
                                               @RequestBody UpdateContactRequest request,
                                               @PathVariable(name = "contactId", required = true) String contactId) {
        request.setId(contactId);
        ContactResponse response = contactService.update(user, request);
        return WebResponse.<ContactResponse>builder().data(response).build();
    }

    @DeleteMapping(
            value = "/api/contacts/{contactId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(User user, @PathVariable(name = "contactId", required = true) String contactId) {
        String response = contactService.delete(user, contactId);
        return WebResponse.<String>builder().data(response).build();
    }

    @GetMapping(
            path = "/api/contacts",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<ContactResponse>> search(User user,
                                                     @RequestParam(name = "name", required = false) String name,
                                                     @RequestParam(name = "email", required = false) String email,
                                                     @RequestParam(name = "phone", required = false) String phone,
                                                     @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                     @RequestParam(name = "size", required = false, defaultValue = "10") Integer size
    ) {
        SearchContactRequest request = SearchContactRequest.builder()
                .name(name)
                .email(email)
                .phone(phone)
                .page(page)
                .size(size)
                .build();

        Page<ContactResponse> responses = contactService.search(user, request);
        return WebResponse.<List<ContactResponse>>builder()
                .data(responses.getContent())
                .pagingResponses(PagingResponses.builder()
                        .currentPage(responses.getNumber())
                        .totalPage(responses.getTotalPages())
                        .size(responses.getSize())
                        .build())
                .build();
    }
}
