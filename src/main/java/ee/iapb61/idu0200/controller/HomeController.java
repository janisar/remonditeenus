package ee.iapb61.idu0200.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ee.iapb61.idu0200.model.bean.DeviceBean;

@Controller
public class HomeController {

	@RequestMapping(value={"/", "/welcome"})
	public String redirectToHome() {
		return "redirect:/home";
	}
	
	@RequestMapping(value="/home")
	public ModelAndView home(@ModelAttribute("deviceBean") DeviceBean deviceBean) {
		ModelAndView model = new ModelAndView("index");
		
		return model;
	}
}
