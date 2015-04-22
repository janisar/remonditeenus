package ee.iapb61.idu0200.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ee.iapb61.idu0200.model.Device;
import ee.iapb61.idu0200.model.ServiceDevice;
import ee.iapb61.idu0200.model.ServiceOrder;
import ee.iapb61.idu0200.model.bean.ServiceOrderBean;
import ee.iapb61.idu0200.service.DeviceService;
import ee.iapb61.idu0200.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private DeviceService deviceService;
	
	@RequestMapping(value="/addDevice", method=RequestMethod.POST)
	public @ResponseBody String addDeviceToRequest(@ModelAttribute("serviceOrderBean") final ServiceOrderBean serviceOrderBean,
			@RequestParam("deviceId") String deviceId) {
		ObjectMapper mapper = new ObjectMapper();
		Device device = deviceService.getDeviceById(deviceId);
		ServiceDevice sDevice = new ServiceDevice();
		sDevice.setDevice(device);
		
		serviceOrderBean.setHello("Hello");
		ServiceOrder order = serviceOrderBean.getServiceOrder();
		if (order == null) {
			order = new ServiceOrder();
			serviceOrderBean.setServiceOrder(order);
		}
		List<ServiceDevice> devices = order.getServiceDevices();
		if (devices == null) {
			devices = new ArrayList<ServiceDevice>();
		}
		devices.add(sDevice);
		serviceOrderBean.getServiceOrder().setServiceDevices(devices);
		
		try {
			return mapper.writeValueAsString(device);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
