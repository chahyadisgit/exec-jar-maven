package com.std.pack.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author CHAHYADIS
 * @since January, 27, 2014
 */
public class PropertiesReader {
	private static Logger log = Logger.getLogger(PropertiesReader.class);

	/**
	 * Read internal properties file.
	 * <p/>
	 * Use this method for development.
	 * 
	 * @return {@link Properties}
	 */
	public static Properties readPropsIn() {
		Properties props = new Properties();
		InputStream in = PropertiesReader.class.getClassLoader()
				.getResourceAsStream("config.properties");

		try {
			props.load(in);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return props;
	}

	/**
	 * Read content of external properties file.
	 * <p/>
	 * Use this method for production.
	 * 
	 * @return {@link Properties}
	 */
	public static Properties readPropsExt() {
		Properties props = new Properties();
		String path = PropertiesReader.class.getProtectionDomain()
				.getCodeSource().getLocation().toString().replace("%20", " ");
		System.out.println(">> " + path);
		int i;
		if (path.substring(6).indexOf(":/") > 0) { // buat windows
			i = 6;
		} else {
			i = 5;
		}
		int p = Utility.parseExtention(path, "/").length();
		path = path.substring(i);
		System.out.println(">>1: " + path);
		path = path.substring(0, path.length() - p - 1);
		System.out.println(">>2: " + path);

		try {
			FileInputStream fis = new FileInputStream(new File(path
					+ File.separator + "config.properties"));
			props.load(fis);
			fis.close();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return props;
	}

}
