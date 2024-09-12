package org.adam.springrestfulapi.repository;

import org.adam.springrestfulapi.entity.Address;
import org.adam.springrestfulapi.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, String> {

    Optional<Address> findFirstByContactAndId(Contact contact, String addressId);

    List<Address> findAllByContact(Contact contact);
}
