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

package icu.fangkehou.dapr.feign.targeter;

import java.lang.reflect.Field;

import org.springframework.cloud.openfeign.FeignClientFactory;
import org.springframework.cloud.openfeign.FeignClientFactoryBean;
import org.springframework.cloud.openfeign.Targeter;

import feign.Client;
import feign.Feign;
import feign.Target;
import icu.fangkehou.dapr.feign.DaprInvokeFeignClient;
import icu.fangkehou.dapr.feign.annotation.UseDaprClient;

public class DaprClientTargeter implements Targeter {

    private DaprInvokeFeignClient daprInvokeFeignClient;

    public DaprClientTargeter(DaprInvokeFeignClient daprInvokeFeignClient) {
        this.daprInvokeFeignClient = daprInvokeFeignClient;
    }

    @Override
    public <T> T target(FeignClientFactoryBean factory, Feign.Builder feign, FeignClientFactory context,
            Target.HardCodedTarget<T> target) {
        Class<Feign.Builder> builderClass = Feign.Builder.class;

        Client defaultClient = null;

        try {
            Field clientField = builderClass.getDeclaredField("client");
            clientField.setAccessible(true);

            defaultClient = (Client) clientField.get(feign);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        Class<T> classOfT = target.type();
        UseDaprClient useDaprClient = classOfT.getAnnotation(UseDaprClient.class);

        if (useDaprClient != null) {
            feign.client(daprInvokeFeignClient);
        }

        T targetInstance = feign.target(target);

        feign.client(defaultClient);

        return targetInstance;
    }
}
