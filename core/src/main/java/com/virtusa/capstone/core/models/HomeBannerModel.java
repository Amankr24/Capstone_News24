package com.virtusa.capstone.core.models;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Model (
        adaptables = { 
                    Resource.class,
                    SlingHttpServletRequest.class },
                    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
        )
public class HomeBannerModel {

	@ValueMapValue
	private String[] articleLinks;
	
	@ScriptVariable
    PageManager pageManager;
    
    @SlingObject
    ResourceResolver resolver;

    
    List<ArticleBannerModel> bannerArticles;
	
    
    @PostConstruct
    public void init() {
        
        if(articleLinks !=null && articleLinks.length > 0) {
  
            bannerArticles= new ArrayList<>();          
            for(String path : articleLinks) {
               
                Page  page=pageManager.getPage(path);
                SimpleDateFormat formatter=new SimpleDateFormat("EEE,dd MMMM yyyy");
                String createdDateStr=formatter.format(page.getProperties().get("jcr:created", Date.class));
                Resource resource=resolver.getResource(path + "/jcr:content/root/container/article_banner");
           
                if(resource!=null) {
                  
                    ArticleBannerModel bannerArticleModel=resource.adaptTo(ArticleBannerModel.class);
                    bannerArticleModel.setPageCreatedDate(createdDateStr);
                    bannerArticles.add(resource.adaptTo(ArticleBannerModel.class));
                    if(bannerArticles.size() >= 5) {
                        break;
                    }
                }           
            }
        }
    }
    public String[] getArticleLinks() {
        return articleLinks;
    }

    public List<ArticleBannerModel> getBannerArticles() {
        return bannerArticles;
    }
}
