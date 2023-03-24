package com.thuctaptotnghiep.food.controller.client;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.thuctaptotnghiep.food.dto.AccountDTO;
import com.thuctaptotnghiep.food.service.IAccountService;

@Controller
public class LoginController {
	@Autowired
	private IAccountService accountService;
	
	@GetMapping("/login")
	public String getLoginForm(HttpSession session) {
		session.removeAttribute("cart");
		return "/client/pages/login/login";
	}
	
	@GetMapping("/register")
	public String getRegisterForm(Model model) {
		model.addAttribute("account", new AccountDTO());
		return "/client/pages/login/register";
	}
	
	@PostMapping("/register")
	public String postRegister(@Valid @ModelAttribute("account") AccountDTO accountDTO,BindingResult result,Model model) {
		if(!accountDTO.getPasswordReEnter().equals(accountDTO.getPassword())) {
			result.addError(new FieldError("account", "passwordReEnter", "Mật khẩu không trùng khớp"));
		}
		if(result.hasErrors()) {
			return "/client/pages/login/register";
		}
		accountDTO.setStatus(1);
		accountDTO.setPassword(new BCryptPasswordEncoder().encode(accountDTO.getPassword()));
		accountService.save(accountDTO);
		return "redirect:/login";
	}
}
