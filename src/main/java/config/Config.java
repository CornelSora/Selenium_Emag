package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

public class Config {

    public static final String emagURL = "https://www.emag.ro/homepage";

    private static final ThreadLocal<RemoteWebDriver> drivers = new ThreadLocal<>();


    //private static WebDriver webDriver;
    private static RemoteWebDriver webDriver;
    /*public static WebDriver getWebDriver() {
        return webDriver;
    }*/

    public static RemoteWebDriver getWebDriver() {
        RemoteWebDriver driver = drivers.get();
        return driver;
        //return webDriver;
    }

    /*public static void setWebDriver(WebDriver webDriver) {
        Config.webDriver = webDriver;
    }*/

    public static void setWebDriver(RemoteWebDriver webDriver) {
        Config.webDriver = webDriver;
    }

    public Config(String browser) {
        if ((browser.equals("ie")) || (browser.equals("chrome")) || (browser.equals("firefox"))) {
            if (browser.equals("chrome")) {
                System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
                RemoteWebDriver driver = new ChromeDriver();
                driver.manage().window().maximize();
                //setWebDriver(driver);
                drivers.set(driver);

                /*Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    try {
                        webDriver.quit();
                    } catch (Exception ex) {}
                }));*/


            } else {
                System.out.println(
                        "Added just Chrome in the config at this point. Your browser:"
                                + browser + "is not supported right now");
            }
        }
    }

    public static void loadURL(String URL) {
        //webDriver.get(URL);
        RemoteWebDriver driver = drivers.get();
        driver.get(URL);
    }

    public static void closeDriver() {
        //webDriver.quit();
        RemoteWebDriver driver = drivers.get();
        driver.quit();
    }

    private RemoteWebDriver driver() {
        RemoteWebDriver driver = drivers.get();
        if (driver == null) {
            throw new IllegalStateException("Driver should have not been null.");
        }
        return driver;
    }
}
