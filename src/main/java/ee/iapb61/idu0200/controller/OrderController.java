package ee.iapb61.idu0200.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ee.iapb61.idu0200.model.ServiceDevice;
import ee.iapb61.idu0200.model.bean.ServiceOrderBean;
import ee.iapb61.idu0200.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	
	@RequestMapping(value="/addDevice", method=RequestMethod.POST)
	public String addDeviceToRequest(@ModelAttribute("serviceOrderBean") ServiceOrderBean serviceOrderBean,
			@RequestParam("deviceId") String deviceId) {
		List<ServiceDevice> serviceDevices = orderService.getServiceDevices(serviceOrderBean);
		System.out.println("HELLOOOO");
		orderService.addNewDevice(serviceDevices, deviceId);
		return "Success";
	}
}
