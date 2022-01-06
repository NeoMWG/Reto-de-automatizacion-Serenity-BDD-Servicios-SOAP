package co.com.sofka.utils.service.soap.countryisocode;

public enum Response {

    COUNTRY_ISO_CODE_RESPONSE("<m:CountryISOCodeResult>%s</m:CountryISOCodeResult>");

    private final String value;

    Response(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
