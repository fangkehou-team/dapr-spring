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

package icu.fangkehou.dapr.secretstore.configdata;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.springframework.boot.BootstrapRegistry;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.context.config.*;
import org.springframework.boot.context.properties.bind.BindHandler;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.logging.DeferredLogFactory;
import org.springframework.core.Ordered;

import icu.fangkehou.dapr.client.config.DaprClientConfig;
import icu.fangkehou.dapr.secretstore.config.DaprClientSecretStoreConfigManager;
import icu.fangkehou.dapr.secretstore.config.DaprSecretStoreConfig;

public class DaprSecretStoreConfigDataLocationResolver
        implements ConfigDataLocationResolver<DaprSecretStoreConfigDataResource>, Ordered {

    public static final String PREFIX = "dapr:secret:";

    private final Log log;

    public DaprSecretStoreConfigDataLocationResolver(DeferredLogFactory logFactory) {
        this.log = logFactory.getLog(getClass());
    }

    /**
     * Returns if the specified location address contains dapr prefix.
     *
     * @param context the location resolver context
     * @param location the location to check.
     * @return if the location is supported by this resolver
     */
    @Override
    public boolean isResolvable(ConfigDataLocationResolverContext context, ConfigDataLocation location) {
        log.debug(String.format("checking if %s suits for dapr secret", location.toString()));
        return location.hasPrefix(PREFIX);
    }

    /**
     * Resolve a {@link ConfigDataLocation} into one or more {@link ConfigDataResource} instances.
     *
     * @param context the location resolver context
     * @param location the location that should be resolved
     * @return a list of {@link ConfigDataResource resources} in ascending priority order.
     * @throws ConfigDataLocationNotFoundException on a non-optional location that cannot be found
     * @throws ConfigDataResourceNotFoundException if a resolved resource cannot be found
     */
    @Override
    public List<DaprSecretStoreConfigDataResource> resolve(ConfigDataLocationResolverContext context,
            ConfigDataLocation location)
            throws ConfigDataLocationNotFoundException, ConfigDataResourceNotFoundException {

        DaprSecretStoreConfig daprSecretStoreConfig = loadProperties(context);
        DaprClientConfig daprClientConfig = loadClientProperties(context);

        ConfigurableBootstrapContext bootstrapContext = context
                .getBootstrapContext();

        registerConfigManager(daprSecretStoreConfig, daprClientConfig, bootstrapContext);

        List<DaprSecretStoreConfigDataResource> result = new ArrayList<>();

        String[] secretConfig = location.getNonPrefixedValue(PREFIX).split("/");

        log.info(String.format("there is %d of values in secretConfig", secretConfig.length));

        switch (secretConfig.length) {
            case 2 -> {
                log.debug("Dapr Secret Store now gains store name: '" + secretConfig[0] + "' and secret name: '"
                        + secretConfig[1] + "' secret store for config");
                result.add(
                        new DaprSecretStoreConfigDataResource(location.isOptional(), secretConfig[0], secretConfig[1]));
            }
            case 1 -> {
                log.debug("Dapr Secret Store now gains store name: '" + secretConfig[0] + "' secret store for config");
                result.add(new DaprSecretStoreConfigDataResource(location.isOptional(), secretConfig[0], null));
            }
            default -> throw new ConfigDataLocationNotFoundException(location);
        }

        return result;
    }

    /**
     * @return
     */
    @Override
    public int getOrder() {
        return -1;
    }

    private void registerConfigManager(DaprSecretStoreConfig properties,
            DaprClientConfig clientConfig,
            ConfigurableBootstrapContext bootstrapContext) {
        if (!bootstrapContext.isRegistered(DaprClientSecretStoreConfigManager.class)) {
            bootstrapContext.register(DaprClientSecretStoreConfigManager.class,
                    BootstrapRegistry.InstanceSupplier
                            .of(new DaprClientSecretStoreConfigManager(properties, clientConfig)));
        }
    }

    protected DaprSecretStoreConfig loadProperties(
            ConfigDataLocationResolverContext context) {
        Binder binder = context.getBinder();
        BindHandler bindHandler = getBindHandler(context);

        DaprSecretStoreConfig nacosConfigProperties;
        if (context.getBootstrapContext().isRegistered(DaprSecretStoreConfig.class)) {
            nacosConfigProperties = context.getBootstrapContext()
                    .get(DaprSecretStoreConfig.class);
        } else {
            nacosConfigProperties = binder
                    .bind(DaprSecretStoreConfig.PROPERTY_PREFIX, Bindable.of(DaprSecretStoreConfig.class),
                            bindHandler)
                    .orElseGet(DaprSecretStoreConfig::new);
        }

        return nacosConfigProperties;
    }

    protected DaprClientConfig loadClientProperties(
            ConfigDataLocationResolverContext context) {
        Binder binder = context.getBinder();
        BindHandler bindHandler = getBindHandler(context);

        DaprClientConfig daprClientConfig;
        if (context.getBootstrapContext().isRegistered(DaprClientConfig.class)) {
            daprClientConfig = context.getBootstrapContext()
                    .get(DaprClientConfig.class);
        } else {
            daprClientConfig = binder
                    .bind(DaprClientConfig.PROPERTY_PREFIX, Bindable.of(DaprClientConfig.class),
                            bindHandler)
                    .orElseGet(DaprClientConfig::new);
        }

        return daprClientConfig;
    }

    private BindHandler getBindHandler(ConfigDataLocationResolverContext context) {
        return context.getBootstrapContext().getOrElse(BindHandler.class, null);
    }
}
