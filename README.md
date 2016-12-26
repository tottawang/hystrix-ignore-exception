# hystrix-ignore-exception
Investigate how to correctly ignore exception for circuit breaker when using RestTemplate and Hystrix

* When any connection timeout, socket read timeout, network connection issue during endpoint call happens, `RestTemplate` throws ResourceAccessException and then Hystrix re-throws `HystrixRuntimeException`, which is exactly a good case for Hystrix circuit breaker.
* When endpoint call returns 4xx, `RestTemplate` throws `HttpClientErrorException`.
* When endpoint call returns 5xx, `RestTemplate` throws `HttpServerErrorException`.

The right way to use `ignoreExceptions` feature is to ignore `HttpClientErrorException` and leave other types of exceptions to Hystrix to re-throw HystrixRuntimeException (CB enabled).

### Run and build
Step-1: 
./gradlew clean build

Step-2:
* export HYSTRIX_TEST_URL=ANY_TEST_GET_ENDPOINT
* java -jar build/libs/sample-0.0.1-SNAPSHOT.jar

