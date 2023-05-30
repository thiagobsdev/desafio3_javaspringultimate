package com.thiagobs.desafio3_springUltimate.dto;

public class FieldMessage {
	
	private String fieldName;
	
	private String fielMessage;

	public FieldMessage(String fieldName, String fielMessage) {
		super();
		this.fieldName = fieldName;
		this.fielMessage = fielMessage;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getFielMessage() {
		return fielMessage;
	}
	
	
	

}
