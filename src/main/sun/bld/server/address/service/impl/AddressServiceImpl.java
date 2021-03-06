package main.sun.bld.server.address.service.impl;

import main.sun.bld.server.address.dao.impl.AddressImpl;
import main.sun.bld.server.address.model.Address;
import main.sun.bld.server.address.service.AddressService;

import java.util.List;

/**
 * Created by SUN on 2017/3/8.
 */
public class AddressServiceImpl implements AddressService{
    private AddressImpl  addressImpl = new AddressImpl();
    public void addAddress(Address address)
    {
        addressImpl.addAddress(address);
    }

    public List<Address> getAllAddressByUserName(String userName)
    {
        List<Address> addressList = addressImpl.getAllAddressByUserName(userName);

        return addressList;
    }

    public List<Address> getAllAddress()
    {
        List<Address> addressList = addressImpl.getAllAddress();

        return addressList;
    }

    public void deleteAddressByUserNameAndAddressID(String userName, int addressID)
    {
        addressImpl.deleteAddressByUserNameAndAddressID(userName, addressID);
    }

    public Address getAddressByAddressID(int addressID)
    {
        return addressImpl.getAddressByAddressID(addressID);
    }
}
