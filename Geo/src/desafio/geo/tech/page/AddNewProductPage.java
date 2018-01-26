package desafio.geo.tech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddNewProductPage {

	private WebDriver driver;
	private String price;
	private String productName;
	private String dateValid;

	public AddNewProductPage(WebDriver driver) {
		this.driver = driver;
		this.price = "0";
		this.productName = "";
		this.dateValid = "";
	}

	public ProductListPage addProduct(String productName, String price, String dateValidate) {
		driver.findElement(By.id("campo1")).clear();
		driver.findElement(By.id("campo1")).sendKeys(productName);
		driver.findElement(By.name("price")).clear();
		driver.findElement(By.name("price")).sendKeys(price);

		// JsEnables the from date box
		((JavascriptExecutor) driver).executeScript("document.getElementById('campo3').removeAttribute('readonly',0);"); 
		WebElement fromDateBox = driver.findElement(By.id("campo3"));
		fromDateBox.clear();
		fromDateBox.sendKeys(dateValidate);

		// click button save
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();

		setPrice(price);
		setProductName(productName);
		setDateValidate(dateValidate);

		return new ProductListPage(driver);
	}

	public String assertValueFieldProductName() {
		return driver.findElement(By.xpath("//div[@id='list']/div/table/tbody/tr/td[2]")).getText();
	}

	public String assertValueFieldPrice() {
		return driver.findElement(By.xpath("//div[@id='list']/div/table/tbody/tr/td[3]")).getText();
	}

	public String assertValueFieldDatevalidate() {
		return driver.findElement(By.xpath("//div[@id='list']/div/table/tbody/tr/td[4]")).getText();
	}
	
	public String assertValueFieldProductNameEmpty() {
		return driver.findElement(By.xpath("//div[@id='main']/form/div/div/div/span")).getText();
	}

	public String assertValueFieldPriceEmpty() {
		return driver.findElement(By.xpath("//div[@id='main']/form/div/div[2]/div[2]/span")).getText();
	}

	public String assertValueFieldDatevalidateEmpty() {
		return driver.findElement(By.xpath("//div[@id='main']/form/div/div[3]/div/span")).getText();
	}
	
	public void setPrice(String newPrice) {
		this.price = newPrice;
	}

	public void setProductName(String name) {
		this.productName = name;
	}

	public void setDateValidate(String date) {
		this.dateValid = date;
	}

	public String getPrice() {
		return this.price;
	}

	public String getProductName() {
		return this.productName;
	}

	public String getDate() {
		return this.dateValid;
	}

}
