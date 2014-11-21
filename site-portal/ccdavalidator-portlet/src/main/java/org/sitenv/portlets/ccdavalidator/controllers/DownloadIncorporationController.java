package org.sitenv.portlets.ccdavalidator.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.log4j.Logger;
import org.sitenv.common.statistics.manager.StatisticsManager;
import org.sitenv.common.utilities.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.servlet.HttpHeaders;


@Controller
@RequestMapping("VIEW")
public class DownloadIncorporationController extends BaseController {	
	
	@Autowired
	private StatisticsManager statisticsManager;
	
	private static Logger logger = Logger.getLogger(DownloadIncorporationController.class);
	
    private static final Map<String, String> negativeTestingCCDAFileNames;
    static
    {
    	negativeTestingCCDAFileNames = new HashMap<String, String>();
    	negativeTestingCCDAFileNames.put("0", "AllNegativeTestingCCDAs.zip");
    	negativeTestingCCDAFileNames.put("1", "ExpiredVocabularyTesting.zip");
    	negativeTestingCCDAFileNames.put("2", "CodeNotInValueSet.zip");
    	negativeTestingCCDAFileNames.put("3", "TemplateIdsNotIncludedInBaseDocument.zip");
    	negativeTestingCCDAFileNames.put("4", "DoesNotIncludeRequiredTemplateIds.zip");
    	negativeTestingCCDAFileNames.put("5", "SectionWithMissingNarrative.zip");
    	negativeTestingCCDAFileNames.put("6", "SectionWithInvalidCodeSystem.zip");
    	negativeTestingCCDAFileNames.put("7", "EntryThatIncludedInvalidDataTypes.zip");
    	negativeTestingCCDAFileNames.put("8", "DocumentDoesNotIncludeCoreMUDataElements.zip");
    	negativeTestingCCDAFileNames.put("9", "IncorrectCodingOfMedicationData.zip");
    	negativeTestingCCDAFileNames.put("10", "IncorrectCodingOfProblemsData.zip");
    	negativeTestingCCDAFileNames.put("11", "IncorrectCodingOfAllergiesData.zip");
    	negativeTestingCCDAFileNames.put("12", "IncorrectCodingOfLabResultsData.zip");
    	negativeTestingCCDAFileNames.put("13", "IncorrectCodingOfProceduresData.zip");
    	negativeTestingCCDAFileNames.put("14", "IncorrectCodingOfVitalSignsData.zip");
    	negativeTestingCCDAFileNames.put("15", "IncorrectCodingOfImmunizationData.zip");
    	
    }
	
	
    private static final Map<String, String> referenceCCDAFileNames;
    static
    {
    	referenceCCDAFileNames = new HashMap<String, String>();
    	referenceCCDAFileNames.put("0", "BaseCCDA.zip");
    	referenceCCDAFileNames.put("1", "CCDA1.zip");
    	referenceCCDAFileNames.put("2", "CCDA2.zip");
    	referenceCCDAFileNames.put("3", "CCDA3.zip");
    	referenceCCDAFileNames.put("4", "CCDA4.zip");
    }
    
    
    private static final Map<String, String> testInputFileNames;
    static
    {
    	testInputFileNames = new HashMap<String, String>();
    	testInputFileNames.put("0", "ReferenceFile1.pdf");
    	testInputFileNames.put("1", "ReferenceFile2.pdf");
    	testInputFileNames.put("2", "ReferenceFile3.pdf");
    	testInputFileNames.put("3", "ReferenceFile4.pdf");
    	testInputFileNames.put("4", "ReferenceFile5.pdf");
    	testInputFileNames.put("5", "ReferenceFile6.pdf");
    	testInputFileNames.put("6", "ReferenceFile7.pdf");
    	testInputFileNames.put("7", "ReferenceFile8.pdf");
    	testInputFileNames.put("8", "ReferenceFile9.pdf");
    }
    
    
	
	
	public void copyStream(InputStream in, OutputStream out){
		byte[] buffer = new byte[1024];
		int len;
		try {
			while ((len = in.read(buffer)) != -1) {
			    out.write(buffer, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@ResourceMapping("downloadTestInputFile")
	public void serveTestInputFileSamples(ResourceRequest resourceRequest, ResourceResponse res) throws PortletException, IOException {
		
		if (this.props == null)
		{
			this.loadProperties();
		}
		
		String fileVal = resourceRequest.getParameter("getFile");
		
		String fileName = DownloadIncorporationController.testInputFileNames.get(fileVal);
		
		
		String downloadPath = props.getProperty("TestInputFile") + "/" + fileName;
		
		File downloadFile = new File(downloadPath);
		InputStream in = new FileInputStream(downloadFile);
		
		res.setContentType("application/zip");
		res.addProperty(HttpHeaders.CACHE_CONTROL,
				"max-age=3600, must-revalidate");
		res.addProperty(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
				+ fileName + "\"");
		// Use this to directly download the file
		res.addProperty("Set-Cookie", "fileDownload=true; path=/");
		
		OutputStream out = res.getPortletOutputStream();
		
		copyStream(in, out);
		out.flush();
		out.close();
		in.close();
		statisticsManager.addReferenceCcdaDownload(fileName);
		
	}
	
	
	@ResourceMapping("downloadNegativeTesting")
	public void serveNegativeTestingSamples(ResourceRequest resourceRequest, ResourceResponse res) throws PortletException, IOException {
		
		if (this.props == null)
		{
			this.loadProperties();
		}
		
		String ccdaVal = resourceRequest.getParameter("getCCDA");
		
		String fileName = DownloadIncorporationController.negativeTestingCCDAFileNames.get(ccdaVal);
		
		
		String downloadPath = props.getProperty("CcdasForNegativeTesting") + "/" + fileName;
		
		File downloadFile = new File(downloadPath);
		InputStream in = new FileInputStream(downloadFile);
		
		res.setContentType("application/zip");
		res.addProperty(HttpHeaders.CACHE_CONTROL,
				"max-age=3600, must-revalidate");
		res.addProperty(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
				+ fileName + "\"");
		// Use this to directly download the file
		res.addProperty("Set-Cookie", "fileDownload=true; path=/");
		
		OutputStream out = res.getPortletOutputStream();
		
		copyStream(in, out);
		out.flush();
		out.close();
		in.close();
		statisticsManager.addReferenceCcdaDownload(fileName);
		
	}
	
	
	@ResourceMapping("downloadReferenceIncorporation")
	public void serveReferenceSamples(ResourceRequest resourceRequest, ResourceResponse res) throws PortletException, IOException {
		
		if (this.props == null)
		{
			this.loadProperties();
		}
		
		String ccdaVal = resourceRequest.getParameter("getCCDA");
		
		String fileName = DownloadIncorporationController.referenceCCDAFileNames.get(ccdaVal);
		
		String downloadPath = props.getProperty("referenceCcdasForIncorporation") + "/" + fileName;
		
		File downloadFile = new File(downloadPath);
		InputStream in = new FileInputStream(downloadFile);
		
		res.setContentType("application/zip");
		res.addProperty(HttpHeaders.CACHE_CONTROL,
				"max-age=3600, must-revalidate");
		res.addProperty(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
				+ fileName + "\"");
		// Use this to directly download the file
		res.addProperty("Set-Cookie", "fileDownload=true; path=/");
		
		
		OutputStream out = res.getPortletOutputStream();
		
		copyStream(in, out);
		out.flush();
		out.close();
		in.close();
		statisticsManager.addReferenceCcdaDownload(fileName);
		
	}
	
	
	@ResourceMapping("downloadVendorIncorporation")
	public void serveSamplesFromVendors(ResourceRequest resourceRequest, ResourceResponse res) throws PortletException, IOException {
		
		if (this.props == null)
		{
			this.loadProperties();
		}
		
		String downloadPath = props.getProperty("samplesFromVendorsForIncorporation") + "/" + resourceRequest.getParameter("incorpfilepath");
		
		logger.info("DOWNLOAD PATH!!!!!!!!!!!!!!!!!!!!! "  + downloadPath);
		
		String[] downloadPathTokens = downloadPath.split("[/\\\\]");
		String fileName = downloadPathTokens[downloadPathTokens.length-1];
		
		
		File downloadFile = new File(downloadPath);
		InputStream in = new FileInputStream(downloadFile);
		
		res.setContentType("application/xml");
		res.addProperty(HttpHeaders.CACHE_CONTROL,
				"max-age=3600, must-revalidate");
		res.addProperty(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
				+ fileName + "\"");
		// Use this to directly download the file
		res.addProperty("Set-Cookie", "fileDownload=true; path=/");
		
		
		OutputStream out = res.getPortletOutputStream();
		
		copyStream(in, out);
		out.flush();
		out.close();
		in.close();
		statisticsManager.addReferenceCcdaDownload(fileName);
		
	}
	
	
	public StatisticsManager getStatisticsManager() {
		return statisticsManager;
	}

	public void setStatisticsManager(StatisticsManager statisticsManager) {
		this.statisticsManager = statisticsManager;
	}
	
}
