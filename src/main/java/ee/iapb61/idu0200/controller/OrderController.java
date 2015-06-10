package ee.iapb61.idu0200.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import ee.iapb61.idu0200.dao.ClassifierDao;
import ee.iapb61.idu0200.model.Device;
import ee.iapb61.idu0200.model.DeviceType;
import ee.iapb61.idu0200.model.ServiceAction;
import ee.iapb61.idu0200.model.ServiceActionFront;
import ee.iapb61.idu0200.model.ServiceDevice;
import ee.iapb61.idu0200.model.ServiceDeviceStatusType;
import ee.iapb61.idu0200.model.ServiceOrder;
import ee.iapb61.idu0200.model.ServicePart;
import ee.iapb61.idu0200.model.ServicePartFront;
import ee.iapb61.idu0200.model.ServiceRequest;
import ee.iapb61.idu0200.model.ServiceRequestStatusType;
import ee.iapb61.idu0200.model.ServiceType;
import ee.iapb61.idu0200.model.bean.DeviceBean;
import ee.iapb61.idu0200.model.bean.ServiceOrderBean;
import ee.iapb61.idu0200.service.DeviceService;
import ee.iapb61.idu0200.service.OrderService;
import ee.iapb61.idu0200.service.PersonService;
import ee.iapb61.idu0200.service.RequestService;

@Controller
@SessionAttributes("serviceOrderBean")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private ClassifierDao classifierDao;
	
	@Autowired
	private RequestService requestService;
	
