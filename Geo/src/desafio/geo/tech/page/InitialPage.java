package desafio.geo.tech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InitialPage {

	private WebDriver driver;

	public InitialPage(WebDriver driver) {
		this.driver = driver;
	}

	public ProductListPage doLogin(String name) {
		// assertEquals("CRUD - Golang Lucas", driver.getTitle());
		driver.findElement(By.id("owner")).clear();
		driver.findElement(By.name("data[owner]")).sendKeys(name);
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();

		return new ProductListPage(driver);

	}

}
