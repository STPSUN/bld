package main.sun.bld.server.address.dao;

import main.sun.bld.server.address.model.Address;

import java.util.List;

/**
 * Created by SUN on 2017/3/8.
 */
public interface AddressDao {
    public boolean addAddress(Address address);

    public List<Address> getAllAddressByUserName(String userName);

    public List<Address> getAllAddress();

    public void deleteAddressByUserNameAndAddressID(String userName, int addreddID);

    public Address getAddressByAddressID(int addressID);
}
