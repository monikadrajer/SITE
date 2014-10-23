package org.sitenv.services.ccda.web.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.Resource;

import org.sitenv.ccda.CCDAConstants;
import org.sitenv.ccda.service.CCDAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value="/CCDA/", produces=MediaType.TEXT_XML_VALUE)
public class CCDAValidationController 
{	
	// Instance Data
	private Properties properties;

	@Autowired
	@Resource(name="CCDA1_1")
	private CCDAService service;

	// Constructor
	public CCDAValidationController() throws IOException
	{
		loadProperties();
	}
	
	@RequestMapping(value="/Validate/", headers = "content-type=multipart/*", method= RequestMethod.POST, produces="application/json; charset=utf-8")
	public String validate(@RequestParam(value="type_val", required=false) String type_val, @RequestParam(value="file", required=false) MultipartFile file)  
	{
		if (service.getProperties() == null)
		{
			service.setProperties(properties);
		}
		return service.validate(type_val, file);
	}
	
	/*
	 * 	Utility
	 */
	private void loadProperties() throws IOException 
	{
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(CCDAConstants.DEFAULT_PROPERTIES_FILE);
		
		if (in == null)
		{
			properties = null;
			throw new FileNotFoundException("CCDA Environment Properties File not found in class path.");
		}
		else
		{
			properties = new Properties();
			properties.load(in);
		}
	}
}
