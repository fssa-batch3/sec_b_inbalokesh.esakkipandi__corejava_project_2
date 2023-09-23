package in.fssa.onlyhomefood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import in.fssa.onlyhomefood.exception.PersistenceException;
import in.fssa.onlyhomefood.model.Order;
import in.fssa.onlyhomefood.util.ConnectionUtil;

public class OrderDAO {
	/**
	 * 
	 * @param newOrder
	 * @throws PersistenceException
	 */
	public int create(Order newOrder) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int orderId = -1;

		try {
			String query = "INSERT INTO orders (total_price, created_by, delivery_address_id) VALUES (?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, newOrder.getTotalPrice());
			ps.setInt(2, newOrder.getCreatedBy());
			ps.setInt(3, newOrder.getDeliveryAddressId());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();

			if (rs.next()) {
				orderId = rs.getInt(1);
			}

		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return orderId;
	}

	/**
	 * 
	 * @return
	 * @throws PersistenceException
	 */
	public Set<Order> findAll() throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<Order> orderList = new HashSet<>();

		try {
			String query = "SELECT id,total_price,created_by,delivery_address_id,created_at FROM orders";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setTotalPrice(rs.getInt("toatl_price"));
				order.setCreatedBy(rs.getInt("created_by"));
				order.setDeliveryAddressId(rs.getInt("delivery_address_id"));
				order.setCreatedAt(rs.getTimestamp("created_at"));

				orderList.add(order);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return orderList;
	}

	public List<Order> findAllById(int userId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Order> userOrderList = new ArrayList<>();

		try {
			String query = "SELECT id,total_price,created_by,delivery_address_id,created_at FROM orders WHERE created_by = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, userId);
			rs = ps.executeQuery();

			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setTotalPrice(rs.getInt("total_price"));
				order.setCreatedBy(rs.getInt("created_by"));
				order.setDeliveryAddressId(rs.getInt("delivery_address_id"));
				order.setCreatedAt(rs.getTimestamp("created_at"));

				userOrderList.add(order);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return userOrderList;
	}

	public void checkIdExists(int orderId) throws PersistenceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT 1 FROM orders WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, orderId);
			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new PersistenceException("Order not found");
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}
}
