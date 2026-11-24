package mavenpackage;

public final class EnvironmentConfig {

    private EnvironmentConfig() {}

    public static String getEnv() {
        return System.getProperty("env", "qa");
    }

    public static String getBrowser() {
        return System.getProperty("browser", "chrome");
    }

    public static String getBaseUrl() {
        return System.getProperty("baseUrl", "https://qa.example.com");
    }

    public static String getApiBaseUrl() {
        return System.getProperty("apiBaseUrl", "https://qa-api.example.com");
    }

    public static void printActiveConfig() {
        System.out.printf("Active Config -> env=%s | browser=%s | baseUrl=%s | apiBaseUrl=%s%n",
                getEnv(), getBrowser(), getBaseUrl(), getApiBaseUrl());
    }
}