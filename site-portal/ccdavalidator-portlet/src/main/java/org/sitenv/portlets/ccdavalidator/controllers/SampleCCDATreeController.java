package org.sitenv.portlets.ccdavalidator.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import org.apache.log4j.Logger;
import org.sitenv.common.utilities.controller.BaseController;
import org.sitenv.portlets.ccdavalidator.models.SampleCCDATreeNode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

import com.google.gson.Gson;


@Controller
@RequestMapping("VIEW")
public class SampleCCDATreeController extends BaseController {
	
	private static Logger _log = Logger.getLogger(SampleCCDATreeController.class);
	
	/*
	private SampleCCDATreeNode vendorCCDAroot = null;

	private void traverseDir(String rootDirectory, String path, SampleCCDATreeNode root, int deep)
			throws IOException {
		if (this.props == null) {
			this.loadProperties();
		}

		File[] files = (new File(path)).listFiles();

		if (files == null)
			return;

		Arrays.sort(files);

		int count = 1;
		deep++;
		for (File file : files) {
			count++;
			if (!file.getName().equalsIgnoreCase(".git")
					&& !file.getName().equalsIgnoreCase("README.md")) {

				if (file.isDirectory()) {
					String dirPath = file.getCanonicalPath().replace("\\", "/");
					
					SampleCCDATreeNode folder = new SampleCCDATreeNode(
							file.getName().replace("\\", "/"), "folder", "open", String.format(
									"%d_%d", deep, count), "helloword");
					folder.getMetadata().setDescription("This is CCDA file 1.");
					root.addChild(folder);
					traverseDir(rootDirectory, dirPath, folder, deep);
				} else {
					
					String dirPath = file.getCanonicalPath().replace("\\", "/");
					
					SampleCCDATreeNode folder = new SampleCCDATreeNode(
							file.getName().replace("\\", "/"), "file", "leaf", String.format(
									"%d_%d", deep, count), "helloword");
					folder.getMetadata().setDescription("This is CCDA file 1.");
					folder.getMetadata().setServerPath(
							dirPath.replace(rootDirectory.replace("\\", "/")
									+ "/", ""));
					root.addChild(folder);
				}
			}
		}
	}

	@ActionMapping(params = "javax.portlet.action=sampleCCDATree")
	public void CCDASampleResponse(ActionRequest request, ActionResponse response)
			throws IOException {
		
		
		
		if (this.props == null) {
			this.loadProperties();
		}

		SampleCCDATreeNode root = new SampleCCDATreeNode("Localhost", "root",
				"open", "1", "helloword");
		
		String SampleDir = props.getProperty("samplesFromVendorsForIncorporation").replace("\\", "/");
		
		this.traverseDir(SampleDir, SampleDir, root, 1);
		
		this.vendorCCDAroot = root;

		response.setRenderParameter("javax.portlet.action", "sampleCCDATree");
		
	}

	@RequestMapping(params = "javax.portlet.action=sampleCCDATree")
	public ModelAndView processVendorCCDA(RenderRequest request, Model model)
			throws IOException {
		Map map = new HashMap();
		
		
		Gson gson = new Gson();
		String json = gson.toJson(vendorCCDAroot);

		map.put("jsonRoot", json);

		return new ModelAndView("sampleCCDATreeJsonView", map);
	}
	*/
	
	
		
	ArrayList<SampleCCDATreeNode> sampleCCDARoots;
	
	
	private void traverseSampleCcdaDir(String path, SampleCCDATreeNode root, int deep)
			throws IOException {
		
		if (this.props == null) {
			this.loadProperties();
		}

		File[] files = (new File(path)).listFiles();

		if (files == null)
			return;

		Arrays.sort(files);

		int count = 1;
		deep++;
		for (File file : files) {
			count++;
			if (!file.getName().equalsIgnoreCase(".git")
					&& !file.getName().equalsIgnoreCase("README.md")) {

				if (file.isDirectory()) {
					String dirPath = file.getCanonicalPath().replace("\\", "/");
					SampleCCDATreeNode folder = new SampleCCDATreeNode(
							file.getName().replace("\\", "/"), "folder", "open", String.format(
									"%d_%d", deep, count), "helloword");
					folder.getMetadata().setDescription("This is CCDA file 1.");
					if (root == null) { // check for null
						sampleCCDARoots.add(folder);
					} else {
						root.addChild(folder);
					}
					traverseSampleCcdaDir(dirPath, folder, deep);
				} else {
					
					String dirPath = file.getCanonicalPath().replace("\\", "/");
					SampleCCDATreeNode folder = new SampleCCDATreeNode(
							file.getName(), "file", "leaf", String.format(
									"sample_%d_%d", deep, count), "helloword");
					folder.getMetadata().setDescription("This is CCDA file 1.");
					folder.getMetadata().setServerPath(
							
							dirPath.replace(props.getProperty("samplesFromVendorsForIncorporation").replace("\\", "/")
									+ "/", ""));
					
					if (root == null) { // check for null
						sampleCCDARoots.add(folder);
					} else {
						root.addChild(folder);
					}
				}
			}
		}
	}
	
	
	@ActionMapping(params = "javax.portlet.action=sampleCCDATree")
	public synchronized void CCDASampleResponse(ActionRequest request, ActionResponse response)
			throws IOException {
		if (this.props == null) {
			this.loadProperties();
		}
		System.out.println("CCDASampleResponse_____________");
		_log.trace("Start get sample CCDAs.");
		String CCDASampleDir = props.getProperty("samplesFromVendorsForIncorporation");
		sampleCCDARoots = new ArrayList<SampleCCDATreeNode>(); // this got moved; roots is an instance variable
		this.traverseSampleCcdaDir(CCDASampleDir, null, 1); // send in null for the root directory
		response.setRenderParameter("javax.portlet.action", "sampleCCDATree");
	}
	
	
	@RequestMapping(params = "javax.portlet.action=sampleCCDATree")
	public ModelAndView processVendorCCDA(RenderRequest request, Model model)
			throws IOException {
		Map map = new HashMap();
		
		System.out.println("processVendorCCDA_________Controller__________");
		Gson gson = new Gson();
		String json = gson.toJson(sampleCCDARoots);
		
		map.put("jsonRoot", json);
		
		return new ModelAndView("sampleCCDATreeJsonView", map);
	}
	
}