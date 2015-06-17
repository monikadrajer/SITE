package org.sitenv.portlets.FHIR.utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class ApplicationUtils {
	
	/**
	 * Checks if input is null
	 * 
	 * @param input
	 *             Integer :	value
	 * @return Zero, if input is null or its value
	 */
	public static int getDefaultValue(final Integer value)
	{
		int returnValue;
		if (value == null)
		{
			returnValue = 0;
		}
		else
		{
			returnValue = value.intValue();
		}
		return returnValue;
	}
	/**
	 * Checks if input is null
	 * 
	 * @param input
	 *             Integer :	value
	 * @return Zero, if input is null or its value
	 */
	public static short getDefaultValue(final Short value)
	{
		short returnValue;
		if (value == null)
		{
			returnValue = 0;
		}
		else
		{
			returnValue = value.shortValue();
		}
		return returnValue;
	}
	
	/**
	 * Checks if input is null
	 * 
	 * @param input
	 *             Integer :	value
	 * @return Zero, if input is null or its value
	 */
	public static String getDefaultValue(final Timestamp value)
	{
		String returnValue;
		if (value == null)
		{
			returnValue = "";
		}
		else
		{
			returnValue = value.toString();
		}
		return returnValue;
	}
	/**
	 * Checks if input is null
	 * 
	 * @param input
	 *             String :	value
	 * @return Empty String, if input is null or its trimmed value
	 */
	public static String getDefaultValue(final String value)
	{
		String returnValue;
		if (value == null)
		{
			returnValue = "";
		}
		else
		{
			returnValue = value.trim();
		}
		return returnValue;
	}
	
	/**
	 * Checks if input is null
	 * 
	 * @param input
	 *             Long :	value
	 * @return Empty String, if input is null or its Long value
	 */
	public static Object getDefaultValue(final Long value)
	{
		Object returnValue;
		if (value == null)
		{
			returnValue = "";
		}
		else
		{
			returnValue = value.longValue();
		}
		return returnValue;
	}
	
	/**
	 * Checks if input is null
	 * 
	 * @param input
	 *             BigDecimal :	value
	 * @return Empty String, if input is null or its BigDecimal value
	 */
	public static Object getDefaultValue(final BigDecimal value)
	{
		Object returnValue;
		if (value == null)
		{
			returnValue = "";
		}else 
			returnValue = value;
		return returnValue;
	}
	
	/**
	 * Checks if is null or empty.
	 * 
	 * @param input
	 *            List<T> :  list
	 * @return true, if input is null or Empty
	 */
	public static <T> boolean isEmpty(final List<T> list)
	{
		return list == null || list.isEmpty();
	}
	
	/**
	 * Checks if is null or empty.
	 * 
	 * @param input
	 *            String :  str
	 * @return true, if input is null or Zero
	 */
	public static boolean isEmpty(final String str)
	{
		return str == null || str.trim().length() == 0;
	}

}
