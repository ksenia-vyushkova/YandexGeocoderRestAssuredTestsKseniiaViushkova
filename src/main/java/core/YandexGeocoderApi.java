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

import static org.hamcrest.Matchers.lessThan;

public class YandexGeocoderApi {

    private static final String YANDEX_GEOCODER_API_URI =
            "https://geocode-maps.yandex.ru/1.x/";
    private HashMap<String, String> params = new HashMap<>();

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
            geocoderApi.params.put(YandexGeocoderConstants.PARAM_GEOCODE, geocode);
            return this;
        }

        public ApiBuilder apikey(String apikey) {
            geocoderApi.params.put(YandexGeocoderConstants.PARAM_APIKEY, apikey);
            return this;
        }

        public ApiBuilder format(String format) {
            geocoderApi.params.put(YandexGeocoderConstants.PARAM_FORMAT, format);
            return this;
        }

        public Response callApi() {
            return RestAssured.with()
                    .queryParams(geocoderApi.params)
                    .log().all()
                    .get(YANDEX_GEOCODER_API_URI).prettyPeek();
        }
    }
}