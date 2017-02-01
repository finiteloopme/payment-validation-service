package demo.finiteloop.me.payment.validation.service.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import demo.finiteloop.me.payment.validation.service.config.ConfigurationItems;

@Component
public class FileSystemReader extends RouteBuilder {

        @Override
        public void configure() {
        	
        	from("file:" + ConfigurationItems.ROUTE_FILE_SYSTEM_LOCATION)
        		.routeId("file-system-reader")
        		.log("Reading a request from the file system: ${file:name}")
        		.to("direct:" + ConfigurationItems.ROUTE_MULTICAST_THE_REQUEST);
//        		.multicast()
//        			.to(ConfigurationItems.ROUTE_IBAN_VALIDATION_REQUEST)
//        			.to(ConfigurationItems.ROUTE_PAYMENT_METHOD_VALIDATION_REQUEST);

        }
    }
