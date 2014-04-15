package com.std.pack.service;

import java.util.Date;
import java.util.Map;

/**
 * 
 * @author SXCHAH
 * @since April, 10, 2014
 * @param <E>
 */
public interface BaseService<E> {
	void saveOrUpdateData(E object);

	E searchById(String paramColumn, Long paramValue);

	Map<String, Object> findAllData(String fieldOrder);

	Map<String, Object> findDataByCriteria(final E object, String fieldOrder,
			String[] fieldExclude);

	Date getSysdate();

	void wildcardModelString(E object);
	
	int countData(final E object);
}
