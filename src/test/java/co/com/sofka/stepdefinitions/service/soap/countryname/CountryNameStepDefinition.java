package co.com.sofka.stepdefinitions.service.soap.countryname;

import co.com.sofka.ServiceSetup;
import co.com.sofka.models.CountryNameModel;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import static co.com.sofka.questions.ReturnSoapServiceResponse.returnSoapServiceResponse;
import static co.com.sofka.task.DoPost.doPost;
import static co.com.sofka.utils.FileUtilities.readFile;
import static co.com.sofka.utils.service.soap.countryname.Patch.COUNTRYNAME;
import static co.com.sofka.utils.service.soap.countryname.Response.COUNTRYNAME_RESPONSE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

public class CountryNameStepDefinition extends ServiceSetup {

    private static final Logger LOGGER = Logger.getLogger(CountryNameStepDefinition.class);
    CountryNameModel countryName = new CountryNameModel();

    @Given("the user wants to search for the country with ISO code {string}")
    public void theUserWantsToSearchForTheCountryWithISOCode(String countryCode) {

        try {
            super.setup();
            countryName = new CountryNameModel();
            countryName.setIsoCode(countryCode);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }

    }

    @When("the user makes the request and confirms the action")
    public void theUserMakesTheRequestAndConfirmsTheAction() {
        try {
            actor.attemptsTo(
                    doPost()
                            .withTheResource(RESOURCE)
                            .andTheHeaders(super.headers())
                            .andTheBodyRequest(bodyRequest())
            );
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
    }

    @Then("the user should see the name of the country {string}")
    public void theUserShouldSeeTheNameOfTheCountry(String country) {
        try {
            countryName.setCountryName(country);
            actor.should(
                    seeThatResponse("El código de respuesta HTTP debe ser: ",
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat("El resultado de la peticion es :  ",
                            returnSoapServiceResponse(),
                            containsString(bodyResponse()))
            );
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
    }

    @Given("the user wish to search for the country with ISO code {string}")
    public void theUserWishToSearchForTheCountryWithISOCode(String wrongCode) {
        try {
            super.setup();
            countryName = new CountryNameModel();
            countryName.setIsoCode(wrongCode);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
    }

    @When("the user enters the search data and sends the new request")
    public void theUserEntersTheSearchDataAndSendsTheNewRequest() {
        try {
            actor.attemptsTo(
                    doPost()
                            .withTheResource(RESOURCE)
                            .andTheHeaders(super.headers())
                            .andTheBodyRequest(bodyRequest())
            );
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
    }

    @Then("the user should be able to see the message {string}")
    public void theUserShouldBeAbleToSeeTheMessage(String countryMessage) {
        try {
            countryName.setCountryName(countryMessage);
            actor.should(
                    seeThatResponse("El código de respuesta HTTP debe ser: ",
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat("El resultado de la peticion es :  ",
                            returnSoapServiceResponse(),
                            containsString(bodyResponse()))
            );
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
    }


    private String bodyRequest () {
        return String.format(readFile(COUNTRYNAME.getValue()), countryNameModel().getIsoCode());
    }

    private String bodyResponse () {
        return String.format(COUNTRYNAME_RESPONSE.getValue(), countryNameModel().getCountryName());
    }

    private CountryNameModel countryNameModel () {
        return countryName;
    }

}



