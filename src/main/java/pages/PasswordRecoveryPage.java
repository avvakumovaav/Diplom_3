package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Страница восстановления пароля
 */
public class PasswordRecoveryPage extends BasePage {

  private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/register";

  private final By loginLink = By.linkText("Войти");

  public PasswordRecoveryPage(WebDriver driver) {
    super(driver);
  }

  @Step("Нажать на ссылку 'Войти'")
  public void clickLoginLink() {
    waitForLocator(loginLink);
    driver.findElement(loginLink).click();
  }

  @Step("Перейти на страницу восстановления пароля")
  public void open() {
    driver.get(PAGE_URL);
  }
}
