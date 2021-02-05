package com.utopia.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "airport")
public class Airport {
	@Id
	@Column(name = "iata_id")
	private String aiportCode;

	@Column(name = "city")
	private String city;

	public Airport() {}

	public Airport(String aiportCode, String city) {
		super();
		this.aiportCode = aiportCode;
		this.city = city;
	}

	public String getAiportCode() {
		return aiportCode;
	}

	public void setAiportCode(String aiportCode) {
		this.aiportCode = aiportCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	

}
