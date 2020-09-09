import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.ProductDAO;
import com.pojo.Product;

public class Test_ProductDAO_HibernateTemplate {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		ProductDAO product = (ProductDAO) context.getBean("hib_template");
	//	product.addProduct(new Product(127,"led",50,5,"bulb"));
	//	product.updateProduct(127,500.23);
	//  product.deleteProductById(127);
	//  product.findAll().forEach(System.out::println);
		product.findAllByCategory("chips").forEach(System.out::println);
	}

}
