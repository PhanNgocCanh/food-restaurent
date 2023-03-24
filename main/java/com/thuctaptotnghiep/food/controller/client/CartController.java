package com.thuctaptotnghiep.food.controller.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.thuctaptotnghiep.food.dto.CartDTO;
import com.thuctaptotnghiep.food.dto.ProductDTO;
import com.thuctaptotnghiep.food.service.IProductService;
import com.thuctaptotnghiep.food.service.impl.CartService;
import com.thuctaptotnghiep.food.utils.SessionUtils;

@Controller
public class CartController {
	@Autowired
	private IProductService productService;
	@Autowired
	private CartService cartService;
	@Autowired
	private ModelMapper modelMapper;
	@GetMapping("/cart")
	public String getCart(HttpServletRequest request,Model model) {
		List<CartDTO> cart = (List<CartDTO>)SessionUtils.getInstance().getValue(request, "cart");
		if(cart == null||cart.size()==0) {
			model.addAttribute("nocartmessage", "Bạn chưa có sản phẩm nào trong giỏ cả !");
		}else {	
			Map<String, ?> status = RequestContextUtils.getInputFlashMap(request);
			if(status!=null) model.addAttribute("status", status.get("updateFail").toString());
			model.addAttribute("totalMoney", cartService.caculateTotalMoney(cart));
			model.addAttribute("cart", cart);
		}
		return "/client/pages/cart/cart";
	}

	@GetMapping("/add-cart/{productCode}")
	public String addToCart(@PathVariable("productCode") long productCode, HttpServletRequest request)
			throws MalformedURLException {
		String referer = request.getHeader("referer");
		URL url = new URL(referer);
		ProductDTO productDTO = productService.findOne(productCode);
		if(productDTO == null) {
			return "redirect:" + url.toString();
		}
		List<CartDTO> cart = null;
		List<CartDTO> cartExsits = (List<CartDTO>)SessionUtils.getInstance().getValue(request, "cart");
		if(cartExsits == null) {
			cart = new ArrayList<>();
		}else {
			cart = cartExsits;
		}
		CartDTO cartDTO = modelMapper.map(productDTO, CartDTO.class);
		CartDTO cartItem = cartService.checkExsitsProductCode(cart, cartDTO);
		if(cartItem == null) {
			cartDTO.setQuantity(1);
			cart.add(cartDTO);			
		}else {
			cartItem.setQuantity(cartItem.getQuantity()+1);
		}
		SessionUtils.getInstance().putValue(request, "cart", cart);
		return "redirect:" + url.toString();
	}
	

	@GetMapping("/cart/update/{productCode}")
	public String updateCart(HttpServletRequest request,RedirectAttributes redirectAttributes,
			@PathVariable("productCode") long productCode,@RequestParam("type") int type) {
		List<CartDTO> cart = (List<CartDTO>)SessionUtils.getInstance().getValue(request, "cart");
		StringBuilder status = new StringBuilder("");
		cart = cartService.updateCart(cart, productCode, type, status);
		if(status.toString().equals("update fail!")) {
			redirectAttributes.addFlashAttribute("updateFail", "Cập nhật sản phẩm không thành công");
		}
		return "redirect:/cart";
	}
	
	@GetMapping("/cart/delete/{productCode}")
	public String deleteCart(HttpServletRequest request,@PathVariable("productCode") long productCode) {
		List<CartDTO> cart = (List<CartDTO>)SessionUtils.getInstance().getValue(request, "cart");
		cartService.deleteItemCart(cart, productCode);
		return "redirect:/cart";
	}
	
}
