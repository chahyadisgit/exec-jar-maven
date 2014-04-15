/**
 * 
 */
package com.std.pack.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ch.qos.logback.classic.Logger;

import com.std.pack.service.SHolidayService;

/**
 * @author SXCHAH
 *
 */
@Controller
public class SHolidayController {
	Logger log = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SHolidayService sHolidayService;
	
	public void showAll() {
		System.out.println("try to query");
		int total = sHolidayService.countData(null);
		log.info(">> total: " + total);
	}
}
