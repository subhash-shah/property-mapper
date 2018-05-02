package com.subhash.propertymapper.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

public class PropertyMapperImpl<T> implements PropertyMapper<T> {

	Logger logger = Logger.getLogger(PropertyMapperImpl.class.getName());

	private Class<T> mappedClass;

	private Method[] mappedMethods;
	
	private List<String> mappedAttributes;

	public PropertyMapperImpl(Class<T> mappedClass) {
		initialize(mappedClass);
	}

	private void initialize(Class<T> mappedClass) {
		this.mappedClass = mappedClass;
		this.mappedMethods = mappedClass.getMethods();
		this.mappedAttributes = getClassAttributes();
	}

	public T mapProperties(Properties properties) {
		T instance = null;
		try {
			instance = mappedClass.newInstance();
			for (String attribute : mappedAttributes) {
				setAttributeValue(instance, attribute,
						properties.get(attribute));
			}
		} catch (InstantiationException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		} catch (IllegalAccessException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
		return instance;
	}

	private List<String> getClassAttributes() {
		Field[] classAttributes = mappedClass.getDeclaredFields();
		List<String> attributes = new ArrayList<String>(classAttributes.length);
		for (Field f : classAttributes) {
			attributes.add(f.getName());
		}
		return attributes;
	}

	private void setAttributeValue(T object, String attributeName,
			Object attributeValue) {
		Method setterMethod;
		logger.log(Level.INFO, attributeName);
		setterMethod = getMethodByName(
				"set" + StringUtils.capitalize(attributeName));
		if(null != setterMethod) {
			try {
				Class<?> parameterTypeClass = setterMethod.getParameterTypes()[0];
				Constructor<?> parameterTypeClassConstructor = parameterTypeClass
						.getConstructor(String.class);
				Object o = parameterTypeClassConstructor
						.newInstance(attributeValue);
				setterMethod.invoke(object, o);
			} catch (IllegalArgumentException e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			} catch (NoSuchMethodException e) {
				logger.severe(e.getMessage());
			} catch (SecurityException e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			} catch (InstantiationException e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			} catch (IllegalAccessException e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			} catch (InvocationTargetException e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			}
		}
	}

	private Method getMethodByName(String methodName) {
		Method methodByName = null;
		for (Method method : mappedMethods) {
			if (methodName.equalsIgnoreCase(method.getName())) {
				methodByName = method;
				break;
			}
		}
		return methodByName;
	}
}
