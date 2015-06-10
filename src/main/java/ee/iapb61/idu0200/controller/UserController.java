package ee.iapb61.idu0200.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import ee.iapb61.idu0200.dao.ClassifierDao;
import ee.iapb61.idu0200.model.ServiceActionStatusType;
import ee.iapb61.idu0200.model.ServiceDeviceStatusType;
import ee.iapb61.idu0200.model.ServiceOrder;
import ee.iapb61.idu0200.model.ServiceRequest;
import ee.iapb61.idu0200.model.ServiceRequestStatusType;
import ee.iapb61.idu0200.model.ServiceType;
import ee.iapb61.idu0200.model.bean.DeviceBean;
import ee.iapb61.idu0200.model.bean.ServiceOrderBean;
import ee.iapb61.idu0200.service.DeviceService;
import ee.iapb61.idu0200.service.OrderService;
import ee.iapb61.idu0200.service.RequestService;

@Controller
public class UserController {

	@Autowired
	private RequestService requestService;
	
	@Autowired
	DeviceService deviceService;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ClassifierDao classifierDao;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {
 
	  ModelAndView model = new ModelAndView();
	  if (error != null) {
		model.addObject("error", "Invalid username and password!");
	  }
 
	  if (logout != null) {
		model.addObject("msg", "You've been logged out successfully.");
	  }
	  model.setViewName("login");
 
	  return model;
 
	}
	
	
	@RequestMapping(value = "/userDecline", method = RequestMethod.GET)
	public String decline(@RequestParam(value = "requestId", required = true) String requestId) {
		
		ServiceRequest request = requestService.getRequestById(requestId);
		ServiceRequestStatusType statusType = requestService.getStatusTypeById(4);
		request.setServiceRequestStatusType(statusType);
		requestService.saveRequest(request);
		return "redirect:/userRequests";
	}
	
	@RequestMapping(value = "/getRequest", method = RequestMethod.GET)
	public ModelAndView getRequest(HttpServletRequest req, @RequestParam(value = "requestId", required = true) String requestId) {
		ModelAndView mav = new ModelAndView("partials/singleRequest");
		
		List<ServiceDeviceStatusType> devicesTypes = orderService.getServiceDeviceClassifiers();
		List<ServiceRequestStatusType> requestStatusTypes = classifierDao.getServiceRequestStatusTypes();
		
		mav.addObject("statusTypes", requestStatusTypes);
		mav.addObject("deviceStatuses", devicesTypes);
		mav.addObject("requestId", requestId);
		if (!NumberUtils.isNumber(requestId)) {
			return new ModelAndView("error/error");
		}
		ServiceOrder order = orderService.getOrderByRequestId(requestId);
		
		List<ServiceActionStatusType> statusTypes = classifierDao.getServiceActionStatusTypes();
		mav.addObject("serviceActionStatusTypes", statusTypes);
		
		List<ServiceType> serviceTypes = classifierDao.getServiceTypes();
		mav.addObject("serviceTypes", serviceTypes);
		if (order != null) {
			mav.addObject("orderDevices", order.getServiceDevices());
			ServiceRequest request = order.getServiceRequest();
			mav.addObject("request", request);
			mav.addObject("orderParts", order.getServiceParts());
			mav.addObject("price", order.getTotalPrice());
			mav.addObject("initialCall", false);
			mav.addObject("orderJobs", order.getServiceAction());
		} else {
			ServiceRequest request = requestService.getRequestById(requestId);
			mav.addObject("request", request);
			mav.addObject("initialCall", true);
		}
		
		ServiceOrderBean serviceOrderBean = (ServiceOrderBean) WebUtils.getSessionAttribute(req, "serviceOrderBean");
		DeviceBean deviceBean = serviceOrderBean.getDeviceBean();
		
		
		deviceService.setAllDevices(deviceBean);
		deviceService.setAllDeviceTypes(deviceBean);
		
		mav.addObject("devices", deviceBean.getAllDevices());
		mav.addObject("deviceTypes", deviceBean.getDeviceTypeBean().getDeviceTypes());
		return mav;
	}
}
