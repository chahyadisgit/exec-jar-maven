/**
 * 
 */
package com.std.pack.serviceImpl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ch.qos.logback.classic.Logger;

import com.std.pack.constantas.TxJpaConstanta;
import com.std.pack.service.BaseService;

/**
 * @author SXCHAH
 * @since April, 10, 2014
 */
@Transactional(readOnly = true)
public class BaseServiceImpl<E> implements BaseService<E> {

	protected Logger log = (Logger) LoggerFactory.getLogger(this.getClass());

	@Autowired
	public SessionFactory sessionFactory;

	/**
	 * Domain Class.
	 */
	protected Class<E> domainClass;

	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {
		this.domainClass = (Class<E>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * transaction save or update.
	 * 
	 * @param E
	 *            {@link Object}
	 *            <p/>
	 *            as the object data.
	 */
	@Transactional(readOnly = false)
	public void saveOrUpdateData(E object) {
		sessionFactory.getCurrentSession().saveOrUpdate(object);
	}

	/**
	 * Search data by id.
	 * 
	 * @param paramColumn
	 *            {@link String}
	 *            <p/>
	 *            column name as parameter.
	 * @param paramValue
	 *            {@link Long}
	 *            <p/>
	 *            parameter value.
	 */
	@SuppressWarnings("unchecked")
	public E searchById(String paramColumn, Long paramValue) {
		Field field;
		try {
			field = domainClass.getDeclaredField(paramColumn);
			field.setAccessible(true);
			return (E) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from " + domainClass.getName() + " o where o."
									+ field.getName() + " = :id")
					.setParameter("id", new Long(paramValue)).uniqueResult();
		} catch (SecurityException e) {
			log.error("SecurityException: {}", e.getMessage());
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			log.error("NoSuchFieldException: {}", e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * find all data.
	 * 
	 * @param fieldOrder
	 *            {@link String}
	 *            <p/>
	 *            the field as data order.
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> findAllData(String fieldOrder) {
		Map<String, Object> map = new HashMap<String, Object>();

		Integer countData = ((Long) sessionFactory.getCurrentSession()
				.createCriteria(domainClass.getName())
				.setProjection(Projections.rowCount()).uniqueResult())
				.intValue();

		map.put(TxJpaConstanta.RESULT_COUNT, countData);

		if (countData > 0) {

			List<E> result = sessionFactory.getCurrentSession()
					.createCriteria(domainClass.getName())
					.addOrder(Order.asc(fieldOrder)).list();
			map.put(TxJpaConstanta.RESULT_LIST, result);
		}

		return map;
	}

	/**
	 * find or search data by criteria object.
	 * 
	 * @param E
	 *            {@link Object}
	 *            <p/>
	 *            as the object filter.
	 * @param fieldOrder
	 *            {@link String} the field as data order.
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> findDataByCriteria(final E object,
			String fieldOrder, String[] fieldExclude) {
		// set filter value string
		wildcardModelString(object);
		log.info(object.toString());
		Map<String, Object> map = new HashMap<String, Object>();
		Example example = Example.create(object).ignoreCase().enableLike();
		for (String s : fieldExclude) {
			example.excludeProperty(s);
		}

		Integer countData = ((Long) sessionFactory.getCurrentSession()
				.createCriteria(domainClass.getName()).add(example)
				.setProjection(Projections.rowCount()).uniqueResult())
				.intValue();
		map.put(TxJpaConstanta.RESULT_COUNT, countData);

		if (countData > 0) {
			List<E> result = sessionFactory.getCurrentSession()
					.createCriteria(domainClass.getName()).add(example)
					.addOrder(Order.asc(fieldOrder)).list();
			map.put(TxJpaConstanta.RESULT_LIST, result);
		}

		return map;
	}

	/**
	 * get datetime value on the server
	 */
	public Date getSysdate() {
		return (Date) sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT SYSDATE FROM DUAL").uniqueResult();
	}

	/**
	 * add wildcard for query string
	 */
	public void wildcardModelString(E object) {
		Field[] field = object.getClass().getDeclaredFields();
		try {
			for (Field f : field) {
				if (f.getName().equals("serialVersionUID")) {
					continue;
				} else if (f.getType().isInstance(new String())) {
					f.setAccessible(true);
					f.set(object,
							"%"
									+ (null == f.get(object)
											|| "?".equals(f.get(object)) ? ""
											: f.get(object)) + "%");
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}

	public int countData(E object) {
		Integer totalData = ((Long) sessionFactory.getCurrentSession()
				.createCriteria(domainClass.getName())
				.setProjection(Projections.rowCount()).uniqueResult())
				.intValue();
		
		return totalData;
	}

}
