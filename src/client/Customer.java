package client;

import java.util.UUID;

public class Customer {
	private CustomerClasses group;
	private String name; 
	
	public Customer(CustomerClasses group) {
		this.group = group;
		this.name = UUID.randomUUID().toString().split("-")[0];

	}

	public CustomerClasses getGroup() {
		return group;
	}

	public void setGroup(CustomerClasses group) {
		this.group = group;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "(" + name + ", " + group + ")";
	}


}
