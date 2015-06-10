package ee.iapb61.idu0200.model;

import java.util.ArrayList;
import java.util.List;

public class ErrorList {

	private List<String> errors;
	
	public ErrorList() {
		this.errors = new ArrayList<String>();
	}
	
	public List<String> getErrors() {
		return errors;
	}
	
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
