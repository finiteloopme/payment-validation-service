package demo.finiteloop.me.payment.validation.service.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import demo.finiteloop.me.payment.validation.service.config.ConfigurationItems;

@Component
public class PaymentMethodValidation extends RouteBuilder {

        @Override
        public void configure() {
        	
        	from("direct:" + ConfigurationItems.ROUTE_PAYMENT_METHOD_VALIDATION_REQUEST)
        		.routeId("payment-method-validation-request")
        		.setBody()
        			.xpath("PmtValRq/Document/IdVrfctnReq/SplmtryData/Envlp/SplValData/PmtMethodRq")
        			.log("Payment method request: ${body}");

        }
    }
