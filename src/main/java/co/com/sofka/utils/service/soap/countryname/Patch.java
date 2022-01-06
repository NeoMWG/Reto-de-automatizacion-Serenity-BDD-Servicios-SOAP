package co.com.sofka.utils.service.soap.countryname;

public enum Patch {

    COUNTRYNAME(System.getProperty("user.dir")
            + "\\src\\test\\resources\\files\\countryname\\countryname.xml");

    private final String value;

    Patch(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
