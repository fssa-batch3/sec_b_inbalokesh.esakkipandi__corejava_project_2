package in.fssa.onlyhomefood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.onlyhomefood.exception.PersistenceException;
import in.fssa.onlyhomefood.model.Address;
import in.fssa.onlyhomefood.util.ConnectionUtil;

public class AddressDAO {

	public List<Address> findAllById(int user_id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Address> addressList = new ArrayList<>();

		try {
			String query = "SELECT id,user_name,phone_number,location,street_name,town_name,city,state,pin_code,"
					+ "user_id,default_status,is_active FROM address WHERE user_id = ? AND is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, user_id);
			rs = ps.executeQuery();

			while (rs.next()) {
				Address address = new Address();
				address.setId(rs.getInt("id"));
				address.setName(rs.getString("user_name"));
				address.setPhoneNumber(rs.getLong("phone_number"));
				address.setLocation(rs.getString("location"));
				address.setStreetName(rs.getString("street_name"));
				address.setTownName(rs.getString("town_name"));
				address.setCity(rs.getString("city"));
				address.setState(rs.getString("state"));
				address.setPinCode(rs.getInt("pin_code"));
				address.setUserId(rs.getInt("user_id"));
				address.setDefaultStatus(rs.getBoolean("default_status"));
				address.setActive(rs.getBoolean("is_active"));

				addressList.add(address);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return addressList;
	}

	public int findAddressExist(Address address) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int id = 0;

		try {
			String query = "SELECT id FROM address WHERE location = ? AND street_name = ? AND town_name = ? AND city = ? AND state = ? AND pin_code = ? AND user_id = ? AND user_name = ? AND phone_number = ?";

			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, address.getLocation());
			ps.setString(2, address.getStreetName());
			ps.setString(3, address.getTownName());
			ps.setString(4, address.getCity());
			ps.setString(5, address.getState());
			ps.setInt(6, address.getPinCode());
			ps.setInt(7, address.getUserId());
			ps.setString(8, address.getName());
			ps.setLong(9, address.getPhoneNumber());

			rs = ps.executeQuery();

			if (rs.next()) {
				id = rs.getInt("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return id;
	}

	public void create(Address newAddress) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO address (user_name, phone_number,location,street_name,town_name,city,state,"
					+ "pin_code,user_id,default_status) VALUES (?,?,?,?,?,?,?,?,?,?)";

			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, newAddress.getName());
			ps.setLong(2, newAddress.getPhoneNumber());
			ps.setString(3, newAddress.getLocation());
			ps.setString(4, newAddress.getStreetName());
			ps.setString(5, newAddress.getTownName());
			ps.setString(6, newAddress.getCity());
			ps.setString(7, newAddress.getState());
			ps.setInt(8, newAddress.getPinCode());
			ps.setInt(9, newAddress.getUserId());
			ps.setBoolean(10, newAddress.getDefaultStatus());
			ps.executeUpdate();

			System.out.println("Address created sucessfully");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	public void update(int addrressId, boolean defaultStatus, boolean isActive) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "UPDATE address SET default_status = ?, is_active = ? WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setBoolean(1, defaultStatus);
			ps.setBoolean(2, isActive);
			ps.setInt(3, addrressId);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	public void delete(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE address SET is_active = 0 WHERE id = ? AND is_active = 1 AND default_status = 0";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);

			int row = ps.executeUpdate();

			if (row <= 0) {
				throw new PersistenceException("This address cannot be deleted");
			}

		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	public Address findById(int addressId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Address address = null;

		try {
			String query = "SELECT id,user_name,phone_number,location,street_name,town_name,city,state,pin_code,user_id,default_status,is_active FROM address WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, addressId);
			rs = ps.executeQuery();

			if (rs.next()) {
				Address foundAddress = new Address();
				foundAddress.setId(rs.getInt("id"));
				foundAddress.setName(rs.getString("user_name"));
				foundAddress.setPhoneNumber(rs.getLong("phone_number"));
				foundAddress.setLocation(rs.getString("location"));
				foundAddress.setStreetName(rs.getString("street_name"));
				foundAddress.setTownName(rs.getString("town_name"));
				foundAddress.setCity(rs.getString("city"));
				foundAddress.setState(rs.getString("state"));
				foundAddress.setPinCode(rs.getInt("pin_code"));
				foundAddress.setUserId(rs.getInt("user_id"));
				foundAddress.setDefaultStatus(rs.getBoolean("default_status"));
				foundAddress.setActive(rs.getBoolean("is_active"));

				address = foundAddress;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());
		}
		return address;
	}

	public int findDefaultAddress(int userId) throws PersistenceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int addressId = 0;

		String query = "Select id FROM address WHERE default_status = 1 AND user_id = ? AND is_active = 1";

		try {
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, userId);
			rs = ps.executeQuery();

			if (rs.next()) {
				addressId = rs.getInt("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());
		}
		return addressId;
	}
}
