package com.thuctaptotnghiep.food.controller.admin;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.thuctaptotnghiep.food.dto.ProductDTO;
import com.thuctaptotnghiep.food.service.ICategoryService;
import com.thuctaptotnghiep.food.service.IOriginService;
import com.thuctaptotnghiep.food.service.IProductService;
import com.thuctaptotnghiep.food.service.IUnitService;
import com.thuctaptotnghiep.food.service.impl.StorageService;

@Controller
@RequestMapping("/admin")
public class ProductController {
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private IOriginService originService;
	@Autowired
	private IUnitService unitService;
	@Autowired
	private StorageService storageService;
	@Autowired
	private IProductService productService;
	private String categoryCode = "";
	private String originCode = "";
	
	@GetMapping(value = {"/add-product","/search"})
	public String addProduct(@RequestParam(value = "page") int page,@RequestParam(value = "limit") int limit,
			@RequestParam(value = "categoryCode",required = false) String categoryCode,
			@RequestParam(value = "originCode",required = false) String originCode,
			@RequestParam(value = "keyword",required = false) String keyword,Model model) {
		model.addAttribute("product", new ProductDTO());
		Pageable pageable = null;
		model.addAttribute("page", page);
		model.addAttribute("limit", limit);
		pageable = PageRequest.of(page-1, limit);
		if((categoryCode!=null&&!categoryCode.equals(""))||(originCode!=null&&!originCode.equals(""))
				||(keyword!=null&&!keyword.equals(""))) {							
			model.addAttribute("categoryCode", categoryCode);
			model.addAttribute("originCode", originCode);
			model.addAttribute("keyword", keyword);
			if(!this.categoryCode.equals(categoryCode)||!this.originCode.equals(originCode)) {
				model.addAttribute("page", 1);
				page = 1;
				this.categoryCode = categoryCode;
				this.originCode = originCode;				
			}
			pageable = PageRequest.of(page-1, limit);
			if(categoryCode.equals("")) categoryCode = null;
			if(originCode.equals("")) originCode = null;
			model.addAttribute("listProduct", productService.findAllWithSearchAndFilter(categoryCode,originCode,0,keyword,pageable));	
			model.addAttribute("totalPage", 
					(int)(Math.ceil((double)productService.getTotalItem()/limit)));
		}else{					
			model.addAttribute("listProduct", productService.findAll(pageable));			
			model.addAttribute("totalPage", (int)(Math.ceil((double)productService.getTotalItem()/limit)));
		}
		model.addAttribute("category", categoryService.findAll());
		model.addAttribute("origin", originService.findAll());
		model.addAttribute("unit", unitService.findAll());
		return "admin/pages/product/index";
	}

	@PostMapping("/add-product")
	public String postAddProduct(@Valid @ModelAttribute("product") ProductDTO product, BindingResult result,
			Model model, @RequestParam("page") int page,@RequestParam("limit") int limit,
			@RequestParam("imageFile") MultipartFile multipartFile) throws IOException {
		if (multipartFile.getOriginalFilename().isEmpty() && product.getImage().equals("")) {
			result.addError(new FieldError("product", "image", "Image phải chọn"));
		}		
		if (result.hasErrors()) {
			return backWithError(model,page,limit);
		}
		try {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			if (product.getImage().equals("")
					|| (!multipartFile.getOriginalFilename().isEmpty() && !product.getImage().equals(""))) {
				product.setImage(fileName);
				storageService.saveImage(multipartFile, fileName);
			}
			ProductDTO productDTO = productService.save(product);
			return "redirect:/admin/add-product?page=1&limit=4&success";
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return backWithError(model,page,limit);
		}
	}

	public String backWithError(Model model,int page,int limit) {
		model.addAttribute("category", categoryService.findAll());
		model.addAttribute("origin", originService.findAll());
		model.addAttribute("unit", unitService.findAll());
		Pageable pageable = PageRequest.of(page-1, limit);
		model.addAttribute("listProduct", productService.findAll(pageable));
		model.addAttribute("page", page);
		model.addAttribute("limit", limit);
		model.addAttribute("totalPage", (int)(Math.ceil((double)productService.getTotalItem()/limit)));
		model.addAttribute("error", "error");
		return "admin/pages/product/index";
	}

	@GetMapping("/edit-product/{productCode}")
	public String getEditProduct(Model model, @PathVariable("productCode") long productCode) {
		model.addAttribute("product", productService.findOne(productCode));	
		model.addAttribute("category", categoryService.findAll());
		model.addAttribute("origin", originService.findAll());
		model.addAttribute("unit", unitService.findAll());
		return "admin/pages/product/edit-product";
	}

	@GetMapping("/delete-product/{productCode}")
	public String deleteProduct(Model model,@PathVariable("productCode") long productCode) {
		productService.delete(productCode);
		return "redirect:/admin/add-product?page=1&limit=4&success";	
	}
}
