package desafio.geo.tech.page;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeleteProductPage {
	private WebDriver driver;

	public DeleteProductPage(WebDriver driver) {
		this.driver = driver;
	}

	public void btnDeleteProduct() {
		driver.findElement(By.linkText("Excluir")).click();
	}

	public void doDeleteProduct() {
		driver.findElement(By.xpath("//div[@id='delete-modal']/div/div/div[3]/button")).click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		assertEquals("Produto excluído com sucesso!", alert.getText().toString());
		alert.accept();
	}

	public String assertLabelDeleteProduct() {
		return driver.findElement(By.id("modalLabel")).getText();
	}

}
