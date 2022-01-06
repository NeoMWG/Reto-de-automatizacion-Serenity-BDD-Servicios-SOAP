package co.com.sofka.stepdefinitions.service.soap.countryisocode;

import co.com.sofka.ServiceSetup;
import co.com.sofka.models.CountryIsoCodeModel;
import co.com.sofka.stepdefinitions.service.soap.countryname.CountryNameStepDefinition;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import static co.com.sofka.questions.ReturnSoapServiceResponse.returnSoapServiceResponse;
import static co.com.sofka.task.DoPost.doPost;
import static co.com.sofka.utils.FileUtilities.readFile;
import static co.com.sofka.utils.service.soap.countryisocode.Patch.COUNTRY_ISO_CODE;
import static co.com.sofka.utils.service.soap.countryisocode.Response.COUNTRY_ISO_CODE_RESPONSE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

public class CountryIsoCodeStepDefinition extends ServiceSetup {

    private static final Logger LOGGER = Logger.getLogger(CountryNameStepDefinition.class);
    CountryIsoCodeModel countryIsoCode = new CountryIsoCodeModel();

    @Given("the user wants to search for the country named {string}")
    public void theUserWantsToSearchForTheCountryNamed(String countryNamed) {
        try {
            super.setup();
            countryIsoCode = new CountryIsoCodeModel();
            countryIsoCode.setCountryName(countryNamed);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }

    }

    @When("the user makes the request and confirms the new action")
    public void theUserMakesTheRequestAndConfirmsTheNewAction() {
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

    @Then("the user should see the ISO code {string}")
    public void theUserShouldSeeTheISOCode(String isoCodeCountry) {
        try {
            countryIsoCode.setIsoCode(isoCodeCountry);
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

    @Given("the user wish to search for the country named {string}")
    public void theUserWishToSearchForTheCountryNamed(String wrongName) {
        try {
            super.setup();
            countryIsoCode = new CountryIsoCodeModel();
            countryIsoCode.setCountryName(wrongName);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
    }

    @When("the user enters the search data and sends the request")
    public void theUserEntersTheSearchDataAndSendsTheRequest() {
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

    @Then("the user should be able to see the following message {string}")
    public void theUserShouldBeAbleToSeeTheFollowingMessage(String isoMessage) {
        try {
            countryIsoCode.setIsoCode(isoMessage);
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
            return String.format(readFile(COUNTRY_ISO_CODE.getValue()),countryIsoCode().getCountryName());
        }

        private String bodyResponse () {
            return String.format(COUNTRY_ISO_CODE_RESPONSE.getValue(),countryIsoCode().getIsoCode());
        }

        private CountryIsoCodeModel countryIsoCode () {
            return countryIsoCode;
        }

    }