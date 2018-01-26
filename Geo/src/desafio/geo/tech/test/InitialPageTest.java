package desafio.geo.tech.test;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import desafio.geo.tech.page.Helper;
import desafio.geo.tech.page.InitialPage;

public class InitialPageTest {
	private WebDriver driver;
	private InitialPage login;

	@Before
	public void inicializa() {
		System.setProperty("webdriver.gecko.driver", "/tmp/geckodriver");
		driver = new FirefoxDriver();
		driver.get("http://desafio.geofusion.tech");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		this.login = new InitialPage(driver);
	}

	@After
	public void finaliza() {
		driver.close();
		// driver.quit();

	}

	@Test
	public void test_DoLoginWithUserName() {

		login.doLogin("Geofusion");
		assertTrue(driver.getPageSource().contains("Produtos"));
	}

	@Test
	public void test_DoLoginBlankUserName() {

		login.doLogin("");
		assertTrue(driver.getPageSource().contains("Produtos"));
	}

	@Test
	public void test_DoLoginWithSpecialCaracteres() {

		login.doLogin("$@#&*&*!;");
		assertTrue(driver.getPageSource().contains("Produtos"));
	}

	@Test
	public void test_DoLoginUserNameGreaterThan50() {
		Helper help = new Helper();

		login.doLogin(help.generateRandomString(51));
		assertTrue(driver.getPageSource().contains("Produtos"));
	}

}
