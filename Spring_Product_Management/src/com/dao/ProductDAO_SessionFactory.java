package com.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pojo.Product;

@Repository("product_factory")
public class ProductDAO_SessionFactory implements ProductDAO {

	@Autowired
	SessionFactory factory;

	@Override
	public int addProduct(Product product) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(product);
		transaction.commit();
		return 1;
	}

	@Override
	public boolean updateProduct(int productId, double price) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Product product = session.get(Product.class, productId);
		product.setPrice(price);
		session.update(product);
		transaction.commit();
		return true;
	}

	@Override
	public boolean updateProduct(int productId, int quantity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteProductById(int productId) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Product product = session.get(Product.class, productId);
		session.delete(product);
		transaction.commit();
		return true;
	}

	@Override
	public Product findById(int productId) {

		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Product product = session.get(Product.class, productId);
		return product;
	}

	@Override
	public List<Product> findAll() {
		List<Product> products = null;

		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);

		Root<Product> root = criteriaQuery.from(Product.class);
		criteriaQuery.select(root);

		products = session.createQuery(criteriaQuery).getResultList();

		transaction.commit();

		return products;
	}

	@Override
	public List<Product> findAllByCategory(String category) {
		List<Product> products = null;

		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		
		CriteriaBuilder builder=session.getCriteriaBuilder();
		CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
	
		Root<Product>root=criteriaQuery.from(Product.class);
		criteriaQuery.select(root).where(builder.equal(root.get("category"), category));
		
		products=session.createQuery(criteriaQuery).getResultList();

		return products;
	}

}
