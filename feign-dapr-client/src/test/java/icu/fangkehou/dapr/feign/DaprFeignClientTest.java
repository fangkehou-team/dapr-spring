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

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.junit.jupiter.api.Test;

import feign.*;

public class DaprFeignClientTest {

    @Test
    void daprClientCreateTest() {
        JaxRsTestInterface repository = newBuilder().target(JaxRsTestInterface.class, "binding://myBinding");

        System.out.println(repository);
    }

    public Feign.Builder newBuilder() {
        return Feign.builder().client(new DaprInvokeFeignClient());
    }

    @Path("/")
    public interface JaxRsTestInterface {
        @PUT
        @Path("/withBody")
        public String withBody(@QueryParam("foo") String foo, String bar);

        @PUT
        @Path("/withoutBody")
        public String withoutBody(@QueryParam("foo") String foo);

        @GET
        @Path("/withOptions")
        public String withOptions(Request.Options options);
    }
}
