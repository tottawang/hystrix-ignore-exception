package com.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sample.conf.Application;
import com.sample.conf.HttpWebClient;

@Component
public class HystrixService {

  @Value("${HYSTRIX_TEST_URL:url_not_accessiable}")
  private String HYSTRIX_TEST_URL;

  @Autowired
  @Qualifier(Application.DEFAULT_REST_TEMPLATE)
  protected RestTemplate restTemplate;

  @HystrixCommand(groupKey = HttpWebClient.GROUP, commandKey = HttpWebClient.COMMAND_GET,
      threadPoolKey = HttpWebClient.THREAD_POOL_KEY,
      ignoreExceptions = {IntegrationRuntimeException.class})
  public String getContent() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
    HttpEntity<String> entity = new HttpEntity<String>(headers);
    try {
      ResponseEntity<String> responseEntity =
          restTemplate.exchange(HYSTRIX_TEST_URL, HttpMethod.GET, entity, String.class);
      return responseEntity.getBody();
    } catch (HttpClientErrorException ex) {
      throw new IntegrationRuntimeException(
          "client exception, can be ingored for hystrix circuit breaker", ex);
    }
  }
}
