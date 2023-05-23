package org.zerock.myapp.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;



@Log4j2
@NoArgsConstructor

//@WebListener
public class ContextListenerImpl implements ServletContextListener {
	
	
//	public ContextListenerImpl() {
//		log.trace("Default Constructor invoked.");
//				
//	} // Default Constructor
	

	@Override
    public void contextDestroyed(ServletContextEvent sce) {
		log.trace("contextDestroyed({}) invoked", sce);
		
    } // contextDestroyed


	@Override
    public void contextInitialized(ServletContextEvent sce) { 
		log.trace("contextInitialized({}) invoked", sce);
		
    } // contextInitialized
	
} // end class
