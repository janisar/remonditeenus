package ee.iapb61.idu0200.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import ee.iapb61.idu0200.model.Device;
import ee.iapb61.idu0200.model.DeviceFront;
import ee.iapb61.idu0200.model.DeviceType;
import ee.iapb61.idu0200.model.bean.DeviceBean;
import ee.iapb61.idu0200.model.bean.ServiceOrderBean;
import ee.iapb61.idu0200.model.ui.UiDeviceResult;
import ee.iapb61.idu0200.service.DeviceService;

@Controller
@SessionAttributes("serviceOrderBean")
public class DeviceController {

	@Autowired
	private DeviceService deviceService;
	
	@RequestMapping(value="/searchDevice", method=RequestMethod.GET)
	public ModelAndView search(HttpServletRequest request, @RequestParam("requestId") String requestId) {
		ModelAndView mav = new ModelAndView("partials/search");
		ServiceOrderBean serviceOrderBean = (ServiceOrderBean) WebUtils.getSessionAttribute(request, "serviceOrderBean");
		DeviceBean deviceBean = serviceOrderBean.getDeviceBean();
		
		mav.addObject("deviceTypes", deviceBean.getDeviceTypeBean().getDeviceTypes());
		mav.addObject("requestId", requestId);
		
		return mav;
	}
	
	@RequestMapping(value="/addDevice", method=RequestMethod.GET)
	public ModelAndView addDevice(HttpServletRequest request, @RequestParam("requestId") String requestId) {
		ModelAndView mav = new ModelAndView("partials/addDevice");
		ServiceOrderBean serviceOrderBean = (ServiceOrderBean) WebUtils.getSessionAttribute(request, "serviceOrderBean");
		DeviceBean deviceBean = serviceOrderBean.getDeviceBean();
		
		mav.addObject("deviceTypes", deviceBean.getDeviceTypeBean().getDeviceTypes());
		mav.addObject("requestId", requestId);
		
		return mav;
	}
	
	@RequestMapping(value="/searchDevice", method=RequestMethod.POST)
	public @ResponseBody UiDeviceResult saveDeviceToRequest(HttpServletRequest request,
			@RequestParam(value = "id", required=false) String id,
			@RequestParam(value= "name", required=false) String name, @RequestParam(value="model", required=false) String model,
			@RequestParam(value= "type", required=false) Integer type) {
		UiDeviceResult devices = new UiDeviceResult();
		devices.setDevices(new ArrayList<DeviceFront>());
		List<Device> deviceList = new ArrayList<Device>();
		int deviceId = 0;
		try {
			if (!StringUtils.isBlank(id))
				deviceId = Integer.valueOf(id);
		} catch(NumberFormatException ex) {
			String message = "Id peab olema numbri tüüpi";
			devices.setMessages(new ArrayList<String>());
			devices.getMessages().add(message);
			return devices;
		}
		
		Device device = new Device();
		ServiceOrderBean serviceOrderBean = (ServiceOrderBean) WebUtils.getSessionAttribute(request, "serviceOrderBean");
		
		DeviceType deviceType = deviceService.getDeviceTypeById(serviceOrderBean, type);
		device.setDeviceId(deviceId);
		device.setDeviceType(deviceType);
		device.setName(name);
		device.setModel(model);
		
		deviceList = deviceService.searchAllDevices(device, type);
		
		for (Device d : deviceList) {
			DeviceFront deviceFront = new DeviceFront(d.getDeviceId(), d.getName());
			deviceFront.setModel(d.getModel());
			deviceFront.setRegNr(d.getRegistrationNumber());
			devices.getDevices().add(deviceFront);
			
		}
		
		return devices;
	}
}
