package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

  protected By logo = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']//a");
  protected By constructorButton = By.xpath("//*[text()='Конструктор']");
  protected By userAccountButton = By.xpath("//*[text()='Личный Кабинет']");

  protected WebDriver driver;

  public BasePage(WebDriver driver) {
    this.driver = driver;
  }

  public void waitForLocator(By locator) {
    new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  @Step("Нажать на кнопку 'Конструктор'")
  public void clickConstructorButton() {
    driver.findElement(constructorButton).click();
  }

  @Step("Нажать на кнопку 'Личный кабинет'")
  public void clickUserAccountButton() {
    driver.findElement(userAccountButton).click();
  }

  @Step("Нажать на логотип")
  public void clickLogo() {
    driver.findElement(logo).click();
  }


}
