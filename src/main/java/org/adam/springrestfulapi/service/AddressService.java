package org.adam.springrestfulapi.service;

import org.adam.springrestfulapi.entity.User;
import org.adam.springrestfulapi.model.AddressResponse;
import org.adam.springrestfulapi.model.CreateAddressRequest;
import org.adam.springrestfulapi.model.UpdateAddressRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AddressService {

    @Transactional
    public AddressResponse create(User user, CreateAddressRequest request);

    @Transactional(readOnly = true)
    public AddressResponse get(User user, String contactId, String addressId);

    @Transactional
    public AddressResponse update(User user, UpdateAddressRequest request);

    @Transactional
    public String remove(User user, String contactId, String addressId);

    public List<AddressResponse> listData(User user, String contactId);
}
