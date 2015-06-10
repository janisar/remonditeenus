package ee.iapb61.idu0200.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import ee.iapb61.idu0200.factory.RequestFactory;
import ee.iapb61.idu0200.model.ServiceOrder;
import ee.iapb61.idu0200.model.ServiceRequest;
import ee.iapb61.idu0200.model.ServiceRequestStatusType;
import ee.iapb61.idu0200.model.bean.DeviceBean;
import ee.iapb61.idu0200.model.bean.ServiceOrderBean;
import ee.iapb61.idu0200.model.user.Customer;
import ee.iapb61.idu0200.model.user.Person;
import ee.iapb61.idu0200.model.user.UserAccount;
import ee.iapb61.idu0200.service.DeviceService;
import ee.iapb61.idu0200.service.PersonService;
import ee.iapb61.idu0200.service.RequestService;

@Controller
public class RequestController {

	@Autowired
	private PersonService personService;
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private DeviceService deviceService;
	
	@RequestMapping(value="/saveRequest", method=RequestMethod.POST)
	public @ResponseBody String saveOrder(HttpServletRequest request, @RequestParam("description") String description) {
		UserAccount person = personService.getCurrentPerson();
		
		Customer customer = personService.getCustomer(person.getSubject().getId());
		ServiceRequestStatusType statusType = requestService.getStatusTypeById(1);
		ServiceRequest serviceRequest = new RequestFactory().createNewRequest(description, customer, statusType);
		
		requestService.saveRequest(serviceRequest);
		
		return "OK";
		//return personService.getPerson();
	}
	
	@RequestMapping("/accept")
	public ModelAndView acceptRequest(@RequestParam("requestId") String requestId, 
			HttpServletRequest request, HttpServletResponse response) {
		ServiceOrderBean serviceOrderBean = (ServiceOrderBean) WebUtils.getSessionAttribute(request, "serviceOrderBean");
		DeviceBean deviceBean = serviceOrderBean.getDeviceBean();
		
		ModelAndView mav = new ModelAndView("partials/order");
		Person person = personService.getCurrentPerson().getSubject();

		
		ServiceRequest serviceRequest = requestService.getRequestById(requestId);
		requestService.setRequestToAccepted(person, serviceRequest);
		ServiceOrder serviceOrder = requestService.generateOrder(person, serviceRequest);
		

		deviceService.setAllDevices(deviceBean);
		deviceService.setAllDeviceTypes(deviceBean);
		
		mav.addObject("devices", deviceBean.getAllDevices());
		mav.addObject("deviceTypes", deviceBean.getDeviceTypeBean().getDeviceTypes());
		mav.addObject("serviceRequest", serviceRequest);
		return mav;
	}
	
	@RequestMapping("/decline")
	public String declineRequest(@RequestParam("requestId") String requestId) {
		Person person = personService.getCurrentPerson().getSubject();
		
		requestService.setRequestToDeclined(person, requestId);
		return "redirect:/requests";
	}
	
	@RequestMapping("/myRequests")
	public String userRequests(Model model) {
		Person person = personService.getCurrentPerson().getSubject();
		
		Customer customer = personService.getCurrentCustomer(person);
		List<ServiceRequest> requests = requestService.getAllRequestsByUser(customer);
		model.addAttribute("requests", requests);
		return "userRequests";
	}
}
