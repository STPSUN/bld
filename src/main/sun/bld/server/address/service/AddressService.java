package main.sun.bld.server.address.service;

import main.sun.bld.server.address.model.Address;

import java.util.List;

/**
 * Created by SUN on 2017/3/8.
 */
public interface AddressService {
    public void addAddress(Address address);

    public List<Address> getAllAddressByUserName(String userName);

    public List<Address> getAllAddress();

    public void deleteAddressByUserNameAndAddressID(String userName, int addressID);

    public Address getAddressByAddressID(int addressID);
}
