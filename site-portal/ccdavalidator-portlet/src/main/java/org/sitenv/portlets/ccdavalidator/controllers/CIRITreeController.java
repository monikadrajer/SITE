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
public class CIRITreeController extends BaseController {
	
	private static Logger _log = Logger.getLogger(CIRITreeController.class);
	private SampleCCDATreeNode reconciledCCDARoot = null;
	
	
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
									"reconciled_%d_%d", deep, count), "helloword");
					folder.getMetadata().setDescription("This is CCDA file 1.");
					root.addChild(folder);
					traverseDir(rootDirectory, dirPath, folder, deep);
				} else {
					
					String dirPath = file.getCanonicalPath().replace("\\", "/");
					
					SampleCCDATreeNode folder = new SampleCCDATreeNode(
							file.getName().replace("\\", "/"), "file", "leaf", String.format(
									"reconciled_%d_%d", deep, count), "helloword");
					folder.getMetadata().setDescription("This is CCDA file 1.");
					folder.getMetadata().setServerPath(
							dirPath.replace(rootDirectory.replace("\\", "/")
									+ "/", ""));
					root.addChild(folder);
				}
			}
		}
	}
	
	
	@ActionMapping(params = "javax.portlet.action=reconciledCCDATree")
	public synchronized void ReconciledSampleResponse(ActionRequest request, ActionResponse response)
			throws IOException {
		
		if (this.props == null) {
			this.loadProperties();
		}

		System.out.println("ReconciledSampleResponse________________");
		
		SampleCCDATreeNode root = new SampleCCDATreeNode("", "root",
				"open", "CCDA_Reconciled_Tree", "helloword");
		
		String SampleDir = props.getProperty("ReconciledFileBundles");
		
		this.traverseDir(SampleDir, SampleDir, root, 1);
		this.reconciledCCDARoot = root;
		
		response.setRenderParameter("javax.portlet.action", "reconciledCCDATree");

	}

	@RequestMapping(params = "javax.portlet.action=reconciledCCDATree")
	public ModelAndView processReconciledDownload(RenderRequest request, Model model)
			throws IOException {
		Map map = new HashMap();
		
		
		System.out.println("processReconciledDownload________Controller________");
		
		Gson gson = new Gson();
		String json = gson.toJson(reconciledCCDARoot);
		map.put("jsonRoot", json);
		

		return new ModelAndView("reconciledCCDATreeJsonView", map);
	}

}