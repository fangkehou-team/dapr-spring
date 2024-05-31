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

package icu.fangkehou.dapr.client.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import icu.fangkehou.dapr.client.config.DaprClientConfig;
import io.dapr.client.DaprClient;

@SpringBootTest(properties = {
        "dapr.client.sidecar-ip=102.102.102.102",
        "dapr.client.timeout=5000",
        "dapr.client.sidecar-connect-wait-millis=5000"
})
@ExtendWith(SpringExtension.class)
public class DaprClientConfigTest {

    @MockBean
    DaprClient daprClient;

    @Autowired
    DaprClientConfig daprClientConfig;

    @Test
    void DaprClientConfig_DefaultValueConfig() {
        assertEquals(daprClientConfig.getHttpPort(), 3500);
        assertEquals(daprClientConfig.getGrpcPort(), 50001);

        assertEquals(daprClientConfig.getStringCharset(), StandardCharsets.UTF_8);
    }

    @Test
    void DaprClientConfig_OverrideValueConfig() {
        assertEquals(daprClientConfig.getTimeout(), Duration.ofMillis(5000L));

        assertEquals(daprClientConfig.getSidecarIp(), "102.102.102.102");

        assertEquals(daprClientConfig.getSidecarConnectWaitMillis(), 5000);
    }
}
