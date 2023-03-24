package com.thuctaptotnghiep.food.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thuctaptotnghiep.food.dto.CartDTO;
import com.thuctaptotnghiep.food.dto.OrderDetailDTO;
import com.thuctaptotnghiep.food.entity.Order;
import com.thuctaptotnghiep.food.entity.OrderDetail;
import com.thuctaptotnghiep.food.entity.Product;
import com.thuctaptotnghiep.food.entity.ProductOrderKey;
import com.thuctaptotnghiep.food.repository.OrderRepository;
import com.thuctaptotnghiep.food.repository.ProductRepository;

@Component
public class OrderDetailConverter {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private OrderRepository orderRepository;
	
	public OrderDetailDTO toDTO(CartDTO cart,long orderCode) {
		OrderDetailDTO orderDetail = new OrderDetailDTO();
		orderDetail.setOrderCode(orderCode);
    	orderDetail.setProductCode(cart.getProductCode());
    	orderDetail.setQuantity(cart.getQuantity());
    	orderDetail.setPrice(cart.getPrice());
    	orderDetail.setDiscount(cart.getDiscount());
    	orderDetail.setIntoMoney(cart.getTotalMoney());
    	return orderDetail;
	}
	public OrderDetailDTO toDTO(OrderDetail orderDetail) {
		OrderDetailDTO orderDetailDTO = modelMapper.map(orderDetail, OrderDetailDTO.class);
		orderDetailDTO.setOrderCode(orderDetail.getProductOrderKey().getOrderCode());
	    orderDetailDTO.setProductCode(orderDetail.getProductOrderKey().getOrderCode());
    	return orderDetailDTO;
	}
	public OrderDetail toEntity(OrderDetailDTO orderDetailDTO) {
		OrderDetail orderDetail = modelMapper.map(orderDetailDTO, OrderDetail.class);
		ProductOrderKey productOrderKey = new ProductOrderKey();
		productOrderKey.setOrderCode(orderDetailDTO.getOrderCode());
		productOrderKey.setProductCode(orderDetailDTO.getProductCode());
		Order order = orderRepository.findOneByOrderCode(orderDetailDTO.getOrderCode());
		Product product = productRepository.findOneByProductCode(orderDetailDTO.getProductCode());
		orderDetail.setOrder(order);
		orderDetail.setProduct(product);
		orderDetail.setProductOrderKey(productOrderKey);
		return orderDetail;
	}
}
