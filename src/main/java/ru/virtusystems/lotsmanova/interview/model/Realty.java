package ru.virtusystems.lotsmanova.interview.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REALTY")
public class Realty {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated
	private RealtyType realtyType;

	@Column
	private String constructionYear;

	@Column
	private Float area;

	@Embedded
	private Address address;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RealtyType getRealtyType() {
		return realtyType;
	}

	public void setRealtyType(RealtyType realtyType) {
		this.realtyType = realtyType;
	}

	public String getConstructionYear() {
		return constructionYear;
	}

	public void setConstructionYear(String constructionYear) {
		this.constructionYear = constructionYear;
	}

	public Float getArea() {
		return area;
	}

	public void setArea(Float area) {
		this.area = area;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
