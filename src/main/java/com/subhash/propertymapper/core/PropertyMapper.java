package com.subhash.propertymapper.core;

import java.util.Properties;

/**
 * This interface provides a way to prepare objects from the properties
 * @author subhash.shah
 *
 * @param <T>
 */
public interface PropertyMapper<T> {
	
	/**
	 * Populates object from the properties passed in
	 * @param properties
	 * @return
	 */
	public T mapProperties(Properties properties);

}
