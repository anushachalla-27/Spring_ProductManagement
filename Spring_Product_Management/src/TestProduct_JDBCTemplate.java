import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.dao.ProductDAO;
import com.pojo.Product;

public class TestProduct_JDBCTemplate {

	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
		ProductDAO product=(ProductDAO)context.getBean("product");
//		int added=product.addProduct(new Product(127,"led",50,5,"bulb"));
//		if(added>0)
//			System.out.println("inserted");
//		else
//			System.out.println("not inserted");
//		boolean updated=product.updateProduct(127,500.23);
//		System.out.println("updated:-"+updated);
		
//		boolean deleted=product.deleteProductById(127);
//		System.out.println("deleted:-"+deleted);
		
//		Product p1=product.findById(144);
//		System.out.println("product:-"+p1);
		
//	product.findAll().forEach(System.out::println);
		
	}

}
