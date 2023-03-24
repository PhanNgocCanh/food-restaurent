package com.thuctaptotnghiep.food.controller.client;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thuctaptotnghiep.food.converter.OrderDetailConverter;
import com.thuctaptotnghiep.food.dto.CartDTO;
import com.thuctaptotnghiep.food.dto.OrderDTO;
import com.thuctaptotnghiep.food.dto.OrderDetailDTO;
import com.thuctaptotnghiep.food.dto.ProductDTO;
import com.thuctaptotnghiep.food.service.IOrderDetailService;
import com.thuctaptotnghiep.food.service.IOrderService;
import com.thuctaptotnghiep.food.service.IProductService;
import com.thuctaptotnghiep.food.service.impl.CartService;
import com.thuctaptotnghiep.food.service.impl.ShowUserDetail;
import com.thuctaptotnghiep.food.utils.SessionUtils;

@Controller
public class CheckOutController {
	@Autowired
	private CartService cartService;
	@Autowired
	private IOrderService orderService;
	@Autowired
	private IOrderDetailService orderDetailService;
	@Autowired
	private OrderDetailConverter orderDetailConverter;
	@Autowired
	private IProductService productService;
	
	@GetMapping("/check-out")
	public String getCheckOutForm(HttpServletRequest request,Model model) {
		List<CartDTO> cart = (List<CartDTO>)SessionUtils.getInstance().getValue(request, "cart");
		model.addAttribute("cart", cart);
		model.addAttribute("totalMoney", cartService.caculateTotalMoney(cart));
		model.addAttribute("order", new OrderDTO());
		return "/client/pages/cart/checkout";
	}
	
	@PostMapping("/check-out")
	public String postCheckOutForm(HttpServletRequest request,@Valid @ModelAttribute("order") OrderDTO order,
			BindingResult result,Model model,RedirectAttributes redirectAttributes) {
		List<CartDTO> cart = (List<CartDTO>)SessionUtils.getInstance().getValue(request, "cart");
		if(result.hasErrors()) {
			model.addAttribute("cart", cart);
			model.addAttribute("totalMoney", cartService.caculateTotalMoney(cart));
			return "/client/pages/cart/checkout";
		}
		ShowUserDetail user = (ShowUserDetail)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		order.setEmail(user.getUsername());
		order.setOrderStatusCode("OS01");
		order.setOrderDate(new Date());
		order.setTotalMoney(cartService.caculateTotalMoney(cart));
		long orderCode = orderService.save(order);
        for(CartDTO c : cart) {
        	OrderDetailDTO orderDetail = orderDetailConverter.toDTO(c, orderCode);
        	ProductDTO productDTO = productService.findOne(c.getProductCode());
        	productDTO.setQuantity(productDTO.getQuantity()-c.getQuantity());
        	productService.save(productDTO);
        	orderDetailService.save(orderDetail);
        }
        redirectAttributes.addFlashAttribute("checkOutSuccess", "Bạn đã đặt hàng thành công");
        SessionUtils.getInstance().removeValue(request, "cart");
		return "redirect:/";
	}
}
