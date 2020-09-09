package com.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.pojo.Product;


@Repository("product")
public class ProductDAO_JdbcTemplate implements ProductDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public int addProduct(Product product) {
		int added=0;
		String insertProduct="insert into product values(?,?,?,?,?)";
		added=jdbcTemplate.update(insertProduct,product.getpId(),product.getpName(),product.getPrice(),product.getQuantity(),product.getCategory());
		return added;
	}

	@Override
	public boolean updateProduct(int productId, double price) {
		boolean isupdated=false;
		String updateProduct="update product set price=? where pId=?";
		int updated=jdbcTemplate.update(updateProduct,price,productId);
		if(updated>0)
			isupdated=true;
		return isupdated;
	}

	@Override
	public boolean updateProduct(int productId, int quantity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteProductById(int productId) {
		int deleted=0;
		String deleteProduct="delete from product where pId=?";
		deleted=jdbcTemplate.update(deleteProduct,productId);
		if(deleted>0)
			return true;
		return false;
	}

	@Override
	public Product findById(int productId) {
		Product product1=null;
		String findProduct="select * from product where pId=?";
		product1=jdbcTemplate.queryForObject(findProduct,new Integer[]{productId},new RowMapper<Product>(){
			@Override
			public Product mapRow(ResultSet set, int arg1) throws SQLException {
			Product product=new Product();
			product.setpId(set.getInt(1));
			product.setpName(set.getString(2));
			product.setPrice(set.getDouble(3));
			product.setQuantity(set.getInt(4));
			product.setCategory(set.getString(5));
				return product;
			}
		});
		return product1;
	}

	@Override
	public List<Product> findAll() {
		String findProduct="select * from product";
	List<Product>products=jdbcTemplate.query(findProduct,new RowMapper<Product>(){

			@Override
			public Product mapRow(ResultSet set, int arg1) throws SQLException {
				Product product =new Product();
				product.setpId(set.getInt(1));
				product.setpName(set.getString(2));
				product.setPrice(set.getDouble(3));
				product.setQuantity(set.getInt(4));
				product.setCategory(set.getString(5));
				return product;
			}
			
		});
		
		
		return products;
	}

	@Override
	public List<Product> findAllByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

}
