package org.sitenv.portlets.FHIR.utils;

import com.google.gson.Gson;
/**
 * The utility class wraps the implementation of Google's Gson API 
 * and provides the utility of 'from' and 'to' conversions of 
 * JSON <-> Object
 * 
 */
public class JSONGenerator<T>
{

	private static Gson gson = new Gson();
	/**
	 * Generates a json string from an object
	 * 
	 * @param obj : Any  object
	 * @return a JSON string
	 */
	public static String createGson(final Object input)
	{
		return gson.toJson(input);
	}

	public T createObject(final String jsonString, final Class<T> temp)
	{
		return gson.fromJson(jsonString, temp);
	}
	
}
