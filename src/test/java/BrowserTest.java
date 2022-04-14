import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class BrowserTest {
    WebDriver driver;

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Tag("rozetka")
    @ParameterizedTest
    @ValueSource(classes = {ChromeDriver.class, EdgeDriver.class})
    void testRozetkaAvailable(Class<? extends WebDriver> webDriverClass) {
        assertFalse(Config.FAIL_TESTS);
        // Driver management and WebDriver instantiation
        driver = WebDriverManager.getInstance(webDriverClass).create();

        // Exercise
        driver.get("https://rozetka.com.ua/");
        String title = driver.getTitle();

        // Verify
        assertTrue(title.contains("Интернет-магазин ROZETKA"));
        //assertEquals(driver.getClass(), ChromeDriver.class, "fail on browsers other than Chrome");
    }

    @Tag("google")
    @Test
    void testGoogleAvailable() {
        assertFalse(Config.FAIL_TESTS);
        // Driver management and WebDriver instantiation
        driver = WebDriverManager.getInstance(ChromeDriver.class).create();

        // Exercise
        driver.get("https://google.com.ua/");
        String title = driver.getTitle();

        // Verify
        assertTrue(title.contains("Google"));
        //assertEquals(driver.getClass(), ChromeDriver.class, "fail on browsers other than Chrome");
    }

}
