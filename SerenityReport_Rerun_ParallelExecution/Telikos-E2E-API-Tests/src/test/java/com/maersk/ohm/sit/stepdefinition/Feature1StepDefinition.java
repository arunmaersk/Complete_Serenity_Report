package com.maersk.ohm.sit.stepdefinition;


import com.maersk.ohm.sit.config.ConfigReader;
import helper.EndPoints;
import helper.RestAssuredHelper;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import models.request.PetOrderReq;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.maersk.ohm.sit.springapplication.CucumberSpringApplication;
import java.io.File;


@SpringBootTest(classes = CucumberSpringApplication.class)
public class Feature1StepDefinition {
    Scenario scenario;

    FilterableRequestSpecification requestSpecification = (FilterableRequestSpecification) SerenityRest.given();
    Response response;
    @Autowired
    ConfigReader configReader;

    @Autowired
    RestAssuredHelper restAssuredHelper;

    @BeforeStep
    public void before(Scenario scenario) {
        this.scenario = scenario;
        requestSpecification.baseUri(configReader.getBaseURI()).relaxedHTTPSValidation();
    }

    @Given("User provides the resource endPoint")
    public void user_provides_the_resource_end_point() {
        System.out.println("End Point: "+configReader.getBaseURI());
    }

    @Given("User provides the petId {string}")
    public void user_provides_the_pet_id(String id) {
        System.out.println("ID: " + id);
        System.out.println("Thread Name : "+Thread.currentThread().getName());
    }

    @Given("User provides the order quantity {string}")
    public void user_provides_the_order_quantity(String qty) {
        System.out.println("Qty: " + qty);
    }

    @Given("User provides the order status {string}")
    public void user_provides_the_order_status(String string) {
        System.out.println("Order status: Placed");
    }

    @When("User hits a POST request")
    public void user_hits_a_post_request() {
        PetOrderReq petOrderReq = new PetOrderReq();
        String path = configReader.getApiVersion() + EndPoints.TEMPLATE_PET_STORE_ORDER.getValue();
        requestSpecification
                .headers("Content-Type", "application/json")
                .basePath(path)
                .body(petOrderReq);
        response = restAssuredHelper.postRequest(requestSpecification);

    }

    @Then("Validate response status code is 200")
    public void validate_response_status_code_is() {
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Then("Validate response status code is 400")
    public void validate_response_status_code_is_400() {
        Assert.assertEquals(400, response.getStatusCode());
    }

    @Then("Validate schema of the api response")
    public void validate_schema_of_the_api_response() {
        File schema = new File(System.getProperty("user.dir") + "/src/test/resources/schema/PetOrderSchema.json");
        response.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(schema));
    }

    @Then("Validate response body is as expected")
    public void validate_response_body_is_as_expected() {
        System.out.println("Thread ID : "+Thread.currentThread().getId());
        //PetOrderReq petOrderReq = restAssuredHelper.mapToPojo(response, PetOrderReq.class);
        PetOrderReq petOrderReq = response.as(PetOrderReq.class);
        Assert.assertEquals("placed", petOrderReq.getStatus());
        Assert.assertEquals(Integer.valueOf(1), petOrderReq.getQuantity());

    }
}


