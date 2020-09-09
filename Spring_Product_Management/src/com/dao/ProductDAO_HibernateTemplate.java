package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pojo.Product;

@Repository("hib_template")
public class ProductDAO_HibernateTemplate implements ProductDAO {

	@Autowired
	HibernateTemplate template;

	@Override
	@Transactional
	public int addProduct(Product product) {
		template.save(product);
		return 1;
	}

	@Override
	@Transactional
	public boolean updateProduct(int productId, double price) {
		Product product = template.get(Product.class, productId);
		product.setPrice(price);
		template.save(product);
		return true;
	}

	@Override
	@Transactional
	public boolean updateProduct(int productId, int quantity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public boolean deleteProductById(int productId) {
		Product product = template.get(Product.class, productId);
		template.delete(product);
		return true;
	}

	@Override
	public Product findById(int productId) {
		Product product = template.get(Product.class, productId);
		return product;
	}

	@Override
	public List<Product> findAll() {
		return template.loadAll(Product.class);
	}

	@Override
	public List<Product> findAllByCategory(String category) {
		String HQL_QUERY = "select product.pId as product_id,product.pName as product_name,product.price as price,product.quantity "
				+ "as product_qty,product.category as product_category from "
				+ "com.pojo.Product product where product.category=:category";
		Query query = template.getSessionFactory().openSession().createQuery(HQL_QUERY);
		query.setParameter("category",category);
		List<Object[]>list=query.getResultList();
		List<Product>products=new ArrayList<Product>();
		for(Object[]o :list){
			//System.out.println(o[0]+":"+o[1]);
			Product product=new Product();
			product.setpId((Integer)o[0]);
			product.setpName((String)o[1]);
			product.setPrice((Double)o[2]);
			product.setQuantity((Integer)o[3]);
			product.setCategory((String)o[4]);
			products.add(product);		
		}
		
		return products;
	}

}
