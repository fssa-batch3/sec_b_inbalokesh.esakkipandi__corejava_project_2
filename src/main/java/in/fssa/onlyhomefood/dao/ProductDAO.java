package in.fssa.onlyhomefood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import in.fssa.onlyhomefood.exception.PersistanceException;
import in.fssa.onlyhomefood.model.Product;
import in.fssa.onlyhomefood.util.ConnectionUtil;

public class ProductDAO{
	
//	Find all products
	public Set<Product> findAll() throws PersistanceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<Product> productList = new HashSet<>();

		try {
			String query = "select * from products where is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setType(rs.getString("type"));
				product.setQuantity(rs.getInt("quantity"));
				product.setQuantityType(rs.getString("quantity_type"));
				product.setIs_active(rs.getBoolean("is_active"));
				
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return productList;
	}
	
//	Find products by id
	public Product findById(int productId) throws PersistanceException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Product product = null;

		try {
			String query = "select * from products where is_active = 1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, productId);
			rs = ps.executeQuery();

			if (rs.next()) {
				product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setType(rs.getString("type"));
				product.setQuantity(rs.getInt("quantity"));
				product.setQuantityType(rs.getString("quantity_type"));
				product.setIs_active(rs.getBoolean("is_active"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return product;
	}
	
//	Check whether same name is present
	public void checkNameIsPresent(Product product) throws PersistanceException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String query = "select * from products where is_active = 1 AND name=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, product.getName());
			rs = ps.executeQuery();

			if (rs.next() == true) {
				throw new PersistanceException("Product already exist");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}
	
//	Create new product
	public int create(Product newProduct) throws PersistanceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int generatedId = -1;
		
		try {
			String query = "insert into products (name, type, quantity, quantity_type) values (?,?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, newProduct.getName());
			ps.setString(2, newProduct.getType());
			ps.setInt(3, newProduct.getQuantity());
			ps.setString(4, newProduct.getQuantityType());

			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			if(rs.next()) {
				generatedId = rs.getInt(1);
			}
			System.out.println("Product has been created sucessfully");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return generatedId;
	}
	
	// To check whether id is presents
	public void checkIdExists(int id) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "select * from products where is_active = 1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next() == false) {
				throw new PersistanceException("Product not found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistanceException(e.getMessage());
		}  finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}
	
//	Update product
	public void update(int id, Product updateProduct) throws PersistanceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "Update products set type = ?, quantity = ? , quantity_type = ?, modified_at = NOW() where id = ? AND is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, updateProduct.getType());
			ps.setInt(2, updateProduct.getQuantity());
			ps.setString(3, updateProduct.getQuantityType());
			ps.setInt(4, id);

			ps.executeUpdate();
			System.out.println("Product has been updated sucessfully");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
	
	public void delete(int id) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "Update products set is_active = 0 where id = ? AND is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			ps.executeUpdate();
			System.out.println("Product has been deleted sucessfully");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
	

}
