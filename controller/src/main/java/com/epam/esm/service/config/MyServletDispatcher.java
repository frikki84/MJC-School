package com.epam.esm.service.config;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;


public class MyServletDispatcher
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    public static final String PROD_PROFILE = "prod";
    public static final String DEV_PROFILE = "dev";


    public static final String ACTIVE_PROFILE_PARAM = "spring.profiles.active";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        servletContext.setInitParameter(ACTIVE_PROFILE_PARAM, PROD_PROFILE);
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ControllerConfig.class, DbConfiguration.class, ServiceConfig.class, TestConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }


}
