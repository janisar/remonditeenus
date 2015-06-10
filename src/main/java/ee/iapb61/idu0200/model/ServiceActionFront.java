package ee.iapb61.idu0200.model;


public class ServiceActionFront {

	private int id;
	private ServiceActionStatusType serviceActionStatusType;
	private ServiceType serviceType;
	private ServiceDevice serviceDevice;
	private Integer amount;
	private Double price;
	private String description;
	private double newPrice;
	
	public ServiceActionFront(int id,
			ServiceActionStatusType serviceActionStatusType,
			ServiceType serviceType, ServiceDevice serviceDevice,
			Integer amount, Double price, String description, double newPrice) {
		this.id = id;
		this.serviceActionStatusType = serviceActionStatusType;
		this.serviceType = serviceType;
		this.serviceDevice = serviceDevice;
		this.amount = amount;
		this.price = price;
		this.description = description;
		this.newPrice = newPrice;
	}
	
	public double getNewPrice() {
		return newPrice;
	}
	
	public Integer getAmount() {
		return amount;
	}
	
	public String getDescription() {
		return description;
	}
	public int getId() {
		return id;
	}
	public Double getPrice() {
		return price;
	}
	public ServiceActionStatusType getServiceActionStatusType() {
		return serviceActionStatusType;
	}
	public ServiceDevice getServiceDevice() {
		return serviceDevice;
	}
	public ServiceType getServiceType() {
		return serviceType;
	}
}
