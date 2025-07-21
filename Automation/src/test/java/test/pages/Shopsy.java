package test.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Shopsy {
	
	WebDriver driver;
    JavascriptExecutor jsExecutor;

    @FindBy(id = "text")
    WebElement UserName;

    @FindBy(id = "password")
    WebElement Password;

    @FindBy(id = "login-button")
    WebElement Loginbtn;


    public Shopsy(WebDriver driver) {
        this.driver = driver;
        jsExecutor = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void ShopsyLogin(String uname, String pwd) {
        UserName.sendKeys(uname);
        Password.sendKeys(pwd);
        Loginbtn.click();
    }

    public void ShowInstitudes(){ 
    	 }

    public void Close() {
        driver.close();
    }

}
