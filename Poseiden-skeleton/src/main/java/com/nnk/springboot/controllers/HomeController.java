package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.security.RolesAllowed;

@Controller
public class HomeController
{
	@RequestMapping("/")
	public String home()
	{
		return "login";
	}

//	@RequestMapping("/admin/home")
//	public String adminHome(Model model)
//	{
//		return "redirect:/bidList/list";
//	}
//
//	@GetMapping({ "/", "index" })
//	public String mainIndex() {
//
//		return "login";
//	}
//
//	@RequestMapping("home")
//	public String home(Model model)
//	{
//		model.addAttribute("errorMsg", "Message d erreur !");
//		return "home";
//	}

/*	@GetMapping({"/", "login"})
	@RolesAllowed({"USER", "ADMIN"})
	public String loginGet() {
		return "/bidList/list";
	}
*/
//	@GetMapping("/login")
//	@RolesAllowed({"USER"})
//	public String getUser() {
//		return "Welcome User !";
//	}
//	@GetMapping("user/*")
//	@RolesAllowed({"ADMIN"})
//	public String getAdmin() {
//		return "/user/list";
//	}
//    @GetMapping("/app-logout")
//    @RolesAllowed({"USER", "ADMIN"})
//    public ModelAndView logout() {
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("login");
//        return mav;
//    }

}
