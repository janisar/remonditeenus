package ee.iapb61.idu0200.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import ee.iapb61.idu0200.model.bean.ServiceOrderBean;
import ee.iapb61.idu0200.model.user.Customer;
import ee.iapb61.idu0200.model.user.SubjectType;
import ee.iapb61.idu0200.model.user.UserAccount;
import ee.iapb61.idu0200.service.PersonService;

@Controller
public class HomeController {
	
	@Autowired
	private PersonService personService;

	@RequestMapping(value={"/", "/welcome"})
	public String redirectToHome() {
		return "redirect:/home";
	}
	
	@RequestMapping(value="/home")
	public ModelAndView home(@ModelAttribute("serviceOrderBean") ServiceOrderBean serviceOrderBean, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("index");
		UserAccount person = personService.getCurrentPerson();
		SubjectType subjectType = person.getSubjectType();
		model.addObject("subjectType", subjectType);
		
		List<Customer> customers = personService.getAllCustomers();
		
		for (Customer c : customers) {
			System.out.print(c.getSubject().getFirstName());
			System.out.println(c.getSubject().getLastName());
		}
		
		if (!WebUtils.hasSubmitParameter(request, "serviceOrderBean"))
			WebUtils.setSessionAttribute(request, "serviceOrderBean", serviceOrderBean);

		return model;
	}
}
