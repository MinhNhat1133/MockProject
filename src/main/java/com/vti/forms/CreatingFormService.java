package com.vti.forms;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CreatingFormService {

	@NotBlank(message = "The serviceContent cannot be blank")
	private String serviceContent;

	@NotBlank(message = "The serviceWeight cannot be blank")
	private int serviceWeight;

	@NotBlank(message = "The serviceTime cannot be blank")
	private int serviceTime;
	
	@NotBlank(message = "The required cannot be blank")
	private int required;
}
