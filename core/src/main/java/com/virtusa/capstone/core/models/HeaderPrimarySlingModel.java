package com.virtusa.capstone.core.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
		adaptables = Resource.class
		)
public class HeaderPrimarySlingModel {
    
    @ValueMapValue
	private String trendingText;
	
	@ValueMapValue
	private String trendingDesc;
	
	private String curdate;

    @PostConstruct
	public void getDateStr() {
		SimpleDateFormat formatter =  new SimpleDateFormat("EEEE, dd MMM yyyy"); 
		curdate = formatter.format(new Date());
	}

    public String getTrendingText() {
        return trendingText;
    }

    public String getTrendingDesc() {
        return trendingDesc;
    }

    public String getCurdate() {
        return curdate;
    }

    
}
