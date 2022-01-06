package co.com.sofka.runners.service.soap.countryname.cucumbertest;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/services/soap/countryname/countryname.feature","src/test/resources/features/services/soap/isocode/isocodecountry.feature"},
        glue = {"co.com.sofka.stepdefinitions.service.soap"}
        )
public class CountryNameCucumberTest {
}
