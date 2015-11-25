package co.chimeralabs.advertiser.server.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TypeConversion {
	private static final Logger logger = LoggerFactory.getLogger(TypeConversion.class);
	
	public static Long StringToLong(String str){
		try{
			return Long.parseLong(str.trim());
		}
		catch(NumberFormatException e){
			logger.debug("Number format exception in String to Long conversion" + e);
			return null;
		}
	}
	
	public static Double StringToDouble(String str){
		try{
			return Double.parseDouble(str.trim());
		}
		catch(NumberFormatException e){
			logger.debug("Number format exception in String to Double conversion" + e);
			return null;
		}
	}
	
	public static Date StringToDate(String str, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			Date date = sdf.parse(str);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.debug("Parse Exception in String to Date conversion" + e);
			return null;
		}
	}
}
