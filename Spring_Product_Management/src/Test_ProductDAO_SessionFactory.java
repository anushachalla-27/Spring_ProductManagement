import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.ProductDAO;
import com.pojo.Product;

public class Test_ProductDAO_SessionFactory {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		ProductDAO product = (ProductDAO) context.getBean("product_factory");

		 int added=product.addProduct(new Product(127, "led", 500.23,5,"bulb"));
		 System.out.println("added:-"+added);
		 product.updateProduct(127,500d);
		 product.deleteProductById(127);
		 Product product1=product.findById(125);
		 System.out.println("product:-"+product1);
		product.findAll().forEach(System.out::println);
		product.findAllByCategory("chips").forEach(System.out::println);
	}
}
