package event;

import java.util.UUID;

public class Client {
	private ClientClasses group;
	private String name; 
	
	public Client(ClientClasses group) {
		this.group = group;
		this.name = UUID.randomUUID().toString().split("-")[0];

	}

	public ClientClasses getGroup() {
		return group;
	}

	public void setGroup(ClientClasses group) {
		this.group = group;
	}

	public String getName() {
		return name;
	}


}
