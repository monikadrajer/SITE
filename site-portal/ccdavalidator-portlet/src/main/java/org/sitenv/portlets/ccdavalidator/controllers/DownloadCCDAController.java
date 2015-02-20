package org.sitenv.portlets.ccdavalidator.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jsoup.Jsoup;
import org.sitenv.common.utilities.controller.BaseController;
import org.sitenv.common.statistics.manager.StatisticsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import com.liferay.portal.kernel.servlet.HttpHeaders;


@Controller
@RequestMapping("VIEW")
public class DownloadCCDAController extends BaseController {

	@Autowired
	private StatisticsManager statisticsManager;
	
	@ResourceMapping("saveAsPDF")
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse res) throws PortletException, IOException {

		if (this.props == null)
		{
			this.loadProperties();
		}
		
		String reportContent = resourceRequest.getParameter("reportContent");

		String logoImagePath = resourceRequest.getScheme() + "://"
				+ resourceRequest.getServerName() + ":"
				+ resourceRequest.getServerPort()
				+ resourceRequest.getContextPath()
				+ "/css/SITP_Logo_Banner_ClearBG_modified.png";
		
		String css_path = resourceRequest.getScheme() + "://"
				+ resourceRequest.getServerName() + ":"
		    	+ resourceRequest.getServerPort()
				+ resourceRequest.getContextPath()
				+ "/css/pdf_styling.css";
		
		String headerImagePath = resourceRequest.getScheme() + "://"
				+ resourceRequest.getServerName() + ":"
				+ resourceRequest.getServerPort()
				+ resourceRequest.getContextPath()
				+ "/css/header_logo.png";
		
		
		System.out.println("logoImagePath = " + logoImagePath);
		System.out.println("Css path : " + css_path);
		
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"  \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
		sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">");
		sb.append("<head>");
		sb.append("<title>Report Test for SITE Tes:1. </title>");
		sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + css_path + "\" media=\"print\"></link>");
		sb.append("<style type=\"text/css\" media=\"print\"> @import \""+css_path+"\" </style>");
		sb.append("</head>");
		sb.append("<body>");
		
		sb.append("<div style=\"-fs-page-sequence: start; page-break-before:auto\">");
		sb.append("<div style=\"position:running(current);\">");
		sb.append("<div id= \"page-header\" class=\"small\">");
		sb.append("<span class=\"align-left\"><img alt=\"\" src=\""+ headerImagePath +"\" /></span>");
		sb.append("<div class=\"line\"></div>");
		sb.append("</div>");
		sb.append("</div>");
		
		
		sb.append("<div style=\"position: running(footer);\">");
	    sb.append("<div id=\"page-footer\" class=\"small\">");
		sb.append("<div class=\"line\"></div>");
		sb.append("Page <span class=\"page\" /> of <span class=\"pagecount\" />"); 
		sb.append("</div>"); 
	    sb.append("</div>");
		
	//	sb.append("<div class=\"main_content\">");
		
		
		
		String imageTag = "<img alt=\"\" src=\"{IMGPATH}\" />";
		imageTag = imageTag.replace("{IMGPATH}", logoImagePath);
		sb.append("<div class=\"head\">Report</div>");
		sb.append(imageTag);
		sb.append("<br />");
		
		//org.jsoup.nodes.Document doc1 = Jsoup.parse(reportContent
			//	.toString());
		//String reportCpntent_mod = doc1.toString();
		sb.append(reportContent);
		//sb.append("</div>");
		sb.append("</div>");
		sb.append("</body></html>");
		//System.out.println("Report Content:" + reportContent);

		try {

			DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
			fac.setNamespaceAware(false);
			fac.setValidating(false);
			fac.setFeature("http://xml.org/sax/features/namespaces", false);
			fac.setFeature("http://xml.org/sax/features/validation", false);
			fac.setFeature(
					"http://apache.org/xml/features/nonvalidating/load-dtd-grammar",
					false);
			fac.setFeature(
					"http://apache.org/xml/features/nonvalidating/load-external-dtd",
					false);

			
			//--------------------
			//String redefineHtml = sb.toString();
			//System.out.println("FILE : "+redefineHtml);
			org.jsoup.nodes.Document doc = Jsoup.parse(sb
					.toString());
			String refinedHtml = doc.toString();

			System.out.println("FILE : "+refinedHtml);
			
			DocumentBuilder builder = fac.newDocumentBuilder();
			Document refineddoc = builder.parse(new ByteArrayInputStream(
					refinedHtml.getBytes("UTF-8")));

			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocument(refineddoc,null);
			renderer.layout();
			
			
		
		res.setContentType("application/pdf");
		res.addProperty(HttpHeaders.CACHE_CONTROL,
				"max-age=3600, must-revalidate");
		res.addProperty(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
				+ "test.pdf" + "\"");
		//-/----------- Use this to directly download the file
		res.addProperty("Set-Cookie", "fileDownload=true; path=/");
		OutputStream out = res.getPortletOutputStream();
		// out.write(pdfContentVO.getPdfData());
		renderer.createPDF(out);
		out.flush();
		
		out.close();
		
		statisticsManager.addCcdaDownload();
		
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public StatisticsManager getStatisticsManager() {
		return statisticsManager;
	}

	public void setStatisticsManager(StatisticsManager statisticsManager) {
		this.statisticsManager = statisticsManager;
	}
	
}
