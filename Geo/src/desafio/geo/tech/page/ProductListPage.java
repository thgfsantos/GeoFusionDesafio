package desafio.geo.tech.page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductListPage {
	private WebDriver driver;
	private Helper help;

	public ProductListPage(WebDriver driver) {
		this.driver = driver;
	}

	public AddNewProductPage btnAddNewProduct() {
		driver.findElement(By.linkText("Novo Produto")).click();
		return new AddNewProductPage(driver);
	}

	public ProductListPage createNewProduct(AddNewProductPage newProd) {
		this.help = new Helper();
		btnAddNewProduct();

		newProd.addProduct(help.generateRandomString(7), "55.55", help.generateActualDate());

		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alert.accept();

		return new ProductListPage(driver);
	}

	public ProductListPage searchProduct(String productName) {
		driver.findElement(By.id("search")).clear();
		driver.findElement(By.id("search")).sendKeys(productName);
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();

		return new ProductListPage(driver);
	}

	public ProductListPage btnVoltar() {
		driver.findElement(By.linkText("Voltar")).click();
		return new ProductListPage(driver);
	}

	public ProductListPage btnCancel() {
		driver.findElement(By.linkText("Cancelar")).click();
		return new ProductListPage(driver);
	}
	
	public String assertSearchResultEmpty() {
		return driver.findElement(By.xpath("//div[@id='list']/div")).getText();
	}
}
