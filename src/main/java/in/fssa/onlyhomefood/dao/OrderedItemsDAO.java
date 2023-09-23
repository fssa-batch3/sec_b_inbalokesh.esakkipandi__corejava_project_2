package in.fssa.onlyhomefood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.onlyhomefood.exception.PersistenceException;
import in.fssa.onlyhomefood.model.OrderedItems;
import in.fssa.onlyhomefood.model.OrderedItems.DeliveryTime;
import in.fssa.onlyhomefood.model.OrderedItems.OrderStatus;
import in.fssa.onlyhomefood.util.ConnectionUtil;

public class OrderedItemsDAO {

	/**
	 * 
	 * @param userId
	 * @return
	 * @throws PersistenceException
	 */
	public List<OrderedItems> findAllById(int userId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrderedItems> itemsList = new ArrayList<>();

		try {
			String query = "SELECT order_id,user_id,product_id,quantity_ordered,delivery_time,order_status FROM ordered_items WHERE user_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, userId);
			rs = ps.executeQuery();

			while (rs.next()) {
				OrderedItems items = new OrderedItems();
				items.setOrderId(rs.getInt("order_id"));
				items.setUserId(rs.getInt("user_id"));
				items.setProductId(rs.getInt("product_id"));
				items.setQuantityOrdered(rs.getInt("quantity_ordered"));
				items.setDeliveryTime(DeliveryTime.valueOf(rs.getString("delivery_time")));
				items.setOrderStatus(OrderStatus.valueOf((rs.getString("order_status"))));
				itemsList.add(items);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return itemsList;
	}

	/**
	 * 
	 * @param orderedItems
	 * @throws PersistenceException
	 */
	public void create(OrderedItems orderedItems) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO ordered_items (order_id,product_id,quantity_ordered,delivery_time,order_status,user_id) VALUES(?,?,?,?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, orderedItems.getOrderId());
			ps.setInt(2, orderedItems.getProductId());
			ps.setInt(3, orderedItems.getQuantityOrdered());
			ps.setString(4, orderedItems.getDeliveryTime().name());
			ps.setString(5, orderedItems.getOrderStatus().name());
			ps.setInt(6, orderedItems.getUserId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	public void update(int id, OrderStatus status) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE ordered_items SET ordered_status = ? WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, status.name());
			ps.setInt(2, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	public List<OrderedItems> findAllByOrderId(int orderId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrderedItems> itemsList = new ArrayList<>();

		try {
			String query = "SELECT order_id,user_id,product_id,quantity_ordered,delivery_time,order_status FROM ordered_items WHERE order_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, orderId);
			rs = ps.executeQuery();

			while (rs.next()) {
				OrderedItems items = new OrderedItems();
				items.setOrderId(rs.getInt("order_id"));
				items.setUserId(rs.getInt("user_id"));
				items.setProductId(rs.getInt("product_id"));
				items.setQuantityOrdered(rs.getInt("quantity_ordered"));
				items.setDeliveryTime(DeliveryTime.valueOf(rs.getString("delivery_time")));
				items.setOrderStatus(OrderStatus.valueOf((rs.getString("order_status"))));
				itemsList.add(items);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return itemsList;
	}

}
