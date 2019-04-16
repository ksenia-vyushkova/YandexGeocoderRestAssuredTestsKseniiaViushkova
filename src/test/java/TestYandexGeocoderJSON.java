import beans.YandexGeocoderResponse;
import core.YandexGeocoderApi;
import io.restassured.RestAssured;
import org.junit.Test;

import static core.YandexGeocoderApi.baseRequestConfiguration;
import static core.YandexGeocoderApi.successResponse;
import static core.constants.TestAddress.ARBAT;
import static core.constants.YandexGeocoderConstants.Format.JSON;
import static core.constants.YandexGeocoderConstants.PARAM_GEOCODE;
import static core.matchers.ContainsGeoObjectByFormattedAddress.containsGeoObjectByFormattedAddress;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestYandexGeocoderJSON {

    @Test
    public void useBaseRequestAndResponseSpecifications() {
        RestAssured
                .given(baseRequestConfiguration())
                .param(PARAM_GEOCODE, ARBAT.getQuery())
                .get()
                .then()
                .specification(successResponse());
    }

    @Test
    public void useGetYandexGeocoderResponse() {
        YandexGeocoderResponse response =
                YandexGeocoderApi.getYandexGeocoderResponce(
                        YandexGeocoderApi.with()
                                .geocode(ARBAT.getQuery())
                                .format(JSON.getValue())
                                .callApi());
        assertThat("Expected formatted address missing in response", response, containsGeoObjectByFormattedAddress(ARBAT.getExpectedFormattedAddress()));
    }
}