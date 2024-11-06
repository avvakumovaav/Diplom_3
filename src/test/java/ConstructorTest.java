import api.user.User;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.PersonalAccountPage;

public class ConstructorTest extends BaseTest {

  private User user;
  private String accessToken;

  @Test
  @DisplayName("Проверка перехода из личного кабинета в конструктор")
  public void checkNavigateToConstructorFromAccountOnClickConstructor() {
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

    mainPage.clickConstructorButton();
    Assert.assertTrue("Заголовок 'Соберите бургер' не отображается", mainPage.isConstructorHeaderDisplayed());
  }

  @Test
  @DisplayName("Проверка перехода из личного кабинета на логотип Stellar Burgers.")
  public void checkNavigateToConstructorFromAccountOnClickLogo() {
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

    mainPage.clickLogo();
    Assert.assertTrue("Заголовок 'Соберите бургер' не отображается", mainPage.isConstructorHeaderDisplayed());
  }

  @Test
  @DisplayName("Проверка перехода к разделу «Начинки»")
  public void checkScrollToFillingsSectionWhenClickFillingsButton() {
    MainPage mainPage = new MainPage(driver);
    mainPage.open();

    mainPage.clickFillingsTab();

    Assert.assertTrue("Вкладка 'Начинки' не выбрана", mainPage.sectionTabIsSelected(mainPage.fillingsTab));
    Assert.assertTrue("Секция 'Начинки' не видна", mainPage.checkElementInViewport(mainPage.fillingsSection));
  }

  @Test
  @DisplayName("Проверка перехода к разделу «Соусы»")
  public void checkScrollToSaucesSectionWhenClickSaucesButton() {
    MainPage mainPage = new MainPage(driver);
    mainPage.open();

    mainPage.clickFillingsTab();
    mainPage.clickSaucesTab();

    Assert.assertTrue("Вкладка 'Соусы' не выбрана", mainPage.sectionTabIsSelected(mainPage.saucesTab));
    Assert.assertTrue("Секция 'Соусы' не видна на экране", mainPage.checkElementInViewport(mainPage.saucesSection));
  }

  @Test
  @DisplayName("Проверка перехода к разделу «Булки»")
  public void checkScrollToBunsSectionWhenClickBunsButton() {
    MainPage mainPage = new MainPage(driver);
    mainPage.open();

    mainPage.clickFillingsTab();
    mainPage.clickBunsTab();

    Assert.assertTrue("Вкладка 'Булки' не выбрана", mainPage.sectionTabIsSelected(mainPage.bunsTab));
    Assert.assertTrue("Секция 'Булки' не видна на экране", mainPage.checkElementInViewport(mainPage.bunsSection));
  }


  @After
  public void deleteUser() {
    if (accessToken != null) {
      ValidatableResponse response = userClient.deleteUser(accessToken);
      userChecks.checkDeleted(response);
    }
  }
}
