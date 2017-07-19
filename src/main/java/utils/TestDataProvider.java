package utils;

public class TestDataProvider {
    private String email;
    private String password;
    private String name;

    private static TestDataProvider testDataProvider = null;


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public static TestDataProvider getData() {
        if (testDataProvider == null) {
            testDataProvider = new TestDataProvider();
        }
        return testDataProvider;
    }

    private TestDataProvider() {
        email = TempEmailProvider.getTempEmail();
        password = StringUtils.generateRandomPass();
        name = "Vasya";
    }
}
