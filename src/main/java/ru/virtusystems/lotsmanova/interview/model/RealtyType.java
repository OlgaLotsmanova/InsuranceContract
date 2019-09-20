package ru.virtusystems.lotsmanova.interview.model;

import java.util.ResourceBundle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum RealtyType {

	FLAT("Квартира"), HOUSE("Дом"), ROOM("Комната");

	private String titie;
	private float coefficient;

	private RealtyType(String title) {
		ResourceBundle boundle = ResourceBundle.getBundle("calculations");

		this.titie = title;

		this.coefficient = Float.parseFloat(
				boundle.getString("RealtyType." + name() + ".coefficient"));
	}

	public float getCoefficient() {
		return coefficient;
	}

	public String getTitie() {
		return titie;
	}

	@JsonCreator
	public static RealtyType fromString(@JsonProperty("name") String name) {
		for (RealtyType type : values()) {
			if (type.name().equalsIgnoreCase(name)) {
				return type;
			}
		}
		throw new IllegalArgumentException(name);
	}

}
