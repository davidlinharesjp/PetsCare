package br.com.petsCare.entities.enumeration;

import java.util.Arrays;

public enum Sex {
	MALE("M"),
	FEMALE("F");

	private String text;

	private Sex(String text) {
		this.text = text;
	}

	public String getCode() {
		return text;
	}

	public static Sex fromString(String text) {
		/*
		 * for (Sex value : values()) { if (value.getCode().equalsIgnoreCase(text)) {
		 * return value; } } return null;
		 */
		
        return Arrays.stream(values())
                .filter(sex -> sex.text.equalsIgnoreCase(text))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("unknown value: " + text));
	}
	
	

}
