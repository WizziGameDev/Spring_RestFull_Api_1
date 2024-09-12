package org.adam.springrestfulapi.service;

import org.adam.springrestfulapi.entity.User;
import org.adam.springrestfulapi.model.CreateContactRequest;
import org.adam.springrestfulapi.model.ContactResponse;
import org.adam.springrestfulapi.model.SearchContactRequest;
import org.adam.springrestfulapi.model.UpdateContactRequest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

public interface ContactService {

    @Transactional
    public ContactResponse create(User user, CreateContactRequest request);

    public ContactResponse get(User user, String id);

    @Transactional
    public ContactResponse update(User user, UpdateContactRequest request);

    @Transactional
    public String delete(User user, String id);

    public Page<ContactResponse> search(User user, SearchContactRequest request);
}
