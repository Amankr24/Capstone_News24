package com.virtusa.capstone.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
		adaptables = Resource.class
		)
public class FooterModel {

	@ValueMapValue
	private String aboutText;
	
	@ValueMapValue
	private String[] footerLogo;

	public String getAboutText() {
		return aboutText;
	}

	public String[] getFooterLogo() {
		return footerLogo;
	}
	
	
}
