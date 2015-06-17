package org.sitenv.portlets.FHIR.controllers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.sitenv.portlets.FHIR.model.FHIRPortletModel;
import org.sitenv.portlets.FHIR.model.TestCaseResultWrapper;
import org.sitenv.portlets.FHIR.utils.ApplicationConstants;
import org.sitenv.portlets.FHIR.utils.ApplicationUtils;








import com.google.gson.Gson;
import com.liferay.portal.kernel.exception.PortalException;



@Controller
@RequestMapping("VIEW")
public class FHIRPortletController {
	
	// MODEL ATTRIBUTE DEFINITION
	@ModelAttribute("FHIRModel")
	public FHIRPortletModel getCommandObject() {
		return new FHIRPortletModel();
	}
	
	@ActionMapping(params = "javax.portlet.action=getFHIRResults")
	public void getFHIRResults(ActionRequest request, ActionResponse response,
			@ModelAttribute(value = "FHIRModel") FHIRPortletModel fhirPortletModel)throws Exception {
		
		String uri = createURI(fhirPortletModel);
		StringBuffer result = new StringBuffer();
		try{	
			HttpClient client = new DefaultHttpClient();
			HttpGet FHIRRequest = new HttpGet(uri);
			HttpResponse FHIRResponse = client.execute(FHIRRequest);
			BufferedReader rd = new BufferedReader (new InputStreamReader(FHIRResponse.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
			    result.append(line);
			  }
		}
		catch(Exception e)
		{
		     throw e;
		}
		fhirPortletModel.setResult(result.toString());
		response.setRenderParameter("action", "getFHIRResults");
	}
	
	@RenderMapping(params = "action=getFHIRResults")
	public ModelAndView renderFHIRResults(RenderRequest request, RenderResponse response,
			@ModelAttribute(value = "FHIRModel") FHIRPortletModel fhirPortletModel) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("view");
		modelAndView.addObject("FHIRModel", fhirPortletModel);
		return modelAndView;
	}
	
	
	public String createURI(FHIRPortletModel portletModel)
	{
		StringBuffer uri = new StringBuffer();
		uri.append(ApplicationConstants.FHIR_BASEURI);
	    uri.append(portletModel.getResourceType());
		if(! ApplicationUtils.isEmpty(portletModel.getSearchParam()) && 
				             !ApplicationUtils.isEmpty(portletModel.getSearchParamValue()))
		{
			if(!portletModel.getSearchParam().equals("id")){
			   uri.append("?");
			   uri.append(portletModel.getSearchParam()+"="+portletModel.getSearchParamValue());
			}else{
				uri.append("/"+portletModel.getSearchParamValue());
			}
		}else {
			 uri.append("?");
		}
			
		uri.append(ApplicationConstants.FHIR_FORMAT);
		return uri.toString();
	}
	
	// DEFAULT RENDERMAPPING FOR THE VIEW
	@RenderMapping()
	public ModelAndView handleRenderRequest(RenderRequest arg0,RenderResponse arg1) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("view");
		return modelAndView;
	}
	
	@ResourceMapping(value="getFHIR")
	@ResponseBody
	public void getFHIRResults(ResourceRequest request,ResourceResponse response) throws PortalException, Exception, IOException
	{

		String uri = request.getParameter("searchQuery");
		uri = uri.concat(ApplicationConstants.FHIR_FORMAT);
		StringBuffer result = new StringBuffer();
		JSONObject json = new JSONObject();
		try{	
			HttpClient client = new DefaultHttpClient();
			HttpGet FHIRRequest = new HttpGet(uri);
			HttpResponse FHIRResponse = client.execute(FHIRRequest);
			if (FHIRResponse.getStatusLine().getStatusCode() == 200 || FHIRResponse.getStatusLine().getStatusCode() ==400)
			{
				BufferedReader rd = new BufferedReader (new InputStreamReader(FHIRResponse.getEntity().getContent()));
				String line = "";
				while ((line = rd.readLine()) != null) {
					result.append(line);
				}
				json.put("error", false);
			}else {
				result.append(ApplicationConstants.SERVER_NA_ERROR_MESSAGE);
			    json.put("error", true);
			}
		}
		catch(Exception e)
		{
		     throw e;
		}
		json.put("result", result.toString());

		response.getWriter().write(json.toString());
	}
}
