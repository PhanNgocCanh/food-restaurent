package com.thuctaptotnghiep.food.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.thuctaptotnghiep.food.service.IOriginService;
import com.thuctaptotnghiep.food.service.IProductService;

@Controller(value = "clientProduct")
public class ProductController {
	@Autowired
	private IProductService productService;
	@Autowired
	private IOriginService originService;
	private String originCode = "";
	
	@GetMapping(value = {"/shop/{categoryCode}","/search/{categoryCode}"})
	public String getShop(@RequestParam(value = "page") int page,@RequestParam(value = "limit") int limit,
			@RequestParam(value = "originCode",required = false) String originCode,
			@RequestParam(value = "keyword",required = false) String keyword,
			@RequestParam(value = "maxprice",required = false,defaultValue = "0") long maxprice,Model model,
			@PathVariable("categoryCode") String categoryCode) {
		Pageable pageable = PageRequest.of(page-1, limit);
		model.addAttribute("page", page);
		model.addAttribute("limit", limit);
		if(originCode!=null&&originCode.equals("")) originCode = null;
		model.addAttribute("listProduct", productService.findAllWithSearchAndFilter(categoryCode, originCode,maxprice, null, pageable));
		model.addAttribute("totalPage", 
				(int)(Math.ceil((double)productService.getTotalItem()/limit)));
		model.addAttribute("origin", originService.findAll());
		model.addAttribute("categoryCode", categoryCode);
		model.addAttribute("originCode", originCode);
		model.addAttribute("maxPrice", maxprice);
		return "/client/pages/shop/shop";
	}
}
