import api.user.UserChecks;
import api.user.UserClient;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import webDriver.WebDriverFactory;

import java.time.Duration;

public class BaseTest {

  protected UserClient userClient = new UserClient();
  UserChecks userChecks = new UserChecks();

  protected WebDriver driver;

  @After
  public void after() {
    driver.quit();
  }

  @Before
  public void init() {
    driver = WebDriverFactory.getDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
  }
}
