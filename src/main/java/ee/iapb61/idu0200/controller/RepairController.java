package ee.iapb61.idu0200.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import ee.iapb61.idu0200.model.bean.DeviceBean;
import ee.iapb61.idu0200.model.bean.ServiceOrderBean;
import ee.iapb61.idu0200.service.DeviceService;

@Controller
@SessionAttributes("serviceOrderBean")
public class RepairController {
	
	@Autowired
	DeviceService deviceService;

	@RequestMapping(value="/repair")
	public ModelAndView repairHome(HttpServletRequest request, HttpServletResponse response) {
		ServiceOrderBean serviceOrderBean = (ServiceOrderBean) WebUtils.getSessionAttribute(request, "serviceOrderBean");
		DeviceBean deviceBean = serviceOrderBean.getDeviceBean();
		
		ModelAndView model = new ModelAndView("repair");
		
		deviceService.setAllDevices(deviceBean);
		deviceService.setAllDeviceTypes(deviceBean);
		
		model.addObject("devices", deviceBean.getAllDevices());
		model.addObject("deviceTypes", deviceBean.getDeviceTypeBean().getDeviceTypes());
		return model;
	}
}
