package icu.fangkehou.dapr.feign.annotation;

import java.lang.annotation.*;

/**
 * Let OpenFeign create Client Proxy using Dapr Client to handle requests.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UseDaprClient {
}
