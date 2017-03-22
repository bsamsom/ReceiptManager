package group8.comp4020.receiptmanager;

/**
 * Singleton class used to store and retrieve application settings during runtime
 * Created by mark on 2017-03-22.
 */

public class RunSettings {

    private static int method;
    private static RunSettings settings;

    public static RunSettings getInstance() {
        if (settings == null)
            settings = new RunSettings();

        return settings;
    }

    private RunSettings() {
        // initialize any variables
    }

    public  int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        settings.method = method;
    }

    public static void init () {
        settings = new RunSettings();
    }
}
