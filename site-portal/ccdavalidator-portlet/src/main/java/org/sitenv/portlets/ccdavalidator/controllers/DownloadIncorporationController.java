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
	
	
	@ResourceMapping("downloadVendorIncorporation")
	public void serveSamplesFromVendors(ResourceRequest resourceRequest, ResourceResponse res) throws PortletException, IOException {
		
		if (this.props == null)
		{
			this.loadProperties();
		}
		
		String downloadPath = props.getProperty("samplesFromVendorsForIncorporation") + "/" + resourceRequest.getParameter("incorpfilepath");
		
		
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
	

	@ResourceMapping("downloadReconciledBundle")
	public void serveReconciledBundle(ResourceRequest resourceRequest, ResourceResponse res) throws PortletException, IOException {
		
		if (this.props == null)
		{
			this.loadProperties();
		}
		
		String downloadPath = props.getProperty("ReconciledFileBundles") + "/" + resourceRequest.getParameter("reconciledBundleFilepath");
		
		
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
	
	
	@ResourceMapping("downloadReferenceIncorpTree")
	public void serveReferenceIncorpFile(ResourceRequest resourceRequest, ResourceResponse res) throws PortletException, IOException {
		
		if (this.props == null)
		{
			this.loadProperties();
		}
		
		String downloadPath = props.getProperty("referenceCcdasForIncorporation") + "/" + resourceRequest.getParameter("reconciledBundleFilepath");
		
		
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
	
	
	@ResourceMapping("downloadReferenceTestData")
	public void serveReferenceTestData(ResourceRequest resourceRequest, ResourceResponse res) throws PortletException, IOException {
		
		if (this.props == null)
		{
			this.loadProperties();
		}
		
		String downloadPath = props.getProperty("ReferenceDownloadFiles") + "/" + resourceRequest.getParameter("referenceDownloadFilepath");
		
		
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
	
	
	@ResourceMapping("downloadReferenceTreeIncorporation")
	public void serveReferenceIncorp(ResourceRequest resourceRequest, ResourceResponse res) throws PortletException, IOException {
		
		if (this.props == null)
		{
			this.loadProperties();
		}
		
		String downloadPath = props.getProperty("referenceCcdasForIncorporation") + "/" + resourceRequest.getParameter("refIncorpfilepath");
		
		
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
	
	
	@ResourceMapping("downloadNegativeTestTreeIncorporation")
	public void serveNegativeTestIncorp(ResourceRequest resourceRequest, ResourceResponse res) throws PortletException, IOException {
		
		if (this.props == null)
		{
			this.loadProperties();
		}
		
		String downloadPath = props.getProperty("CcdasForNegativeTesting") + "/" + resourceRequest.getParameter("negTestfilepath");
		
		
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
