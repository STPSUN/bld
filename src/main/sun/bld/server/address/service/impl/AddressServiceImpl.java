package main.sun.bld.server.address.service.impl;

import main.sun.bld.server.address.dao.impl.AddressImpl;
import main.sun.bld.server.address.model.Address;
import main.sun.bld.server.address.service.AddressService;

/**
 * Created by SUN on 2017/3/8.
 */
public class AddressServiceImpl implements AddressService{
    private AddressImpl  addressImpl = new AddressImpl();
    public void addAddress(Address address)
    {
        addressImpl.addAddress(address);
    }
}
