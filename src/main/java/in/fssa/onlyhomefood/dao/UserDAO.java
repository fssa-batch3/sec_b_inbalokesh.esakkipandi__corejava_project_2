package in.fssa.onlyhomefood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.onlyhomefood.model.User;
import in.fssa.onlyhomefood.util.ConnectionUtil;

public class UserDAO {
	
	
	public void create(User newUser) throws Exception,SQLException {

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
				throw new Exception("User already exist");
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println(e);
			throw new Exception(e);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
			throw new Exception(e);
			
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
			System.out.println(e.getMessage());
			throw new RuntimeException();
		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

}
