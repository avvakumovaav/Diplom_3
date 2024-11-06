import api.user.User;
import api.user.UserClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.PersonalAccountPage;

public class UserAccountTest extends BaseTest {

  private User user;
  private String accessToken;

  @Test
  @DisplayName("Переход по клику на «Личный кабинет»")
  public void checkUserAccountPageNavigation() {
    user = User.createUser();
    accessToken = userClient.createUser(user);

    MainPage mainPage = new MainPage(driver);
    mainPage.open();
    mainPage.clickLoginButton();

    LoginPage loginPage = new LoginPage(driver);
    loginPage.login(user);

    mainPage.clickUserAccountButton();

    PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
    Assert.assertTrue("Сообщение 'В этом разделе вы можете изменить свои персональные данные' не отображается", personalAccountPage.isAccountTextDisplayed());
    Assert.assertEquals("URL некорректный", PersonalAccountPage.PAGE_URL, driver.getCurrentUrl());
  }

  @Test
  @DisplayName("Выход из аккаунта\n")
  public void checkLogOut() {
    user = User.createUser();
    accessToken = userClient.createUser(user);

    MainPage mainPage = new MainPage(driver);
    mainPage.open();
    mainPage.clickLoginButton();

    LoginPage loginPage = new LoginPage(driver);
    loginPage.login(user);

    mainPage.clickUserAccountButton();

    PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
    personalAccountPage.clickLogoutButton();

    mainPage.clickUserAccountButton();

    Assert.assertTrue("Кнопка 'Войти' не отображается", loginPage.loginButtonIsDisplayed());
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
