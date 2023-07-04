package com.virtusa.capstone.core.models;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import javax.annotation.PostConstruct;
import javax.jcr.query.Query;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Model(adaptables = { Resource.class, SlingHttpServletRequest.class })
public class TrendingArticle {
	@ValueMapValue
	@Default(values = "capstone:/categories/trendings/component-1")
	private String categoryTag;
	
	@SlingObject
	ResourceResolver resolver;
	
	List<ArticleBannerModel> categoryArticles;
	
	@PostConstruct
	public void init() {
		if (categoryTag != null) {
			categoryArticles = new ArrayList();
			String query = "SELECT * FROM [cq:Page] AS s WHERE ISDESCENDANTNODE([/content/capstone]) and s.[jcr:content/cq:tags] like 'capstone:categories/trendings%' order by s.[jcr:content/jcr:created] desc option(limit 5)";
			Iterator<Resource> result = resolver.findResources(query, Query.JCR_SQL2);
			while (result.hasNext()) {
				Resource resource = result.next();
				Resource bannerResource = resolver
						.getResource(resource.getPath() + "/jcr:content/root/container/article_banner");
				Page articlePage = resource.adaptTo(Page.class);
				if (bannerResource != null) {
					ArticleBannerModel articleDetails = bannerResource.adaptTo(ArticleBannerModel.class);
					if (articleDetails != null) {
						SimpleDateFormat formatter = new SimpleDateFormat("MMM dd,yyyy");
						articleDetails.setPageCreatedDate(
								formatter.format(articlePage.getProperties().get("jcr:created", Date.class)));
						categoryArticles.add(articleDetails);
					}
				}
			}
		}
	}
	public String getCategoryTag(String[] tags) {
		for (String tag : tags) {
			if (tag.startsWith("capstone:categories")) {
				String[] tagItems = tag.split("/");
				if (tagItems.length >= 2) {
					return tagItems[0] + "/" + tagItems[1];
				}
			}
		}
		return null;
	}
	public List<ArticleBannerModel> getCategoryArticles() {
		return categoryArticles;
	}
}