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

package icu.fangkehou.test.daprtest.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import icu.fangkehou.dapr.feign.annotation.UseDaprClient;

@FeignClient(name = "dapr", url = "method.myApp")
@UseDaprClient
public interface DaprFeignTestClient {

    @GetMapping("/create")
    String getQuery();

}
