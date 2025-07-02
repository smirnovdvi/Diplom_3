package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Getter
public class DriverRule extends ExternalResource {
    private WebDriver driver;

    @Override
    protected void before() {
        initDriver();
    }

    @Override
    public void after() {
        driver.quit();
    }

    public void initDriver() {
        if ("yandex".equals(System.getProperty("browser"))) {
            initYandex();
        }
        else {
            initChrome();
        }
    }

    private void initYandex() {
        WebDriverManager.chromedriver().driverVersion(System.getProperty("browser.version")).setup();
        var opts = new ChromeOptions();
        opts.setBinary(System.getProperty("webdriver.yandex.bin"));

        driver = new ChromeDriver(opts);
    }

    private void initChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

}