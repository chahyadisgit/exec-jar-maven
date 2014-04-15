package com.std.pack.model;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

public class BaseModel<E> {
	
	protected Class<E> domainClass;

	@SuppressWarnings("unchecked")
	public BaseModel() {
		try {
			this.domainClass = (Class<E>) ((ParameterizedType) this.getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
		} catch (Exception e) {
		}
	}

	public String toString() {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("[SOUT " + domainClass.getName() + "] --> ");
		try {
			Field[] fields = domainClass.getDeclaredFields();

			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				if (fields[i].getName().equals("serialVersionUID")) {
					continue;
				}
				strBuffer.append(fields[i].getName()).append(" = ")
						.append(fields[i].get(this)).append(", ");
			}
			strBuffer.deleteCharAt(strBuffer.length() - 2);
		} catch (IllegalAccessException iae) {
			System.out.println(">> error : " + iae);
		}
		return strBuffer.toString();
	}
}
