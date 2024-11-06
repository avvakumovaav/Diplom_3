package pages;

import api.user.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Страница авторизации
 */
public class LoginPage extends BasePage {

  private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
  private final By passwordInput = By.xpath("//input[@name='Пароль']");
  private final By loginButton = By.xpath("//button[text()='Войти']");

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  @Step("Ввести email {email}")
  public void enterEmail(String email) {
    driver.findElement(emailInput).sendKeys(email);
  }

  @Step("Ввести пароль")
  public void enterPassword(String password) {
    driver.findElement(passwordInput).sendKeys(password);
  }

  @Step("Нажать на кнопку 'Войти'")
  public void clickLoginButton() {
    driver.findElement(loginButton).click();
  }

  @Step("Авторизоваться {email}")
  public void login(User user) {
    enterEmail(user.getEmail());
    enterPassword(user.getPassword());
    clickLoginButton();
  }

  public Boolean loginButtonIsDisplayed() {
    return driver.findElement(loginButton).isDisplayed();
  }
}
