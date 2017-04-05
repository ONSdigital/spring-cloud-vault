package com.sample.controller;

import com.sample.config.NotifyConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RefreshScope
@RestController
public class HelloController {

  @Autowired
  private NotifyConfiguration notifyConfiguration;

  @Autowired
  private DiscoveryClient discoveryClient;

  @RequestMapping("/")
  public String index() {
    log.debug("Entrance of index...");

    List<String> serviceList = discoveryClient.getServices();
    log.debug("Services list:");
    for (String service: serviceList) {
      log.debug(service);
    }

    List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("samplesvc");
    log.debug("ServiceInstances list for samplesvc:");
    for (ServiceInstance serviceInstance: serviceInstanceList) {
      log.debug("host {}", serviceInstance.getHost());
      log.debug("metadata {}", serviceInstance.getMetadata());
      log.debug("port {}", serviceInstance.getPort());
      log.debug("service id {}", serviceInstance.getServiceId());
      log.debug("uri {}", serviceInstance.getUri());
    }

    return String.format("%s %s and Template is %s", "Greetings from Spring Boot: Key is",
            notifyConfiguration.getApiKey(), notifyConfiguration.getTemplateId());
  }

}
