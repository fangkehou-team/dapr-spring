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

import icu.fangkehou.dapr.secretstore.config.DaprClientSecretStoreConfigManager;
import icu.fangkehou.test.daprtest.feign.DaprFeignTestClient;
import io.dapr.client.DaprClient;
import io.dapr.client.domain.InvokeMethodRequest;
import io.dapr.utils.TypeRef;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class DaprOpenFeignClientTest {

    static {
        try {
            DaprClient mockDaprClient = Mockito
                    .mock(DaprClient.class);

            Map<String, Map<String, String>> allConfigMap = new HashMap<>();

            Map<String, String> configMap = new HashMap<>();
            configMap.put("icu.fangkehou.daprsecrettest", "false");

            allConfigMap.put("setsall", configMap);

            Mockito.when(mockDaprClient.getBulkSecret(Mockito.eq("mysecret")))
                    .thenReturn(Mono.just(allConfigMap));

            ReflectionTestUtils.setField(DaprClientSecretStoreConfigManager.class, "daprClient",
                    mockDaprClient);

        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

    @MockBean
    DaprClient daprClient;

    @Autowired
    DaprFeignTestClient daprFeignTestClient;

    @Test
    void DaprOpenFeignClient_testFeignClient() {
        Mockito.when(daprClient.invokeMethod(Mockito.any(InvokeMethodRequest.class), Mockito.eq(TypeRef.BYTE_ARRAY)))
                .thenReturn(Mono.just("Hello World!".getBytes(StandardCharsets.UTF_8)));

        String result = daprFeignTestClient.getQuery();

        assertEquals(result, "Hello World!");
    }
}
