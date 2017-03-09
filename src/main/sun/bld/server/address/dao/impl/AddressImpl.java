package main.sun.bld.server.address.dao.impl;

import main.sun.bld.server.address.dao.AddressDao;
import main.sun.bld.server.address.model.Address;
import main.sun.bld.server.common.ConnectionJdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by SUN on 2017/3/8.
 */
public class AddressImpl implements AddressDao{
    public boolean addAddress(Address address)
    {
        boolean result = false;
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        String sql = "insert into address(user_name, address, postcode, recipients) values(?,?,?,?)";

        try
        {
            ps = connection.prepareStatement(sql);
            ps.setString(1, address.getUserName());
            ps.setString(2, address.getAddress());
            ps.setString(3, address.getPostcode());
            ps.setString(4, address.getRecipients());

            result = ps.executeUpdate() == 1 ? true : false;

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return result;
    }
}
