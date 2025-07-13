package test.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Shopsy {
	
	WebDriver driver;
    JavascriptExecutor jsExecutor;

    @FindBy(xpath="//input[@placeholder='Email']")
    WebElement UserName;

    @FindBy(xpath="//input[@placeholder='Password']")
    WebElement Password;

    @FindBy(xpath="//input[@id='formBasicCheckbox1']")
    WebElement Chkboxbtn;

    @FindBy(xpath="//button[text()='Login']")
    WebElement Loginbtn;

    @FindBy(xpath="//button[normalize-space()='Master Testing']")
    WebElement Showinstitude;

    public Shopsy(WebDriver driver) {
        this.driver = driver;
        jsExecutor = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void ShopsyLogin(String uname, String pwd) {
        UserName.sendKeys(uname);
        Password.sendKeys(pwd);
        Chkboxbtn.click();
        Loginbtn.click();
    }

    public void ShowInstitudes(){ Showinstitude.click(); }

    public void Close() {
        driver.close();
    }

}
