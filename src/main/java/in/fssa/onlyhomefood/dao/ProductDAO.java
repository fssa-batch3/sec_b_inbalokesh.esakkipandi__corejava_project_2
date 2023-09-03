package in.fssa.onlyhomefood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import in.fssa.onlyhomefood.exception.PersistenceException;
import in.fssa.onlyhomefood.model.Product;
import in.fssa.onlyhomefood.util.ConnectionUtil;

public class ProductDAO{
	/**
	 * 
	 * @return
	 * @throws PersistenceException
	 */
//	Find all products
	public Set<Product> findAll() throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<Product> productList = new HashSet<>();

		try {
			String query = "SELECT id,name,type,quantity,quantity_type,image,is_active FROM products WHERE is_active = 1";
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
				product.setImage(rs.getString("image"));
				product.setIs_active(rs.getBoolean("is_active"));
				
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return productList;
	}
	/**
	 * 
	 * @param productId
	 * @return
	 * @throws PersistenceException
	 */
//	Find products by id
	public Product findById(int productId) throws PersistenceException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Product product = null;

		try {
			String query = "SELECT id,name,type,quantity,quantity_type,image,is_active FROM products WHERE is_active = 1 AND id = ?";
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
				product.setImage(rs.getString("image"));
				product.setIs_active(rs.getBoolean("is_active"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return product;
	}
	/**
	 * 
	 * @param product
	 * @throws PersistenceException
	 */
//	Check whether same name is present
	public void checkNameIsPresent(Product product) throws PersistenceException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT 1 FROM products WHERE name=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, product.getName());
			rs = ps.executeQuery();

			if (rs.next() == true) {
				throw new PersistenceException("Product already exist");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}
	/**
	 * 
	 * @param newProduct
	 * @return
	 * @throws PersistenceException
	 */
//	Create new product
	public int create(Product newProduct) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int generatedId = -1;
		
		try {
			String query = "INSERT INTO products (name, type, quantity, quantity_type, image) VALUES (?,?,?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, newProduct.getName());
			ps.setString(2, newProduct.getType());
			ps.setInt(3, newProduct.getQuantity());
			ps.setString(4, newProduct.getQuantityType());
			ps.setString(5, newProduct.getImage());

			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				generatedId = rs.getInt(1);
			}
			System.out.println("Product has been created sucessfully");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return generatedId;
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
			String query = "SELECT 1 FROM products WHERE is_active = 1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next() == false) {
				throw new PersistenceException("Product not found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());
		}  finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}
	/**
	 * 
	 * @param id
	 * @param updateProduct
	 * @throws PersistenceException
	 */
//	Update product
	public void update(int id, Product updateProduct) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE products SET type = ?, quantity = ? , quantity_type = ?, image = ?, modified_at = NOW() WHERE id = ? AND is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, updateProduct.getType());
			ps.setInt(2, updateProduct.getQuantity());
			ps.setString(3, updateProduct.getQuantityType());
			ps.setString(4, updateProduct.getImage());
			ps.setInt(5, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
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
	public void delete(int id) throws PersistenceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE products SET is_active = 0 WHERE id = ? AND is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			ps.executeUpdate();
			System.out.println("Product has been deleted sucessfully");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
	/**
	 * 
	 * @return
	 */
	public int getLastUpdatedUserId() {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int productId = 0;
	    try {
	        String query = "SELECT id FROM products WHERE is_active = 1 ORDER BY created_at DESC LIMIT 1";
	        conn = ConnectionUtil.getConnection();
	        ps = conn.prepareStatement(query);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	        	productId = rs.getInt("id");   
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
//	        throw new PersistenceException(e.getMessage());
	    } finally {
	        ConnectionUtil.close(conn, ps, rs);
	    }
	    return productId;
	}
}
