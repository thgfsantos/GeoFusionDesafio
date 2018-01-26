package desafio.geo.tech.page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditProductPage {
	private WebDriver driver;
	
	public EditProductPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void btnEditProduct() {
		//driver.findElement(By.cssSelector("td.ng-binding"));
		driver.findElement(By.linkText("Editar")).click();
	}
	
	public ProductListPage editExistProduct(AddNewProductPage changeProd, String name, String price, String date) {		
		driver.findElement(By.id("campo1")).clear();
	    driver.findElement(By.id("campo1")).sendKeys(name);
	    driver.findElement(By.name("price")).clear();
	    driver.findElement(By.name("price")).sendKeys(price);
	    ((JavascriptExecutor)driver).executeScript ("document.getElementById('campo3').removeAttribute('readonly',0);"); // Enables the from date box

	    WebElement fromDateBox= driver.findElement(By.id("campo3"));
	    fromDateBox.clear();
	    fromDateBox.sendKeys(date);

	    changeProd.setProductName(name);
		changeProd.setPrice(price);
		changeProd.setDateValidate(date);
				
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		return new ProductListPage(driver);
	}
	
	public String validityProductName() {
		return driver.findElement(By.xpath("//div[@id='list']/div/table/tbody/tr/td[2]")).getText();
	}
	
	public String validityPriceProduct() {
		return driver.findElement(By.xpath("//div[@id='list']/div/table/tbody/tr/td[3]")).getText();
	}
	
	public String validityDateProduct() {
		return driver.findElement(By.xpath("//div[@id='list']/div/table/tbody/tr/td[4]")).getText();
	}

}
