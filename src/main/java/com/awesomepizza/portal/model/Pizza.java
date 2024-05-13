package com.awesomepizza.portal.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Embeddable
@Data
public class Pizza {
	@NotNull
	private String name;
	@NotNull
	@Pattern(regexp = "S|M|L")
	private String size;
	
}
