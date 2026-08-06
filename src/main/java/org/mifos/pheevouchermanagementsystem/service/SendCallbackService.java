package org.mifos.pheevouchermanagementsystem.service;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SendCallbackService {

    private static final Logger logger = LoggerFactory.getLogger(SendCallbackService.class);

    public void sendCallback(String body, String callbackURL) {
        RequestSpecification requestSpec = new RequestSpecBuilder().build();
        requestSpec.relaxedHTTPSValidation();
        Response response = RestAssured.given().baseUri(callbackURL).header("Content-Type", ContentType.JSON).body(body).when().put();
        logger.info("Callback sent to {}, response status {}", callbackURL, response.getStatusCode());
    }

}
