package org.jsp.reservationapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AdminRequest {
	
	private long phone;
	@Email(message = "Invalid Format")
	private String email;
	@NotBlank(message = "GST Number is mandatory")
	private String gst_number;
	@NotBlank(message = "Name is Mandatory")
	private String name;
	@NotBlank(message = "Travels Name is mandatory")
	private String travels_name;
	@Size(min=8,max=15,message = "Password Length must be between 8 to 15")
	private String password;

}
