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

package icu.fangkehou.dapr.feign;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import feign.Feign;
import feign.Headers;
import feign.RequestLine;
import feign.Response;
import io.dapr.client.DaprClient;
import io.dapr.client.domain.InvokeMethodRequest;
import io.dapr.utils.TypeRef;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public class DaprFeignClientMethodTest {

    @Mock
    DaprClient daprClient;

    @Test
    void DaprFeignClient_testMockMethodInvoke() {
        DaprFeignClientTestInterface repository =
                newBuilder().target(DaprFeignClientTestInterface.class, "http://method.myApp/");

        assertEquals(repository.getWithContentType().body().length(), 12);
    }

    public Feign.Builder newBuilder() {
        Mockito.when(daprClient.invokeMethod(Mockito.any(InvokeMethodRequest.class), Mockito.eq(TypeRef.BYTE_ARRAY)))
                .thenReturn(Mono.just("hello world!".getBytes(StandardCharsets.UTF_8)));

        return Feign.builder().client(new DaprInvokeFeignClient(daprClient));
    }

    public interface DaprFeignClientTestInterface {

        @RequestLine("GET /getAll")
        @Headers({"Accept: text/plain", "Content-Type: text/plain"})
        Response getWithContentType();

        @RequestLine("GET /abc")
        Response get();
    }
}
