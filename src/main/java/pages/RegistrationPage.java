package pages;

import api.user.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Страница регистрации
 */
public class RegistrationPage extends BasePage {

  private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/register";

  private final By nameInput = By.xpath("//label[text()='Имя']/following-sibling::input");
  private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
  private final By passwordInput = By.xpath("//input[@name='Пароль']");
  private final By regButton = By.xpath("//button[text()='Зарегистрироваться']");
  private final By invalidPasswordMsg = By.xpath("//p[text()='Некорректный пароль']");
  private final By loginLink = By.linkText("Войти");

  public RegistrationPage(WebDriver driver) {
    super(driver);
  }

  @Step("Ввести имя: {name}")
  public void enterName(String name) {
    driver.findElement(nameInput).sendKeys(name);
  }

  @Step("Ввести email: {email}")
  public void enterEmail(String email) {
    driver.findElement(emailInput).sendKeys(email);
  }

  @Step("Ввести пароль")
  public void enterPassword(String password) {
    driver.findElement(passwordInput).sendKeys(password);
  }

  @Step("Нажать на кнопку 'Зарегистрироваться'")
  public void clickRegisterButton() {
    driver.findElement(regButton).click();
  }

  @Step("Нажать на ссылку 'Войти'")
  public void clickLoginLink() {
    waitForLocator(loginLink);
    driver.findElement(loginLink).click();
  }

  public Boolean errorTextIsDisplayed() {
    return driver.findElement(invalidPasswordMsg).isDisplayed();
  }

  @Step("Зарегистрировать пользователя с именем {name}, email {email} и паролем {password}")
  public void register(User user) {
    enterName(user.getName());
    enterEmail(user.getEmail());
    enterPassword(user.getPassword());
    clickRegisterButton();
  }

  @Step("Перейти на страницу регистрации")
  public void open() {
    driver.get(PAGE_URL);
  }
}
