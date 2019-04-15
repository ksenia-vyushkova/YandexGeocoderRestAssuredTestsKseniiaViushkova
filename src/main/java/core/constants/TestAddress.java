package core.constants;

public enum TestAddress {
    ARBAT("Москва, улица Новый Арбат, дом 24", "Россия, Москва, улица Новый Арбат, 24");

    private String query;
    private String expectedFormattedAddress;

    TestAddress(String query, String expectedFormattedAddress) {
        this.query = query;
        this.expectedFormattedAddress = expectedFormattedAddress;
    }

    public String getQuery() {
        return query;
    }

    public String getExpectedFormattedAddress() {
        return expectedFormattedAddress;
    }
}