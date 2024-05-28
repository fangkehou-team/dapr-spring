package icu.fangkehou.dapr.feign.targeter;

import feign.Client;
import feign.Feign;
import feign.Target;
import icu.fangkehou.dapr.feign.DaprInvokeFeignClient;
import icu.fangkehou.dapr.feign.annotation.UseDaprClient;
import org.springframework.cloud.openfeign.FeignClientFactory;
import org.springframework.cloud.openfeign.FeignClientFactoryBean;
import org.springframework.cloud.openfeign.Targeter;

import java.lang.reflect.Field;

public class DaprClientTargeter implements Targeter {

    private DaprInvokeFeignClient daprInvokeFeignClient;

    public DaprClientTargeter(DaprInvokeFeignClient daprInvokeFeignClient) {
        this.daprInvokeFeignClient = daprInvokeFeignClient;
    }

    @Override
    public <T> T target(FeignClientFactoryBean factory, Feign.Builder feign, FeignClientFactory context, Target.HardCodedTarget<T> target) {
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
