package com.project.config;



import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.project.interceptor.CheckLogin;
import com.project.interceptor.CheckManager;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
	  registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
      registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
      registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
      registry.addResourceHandler("/include/**").addResourceLocations("/js/").setCachePeriod(31556926);
  }

  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
      configurer.enable();
  }
  
  public void addInterceptors (InterceptorRegistry registry) {
	  String[] listLoginExcludePath = {"/home.htm", "/contact.htm", "/about.htm", "/class.htm", "/team.htm", "/blog.htm", "/detail.htm", "/testimonial.htm", "/login.htm", "/captcha"};
	  registry.addInterceptor(new CheckLogin()).addPathPatterns("/**").excludePathPatterns(listLoginExcludePath);
	  registry.addInterceptor(new CheckManager()).addPathPatterns("/dashboard-class.htm");
	  registry.addInterceptor(new CheckManager()).addPathPatterns("/search-class.htm");
	  registry.addInterceptor(new CheckManager()).addPathPatterns("/new-class.htm");
	  registry.addInterceptor(new CheckManager()).addPathPatterns("/edit-class.htm");
	  registry.addInterceptor(new CheckManager()).addPathPatterns("/add-package.htm");
	  registry.addInterceptor(new CheckManager()).addPathPatterns("/edit-package.htm");
	  registry.addInterceptor(new CheckManager()).addPathPatterns("/change-package-status.htm");
	  registry.addInterceptor(new CheckManager()).addPathPatterns("/dashboard.htm");
	  registry.addInterceptor(new CheckManager()).addPathPatterns("/employee.htm");
	  registry.addInterceptor(new CheckManager()).addPathPatterns("/search-employee.htm");
	  registry.addInterceptor(new CheckManager()).addPathPatterns("/add-employee.htm");
	  registry.addInterceptor(new CheckManager()).addPathPatterns("/add-account.htm");
	  registry.addInterceptor(new CheckManager()).addPathPatterns("/change-account-status.htm");
	  registry.addInterceptor(new CheckManager()).addPathPatterns("/edit-employee.htm");
	  registry.addInterceptor(new CheckManager()).addPathPatterns("/statistic.htm");
  }
}