//	@RequestMapping(value="/saveDevice", method=RequestMethod.POST)
//	public @ResponseBody DeviceFront saveDeviceToRequest(
//			@RequestParam(value="requestId") String requestId,
//			@RequestParam(value="deviceId",required=false) Integer deviceTypeId, 
//			@RequestParam(value="newDevice.name", required=true) String name,
//			@RequestParam(value="newDevice.description", required=true) String description,
//			@RequestParam(value="newDevice.model", required=true) String model,
//			@RequestParam(value="newDevice.registrationNumber", required=true) String regNr,
//			@RequestParam(value="newDevice.manufacturer", required=true) String manufacturer,
//			HttpServletRequest request, HttpServletResponse response) {
//		ServiceOrder serviceOrder = orderService.getOrderByRequestId(requestId);
//		Device device = createDevice(name, description, model, regNr,
//				manufacturer);
//		
//		ServiceOrderBean serviceOrderBean = (ServiceOrderBean) WebUtils.getSessionAttribute(request, "serviceOrderBean");
//		DeviceType deviceType = deviceService.getDeviceTypeById(serviceOrderBean, deviceTypeId);
//		
//		device.setDeviceType(deviceType);
//		
//		ServiceDevice sDevice = new ServiceDevice();
//		sDevice.setDevice(device);
//		deviceService.saveDevice(device);
//		deviceService.saveServiceDevice(sDevice);
//		
//		List<ServiceDevice> serviceDevices = serviceOrder.getServiceDevices();
//		if (serviceDevices == null) {
//			serviceDevices = new ArrayList<ServiceDevice>();
//		}
//		serviceDevices.add(sDevice);
//		serviceOrder.setServiceDevices(serviceDevices);
//		orderService.updateOrder(serviceOrder, personService.getCurrentPerson().getSubject());
//		
//		return new DeviceFront(device.getDeviceId(), device.getName());
//	}
	
	
	@RequestMapping(value="/updateRequestStatus", method=RequestMethod.POST)
	public @ResponseBody String updateRequestStatus(@RequestParam("requestId") String requestId,
			@RequestParam("status") Integer status) {
		ServiceRequest request = requestService.getRequestById(requestId);
		ServiceRequestStatusType type = classifierDao.getRequestStatusTypeById(status);
		
		request.setServiceRequestStatusType(type);
		
		requestService.updateServiceRequest(request);
		
		return "ok";
	}
			
	@RequestMapping(value="/saveDevice", method=RequestMethod.POST)
	public ModelAndView saveDeviceToRequest(
			@RequestParam(value="requestId") String requestId,
			@RequestParam(value="deviceId",required=false) Integer deviceTypeId, 
			@Valid Device newDevice, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		
		ServiceOrderBean serviceOrderBean = (ServiceOrderBean) WebUtils.getSessionAttribute(request, "serviceOrderBean");
		DeviceBean deviceBean = serviceOrderBean.getDeviceBean();
		
		ServiceOrder serviceOrder = orderService.getOrderByRequestId(requestId);
		if (result.hasErrors()) {
			 mav = new ModelAndView("partials/addDevice");
			 mav.addObject("requestId", requestId);
			 mav.addObject("errors", result.getAllErrors());
			 mav.addObject("newDevice", newDevice);
			 mav.addObject("deviceTypes", deviceBean.getDeviceTypeBean().getDeviceTypes());
			 mav.addObject("requestId", requestId);
			return mav;
		} else {
			mav = new ModelAndView("partials/singleRequest");
			mav.addObject("newDevice", newDevice);
			
			List<ServiceType> serviceTypes = classifierDao.getServiceTypes();
			List<ServiceDeviceStatusType> devicesTypes = orderService.getServiceDeviceClassifiers();
			mav.addObject("deviceStatuses", devicesTypes);
			mav.addObject("requestId", requestId);
			
			mav.addObject("serviceTypes", serviceTypes);
			if (serviceOrder != null) {
				mav.addObject("orderDevices", serviceOrder.getServiceDevices());
				ServiceRequest req = serviceOrder.getServiceRequest();
				mav.addObject("request", req);
				mav.addObject("orderParts", serviceOrder.getServiceParts());
				mav.addObject("price", serviceOrder.getTotalPrice());
				mav.addObject("initialCall", false);
				mav.addObject("orderJobs", serviceOrder.getServiceAction());
			} else {
				ServiceRequest req = requestService.getRequestById(requestId);
				mav.addObject("request", req);
				mav.addObject("initialCall", true);
			}
		}
		DeviceType deviceType = deviceService.getDeviceTypeById(serviceOrderBean, deviceTypeId);
		
		newDevice.setDeviceType(deviceType);
		
		ServiceDevice sDevice = new ServiceDevice();
		sDevice.setDevice(newDevice);
		deviceService.saveDevice(newDevice);
		deviceService.saveServiceDevice(sDevice);
		
		List<ServiceDevice> serviceDevices = serviceOrder.getServiceDevices();
		if (serviceDevices == null) {
			serviceDevices = new ArrayList<ServiceDevice>();
		}
		serviceDevices.add(sDevice);
		serviceOrder.setServiceDevices(serviceDevices);
		orderService.updateOrder(serviceOrder, personService.getCurrentPerson().getSubject());
		
		return mav;
	}
	
	@RequestMapping(value="/addDevice", method=RequestMethod.POST)
	public @ResponseBody String addDeviceToRequest(@RequestParam("requestId") String requestId,
			@RequestParam("deviceId") String deviceId) {
		ServiceOrder serviceOrder = orderService.getOrderByRequestId(requestId);
		
		if (serviceOrder == null) {
			 serviceOrder = new ServiceOrder();
			 ServiceRequest serviceRequest = requestService.getRequestById(requestId);
			 serviceOrder.setCreatedBy(personService.getCurrentPerson().getSubject());
			 serviceOrder.setServiceRequest(serviceRequest);
		} else {
			//serviceOrder.setUpdatedBy(personService.getCurrentPerson().getSubject());
		}
		ObjectMapper mapper = new ObjectMapper();
		Device device = deviceService.getDeviceById(deviceId);
		ServiceDevice sDevice = new ServiceDevice();
		sDevice.setDevice(device);
		sDevice.setServiceOrder(serviceOrder);
		
		deviceService.saveServiceDevice(sDevice);
		List<ServiceDevice> serviceDevices = serviceOrder.getServiceDevices();
		if (serviceDevices == null) {
			serviceDevices = new ArrayList<ServiceDevice>();
		}
		serviceDevices.add(sDevice);
		serviceOrder.setServiceDevices(serviceDevices);
		orderService.updateOrder(serviceOrder, personService.getCurrentPerson().getSubject());
		try {
			return mapper.writeValueAsString(device);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Device createDevice(String name, String description, String model,
			String regNr, String manufacturer) {
		Device device = new Device();
		device.setName(name);
		device.setManufacturer(manufacturer);
		device.setModel(model);
		device.setRegistrationNumber(regNr);
		device.setDescription(description);
		return device;
	}
	
	@RequestMapping(value="/saveOrder", method=RequestMethod.POST)
	public @ResponseBody String saveDeviceToRequest(@RequestParam("devices") JSONArray devices, 
			@RequestParam("requestId") String requestId,
			@RequestParam("description") String description,
			@RequestParam("initialCall") boolean initialCall, HttpServletRequest req2) throws JSONException {
		ServiceOrder serviceOrder = orderService.getOrderByRequestId(requestId);
		
		
		
		ServiceRequest serviceRequest = requestService.getRequestById(requestId);
		serviceRequest.setServiceDescriptionByEmployee(description);
		requestService.saveRequest(serviceRequest);

		if (serviceOrder == null) {
			 serviceOrder = new ServiceOrder();
			 serviceOrder.setServiceRequest(serviceRequest);
			 serviceOrder.setCreatedBy(personService.getCurrentPerson().getSubject());
		} else {
			serviceOrder.setUpdatedBy(personService.getCurrentPerson().getSubject());
		}
		
		List<ServiceDevice> deviceList = new ArrayList<ServiceDevice>();
		for (int i = 0; i < devices.length(); i++) {
			ServiceDevice serviceDevice = new ServiceDevice();
			int deviceId = devices.getInt(i);
			Device device = deviceService.getDeviceById(Integer.toString(deviceId));
			serviceDevice.setDevice(device);
			serviceDevice.setServiceOrder(serviceOrder);
			orderService.saveOrderDevice(serviceDevice);
			deviceList.add(serviceDevice);
		}
		serviceOrder.setServiceDevices(deviceList);
		
		if (!initialCall) {
			serviceOrder.setUpdatedDate(new Date());
			orderService.updateOrder(serviceOrder, personService.getCurrentPerson().getSubject());
		} else {
			serviceOrder.setCreatedDate(new Date());
			orderService.saveOrder(serviceOrder);
		}
		return "ok";
	}
	
	@RequestMapping(value="/deleteServiceDevice", method=RequestMethod.POST)
	public @ResponseBody String deleteServiceDevice(@RequestParam(value="requestId") String requestId,
			@RequestParam(value="deviceId",required=true) Integer deviceId) {
		ServiceDevice device = orderService.getServiceDeviceById(deviceId);
		boolean delete = false;
		if (device.getParts() == null || device.getParts().size() == 0) {
			orderService.deleteServiceDevice(device);
			delete = true;
		} else {
			return "fail";
		}
		ServiceOrder serviceOrder = orderService.getOrderByRequestId(requestId);
		List<ServiceDevice> serviceDevices = serviceOrder.getServiceDevices();
		for (int i = 0; i < serviceDevices.size(); i++) {
			if (serviceDevices.get(i).getId() == deviceId) {
				if (serviceDevices.get(i).getParts() == null || serviceDevices.get(i).getParts().size() == 0) {
					delete = true;
					serviceDevices.remove(i);
				}
			}
		}
		if (delete) {
			serviceOrder.setServiceDevices(serviceDevices);
			orderService.updateOrder(serviceOrder, personService.getCurrentPerson().getSubject());
			return "ok";
		} else {
			return "fail";
		}
	}
	
	@RequestMapping(value="/deleteServicePart", method=RequestMethod.POST)
	public @ResponseBody String deleteServicePart(@RequestParam(value="servicePartId") Integer servicePartId,
			@RequestParam(value="requestId",required=true) String requestId) {
		ServicePart servicePart = orderService.getServicePartById(servicePartId);
		double priceToRemove = servicePart.getPartCount() * servicePart.getPartPrice();
		
		orderService.deleteServicePart(servicePart);
		ServiceOrder serviceOrder = orderService.getOrderByRequestId(requestId);
		List<ServicePart> serviceParts = serviceOrder.getServiceParts();
		for (int i = 0; i < serviceParts.size(); i++) {
			System.out.println(serviceParts.get(i).getId() + "  " + servicePartId.intValue());
			if (serviceParts.get(i).getId() == servicePartId.intValue()) {
				priceToRemove = serviceParts.get(i).getPartPrice().doubleValue();
				serviceParts.remove(i);
			}
		}
		
		double currentPrice = serviceOrder.getTotalPrice() == null ? 0 : serviceOrder.getTotalPrice();
		double newPrice = currentPrice - priceToRemove;
		serviceOrder.setServiceParts(serviceParts);
		serviceOrder.setTotalPrice(currentPrice - priceToRemove);
		orderService.updateOrder(serviceOrder, null);
		return Double.toString(newPrice);
	}
	
	@RequestMapping(value="/updateServiceDeviceStatus", method=RequestMethod.POST)
	public @ResponseBody String updateServiceDeviceStatus(@RequestParam(value="deviceId") String deviceId,
			@RequestParam(value="status",required=true) Integer status) {
		
		ServiceDevice serviceDevice = orderService.getServiceDeviceById(Integer.valueOf(deviceId));
		ServiceDeviceStatusType statusType = orderService.getServiceDeviceStatusTypeById(status);
		serviceDevice.setServiceDeviceStatusType(statusType);
		orderService.updateServiceDevice(serviceDevice);
		
		return deviceId.toString();
	}
	
	@RequestMapping(value="/updateServicePart", method=RequestMethod.POST)
	public @ResponseBody String updateServicePart(@RequestParam(value="deviceId") Integer deviceId,
			@RequestParam(value="partId",required=true) Integer partId) {
		ServicePart servicePart = orderService.getServicePartById(partId);
		ServiceDevice serviceDevice = orderService.getServiceDeviceById(deviceId);
		
		servicePart.setServiceDevice(serviceDevice);
		orderService.updateServicePart(servicePart);
		return "ok";
	}
	
	@RequestMapping(value="/updateServiceJob", method=RequestMethod.POST)
	public @ResponseBody String updateServiceJob(@RequestParam(value="deviceId") Integer deviceId,
			@RequestParam(value="jobId",required=true) Integer jobId) {
		ServiceAction serviceAction = orderService.getServiceActionById(jobId);
		ServiceDevice serviceDevice = orderService.getServiceDeviceById(deviceId);
		
		serviceAction.setServiceDevice(serviceDevice);
		orderService.updateServiceAction(serviceAction);
		return "ok";
	}
	
	@RequestMapping(value="/saveJobPrice", method=RequestMethod.POST)
	public @ResponseBody Double updatePriceJob(@RequestParam(value="requestId") String requestId, @RequestParam(value="jobId") Integer jobId,
			@RequestParam(value="newPrice",required=true) float price, @RequestParam(value="newAmount") Integer newAmount) {
		Double newPrice = new Double(price);
		ServiceAction serviceAction = orderService.getServiceActionById(jobId);
		int quantity = serviceAction.getAmount().intValue();
		Double previousActionPrice = serviceAction.getPrice() * quantity;
		serviceAction.setPrice(newPrice);
		serviceAction.setAmount(newAmount);
		orderService.updateServiceAction(serviceAction);
		
		ServiceOrder serviceOrder = orderService.getOrderByRequestId(requestId);
		Double previousOrderPrice = serviceOrder.getTotalPrice();
		Double newActionPrice = serviceAction.getPrice() * serviceAction.getAmount();
		Double newOrderPrice = previousOrderPrice + newActionPrice - previousActionPrice;
		serviceOrder.setTotalPrice(newOrderPrice);
		orderService.saveOrder(serviceOrder);
		return serviceOrder.getTotalPrice();
	}
	
	@RequestMapping(value="/deleteJob", method=RequestMethod.POST)
	public @ResponseBody Double deleteJob(@RequestParam(value="requestId") String requestId, @RequestParam(value="jobId") Integer jobId) {
		ServiceAction serviceAction = orderService.getServiceActionById(jobId);
		Double previousActionPrice = serviceAction.getPrice() * serviceAction.getAmount();
		orderService.deleteObject(serviceAction);
		ServiceOrder serviceOrder = orderService.getOrderByRequestId(requestId);
		List<ServiceAction> serviceParts = serviceOrder.getServiceAction();
		for (int i = 0; i < serviceParts.size(); i++) {
			if (serviceParts.get(i).getId() == jobId.intValue()) {
				serviceParts.remove(i);
			}
		}
		
		
		Double previousOrderPrice = serviceOrder.getTotalPrice();
		Double newOrderPrice = previousOrderPrice  - previousActionPrice;
		serviceOrder.setTotalPrice(newOrderPrice);
		orderService.saveOrder(serviceOrder);
		return serviceOrder.getTotalPrice();
	}
	
	//name: name, price: price, amount: amount, seriaNr: seriaNr
	@RequestMapping(value="/addServicePart", method=RequestMethod.POST)
	public @ResponseBody ServicePartFront addServicePart(
			@RequestParam(value="requestId") String requestId,
			@RequestParam(value="name") String name,
			@RequestParam(value="price") Double price,
			@RequestParam(value="amount") Integer amount,
			@RequestParam(value="seriaNr") String seriaNumber) {

		double orderCost = amount * price;
		
		ServiceOrder serviceOrder = orderService.getOrderByRequestId(requestId);
		
		double currentCost = 0.0;
		if (serviceOrder != null && serviceOrder.getTotalPrice() != null ) {
			currentCost = serviceOrder.getTotalPrice();
		}
		double newPrice = currentCost + orderCost;
		serviceOrder.setTotalPrice(newPrice);
		orderService.updateOrder(serviceOrder, personService.getCurrentPerson().getSubject());
		
		ServicePart servicePart = new ServicePart();
		servicePart.setCreated(new Date());
		servicePart.setCreatedBy(personService.getCurrentPerson().getSubject());
		servicePart.setName(name);
		servicePart.setPartCount(amount);
		servicePart.setPartPrice(price);
		servicePart.setSerialNumber(seriaNumber);
		servicePart.setServiceOrder(serviceOrder);
		
		orderService.saveServicePart(servicePart);
		
		return new ServicePartFront(name, price, amount, servicePart.getId(), newPrice);
	}
	
	@RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
	public @ResponseBody String deleteOrder(@RequestParam("requestId") String requestId) {
		ServiceOrder order = orderService.getOrderByRequestId(requestId);
		ServiceRequest request = requestService.getRequestById(requestId);
		
		boolean requestDeleted = requestService.deleteRequest(request);
		
		if (requestDeleted)
			return orderService.deleteServiceOrder(order);
		else 
			return "fail";
	}
	
	//requestId: requestId, description: description, price: price, amount: amount, type: type
	@RequestMapping(value="/addServiceJob", method=RequestMethod.POST)
	public @ResponseBody ServiceActionFront addServiceJob(
			@RequestParam(value="requestId") String requestId,
			@RequestParam(value="description") String description,
			@RequestParam(value="price") Double price,
			@RequestParam(value="amount") Integer amount,
			@RequestParam(value="type") Integer type) {

		double orderCost = amount * price;
		
		ServiceOrder serviceOrder = orderService.getOrderByRequestId(requestId);
		ServiceType serviceType = orderService.getServiceTypeById(type);

		double currentCost = (serviceOrder != null) ? serviceOrder.getTotalPrice() : 0.0;
		double newPrice = currentCost + orderCost;
		
		ServiceAction serviceAction = new ServiceAction();
		serviceAction.setAmount(amount);
		serviceAction.setCreated(new Date());
		serviceAction.setCreatedBy(personService.getCurrentPerson().getSubject());
		serviceAction.setDescription(description);
		serviceAction.setPrice(price);
		serviceAction.setServiceType(serviceType);
		serviceAction.setServiceOrder(serviceOrder);
		
		orderService.saveServiceAction(serviceAction);
		List<ServiceAction> actions = serviceOrder.getServiceAction();
		if (actions == null) {
			actions = new ArrayList<ServiceAction>();
		}
		actions.add(serviceAction);
		serviceOrder.setServiceAction(actions);
		serviceOrder.setTotalPrice(newPrice);
		orderService.updateOrder(serviceOrder, personService.getCurrentPerson().getSubject());
		return new ServiceActionFront(serviceAction.getId(), serviceAction.getServiceActionStatusType(), serviceType, null, amount, price, description, newPrice);
	}
	
	
}
