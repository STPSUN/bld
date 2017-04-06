package main.sun.bld.server.address.dao.impl;

import main.sun.bld.server.address.dao.AddressDao;
import main.sun.bld.server.address.model.Address;
import main.sun.bld.server.common.ConnectionJdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Address> getAllAddressByUserName(String userName)
    {
        List<Address> addressList = new ArrayList<Address>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Connection connection = ConnectionJdbc.connectionJdbc();
        String sql = "select * from address where user_name=?";

        try
        {
            ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            while (rs.next())
            {
                Address address = new Address();
                address.setAddressID(rs.getInt("address_id"));
                address.setAddress(rs.getString("address"));
                address.setUserName(rs.getString("user_name"));
                address.setRecipients(rs.getString("recipients"));
                address.setPostcode(rs.getString("postcode"));

                addressList.add(address);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return addressList;
    }

    public List<Address> getAllAddress()
    {
        List<Address> addressList = new ArrayList<Address>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Connection connection = ConnectionJdbc.connectionJdbc();
        String sql = "select * from address";

        try
        {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
            {
                Address address = new Address();
                address.setAddressID(rs.getInt("address_id"));
                address.setAddress(rs.getString("address"));
                address.setUserName(rs.getString("user_name"));
                address.setRecipients(rs.getString("recipients"));
                address.setPostcode(rs.getString("postcode"));

                addressList.add(address);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return addressList;
    }

    public Address getAddressByAddressID(int addressID)
    {
        Address address = null;
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from address where address_id=?";
        try
        {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, addressID);
            rs = ps.executeQuery();
            while (rs.next())
            {
                address = new Address();
                address.setAddressID(addressID);
                address.setAddress(rs.getString("address"));
                address.setUserName(rs.getString("user_name"));
                address.setPostcode(rs.getString("postcode"));
                address.setRecipients(rs.getString("recipients"));
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return address;
    }


    public void deleteAddressByUserNameAndAddressID(String userName, int addressID)
    {
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        String sql = "delete from address where user_name = ? and address_id=?";

        try
        {
            ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setInt(2, addressID);
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
}
