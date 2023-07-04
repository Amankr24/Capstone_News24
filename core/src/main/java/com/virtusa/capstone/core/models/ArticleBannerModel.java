package com.virtusa.capstone.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model (
        adaptables =  
                    Resource.class,       
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
        )

public class ArticleBannerModel {

	@ValueMapValue
    private String bannerImage;
    
	@ValueMapValue
    private String bannerIcon;
	
    @ValueMapValue
    private String bannerText;
    
    @ValueMapValue
    private String pageCreatedDate;

    public String getBannerImage() {
        return bannerImage;
    }

    public String getBannerText() {
        return bannerText;
    }

    public String getPageCreatedDate() {
        return pageCreatedDate;
    }
    public void setPageCreatedDate(String pageCreatedDate) {
        this.pageCreatedDate= pageCreatedDate;
    }

	public String getBannerIcon() {
		return bannerIcon;
	}
    
}
