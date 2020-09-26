package br.com.pestCare.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.pestCare.entities.Category;
import br.com.pestCare.entities.Order;
import br.com.pestCare.entities.OrderItem;
import br.com.pestCare.entities.Payment;
import br.com.pestCare.entities.Product;
import br.com.pestCare.entities.User;
import br.com.pestCare.entities.enumeration.OrderStatus;
import br.com.pestCare.repository.CategoryRepository;
import br.com.pestCare.repository.OrderItemRepository;
import br.com.pestCare.repository.OrderRepository;
import br.com.pestCare.repository.ProductRepository;
import br.com.pestCare.repository.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	// O metodo rum e chamdo ao inicia o programa e tudo que estiver
	// dentro do run vai ser chamado
	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

		User u1 = new User(null, "David linhares", "emai@teste.com", "874-4545", "1234");
		User u2 = new User(null, "Carlos Alberto", "testi@teste.com", "27445-4545", "1564234");

		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1, OrderStatus.CANCELED);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2, OrderStatus.PAID);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1, OrderStatus.SHIPPED);

		categoryRepository.saveAll(Arrays.asList(cat1,cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);

		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		OrderItem oi1 = new OrderItem(o1, p1, 2L, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1L, p4.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2L, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2L, p5.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(oi1,oi2,oi3,oi4));
		
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1 );
		o1.setPayment(pay1);
		
		orderRepository.save(o1);
	}

}
