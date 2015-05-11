package org.sitenv.portlets.statistics.views;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.sitenv.common.statistics.dto.SmtpWeeklyCounts;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

@Component("smtpWeeklyCountsCsvView")
public class SmtpWeeklyCountsCsvView extends AbstractView {

private Logger logger = Logger.getLogger(SmtpWeeklyCountsCsvView.class);
	
	public SmtpWeeklyCountsCsvView() {
		super();
		
		setContentType("text/plain");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    protected void renderMergedOutputModel(Map map, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    logger.info("Resolving ajax request view - " + map);
    
   
    logger.info("content Type = " + getContentType());
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    
    StringBuilder sbuilder = new StringBuilder();
    sbuilder.append("Week," + map.get("titles") + "\n");
    
    if (map.get("weeklyCounts") != null)
    {
    	for (SmtpWeeklyCounts count : (List<SmtpWeeklyCounts>) map.get("weeklyCounts")) {
    		sbuilder.append(count.getStartDate() + "," + count.getTotalCount() + "," + count.getTotalUniqueDomainCount() + "\n");
    	}
    }
    
    response.getWriter().write(sbuilder.toString());
    response.getWriter().flush();
    }
	
}
