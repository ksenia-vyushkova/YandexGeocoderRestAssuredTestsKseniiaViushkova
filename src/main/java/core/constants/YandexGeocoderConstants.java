package core.constants;

public class YandexGeocoderConstants {

    public static final String PARAM_GEOCODE = "geocode";
    public static final String PARAM_APIKEY = "apikey";
    public static final String PARAM_SCO = "sco";
    public static final String PARAM_KIND = "kind";
    public static final String PARAM_RSPN = "rspn";
    public static final String PARAM_LL = "ll";
    public static final String PARAM_SPN = "spn";
    public static final String PARAM_BBOX = "bbox";
    public static final String PARAM_FORMAT = "format";
    public static final String PARAM_SKIP = "skip";
    public static final String PARAM_LANG = "lang";
    public static final String PARAM_CALLBACK = "callback";

    public enum Sco {
        LONGLAT("longlat"),
        LATLONG("latlong");

        private String value;

        Sco(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum Kind {
        HOUSE("house"),
        STREET("street"),
        METRO("metro"),
        DISTRICT("district"),
        LOCALITY("locality");

        private String value;

        Kind(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

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

    public enum Lang {
        RU_RU("ru_RU"),
        UK_UA("uk_UA"),
        BE_BY("be_BY"),
        EN_RU("en_RU"),
        EN_US("en_US"),
        TR_TR("tr_TR");

        private String value;

        Lang(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}