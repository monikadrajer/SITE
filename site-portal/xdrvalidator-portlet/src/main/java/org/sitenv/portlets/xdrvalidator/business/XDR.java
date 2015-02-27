package org.sitenv.portlets.xdrvalidator.business;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

public class XDR {
	
	public static String sendValidMinimalXDRMessage(
			String endpoint,String doc, String name, String directTo, String directFrom, String replyTo)
	{
		// Send full metadata for now
		String metadata = XDRUtilities.getXDRFullTemplate("Xdr_full_metadata_only.xml");
		metadata = XDRUtilities.replaceHeaders(metadata, directFrom, directTo, replyTo);
		String ccda = XDRUtilities.getFileContent("encodedCCDA.txt");
		
		String mtomPackage = XDRUtilities.createMtomPackage(metadata, ccda);
		
		String payload = XDRUtilities.addHTTPHeadersToPayload(endpoint, mtomPackage);
		
		System.out.println(" PAYLOAD === " + payload);
		
		
		String response = "";
		
		try {
			response = SocketSender.sendMessage(endpoint, payload);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Respsone = " + response);
		return response;
	}
	
	public static String sendValidFullXDRMessage(
			String endpoint,String doc, String name, String directTo, String from, String replyTo)
	{
		return null;
	}
	
	public static String sendInValidMinimalXDRMessage(
			String endpoint,String doc, String name, String directTo, String directFrom, String replyTo)
	{
		return sendValidMinimalXDRMessage(endpoint,doc,name,directTo,directFrom,replyTo);
	//	return null;
	}
	
	public static String sendInValidFullXDRMessage(
			String endpoint,String doc, String name, String directTo, String from, String replyTo)
	{
		return null;
	}
	

}
