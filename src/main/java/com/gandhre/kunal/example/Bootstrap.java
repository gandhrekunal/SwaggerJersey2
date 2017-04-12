package com.gandhre.kunal.example;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.models.Contact;
import io.swagger.models.Info;
import io.swagger.models.License;
import io.swagger.models.Swagger;

/**
 *
 * @author
 * @since  1.1.0.137
 * @Created Apr 11, 2017
 */
public class Bootstrap extends HttpServlet {
    private static final long serialVersionUID = -7688336166061191200L;
    private static final Logger log = LoggerFactory.getLogger(Bootstrap.class);

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        if (true) {
            Info info = new Info().title("REST API")
                    .termsOfService(
                            "http://www.gandhre.in/us/en/terms-of-use.html")
                    .contact(new Contact().email("gandhre.kunal@gmail.com"))
                    .version("1.0").description("Build Number : 0.0.1")
                    .license(new License().name("Apache License 2.0").url(
                            "http://www.apache.org/licenses/LICENSE-2.0.html"));
            Swagger swagger = new Swagger().info(info);
            new SwaggerContextService().withServletConfig(config)
                    .updateSwagger(swagger);

            BeanConfig beanConfig = new BeanConfig();
            beanConfig.setVersion("1.0");
            beanConfig.setSchemes(new String[] { "http", "https" });
            beanConfig.setBasePath("/api");
            beanConfig.setResourcePackage("com.gandhre");
            beanConfig.setScan(true);
            beanConfig.setInfo(info);
            beanConfig.setServletConfig(config);
            if (log.isInfoEnabled()) {
                log.info("Swagger Bootstrap INIT DONE");
            }
        } else {
            if (log.isInfoEnabled()) {
                log.info("Swagger Bootstrap INIT is Disabled");
            }
        }
    }
}
