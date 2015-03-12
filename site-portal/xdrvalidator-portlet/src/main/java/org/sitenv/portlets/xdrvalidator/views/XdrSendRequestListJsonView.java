package org.sitenv.portlets.xdrvalidator.views;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

@Component("xdrSendRequestListJsonView")
public class XdrSendRequestListJsonView extends AbstractView {
		
		private Logger logger = Logger.getLogger(XdrSendRequestListJsonView.class);
		
		public XdrSendRequestListJsonView() {
			super();
			
			setContentType("text/plain");
		}
		
		@Override
	    protected void renderMergedOutputModel(Map map, HttpServletRequest request, HttpServletResponse response)
	            throws Exception {
	    logger.info("Resolving ajax request view - " + map);
	    
	    JSONObject jsonObj = new JSONObject();
	    
	    jsonObj.put("lookupCode", map.get("lookupCode"));
	    if (map.get("requestTimestamps") == null) {
	    	jsonObj.put("requestTimestamps", new ArrayList<String>());
	    }
	    else {
	    	jsonObj.put("requestTimestamps", map.get("requestTimestamps"));
	    }
	    
	    logger.info(jsonObj.toString());
	    
	    logger.info("content Type = " + getContentType());
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(jsonObj.toString());
	    response.getWriter().flush();
	    }
}
