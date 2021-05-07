package spittr.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { RootConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        // to register one or more filters and map them to DispatcherServlet
        // return new Filter[] { new myFilter(); }
        return super.getServletFilters();
    }

    /*
            What you can do with this method:
            1. set the load-on-startup priority by calling setLoadOnStartup()
            2. set an initialization parameter by calling setInitParameter(),
            3. call setMultipartConfig() to configure Servlet 3.0 multipart support
         */
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        // to enable multipart request
        // setting up multipart support to temporarily store uploaded files at
        registration.setMultipartConfig(new MultipartConfigElement("tmp/spittr/upload", 2097152, 4194304, 0));
    }

}
