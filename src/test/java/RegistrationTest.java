import api.user.User;
import api.user.UserClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import pages.PersonalAccountPage;

public class RegistrationTest extends BaseTest {

  private User user;

  @Test
  @DisplayName("Успешная регистрация.\n" +
          "Ошибку для некорректного пароля. Минимальный пароль — шесть символов.")
  public void checkUserRegistration() {
    user = User.createUser();

    RegistrationPage registrationPage = new RegistrationPage(driver);
    registrationPage.open();
    registrationPage.register(user);

    LoginPage loginPage = new LoginPage(driver);
    Assert.assertTrue(loginPage.loginButtonIsDisplayed());

    loginPage.login(user);

    MainPage mainPage = new MainPage(driver);
    mainPage.clickUserAccountButton();

    PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
    Assert.assertEquals("Некорректное имя пользователя", user.getName(), personalAccountPage.getName());
    Assert.assertEquals("Некорректный email пользователя ", user.getEmail(), personalAccountPage.getEmail());
    Assert.assertTrue("Сообщение \"В этом разделе вы можете изменить свои персональные данные\" не отображается", personalAccountPage.isAccountTextDisplayed());
  }

  @Test
  @DisplayName("Ошибка для некорректного пароля. Минимальный пароль — шесть символов.")
  public void checkUserRegistrationWithInvalidPassword() {
    user = User.createUserWithInvalidPassword();

    RegistrationPage registrationPage = new RegistrationPage(driver);
    registrationPage.open();
    registrationPage.register(user);

    Assert.assertTrue("Сообщение об ошибке некорректного пароля не отображается", registrationPage.errorTextIsDisplayed());
  }

  @After
  public void deleteUser() {
    UserClient client = new UserClient();
    String accessToken = client.loginUser(user);

    if (accessToken != null) {
      ValidatableResponse response = userClient.deleteUser(accessToken);
      userChecks.checkDeleted(response);
    }
  }
}
