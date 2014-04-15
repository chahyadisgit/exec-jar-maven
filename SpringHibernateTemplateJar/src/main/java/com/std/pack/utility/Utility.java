package com.std.pack.utility;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * 
 * @author CHAHYADIS
 * @since Thursday, September 12, 2013
 */
public class Utility {

	public static String dateToStringConverter(String pattern, Date date) {
		if (pattern.equals("") || null == pattern) {
			pattern = "dd/MM/yyyy";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		return sdf.format(date);
	}

	public static Date StringToDateConverter(String pattern, String sDate) {
		if (pattern.equals("") || null == pattern) {
			pattern = "dd/MM/yyyy";
		}
		try {
			DateFormat format = new SimpleDateFormat(pattern);

			return (Date) format.parse(sDate);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Casting BigDecimal to String.
	 * <p/>
	 * 
	 * @param value
	 *            BigDecimal
	 *            <p/>
	 *            object for formating
	 * @param flag
	 *            boolean
	 *            <p/>
	 *            true for IDR format
	 * @return String
	 */
	public static String BigDecimalToStringConverter(BigDecimal value,
			boolean flag) {
		NumberFormat nf = new DecimalFormat("##0.00");
		String result = nf.format(value.doubleValue());
		if (flag) {
			result = result.replace(".", "x").replace(",", ".")
					.replace("x", ",");
		}
		return result;
	}

	/**
	 * get extention filename
	 * 
	 * @param filename
	 *            String
	 *            <p/>
	 *            nama file yang akan di proses.
	 * @param delimiter
	 *            String
	 *            <p/>
	 *            karakter pemisah.
	 * @return String
	 *         <p/>
	 *         berupa extension suatu file.
	 */
	public static String parseExtention(String filename, String delimiter) {
		if (delimiter.equals("")) {
			delimiter = ".";
		}
		StringTokenizer st = new StringTokenizer(filename, delimiter);
		String result = "";

		while (st.hasMoreTokens()) {
			result = st.nextToken();
		}
		
		return result;
	}
	
}
