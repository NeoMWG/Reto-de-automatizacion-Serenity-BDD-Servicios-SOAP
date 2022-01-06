package co.com.sofka.utils.service.soap.countryisocode;

public enum Patch {

    COUNTRY_ISO_CODE(System.getProperty("user.dir")
            + "\\src\\test\\resources\\files\\countryisocode\\contryisocode.xml");

    private final String value;

    Patch(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
