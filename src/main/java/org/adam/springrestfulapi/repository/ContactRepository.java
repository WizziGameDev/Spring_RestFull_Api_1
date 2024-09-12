package org.adam.springrestfulapi.repository;

import org.adam.springrestfulapi.entity.Contact;
import org.adam.springrestfulapi.entity.User;
import org.adam.springrestfulapi.model.ContactResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {

    Optional<Contact> findFirstByUserAndId(User user, String id);

    Page<Contact> findAll(Specification<Contact> specification, Pageable pageable);

}
