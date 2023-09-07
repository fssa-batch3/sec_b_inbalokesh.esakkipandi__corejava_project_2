package in.fssa.onlyhomefood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import in.fssa.onlyhomefood.exception.PersistenceException;
import in.fssa.onlyhomefood.model.ProductPrice;
import in.fssa.onlyhomefood.util.ConnectionUtil;

public class ProductPriceDAO {
	/**
	 * 
	 * @return
	 * @throws PersistenceException
	 */
//	Find all products
	public Set<ProductPrice> findAll() throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<ProductPrice> productPriceList = new HashSet<>();

		try {
			String query = "SELECT id,price,product_id,start_date,end_date FROM product_price";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				ProductPrice productPrice = new ProductPrice();
				productPrice.setId(rs.getInt("id"));
				productPrice.setPrice(rs.getInt("price"));
				productPrice.setProduct_id(rs.getInt("product_id"));
				productPrice.setStart_date(rs.getTimestamp("start_date"));
				productPrice.setEnd_date(rs.getTimestamp("end_date"));

				productPriceList.add(productPrice);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return productPriceList;
	}

	/**
	 * 
	 * @param product_id
	 * @return
	 * @throws PersistenceException
	 */
	public Timestamp getDate(int product_id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Timestamp updateDate = null;

		try {
			String query = "SELECT modified_at FROM products WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, product_id);
			rs = ps.executeQuery();

			if (rs.next()) {
				updateDate = rs.getTimestamp("modified_at");
			}

		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return updateDate;
	}

	/**
	 * 
	 * @param updateDate
	 * @param product_id
	 * @param price
	 * @throws PersistenceException
	 */
	public void create(Timestamp updateDate, int product_id, int price) throws PersistenceException {

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

		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	/**
	 * 
	 * @param updateDate
	 * @param product_id
	 * @throws PersistenceException
	 */
	public void update(Timestamp updateDate, int product_id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE product_price SET end_date = ? WHERE product_id = ? AND end_date IS NULL";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setTimestamp(1, updateDate);
			ps.setInt(2, product_id);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	/**
	 * 
	 * @param productId
	 * @return
	 * @throws PersistenceException
	 */
	public int getPrice(int productId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int productPrice = 0;

		try {
			String query = "SELECT price FROM product_price WHERE product_id = ? AND end_date IS NULL";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, productId);
			rs = ps.executeQuery();

			if (rs.next()) {
				productPrice = rs.getInt("price");
			}

		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return productPrice;
	}

}
