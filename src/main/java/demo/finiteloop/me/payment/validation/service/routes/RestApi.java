package demo.finiteloop.me.payment.validation.service.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import demo.finiteloop.me.payment.validation.service.config.ConfigurationItems;

@Component
public class RestApi extends RouteBuilder {

        @Override
        public void configure() {
            restConfiguration()
                .contextPath(ConfigurationItems.SERVICE_ENDPOINT).apiContextPath("/api-doc")
                    .apiProperty("api.title", "Docs for Payment Validation Service REST API")
                    .apiProperty("api.version", "1.0")
                    .apiProperty("cors", "true")
                    .apiContextRouteId("doc-api")
                .component("servlet")
                .bindingMode(RestBindingMode.off);

            rest("/")
            	//.get("/").description("Status check for the payment validation service")
            	.post().description("Validates payment requests")
            	.type(String.class)
                    .route().routeId("validation-request-api")
                    .log("VALIDATION SERVICE INVOKED")
                    .to("direct:" + ConfigurationItems.ROUTE_MULTICAST_THE_REQUEST)
                    .endRest();
        }
    }
