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
import org.springframework.boot.context.config.*;
import org.springframework.boot.logging.DeferredLogFactory;
import org.springframework.core.Ordered;

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
        List<DaprSecretStoreConfigDataResource> result = new ArrayList<>();

        String[] secretConfig = location.getNonPrefixedValue(PREFIX).split("/");

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
}
