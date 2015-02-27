package org.sitenv.portlets.xdrvalidator.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.sitenv.common.statistics.manager.StatisticsManager;
import org.sitenv.common.utilities.controller.BaseController;
import org.sitenv.portlets.xdrvalidator.business.XDR;
import org.sitenv.portlets.xdrvalidator.models.XdrReceiveResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.multipart.MultipartActionRequest;



@Controller
@RequestMapping("VIEW")
public class XdrValidationController extends BaseController {

	private static Logger logger = Logger.getLogger(XdrValidationController.class);
	
	@Autowired
	private XdrReceiveResults xdrReceiveResults;
	
	@Autowired
	private StatisticsManager statisticsManager;

	@ActionMapping(params = "javax.portlet.action=uploadXDR")
	public void response(MultipartActionRequest request, ActionResponse response) throws IOException {
		
		JSONArray fileJson;
		JSONObject jsonResponseBody;
		
		if (this.props == null)
		{
			this.loadProperties();
		}
		
		// handle the files:
		
		response.setRenderParameter("javax.portlet.action", "uploadXDR");
		MultipartFile file = request.getFile("file");
		String endpoint = request.getParameter("wsdlLocation");

		fileJson = new JSONArray();
		
		jsonResponseBody = new JSONObject();
		
		
		String test = XDR.sendValidMinimalXDRMessage(endpoint, null, null, "edge@nist.gov", "admin@sitenv.org", endpoint);
		
		logger.info(test);
		
		try {

				JSONObject jsono = new JSONObject();
				jsono.put("name", file.getOriginalFilename());
				jsono.put("size", file.getSize());
				
				fileJson.put(jsono);
				
				// handle the data
				jsonResponseBody.put("IsSuccess", "true");
				jsonResponseBody.put("ErrorMessage", "Message Sent Successfully!");
				
				
				
				

		} catch (Exception e) {
			//statisticsManager.addCcdaValidation(ccda_type_value, false, false, false, true);
			
			throw new RuntimeException(e);
		} 
		
		xdrReceiveResults.setFileJson(fileJson);
		xdrReceiveResults.setJsonResponseBody(jsonResponseBody);
		
	}
	
	@ActionMapping(params = "javax.portlet.action=precannedXDR")
	public void responsePrecanned(ActionRequest request, ActionResponse response) throws IOException {
		
		JSONArray fileJson;
		JSONObject jsonResponseBody;
		
		if (this.props == null)
		{
			this.loadProperties();
		}
		
		// handle the files:
		
		response.setRenderParameter("javax.portlet.action", "precannedXDR");
		String endpoint = request.getParameter("precannedWsdlLocation");
		//MultipartFile file = request.getFile("file");

		fileJson = new JSONArray();
		
		jsonResponseBody = new JSONObject();
		

		String test = XDR.sendValidMinimalXDRMessage(endpoint, null, null, "edge@nist.gov", "admin@sitenv.org", endpoint);
		
		logger.info(test);
		
		try {

				JSONObject jsono = new JSONObject();
				//jsono.put("name", file.getOriginalFilename());
				//jsono.put("size", file.getSize());
				
				fileJson.put(jsono);
				
				// handle the data
				jsonResponseBody.put("IsSuccess", "true");
				jsonResponseBody.put("ErrorMessage", "Message Sent Successfully!");
				
				
				
				

		} catch (Exception e) {
			//statisticsManager.addCcdaValidation(ccda_type_value, false, false, false, true);
			
			throw new RuntimeException(e);
		} 
		

		xdrReceiveResults.setFileJson(fileJson);
		xdrReceiveResults.setJsonResponseBody(jsonResponseBody);
	}

	@RequestMapping(params = "javax.portlet.action=uploadXDR")
	public ModelAndView process(RenderRequest request, Model model)
			throws IOException {
		Map map = new HashMap();

		map.put("files", xdrReceiveResults.getFileJson());
		map.put("result", xdrReceiveResults.getJsonResponseBody());
		
		
		return new ModelAndView("xdrValidatorJsonView", map);
	}
	
	@RequestMapping(params = "javax.portlet.action=precannedXDR")
	public ModelAndView processPrecanned(RenderRequest request, Model model)
			throws IOException {
		Map map = new HashMap();

		map.put("files", xdrReceiveResults.getFileJson());
		map.put("result", xdrReceiveResults.getJsonResponseBody());
		
		
		return new ModelAndView("xdrValidatorJsonView", map);
	}

	@RenderMapping()
	public ModelAndView handleRenderRequest(RenderRequest request,
			RenderResponse response) throws IOException {

		if (this.props == null)
		{
			this.loadProperties();
		}
		
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("view");
		
		modelAndView.addObject("xdrSoapEndpoint", props.get("xdrvalidator.service.endpoint"));

		return modelAndView;
	}
	
}
