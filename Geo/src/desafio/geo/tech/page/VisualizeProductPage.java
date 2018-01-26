package desafio.geo.tech.page;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class VisualizeProductPage {
	private WebDriver driver;

	public VisualizeProductPage(WebDriver driver) {
		this.driver = driver;
	}

	public void btnVisualizeProduct() {
		driver.findElement(By.linkText("Visualizar")).click();
		assertTrue(isElementPresent(By.cssSelector("div.col-md-3")));
	}

	public String validityProductName() {
		return driver.findElement(By.xpath("//div[@id='main']/div/div[2]/p[2]")).getText();
	}

	public String validityPriceProduct() {
		return driver.findElement(By.xpath("//div[@id='main']/div/div[3]/p[2]")).getText();
	}

	public String validityDateProduct() {
		return driver.findElement(By.xpath("//div[@id='main']/div/div[4]/p[2]")).getText();
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
