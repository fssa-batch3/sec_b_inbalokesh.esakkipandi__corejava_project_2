package in.fssa.onlyhomefood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import in.fssa.onlyhomefood.exception.PersistanceException;
import in.fssa.onlyhomefood.util.ConnectionUtil;

public class ProductPriceDAO {
	
	// To check whether id is presents
	public void checkIdExists(int product_id) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "select * from products where is_active = 1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, product_id);
			rs = ps.executeQuery();

			if (rs.next() == false) {
				throw new PersistanceException("Product not found");
			}
		} catch (SQLException e) {
			System.out.println(e);
			throw new PersistanceException(e.getMessage());
		}  finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}
	
	public Timestamp getDate(int product_id) throws PersistanceException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Timestamp updateDate = null;
		
		
		try {
			String query = "SELECT * FROM products where id = ? and is_active=1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, product_id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				updateDate = rs.getTimestamp("modified_at");	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return updateDate;
	}
	
	public void create(Timestamp updateDate, int product_id, int price) throws PersistanceException {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			String query = "INSERT INTO product_price (product_id, price, start_date) VALUES (?,?,?)";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, product_id);
			ps.setInt(2, price);
			ps.setTimestamp(3, updateDate);

			ps.executeUpdate();
			
			System.out.println("Product price created Sucessfully");
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
		
	}


}
