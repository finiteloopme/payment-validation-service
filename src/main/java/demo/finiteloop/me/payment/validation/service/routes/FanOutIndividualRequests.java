package demo.finiteloop.me.payment.validation.service.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import demo.finiteloop.me.payment.validation.service.config.ConfigurationItems;

@Component
public class FanOutIndividualRequests extends RouteBuilder {

        @Override
        public void configure() {
        	
        	from("direct:" + ConfigurationItems.ROUTE_MULTICAST_THE_REQUEST)
        		.routeId("fan-out-requests")
        		// TODO: the route fails with parsing exception if below is uncommented
        		// see http://stackoverflow.com/questions/10022796/why-am-i-getting-this-error-premature-end-of-file
        		//.log("Received a payment validation request: ${body}")
        		.convertBodyTo(Document.class)
        		.multicast()
        			.to("direct:" + ConfigurationItems.ROUTE_IBAN_VALIDATION_REQUEST)
        			.to("direct:" + ConfigurationItems.ROUTE_PAYMENT_METHOD_VALIDATION_REQUEST);

        }
    }
