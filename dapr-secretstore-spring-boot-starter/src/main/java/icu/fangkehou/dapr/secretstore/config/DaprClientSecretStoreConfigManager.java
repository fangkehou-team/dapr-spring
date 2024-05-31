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

package icu.fangkehou.dapr.secretstore.config;

import icu.fangkehou.dapr.client.config.DaprClientConfig;
import icu.fangkehou.dapr.client.utils.SetupDaprPropertyUtil;
import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import io.dapr.client.DaprPreviewClient;


public class DaprClientSecretStoreConfigManager {

    private static DaprClient daprClient;
    private static DaprPreviewClient daprPreviewClient;
    private final DaprSecretStoreConfig daprSecretStoreConfig;
    private final DaprClientConfig daprClientConfig;

    public DaprClientSecretStoreConfigManager(DaprSecretStoreConfig daprSecretStoreConfig,
            DaprClientConfig daprClientConfig) {
        this.daprSecretStoreConfig = daprSecretStoreConfig;
        this.daprClientConfig = daprClientConfig;

        SetupDaprPropertyUtil.setupDaprSystemProperty(daprClientConfig);

        DaprClientBuilder daprClientBuilder = new DaprClientBuilder();

        if (DaprClientSecretStoreConfigManager.daprClient == null) {
            DaprClientSecretStoreConfigManager.daprClient = daprClientBuilder.build();
        }

        if (DaprClientSecretStoreConfigManager.daprPreviewClient == null) {
            DaprClientSecretStoreConfigManager.daprPreviewClient = daprClientBuilder.buildPreviewClient();
        }
    }

    public static DaprPreviewClient getDaprPreviewClient() {
        return DaprClientSecretStoreConfigManager.daprPreviewClient;
    }

    public static DaprClient getDaprClient() {
        return DaprClientSecretStoreConfigManager.daprClient;
    }

    public DaprSecretStoreConfig getDaprSecretStoreConfig() {
        return daprSecretStoreConfig;
    }

    public DaprClientConfig getDaprClientConfig() {
        return daprClientConfig;
    }
}
