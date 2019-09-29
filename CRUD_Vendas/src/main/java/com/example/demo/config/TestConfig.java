package com.example.demo.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.demo.models.Category;
import com.example.demo.models.Order;
import com.example.demo.models.OrderItem;
import com.example.demo.models.Payment;
import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.models.enums.OrderStatus;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.OrderItemRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		User u1 = new User(null, "Joao", "joao@gmail.com", "11111111", "123456");
		User u2 = new User(null, "Pedro", "pedro@gmail.com", "22222222", "123456");
		User u3 = new User(null, "Marcos", "marcos@gmail.com", "33333333", "123456");
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		
		Product p1 = new Product(null,"TV", "Televisao LCD", 1300.0, "");
		Product p2 = new Product(null, "Radio", "Radio Bluetooth", 700.0, "");
		Product p3 = new Product(null, "Churrasqueira", "Churrasqueira Controle Remoto", 300.0, "");
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Category c1 = new Category(null, "Eletrônicos");
		Category c2 = new Category(null, "Domésticos");
		
		c1.getProducts().add(p1);
		c1.getProducts().add(p2);
		c1.getProducts().add(p3);
		c2.getProducts().add(p3);
		
		categoryRepository.saveAll(Arrays.asList(c1, c2));
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1, OrderStatus.DELIVERED);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2, OrderStatus.PAID);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1, OrderStatus.SHIPPED);
		
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
		o1.setPayment(pay1);
		
		orderRepository.save(o1);
		

		OrderItem oi1 = new OrderItem(p1, o1, 2, p1.getPrice()); 
		OrderItem oi2 = new OrderItem(p3, o1, 1, p3.getPrice()); 
		OrderItem oi3 = new OrderItem(p3, o2, 2, p3.getPrice()); 

		orderItemRepository.saveAll(Arrays.asList(oi1,oi2, oi3));
		
	}
}
