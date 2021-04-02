package browserTesting;


import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Steps {

    private WebDriver driver;
    Properties prop = new Properties();


    @Given("^I open Chrome and launch the application$")
    public void openChromeAndLaunchApplication() throws IOException {
        prop.load(new FileInputStream("src/test/resources/config.properties"));
        System.setProperty("webdriver.chrome.driver", prop.getProperty("driver"));
        driver = new ChromeDriver();
        driver.get("https://qa-automation-challenge.github.io/sandbox/");
    }

    @When("^I select type \"(.*)\"$")
    public void selectType(String type) {
        Select select = new Select(driver.findElement(By.id("type")));
        select.selectByVisibleText(type);
    }

    @When("^I select support plan \"(.*)\"$")
    public void selectSupportPlan(String plan) {
        Select select = new Select(driver.findElement(By.id("support")));
        select.selectByVisibleText(plan);
    }

    @When("^I write monthly duration of \"(.*)\"$")
    public void writeMonthlyDuration(String duration) {
        driver.findElement(By.id("duration")).sendKeys(duration);
    }

    @When("^I click in calculate price button")
    public void clickCalculatePriceButton() {
        driver.findElement(By.id("calculate")).click();
    }

    @Then("^I validate price is \"(.*)\"$")
    public void validatePrice(String price) throws InterruptedException {
        Thread.sleep(5000L);
        Assert.assertEquals(price, driver.findElement(By.id("price")).getText());
    }

/*
    @Then("^I attach file \"(.*)\"$")
    public void	attachFile(String file) throws  {
        WebElement uploadElement = driver.findElement(By.id("attachment"));
        uploadElement.sendKeys(file);
    }
*/
}
