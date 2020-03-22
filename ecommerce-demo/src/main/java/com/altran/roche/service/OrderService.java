package com.altran.roche.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.altran.roche.model.Orders;
import com.altran.roche.model.Product;
import com.altran.roche.model.RocheOrder;
import com.altran.roche.model.RocheOrderDetails;
import com.altran.roche.model.RocheProduct;
import com.altran.roche.repository.OrderProductRepository;
import com.altran.roche.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderProductRepository orderProductRepository;

	@Transactional
	public Double processOrder(List<Product> products, String emailId) {

		RocheOrder order = new RocheOrder(emailId);
		UUID orderId = orderRepository.save(order).getOrderId();
		Double totalAmount = 0d;
		for (Product product : products) {
			RocheOrderDetails rocheOrderProduct = new RocheOrderDetails(orderId, product.getSku(),
					product.getQuantity(), product.getPrice());
			totalAmount += rocheOrderProduct.getPrice();
			orderProductRepository.save(rocheOrderProduct);
		}
		return totalAmount;
	}

	public List<Orders> searchOrders(String fromDate, String toDate) {

		LocalDateTime fromDateRange = LocalDateTime.parse(fromDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		LocalDateTime toDateRange = LocalDateTime.parse(toDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		List<RocheOrder> orders = orderRepository.findOrderIdByDateRange(fromDateRange, toDateRange);
		List<UUID> orderIds = orders.stream().map(o -> o.getOrderId()).collect(Collectors.toList());
		List<RocheOrderDetails> orderDetails = orderProductRepository.findOrders(orderIds);

		Map<UUID, List<RocheOrderDetails>> orderIdDetailsMapping = groupByOrderId(orderDetails);

		if (Optional.of(orders).isPresent()) {

			return orders.stream().map(o -> {

				List<RocheOrderDetails> detailsPerOrder = orderIdDetailsMapping.get(o.getOrderId());
				List<Product> products = detailsPerOrder.stream()
						.map(d -> new Product(d.getProductId(), null, d.getPrice(), d.getQuantity()))
						.collect(Collectors.toList());
				return new Orders(products, o.getBuyerEmailId());

			}).collect(Collectors.toList());

		}
		return null;

	}
 
	private Map<UUID, List<RocheOrderDetails>> groupByOrderId(List<RocheOrderDetails> orderIds) {
		Map<UUID, List<RocheOrderDetails>> orderIdDetailsMap = new HashMap<>();
		orderIds.forEach(o -> {
			if (orderIdDetailsMap.containsKey(o.getOrderId()))
				orderIdDetailsMap.get(o.getOrderId()).add(o);
			else {
				List<RocheOrderDetails> l = new ArrayList<>();
				l.add(o);
				orderIdDetailsMap.put(o.getOrderId(), l);
			}
		});
		return orderIdDetailsMap;
	}

}
