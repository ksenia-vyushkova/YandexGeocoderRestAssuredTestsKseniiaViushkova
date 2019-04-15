package core.constants;

public class YandexGeocoderConstants {

    public static final String PARAM_GEOCODE = "geocode";
    public static final String PARAM_APIKEY = "apikey";
    public static final String PARAM_FORMAT = "format";

    public enum Format {
        XML("xml"),
        JSON("json");

        private String value;

        Format(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}