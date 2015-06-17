package org.sitenv.portlets.FHIR.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.sitenv.portlets.FHIR.model.TestCaseResultWrapper;
import org.sitenv.portlets.FHIR.utils.ApplicationConstants;


/**
 * Servlet implementation class FHIRTestCaseServlet
 */
public class FHIRTestCaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			input = classloader.getResourceAsStream(ApplicationConstants.TESTCASE_PROP_FILE_NAME);
			prop.load(input);
			for (final String name: prop.stringPropertyNames()){
				ApplicationConstants.FHIR_TESTCASE_MAP.put(name, prop.getProperty(name));
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FHIRTestCaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			executeFHIRTestCases(request, response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void executeFHIRTestCases(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, JSONException
	{
		String serverURL = request.getParameter("fhirServerURL");
		String testCaseName = request.getParameter("testCaseName");
		String testCaseValue = request.getParameter("testCaseValue");
		List<TestCaseResultWrapper> testCaseResultList  = new ArrayList<TestCaseResultWrapper>();
		if(ApplicationConstants.allTestCaseMap.containsKey(testCaseValue)){
			for (String testCaseVal : ApplicationConstants.allTestCaseMap.get(testCaseValue))
			{
				testCaseResultList.add(executeTestCase(serverURL, testCaseVal, testCaseVal));
			}
			HttpSession session = request.getSession();
			session.setAttribute("testCaseResultList", testCaseResultList);
			request.getRequestDispatcher("/testcase_results.jsp").forward(request, response);
		}else{
			testCaseResultList.add(executeTestCase(serverURL, testCaseValue, testCaseName));
			HttpSession session = request.getSession();
			session.setAttribute("testCaseResultList", testCaseResultList);
			request.getRequestDispatcher("/testcase_results.jsp").forward(request, response);
		}
	}
	
	public String getURI(String testCaseValue){
		return ApplicationConstants.FHIR_TESTCASE_MAP.get(testCaseValue) == null ? "" : 
			            ApplicationConstants.FHIR_TESTCASE_MAP.get(testCaseValue);
	}
	
	public TestCaseResultWrapper executeTestCase(String serverURL,String testCaseValue,String testCaseName)
														throws ServletException, IOException, JSONException
	{
		StringBuffer result = new StringBuffer();
		TestCaseResultWrapper testCaseResultWrapper = new TestCaseResultWrapper();
		String queryURL = serverURL.concat("/").concat(getURI(testCaseValue)).
		          concat(ApplicationConstants.FHIR_FORMAT);
		testCaseResultWrapper.setRequest(queryURL);
		try{	
			HttpClient client = new DefaultHttpClient();
			HttpGet FHIRRequest = new HttpGet(queryURL);
			HttpResponse FHIRResponse = client.execute(FHIRRequest);
			if (FHIRResponse.getStatusLine().getStatusCode() == 200 || FHIRResponse.getStatusLine().getStatusCode() ==400)
			{
				BufferedReader rd = new BufferedReader (new InputStreamReader(FHIRResponse.getEntity().getContent()));
				String line = "";
				while ((line = rd.readLine()) != null) {
					result.append(line);
				}
				JSONObject resultJson = new JSONObject(result.toString());
				if(resultJson.has("issue")){
					testCaseResultWrapper.setStatus("FAILURE");
				}else{
					testCaseResultWrapper.setStatus("PASSED");
				}
				testCaseResultWrapper.setResponse(resultJson.toString(2));
			}else {
				testCaseResultWrapper.setResponse(ApplicationConstants.SERVER_NA_ERROR_MESSAGE);
				testCaseResultWrapper.setStatus("FAILURE");
			}
			testCaseResultWrapper.setName(testCaseName);
		 }
		 catch(IOException e)
		 {
			 throw e;
		 }
		return testCaseResultWrapper;
	}
	
}
