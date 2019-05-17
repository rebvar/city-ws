package com.rebvar.location_app.backend.ws;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * The Class SpringApplicationContext.
 */
public class SpringApplicationContext implements ApplicationContextAware {
	
	/** The context. */
	private static ApplicationContext CONTEXT;

    /**
     * Sets the application context.
     *
     * @param context the new application context
     * @throws BeansException the beans exception
     */
    @Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		CONTEXT = context;
	}

	/**
	 * Gets the bean.
	 *
	 * @param beanName the bean name
	 * @return the bean
	 */
	public static Object getBean(String beanName) {
		return CONTEXT.getBean(beanName);
	}
}