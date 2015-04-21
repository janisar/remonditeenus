package ee.iapb61.idu0200.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ee.iapb61.idu0200.model.bean.DeviceBean;
import ee.iapb61.idu0200.model.bean.ServiceOrderBean;
import ee.iapb61.idu0200.service.DeviceService;

@Controller
public class RepairController {
	
	@Autowired
	DeviceService deviceService;

	@RequestMapping(value="/repair")
	public ModelAndView repairHome(@ModelAttribute("serviceOrderBean") ServiceOrderBean serviceOrderBean, @ModelAttribute("deviceBean") DeviceBean deviceBean) {
		ModelAndView model = new ModelAndView("repair");
		
		deviceService.setAllDevices(deviceBean);
		deviceService.setAllDeviceTypes(deviceBean);
		
		System.out.println(deviceBean.getAllDevices().size());
		model.addObject("devices", deviceBean.getAllDevices());
		model.addObject("serviceOrderBean", serviceOrderBean);
		model.addObject("deviceTypes", deviceBean.getDeviceTypeBean().getDeviceTypes());
		return model;
	}
}
