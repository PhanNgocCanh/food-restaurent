package com.thuctaptotnghiep.food.controller.admin;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.thuctaptotnghiep.food.dto.OrderDTO;
import com.thuctaptotnghiep.food.service.IOrderService;
import com.thuctaptotnghiep.food.service.IOrderStatusService;

@Controller(value = "OrderControllerOfAdmin")
@RequestMapping("/admin")
public class OrderController {
	@Autowired
	private IOrderService orderService;
	@Autowired
	private IOrderStatusService orderStatusService;
	
	@GetMapping("/order")
	public String getOrder(Model model,@RequestParam(value = "orderStatusCode",required = false) String orderStatusCode,
			@RequestParam("page") int page,@RequestParam("limit")int limit) {
	    Pageable pageable = PageRequest.of(page-1, limit);
	    model.addAttribute("page", page);
	    model.addAttribute("limit", limit);	    
	    if(orderStatusCode!=null&&!orderStatusCode.equals("")) {	    	
	    	List<OrderDTO> orderDTOs = orderService.findByOrderStatus(orderStatusCode,pageable);
	    	model.addAttribute("orderStatusCode", orderStatusCode);
	    	model.addAttribute("orders", orderDTOs);
	    }else {
	    	model.addAttribute("orders", orderService.findAll(pageable));	    	
	    }
	    model.addAttribute("totalPage", (int)(Math.ceil((double)orderService.getTotal()/limit)));
		model.addAttribute("orderStatus", orderStatusService.findAll());
		return "/admin/pages/order/order";
	}
	
	@PutMapping("/order/update/{orderCode}")
	@ResponseStatus(code = HttpStatus.OK, reason = "OK")
	public String updateStatusOrder(@PathVariable("orderCode") long orderCode,
			@RequestParam("orderStatusCode") String orderStautsCode) {
		OrderDTO orderDTO = orderService.findOne(orderCode);
		orderDTO.setOrderStatusCode(orderStautsCode);
		orderService.save(orderDTO);
		return "success";
	}
}
