import api.user.User;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import pages.*;

public class LoginTest extends BaseTest {

  private User user;
  private String accessToken;

  @Test
  @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
  public void checkLoginViaMainPageLoginButton() {
    user = User.createUser();
    accessToken = userClient.createUser(user);

    MainPage mainPage = new MainPage(driver);
    mainPage.open();
    mainPage.clickLoginButton();

    LoginPage loginPage = new LoginPage(driver);
    loginPage.login(user);

    mainPage.clickUserAccountButton();

    PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
    Assert.assertEquals("Некорректное имя пользователя", user.getName(), personalAccountPage.getName());
    Assert.assertEquals("Некорректный email пользователя ", user.getEmail(), personalAccountPage.getEmail());
    Assert.assertTrue("Сообщение \"В этом разделе вы можете изменить свои персональные данные\" не отображается", personalAccountPage.isAccountTextDisplayed());
  }

  @Test
  @DisplayName("Вход через кнопку «Личный кабинет»")
  public void checkLoginViaUserAccountButton() {
    user = User.createUser();
    accessToken = userClient.createUser(user);

    MainPage mainPage = new MainPage(driver);
    mainPage.open();
    mainPage.clickUserAccountButton();

    LoginPage loginPage = new LoginPage(driver);
    loginPage.login(user);

    mainPage.clickUserAccountButton();

    PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
    Assert.assertEquals("Некорректное имя пользователя", user.getName(), personalAccountPage.getName());
    Assert.assertEquals("Некорректный email пользователя ", user.getEmail(), personalAccountPage.getEmail());
    Assert.assertTrue("Сообщение \"В этом разделе вы можете изменить свои персональные данные\" не отображается", personalAccountPage.isAccountTextDisplayed());
  }

  @Test
  @DisplayName("Вход через кнопку в форме регистрации")
  public void checkLoginViaRegistrationFormLoginButton() {
    user = User.createUser();
    accessToken = userClient.createUser(user);

    RegistrationPage registrationPage = new RegistrationPage(driver);
    registrationPage.open();
    registrationPage.clickLoginLink();

    LoginPage loginPage = new LoginPage(driver);
    loginPage.login(user);

    MainPage mainPage = new MainPage(driver);
    mainPage.clickUserAccountButton();

    PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
    Assert.assertEquals("Некорректное имя пользователя", user.getName(), personalAccountPage.getName());
    Assert.assertEquals("Некорректный email пользователя ", user.getEmail(), personalAccountPage.getEmail());
    Assert.assertTrue("Сообщение \"В этом разделе вы можете изменить свои персональные данные\" не отображается", personalAccountPage.isAccountTextDisplayed());
  }

  @Test
  @DisplayName("Вход через кнопку в форме восстановления пароля.")
  public void checkLoginViaPasswordRecoveryFormLoginButton() {
    user = User.createUser();
    accessToken = userClient.createUser(user);

    PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
    passwordRecoveryPage.open();
    passwordRecoveryPage.clickLoginLink();

    LoginPage loginPage = new LoginPage(driver);
    loginPage.login(user);

    MainPage mainPage = new MainPage(driver);
    mainPage.clickUserAccountButton();

    PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
    Assert.assertEquals("Некорректное имя пользователя", user.getName(), personalAccountPage.getName());
    Assert.assertEquals("Некорректный email пользователя ", user.getEmail(), personalAccountPage.getEmail());
    Assert.assertTrue("Сообщение \"В этом разделе вы можете изменить свои персональные данные\" не отображается", personalAccountPage.isAccountTextDisplayed());
  }

  @After
  public void deleteUser() {
    if (accessToken != null) {
      ValidatableResponse response = userClient.deleteUser(accessToken);
      userChecks.checkDeleted(response);
    }
  }
}
