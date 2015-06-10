package ee.iapb61.idu0200.model;

public class ServicePartFront {

	private int count;
	private String name;
	private Double price;
	private int id;
	private double totalPrice;
	
	public ServicePartFront(String name, Double price, int count, int id, double totalPrice) {
		this.count = count;
		this.id = id;
		this.name = name;
		this.price = price;
		this.totalPrice = totalPrice;
	}
	
	public int getCount() {
		return count;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
}
