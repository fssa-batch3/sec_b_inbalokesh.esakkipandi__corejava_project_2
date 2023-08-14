package in.fssa.onlyhomefood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import in.fssa.onlyhomefood.Interface.UserInterface;
import in.fssa.onlyhomefood.exception.PersistanceException;
import in.fssa.onlyhomefood.model.User;
import in.fssa.onlyhomefood.util.ConnectionUtil;

public class UserDAO implements UserInterface {
	
	public Set<User> findAll() throws PersistanceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<User> userList = new HashSet<>();

		try {
			String query = "select * from users where is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("user_name"));
				user.setMobNumber(rs.getLong("phone_number"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setActive(rs.getBoolean("is_active"));

				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return userList;
	}
	
	public User findById(int userId) throws PersistanceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;

		try {
			String query = "select * from users where is_active = 1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, userId);
			rs = ps.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("user_name"));
				user.setMobNumber(rs.getLong("phone_number"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setActive(rs.getBoolean("is_active"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return user;
	}

	public void create(User newUser) throws PersistanceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "select * from users where is_active = 1 AND phone_number = ? OR email = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, newUser.getMobNumber());
			ps.setString(2, newUser.getEmail());
			rs = ps.executeQuery();

			if (rs.next() == true) {
				throw new PersistanceException("User already exist");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		try {
			String query = "insert into users (user_name, phone_number, email, password) values (?,?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, newUser.getName());
			ps.setLong(2, newUser.getMobNumber());
			ps.setString(3, newUser.getEmail());
			ps.setString(4, newUser.getPassword());

			ps.executeUpdate();
			System.out.println("User has been created sucessfully");

		} catch (SQLException e) {
			e.printStackTrace();
//			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	public void update(int id, User updateUser) throws PersistanceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "Update users set user_name = ?, password = ? where id = ? AND is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, updateUser.getName());
			ps.setString(2, updateUser.getPassword());
			ps.setInt(3, id);

			ps.executeUpdate();
			System.out.println("User has been updated sucessfully");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
	
	// To check whether id is presents
	public void checkIdExists(int id) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "select * from users where is_active = 1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next() == false) {
				throw new PersistanceException("User not found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistanceException(e.getMessage());
		}  finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}

	public void delete(int id) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "Update users set is_active = 0 where id = ? AND is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			ps.executeUpdate();
			System.out.println("User has been deleted sucessfully");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

}
