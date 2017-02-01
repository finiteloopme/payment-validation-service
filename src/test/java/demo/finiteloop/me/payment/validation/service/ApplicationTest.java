/*
 * Copyright 2005-2016 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package demo.finiteloop.me.payment.validation.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import demo.finiteloop.me.payment.validation.service.config.ConfigurationItems;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Paths;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    
    @Test
    public void checkServiceStatus() throws IOException{

        //FileInputStream fis = new FileInputStream("src/main/resources/PaymentValidationRequest.xml");
        String request = new String(
        		java.nio.file.Files.readAllBytes(Paths.get("src/main/resources/PaymentValidationRequest.xml"))
        		);
    	ResponseEntity<String> responseEntity = restTemplate.postForEntity(ConfigurationItems.SERVICE_ENDPOINT + "/", request, String.class);
    	assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}