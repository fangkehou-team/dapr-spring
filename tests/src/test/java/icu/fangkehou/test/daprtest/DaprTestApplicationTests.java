/*
 * Copyright (c) 2016-2024 Team Fangkehou
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package icu.fangkehou.test.daprtest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import icu.fangkehou.dapr.client.autoconfigure.DaprClientAutoConfiguration;
import icu.fangkehou.dapr.client.config.DaprClientConfig;
import icu.fangkehou.test.daprtest.feign.DaprFeignTestClient;
import icu.fangkehou.test.daprtest.feign.DefaultFeignTestClient;
import io.dapr.client.DaprClient;
import reactor.core.publisher.Mono;

@SpringBootTest
@Import(DaprClientAutoConfiguration.class)
class DaprTestApplicationTests {

//    @Autowired
//    DaprClientConfig daprClientConfig;
//
//    @Autowired
//    DaprFeignTestClient daprFeignClient;
//
//    @Autowired
//    DefaultFeignTestClient defaultFeignClient;
//
//    @Autowired
//    DaprClient daprClient;
//
//    @Test
//    void contextLoads() {}
//
//    @Test
//    void testClientConfig() {
//        System.out.println(daprClientConfig.getSidecarIp());
//
//        assertEquals(daprClientConfig.getSidecarIp(), "127.0.0.1");
//    }
//
//    @Test
//    void testDaprClient() {
//        Mono<Void> result = daprClient.waitForSidecar(2000);
//
//        result.block();
//    }
//
//    @Test
//    void testDefaultFeignClient() {
//        defaultFeignClient.getQuery();
//    }
//
//    @Test
//    void testDaprFeignClient() {
//        daprFeignClient.getQuery();
//    }

}
