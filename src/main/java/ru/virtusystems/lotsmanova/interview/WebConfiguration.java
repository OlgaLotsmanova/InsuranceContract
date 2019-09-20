package ru.virtusystems.lotsmanova.interview;

import org.h2.server.web.WebServlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	private static final String HOME_VIEW = "index";
	private static final String VIEW_PATH = "/";
	private static final String VIEW_TYPE = ".html";

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName(HOME_VIEW);
	}

	@Bean
	public InternalResourceViewResolver setupViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix(VIEW_PATH);
		resolver.setSuffix(VIEW_TYPE);

		return resolver;
	}

	@Bean
	ServletRegistrationBean<WebServlet> h2servletRegistration() {
		ServletRegistrationBean<WebServlet> registrationBean = new ServletRegistrationBean<WebServlet>(
				new WebServlet());
		registrationBean.addUrlMappings("/console/*");
		return registrationBean;
	}

}
