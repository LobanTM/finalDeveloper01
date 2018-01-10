package com.loban.finalProject.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
//import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext context) throws ServletException {
        //Create and register root context
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(DataConfig.class);
        context.addListener(new ContextLoaderListener(rootContext));

        //Dispatcher's servlet context
//        AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
//        dispatcherServlet.register(WebConfig.class);
//
//        //Register and map servlet
//        ServletRegistration.Dynamic dispatcher = context.addServlet("dispatcher", new DispatcherServlet(dispatcherServlet));
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/");
    }
}
