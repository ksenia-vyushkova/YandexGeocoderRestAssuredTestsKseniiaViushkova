package core.matchers;

import beans.YandexGeocoderResponse;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ContainsGeoObjectByFormattedAddress extends TypeSafeMatcher<YandexGeocoderResponse> {

    private String formattedAddress;

    private ContainsGeoObjectByFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public static Matcher<YandexGeocoderResponse> containsGeoObjectByFormattedAddress(String formattedAddress) {
        return new ContainsGeoObjectByFormattedAddress(formattedAddress);
    }

    @Override
    public boolean matchesSafely(YandexGeocoderResponse yandexGeocoderResponse) {
        return yandexGeocoderResponse.response.geoObjectCollection.featureMember.stream()
                .anyMatch(featureMember -> featureMember.geoObject.metaDataProperty.geocoderMetaData.address.formatted
                        .equals(formattedAddress));
    }

    public void describeTo(Description description) {
        description.appendText("geoObjects list contains formatted address <" + formattedAddress + ">");
    }
}
