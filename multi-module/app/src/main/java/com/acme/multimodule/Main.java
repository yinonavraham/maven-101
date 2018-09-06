package com.acme.multimodule;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  public static void main(String[] args) {
    try (ConfigurableApplicationContext appCtx = createApplicationContext()) {
      HelloService helloService = appCtx.getBean(HelloService.class);
      System.out.println(helloService.sayHello(args[0]));
    } catch (Exception e) {
      System.out.println("[ERROR] " + e);
      System.exit(1);
    }
  }

  private static ConfigurableApplicationContext createApplicationContext() {
    return new AnnotationConfigApplicationContext(Main.class.getPackage().getName());
  }
}
