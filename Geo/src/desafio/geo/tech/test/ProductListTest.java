package desafio.geo.tech.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import desafio.geo.tech.page.AddNewProductPage;
import desafio.geo.tech.page.DeleteProductPage;
import desafio.geo.tech.page.EditProductPage;
import desafio.geo.tech.page.Helper;
import desafio.geo.tech.page.InitialPage;
import desafio.geo.tech.page.ProductListPage;
import desafio.geo.tech.page.VisualizeProductPage;

public class ProductListTest {
	private WebDriver driver;
	private InitialPage login;
	private ProductListPage listProd;
	private AddNewProductPage newProd;
	private EditProductPage editProd;
	private VisualizeProductPage visProd;
	private DeleteProductPage delProd;
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
		this.editProd = new EditProductPage(driver);
		this.visProd = new VisualizeProductPage(driver);
		this.delProd = new DeleteProductPage(driver);
		this.help = new Helper();

		login.doLogin("Geofusion");
		listProd.createNewProduct(this.newProd);

	}

	@After
	public void finaliza() {
		driver.close();
	}

	@Test
	public void test_VisualizeProductDetails() {

		listProd.searchProduct(newProd.getProductName());

		assertTrue(isElementPresent(By.cssSelector("td.ng-binding")));

		visProd.btnVisualizeProduct();

		assertEquals(newProd.getProductName(), visProd.validityProductName());
		assertEquals(newProd.getPrice(), visProd.validityPriceProduct());
		assertEquals(newProd.getDate(), visProd.validityDateProduct());

		listProd.btnVoltar();
	}

	@Test
	public void test_EditExistProduct() {
		listProd.searchProduct(newProd.getProductName());

		assertTrue(isElementPresent(By.cssSelector("td.ng-binding")));

		editProd.btnEditProduct();

		editProd.editExistProduct(this.newProd, this.help.generateRandomString(5), "44.44",
				this.help.generateActualDate());

		assertEquals("Produto editado com sucesso!", closeAlertAndGetItsText());

		listProd.searchProduct(newProd.getProductName());

		assertEquals(newProd.getProductName(), editProd.validityProductName());
		assertEquals("R$ "+newProd.getPrice(), editProd.validityPriceProduct());
		assertEquals(newProd.getDate(), editProd.validityDateProduct());
	}

	@Test
	public void test_DeleteProducts() {
		listProd.searchProduct(newProd.getProductName());
		delProd.btnDeleteProduct();
		assertEquals("Excluir Produto", delProd.assertLabelDeleteProduct());
		delProd.doDeleteProduct();
	}

	@Test
	public void test_VisualizeProductThenEditProduct() {
		listProd.searchProduct(newProd.getProductName());
		visProd.btnVisualizeProduct();
		editProd.btnEditProduct();
		editProd.editExistProduct(newProd, help.generateRandomString(5), help.generatePrice(), help.generateActualDate());

		listProd.searchProduct(newProd.getProductName());

		assertEquals(newProd.getProductName(), editProd.validityProductName());
		assertEquals(newProd.getPrice(), editProd.validityPriceProduct());
		assertEquals(newProd.getDate(), editProd.validityDateProduct());
	}

	@Test
	public void test_VisualizeProductThenDeleteProduct() {
		listProd.searchProduct(newProd.getProductName());
		visProd.btnVisualizeProduct();
		delProd.btnDeleteProduct();
		delProd.doDeleteProduct();

		//assertEquals("Produto excluído com sucesso!", closeAlertAndGetItsText());
	}
	
	@Test
	public void test_SearchProductNameNotFound() {
		listProd.searchProduct(help.generateRandomString(20));
		assertNotNull(listProd.assertSearchResultEmpty());
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
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
