package desafio.geo.tech.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import desafio.geo.tech.page.AddNewProductPage;
import desafio.geo.tech.page.Helper;
import desafio.geo.tech.page.InitialPage;
import desafio.geo.tech.page.ProductListPage;

public class AddNewProductsTest {
	private WebDriver driver;
	private InitialPage login;
	private ProductListPage listProd;
	private AddNewProductPage newProd;
	private Helper help;

	@Before
	public void inicializa() {
		System.setProperty("webdriver.gecko.driver", "/tmp/geckodriver");
		driver = new FirefoxDriver();
		driver.get("http://desafio.geofusion.tech");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		this.login = new InitialPage(driver);
		this.listProd = new ProductListPage(driver);
		this.newProd = new AddNewProductPage(driver);
		this.help = new Helper();

		login.doLogin("Geofusion");
		listProd.btnAddNewProduct();

	}

	@After
	public void finaliza() {
		driver.close();
		// driver.quit();
	}

	@Test
	public void test_AddNewProductSuccess() {
		assertTrue(driver.getPageSource().contains("Adicionar Produto"));

		newProd.addProduct(help.generateRandomString(5), help.generatePrice(), help.generateActualDate());

		// Alert alert = driver.switchTo().alert();

		// assertEquals(alert.getText().toString(), "Produto adicionado com sucesso!");
		// alert.accept();
		assertEquals("Produto adicionado com sucesso!", closeAlertAndGetItsText());
	}

	@Test
	public void test_AllFieldsProductsEmpty() {
		assertTrue(driver.getPageSource().contains("Adicionar Produto"));
		newProd.addProduct("", "", "");

		assertEquals("Nome é obrigatório.", newProd.assertValueFieldProductNameEmpty());
		assertEquals("Preço é obrigatório.", newProd.assertValueFieldPriceEmpty());
		assertEquals("Data de validade é obrigatório.", newProd.assertValueFieldDatevalidateEmpty());

		assertTrue(driver.getPageSource().contains("Nome é obrigatório."));
		assertTrue(driver.getPageSource().contains("Preço é obrigatório."));
		assertTrue(driver.getPageSource().contains("Data de validade é obrigatório."));
	}

	@Test
	public void test_ProductNameFieldsEmpty() {
		assertTrue(driver.getPageSource().contains("Adicionar Produto"));
		newProd.addProduct("", help.generatePrice(), help.generateActualDate());

		assertEquals("Nome é obrigatório.", newProd.assertValueFieldProductNameEmpty());

		assertTrue(driver.getPageSource().contains("Nome é obrigatório."));
	}

	@Test
	public void test_PriceFieldsEmpty() {

		newProd.addProduct(help.generateRandomString(5), "", help.generateActualDate());

		assertEquals("Preço é obrigatório.", newProd.assertValueFieldPriceEmpty());
		assertTrue(driver.getPageSource().contains("Preço é obrigatório."));
	}

	@Test
	public void test_DateValidateFieldsEmpty() {
		assertTrue(driver.getPageSource().contains("Adicionar Produto"));
		newProd.addProduct(help.generateRandomString(5), help.generatePrice(), "");

		assertEquals("Data de validade é obrigatório.", newProd.assertValueFieldDatevalidateEmpty());
		assertTrue(driver.getPageSource().contains("Data de validade é obrigatório."));
	}

	@Test
	public void test_ProductNameFieldsGreaterThan50Caracter() {
		assertTrue(driver.getPageSource().contains("Adicionar Produto"));
		newProd.addProduct(help.generateRandomString(51), help.generatePrice(), help.generateActualDate());

		// Alert alert = driver.switchTo().alert();

		// assertEquals(alert.getText().toString(), "Produto adicionado com sucesso!");
		// alert.accept();
		assertEquals("Produto adicionado com sucesso!", closeAlertAndGetItsText());
	}

	@Test
	public void test_ProductNameFieldsEquals50Caracter() {

		newProd.addProduct(help.generateRandomString(50), help.generatePrice(), help.generateActualDate());

		// Alert alert = driver.switchTo().alert();
		// assertEquals(alert.getText().toString(), "Produto adicionado com sucesso!");
		// alert.accept();
		assertEquals("Produto adicionado com sucesso!", closeAlertAndGetItsText());
	}

	@Test
	public void test_NotAllowedAddNewProductWithYesterdayDate() {

		newProd.addProduct(help.generateRandomString(50), help.generatePrice(), help.generateYesterdayDate());

		// Alert alert = driver.switchTo().alert();

		// assertEquals(alert.getText().toString(), "Produto adicionado com sucesso!");
		// alert.accept();
		assertEquals("Produto adicionado com sucesso!", closeAlertAndGetItsText());
	}
	
	@Test
	public void test_CreateProductNamesEquals() {
		String sameNames = help.generateRandomString(5);
		
		newProd.addProduct(sameNames, "45.44", help.generateActualDate());
		assertEquals("Produto adicionado com sucesso!", closeAlertAndGetItsText());
		listProd.btnAddNewProduct();
		
		newProd.addProduct(sameNames, "45.44", help.generateActualDate());
		assertEquals("Produto adicionado com sucesso!", closeAlertAndGetItsText());
		
		listProd.searchProduct(newProd.getProductName());
		
		assertEquals(newProd.getProductName(), newProd.assertValueFieldProductName());
		assertEquals("R$ "+newProd.getPrice(), newProd.assertValueFieldPrice());
		assertEquals(newProd.getDate(), newProd.assertValueFieldDatevalidate());
		
	}
	
	@Test
	public void test_CreateProductWithPriceMaximumValueAllow() {
		newProd.addProduct(help.generateRandomString(5), 
				"33,333,333,333,333,333,333,333,333,333,333,333,333,333,333,333,333,333,333,333,333,333,333,333,333,333,333,333,333,333.33", 
				help.generateActualDate());
		assertEquals("Produto adicionado com sucesso!", closeAlertAndGetItsText());
		listProd.searchProduct(newProd.getProductName());
		
		assertEquals(newProd.getProductName(), newProd.assertValueFieldProductName());
		assertEquals("R$ 0", newProd.assertValueFieldPrice());
		assertEquals(newProd.getDate(), newProd.assertValueFieldDatevalidate());
	}

	private String closeAlertAndGetItsText() {
		boolean acceptNextAlert = true;
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}

}
