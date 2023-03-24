package com.thuctaptotnghiep.food.controller.client;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.thuctaptotnghiep.food.service.IProductService;

@Controller(value = "clientControler")
public class HomeController {
	@Autowired
	private IProductService productService;
	@GetMapping("/")
	public String getHomeClient(HttpServletRequest request,Model model) {
		Map<String, ?> status = RequestContextUtils.getInputFlashMap(request);
		if(status!=null) model.addAttribute("status", status.get("checkOutSuccess").toString());
		model.addAttribute("listProduct", productService.findAll());
		return "/client/pages/home/index";
	}
	
}
