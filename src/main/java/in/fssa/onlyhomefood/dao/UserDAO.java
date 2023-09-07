package in.fssa.onlyhomefood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import in.fssa.onlyhomefood.exception.PersistenceException;
import in.fssa.onlyhomefood.interfaces.UserInterface;
import in.fssa.onlyhomefood.model.User;
import in.fssa.onlyhomefood.util.ConnectionUtil;

public class UserDAO implements UserInterface {
	/**
	 * @return
	 * @throws PersistenceException
	 */
	public Set<User> findAll() throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<User> userList = new HashSet<>();

		try {
			String query = "SELECT id,user_name,phone_number,email,password,is_active FROM users WHERE is_active = 1";
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
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return userList;
	}

	/**
	 * @return user
	 * @param userId
	 * @throws PersistenceException
	 */
	public User findById(int userId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;

		try {
			String query = "SELECT id,user_name,phone_number,email,password,is_active FROM users WHERE is_active = 1 AND id = ?";
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
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return user;
	}

	/**
	 * @return user
	 * @param userPhoneNumber
	 * @throws PersistenceException
	 */
	public User findByPhoneNumber(long userPhoneNumber) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;

		try {
			String query = "SELECT id,user_name,phone_number,email,password,is_active FROM users WHERE is_active = 1 AND phone_number = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, userPhoneNumber);
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
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return user;
	}

	/**
	 * @param newUser
	 * @throws PersistenceException
	 */
	public void create(User newUser) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT phone_number,email FROM users WHERE is_active = 1 AND phone_number = ? OR email = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, newUser.getMobNumber());
			ps.setString(2, newUser.getEmail());
			rs = ps.executeQuery();

			if (rs.next()) {
				throw new PersistenceException("User already exist");
			}

		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		try {
			String query = "INSERT INTO users (user_name, phone_number, email, password) VALUES (?,?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, newUser.getName());
			ps.setLong(2, newUser.getMobNumber());
			ps.setString(3, newUser.getEmail());
			ps.setString(4, newUser.getPassword());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	/**
	 * @param id
	 * @param updateUser
	 * @throws PersistenceException
	 */
	public void update(int id, User updateUser) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE users SET user_name = ?, password = ? WHERE id = ? AND is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, updateUser.getName());
			ps.setString(2, updateUser.getPassword());
			ps.setInt(3, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	/**
	 * 
	 * @param id
	 * @throws PersistenceException
	 */
	// To check whether id is presents
	public void checkIdExists(int id) throws PersistenceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT 1 FROM users WHERE is_active = 1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new PersistenceException("User not found");
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}

	/**
	 * @param id
	 * @throws PersistenceException
	 */
	public void delete(int id) throws PersistenceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE users SET is_active = 0 WHERE id = ? AND is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	/**
	 * 
	 * @param number
	 * @param password
	 * @throws PersistenceException
	 */
	public void checkUserCredentials(long number, String password) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT password FROM users WHERE phone_number = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setLong(1, number);

			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new PersistenceException("Invalid Login Credentials");
			} else {
				if (!rs.getString("password").equals(password)) {
					throw new PersistenceException("Invalid Login Credentials");
				}
			}

		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

	}

	/**
	 * 
	 * @return userId
	 */
	public int getLastUpdatedUserId() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int userId = 0;
		try {
			String query = "SELECT id FROM users WHERE is_active = 1 ORDER BY created_at DESC LIMIT 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				userId = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return userId;
	}

}
