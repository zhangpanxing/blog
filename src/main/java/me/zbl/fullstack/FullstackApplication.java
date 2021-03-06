package me.zbl.fullstack;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
public class  FullstackApplication {

  public static void main(String[] args) {
    SpringApplication.run(FullstackApplication.class, args);

  }


// @Bean
//  public EmbeddedServletContainerFactory servletContainer() {
//    TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
//      @Override
//      protected void postProcessContext(Context context) {
//        SecurityConstraint constraint = new SecurityConstraint();
//        constraint.setUserConstraint("CONFIDENTIAL");
//        SecurityCollection collection = new SecurityCollection();
//        collection.addPattern("/*");
//        constraint.addCollection(collection);
//        context.addConstraint(constraint);
//      }
//    };
//    tomcat.addAdditionalTomcatConnectors(httpConnector());
//    return tomcat;
//  }
//
//  //配置http转https
//  @Bean
//  public Connector httpConnector() {
//    Connector connector = new Connector(TomcatEmbeddedServletContainerFactory.DEFAULT_PROTOCOL);
//    connector.setScheme("http");
////Connector监听的http的端口号
//    connector.setPort(8080);
//    connector.setSecure(false);
////监听到http的端口号后转向到的https的端口号
//    connector.setRedirectPort(443);
//    return connector;
//  }

  //这里设置默认端口为443，即https的
  public void customize(ConfigurableEmbeddedServletContainer container) {
    container.setPort(443);
  }

}
