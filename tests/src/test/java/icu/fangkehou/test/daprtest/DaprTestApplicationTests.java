package icu.fangkehou.test.daprtest;

import icu.fangkehou.dapr.client.autoconfigure.DaprClientAutoConfiguration;
import icu.fangkehou.dapr.client.config.DaprClientConfig;
import icu.fangkehou.test.daprtest.feign.DaprFeignTestClient;
import icu.fangkehou.test.daprtest.feign.DefaultFeignTestClient;
import io.dapr.client.DaprClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Import(DaprClientAutoConfiguration.class)
class DaprTestApplicationTests {

	@Autowired
	DaprClientConfig daprClientConfig;

	@Autowired
	DaprFeignTestClient daprFeignClient;

	@Autowired
	DefaultFeignTestClient defaultFeignClient;

	@Autowired
	DaprClient daprClient;

	@Test
	void contextLoads() {
	}

	@Test
	void testClientConfig() {
		System.out.println(daprClientConfig.getSidecarIp());

		assertEquals(daprClientConfig.getSidecarIp(), "127.0.0.1");
	}

	@Test
	void testDaprClient() {
		Mono<Void> result = daprClient.waitForSidecar(2000);

		result.block();
	}

	@Test
	void testDefaultFeignClient() {
		defaultFeignClient.getQuery();
	}

	@Test
	void testDaprFeignClient() {
		daprFeignClient.getQuery();
	}

}
