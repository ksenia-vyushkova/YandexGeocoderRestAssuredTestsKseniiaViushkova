package core;

import beans.YandexGeocoderResponse;
import com.google.gson.Gson;
import core.constants.YandexGeocoderConstants;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

import java.util.HashMap;

import static core.constants.YandexGeocoderConstants.*;
import static org.hamcrest.Matchers.lessThan;

public class YandexGeocoderApi {

    private static final String YANDEX_GEOCODER_API_URI =
            "https://geocode-maps.yandex.ru/1.x/";
    private HashMap<String, String> params = new HashMap<>();
    private static String apikey = System.getProperty("apikey");

    private YandexGeocoderApi() {
    }

    public static ApiBuilder with() {
        YandexGeocoderApi api = new YandexGeocoderApi();
        return new ApiBuilder(api);
    }

    public static YandexGeocoderResponse getYandexGeocoderResponce(Response response) {
        return new Gson().fromJson(response.asString().trim(), YandexGeocoderResponse.class);
    }

    public static RequestSpecification baseRequestConfiguration() {
        return new RequestSpecBuilder()
                .addParam(PARAM_APIKEY, apikey)
                .addParam(PARAM_FORMAT, Format.JSON.getValue())
                .setAccept(ContentType.JSON)
                .setRelaxedHTTPSValidation()
                .setBaseUri(YANDEX_GEOCODER_API_URI)
                .build();
    }

    public static ResponseSpecification successResponse() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectHeader("Connection", "keep-alive")
                .expectResponseTime(lessThan(20000L))
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }

    public static class ApiBuilder {
        YandexGeocoderApi geocoderApi;

        private ApiBuilder(YandexGeocoderApi geocoderApi) {
            this.geocoderApi = geocoderApi;
        }

        public ApiBuilder geocode(String geocode) {
            geocoderApi.params.put(PARAM_GEOCODE, geocode);
            return this;
        }

        public ApiBuilder apikey(String apikey) {
            geocoderApi.params.put(PARAM_APIKEY, apikey);
            return this;
        }

        public ApiBuilder sco(Sco sco) {
            geocoderApi.params.put(PARAM_SCO, sco.getValue());
            return this;
        }

        public ApiBuilder kind(Kind kind) {
            geocoderApi.params.put(PARAM_KIND, kind.getValue());
            return this;
        }

        public ApiBuilder rspn(boolean rspn) {
            geocoderApi.params.put(YandexGeocoderConstants.PARAM_RSPN, rspn ? "1" : "0");
            return this;
        }

        public ApiBuilder ll(int lng, int lat) {
            geocoderApi.params.put(YandexGeocoderConstants.PARAM_LL, lng + ", " + lat);
            return this;
        }

        public ApiBuilder spn(int deltaLong, int deltaLat) {
            geocoderApi.params.put(YandexGeocoderConstants.PARAM_SPN, deltaLong + ", " + deltaLat);
            return this;
        }

        public ApiBuilder bbox(int x1, int y1, int x2, int y2) {
            geocoderApi.params.put(PARAM_BBOX, x1 + "," + y1 + "~" + x2 + "," + y2);
            return this;
        }

        public ApiBuilder format(String format) {
            geocoderApi.params.put(YandexGeocoderConstants.PARAM_FORMAT, format);
            return this;
        }

        public ApiBuilder skip(int skip) {
            geocoderApi.params.put(PARAM_SKIP, String.valueOf(skip));
            return this;
        }

        public ApiBuilder lang(Lang lang) {
            geocoderApi.params.put(PARAM_LANG, lang.getValue());
            return this;
        }

        public ApiBuilder callback(String callback) {
            geocoderApi.params.put(PARAM_CALLBACK, callback);
            return this;
        }

        public Response callApi() {
            return RestAssured.with()
                    .param(PARAM_APIKEY, apikey)
                    .queryParams(geocoderApi.params)
                    .get(YANDEX_GEOCODER_API_URI);
        }
    }
}