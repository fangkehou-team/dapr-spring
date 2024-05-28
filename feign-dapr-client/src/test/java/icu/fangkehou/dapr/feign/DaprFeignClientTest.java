package icu.fangkehou.dapr.feign;

import feign.*;
import org.junit.jupiter.api.Test;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

public class DaprFeignClientTest{

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
