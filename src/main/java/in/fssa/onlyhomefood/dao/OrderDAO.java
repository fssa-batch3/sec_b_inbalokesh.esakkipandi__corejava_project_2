package in.fssa.onlyhomefood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import in.fssa.onlyhomefood.exception.PersistenceException;
import in.fssa.onlyhomefood.model.Order;
import in.fssa.onlyhomefood.model.OrderEntity.DeliveryTime;
import in.fssa.onlyhomefood.model.OrderEntity.OrderStatus;
import in.fssa.onlyhomefood.util.ConnectionUtil;

public class OrderDAO {
	/**
	 * 
	 * @param newOrder
	 * @throws PersistenceException
	 */
	public void create(Order newOrder) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "INSERT INTO orders (quantity, total_price, delivery_time, order_status, created_by, address, product_id) VALUES (?,?,?,?,?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, newOrder.getQuantity());
			ps.setInt(2, newOrder.getTotal_price());
			ps.setString(3, newOrder.getDelivery_time().name());
			ps.setString(4, newOrder.getOrder_status().name());
			ps.setInt(5, newOrder.getCreated_by());
			ps.setString(6, newOrder.getAddress());
			ps.setInt(7, newOrder.getProduct_id());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
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
			String query = "SELECT id,quantity,total_price,delivery_time,order_status,created_by,address,product_id FROM orders";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setQuantity(rs.getInt("quantity"));
				order.setTotal_price(rs.getInt("total_price"));
				order.setDelivery_time(DeliveryTime.valueOf(rs.getString("delivery_time")));
				order.setOrder_status(OrderStatus.valueOf((rs.getString("order_status"))));
				order.setCreated_by(rs.getInt("created_by"));
				order.setAddress(rs.getString("address"));
				order.setProduct_id(rs.getInt("product_id"));

				orderList.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return orderList;
	}

}
