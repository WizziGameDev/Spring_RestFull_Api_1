package org.adam.springrestfulapi.controller;

import org.adam.springrestfulapi.entity.User;
import org.adam.springrestfulapi.model.AddressResponse;
import org.adam.springrestfulapi.model.CreateAddressRequest;
import org.adam.springrestfulapi.model.UpdateAddressRequest;
import org.adam.springrestfulapi.model.WebResponse;
import org.adam.springrestfulapi.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping(
            path = "/api/contacts/{contactId}/addresses",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AddressResponse> create(User user,
                                               @RequestBody CreateAddressRequest request,
                                               @PathVariable(name = "contactId", required = true) String contactId) {
        request.setContactId(contactId);
        AddressResponse addressResponse = addressService.create(user, request);
        return WebResponse.<AddressResponse>builder().data(addressResponse).build();
    }

    @GetMapping(
            path = "/api/contact/{idContact}/addresses/{idAddresses}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AddressResponse> get(User user,
                                            @PathVariable(name = "idContact") String contactId,
                                            @PathVariable(name = "idAddresses") String addressId) {
        AddressResponse addressResponse = addressService.get(user, contactId, addressId);
        return WebResponse.<AddressResponse>builder().data(addressResponse).build();
    }

    @PutMapping(
            path = "/api/contact/{idContact}/addresses/{idAddresses}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AddressResponse> update(User user,
                                               @PathVariable(name = "idContact") String contactId,
                                               @PathVariable(name = "idAddresses") String addressId,
                                               @RequestBody UpdateAddressRequest request) {
        request.setContactId(contactId);
        request.setAddressId(addressId);
        AddressResponse response = addressService.update(user, request);

        return WebResponse.<AddressResponse>builder().data(response).build();
    }

    @DeleteMapping(
            path = "/api/contact/{idContact}/addresses/{idAddresses}"
    )
    public WebResponse<String> remove(User user,
                                   @PathVariable(name = "idContact") String contactId,
                                   @PathVariable(name = "idAddresses") String addressId) {
        String response = addressService.remove(user, contactId, addressId);
        return WebResponse.<String>builder().data(response).build();
    }

    @GetMapping(
            path = "/api/contact/{idContact}/addresses/{idAddresses}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<AddressResponse>> list(User user,
                                      @PathVariable(name = "idContact") String contactId
    ) {
        List<AddressResponse> addressResponses = addressService.listData(user, contactId);
        return WebResponse.<List<AddressResponse>>builder().data(addressResponses).build();
    }
}
