package com.virtusa.capstone.core.models;

import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.api.resource.Resource;
@Model(
		adaptables = Resource.class
		)

public class HeaderSecondarySlingModel {
	
	@ValueMapValue
	private String logo;
	
	@ValueMapValue
	private String[] languagesList;

	@ValueMapValue
	private boolean displayLanguages;

	public String getLogo() {
		return logo;
	}

	public String[] getLanguagesList() {
		return languagesList;
	}

	public boolean getDisplayLanguages() {
		return displayLanguages;
	}
	
	
}
