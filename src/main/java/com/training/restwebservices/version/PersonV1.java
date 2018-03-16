package com.training.restwebservices.version;

public class PersonV1 {
	
	private Name name;
	
	public PersonV1() {
		
	}
	
	public PersonV1(Name name) {
		super();
		this.name = name;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}
}
