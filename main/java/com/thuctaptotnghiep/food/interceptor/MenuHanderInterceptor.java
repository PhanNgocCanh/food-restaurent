package com.thuctaptotnghiep.food.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.thuctaptotnghiep.food.service.ICategoryService;

@Component
public class MenuHanderInterceptor implements HandlerInterceptor{
	@Autowired
	private ICategoryService categoryService;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setAttribute("category", categoryService.findAll());
		return true;
	}

	
}
