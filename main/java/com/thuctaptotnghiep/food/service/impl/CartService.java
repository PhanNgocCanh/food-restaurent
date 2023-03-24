package com.thuctaptotnghiep.food.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thuctaptotnghiep.food.dto.CartDTO;
import com.thuctaptotnghiep.food.service.IProductService;

@Service
public class CartService {
	@Autowired
	private IProductService productService;
	
	public long caculateTotalMoney(List<CartDTO> cart) {
		long totalMoney = 0;
		for(CartDTO c : cart) {
			totalMoney +=c.getTotalMoney();
		}
		return totalMoney;
	}
	
	public CartDTO checkExsitsProductCode(List<CartDTO> cart,CartDTO cartDTO) {
		CartDTO cartItem = null;
		for(CartDTO c : cart) {
			if(c.equals(cartDTO)) {
				cartItem = c;
			}
		}
		return cartItem;
	}
	
	public List<CartDTO> updateCart(List<CartDTO> cart,long productCode,int type,StringBuilder status){
		for(CartDTO c : cart) {
			if(c.getProductCode() == productCode) {
				int quantity = productService.findOne(productCode).getQuantity();
				if(c.getQuantity()+type>quantity||c.getQuantity()+type<=0) {
					status.append("update fail!");
					break;
				}
				c.setQuantity(c.getQuantity()+type);
			}
		}
		return cart;
	}
	
	public List<CartDTO> deleteItemCart(List<CartDTO> cart,long productCode){
		for(CartDTO c : cart) {
			if(c.getProductCode() == productCode) {
				cart.remove(c);
				break;
			}
		}
		return cart;
	}
}
