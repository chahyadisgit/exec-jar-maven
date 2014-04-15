package com.std.pack;

import java.util.Properties;

import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ch.qos.logback.classic.Logger;

import com.std.pack.controller.SHolidayController;
import com.std.pack.utility.PropertiesReader;

/**
 * Hello world!
 * 
 */
public class App {
	private static Logger log = (Logger) LoggerFactory.getLogger("App");

	public static void main(String[] args) {
		Properties props = PropertiesReader.readPropsIn();
		System.out.println(">> " + props.getProperty("say.hello"));
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
			SHolidayController sHolidayController = context.getBean(SHolidayController.class);
			sHolidayController.showAll();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
