package ee.iapb61.idu0200.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ee.iapb61.idu0200.dao.ClassifierDao;
import ee.iapb61.idu0200.model.ServiceRequest;
import ee.iapb61.idu0200.service.PersonService;
import ee.iapb61.idu0200.service.RequestService;

@Controller
public class AdminController {

	@Autowired
	RequestService requestService;
	
	@Autowired
	PersonService personService;
	
	@Autowired
	private ClassifierDao classifierDao;
	
	@RequestMapping(value="/requests", method = RequestMethod.GET)
	public ModelAndView showRequest() {
		ModelAndView mav = new ModelAndView("requests");
		List<ServiceRequest> requests = requestService.getAllRequests();
		mav.addObject("subjectType", personService.getCurrentPerson().getSubjectType());
		
		mav.addObject("requests", requests);
		return mav;
	}
	
}
