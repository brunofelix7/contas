package br.com.caelum.contas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import br.com.caelum.contas.interceptor.AutorizadorInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "br.com.caelum.contas")
public class AppWebConfiguration extends WebMvcConfigurerAdapter{

   @Override
   public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
      configurer.enable();
   }

   @Bean
   public InternalResourceViewResolver internalResourceViewResolver(){
      InternalResourceViewResolver resolver = new InternalResourceViewResolver();
      resolver.setPrefix("/WEB-INF/views/");
      resolver.setSuffix(".jsp");
      return resolver;
   }
   
   @Bean
   public AutorizadorInterceptor localInterceptor() {
        return new AutorizadorInterceptor();
   }

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(localInterceptor());
   }
}
