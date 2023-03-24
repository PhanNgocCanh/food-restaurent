package com.thuctaptotnghiep.food.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.thuctaptotnghiep.food.dto.OrderDTO;
import com.thuctaptotnghiep.food.service.IOrderDetailService;
import com.thuctaptotnghiep.food.service.IOrderService;
import com.thuctaptotnghiep.food.service.IOrderStatusService;
import com.thuctaptotnghiep.food.service.impl.ShowUserDetail;

@Controller
public class OrderTrackController {
	@Autowired
	private IOrderService orderService;
	@Autowired
	private IOrderDetailService orderDetailService;
	@Autowired
	private IOrderStatusService orderStatusService;
	
	@GetMapping("/order-tracking")
	public String getOrderTracking(Model model) {
		ShowUserDetail user = (ShowUserDetail)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("orders", orderService.findByAccount(user.getUsername()));
		model.addAttribute("orderStatus", orderStatusService.findAll());
		return "/client/pages/order-tracking/order-tracking";
	}
	
	@GetMapping("/delete-order/{orderCode}")
	public String deleteOrder(@PathVariable("orderCode") long orderCode) {
		OrderDTO order = orderService.findOne(orderCode);
		if(order == null) {
			return "redirect:/order-tracking";
		}
		orderDetailService.deleteAllByOrderCode(orderCode);
		orderService.delete(orderCode);
		return "redirect:/order-tracking";
	}
}
