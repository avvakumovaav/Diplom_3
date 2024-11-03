package pages;

import api.user.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Страница авторизации
 */
public class LoginPage extends BasePage {

  private By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
  private By passwordInput = By.xpath("//input[@name='Пароль']");
  private By loginButton = By.xpath("//button[text()='Войти']");

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  @Step("Ввести email: {email}")
  public void enterEmail(String email) {
    driver.findElement(emailInput).sendKeys(email);
  }

  @Step("Ввести пароль: {password}")
  public void enterPassword(String password) {
    driver.findElement(passwordInput).sendKeys(password);
  }

  @Step("Нажать на кнопку 'Войти'")
  public void clickLoginButton() {
    driver.findElement(loginButton).click();
  }

  @Step("Авторизоваться: {email} {password}")
  public void login(User user) {
    enterEmail(user.getEmail());
    enterPassword(user.getPassword());
    clickLoginButton();
  }

  public Boolean isLoginButtonDisplayed() {
    return driver.findElement(loginButton).isDisplayed();
  }
}
