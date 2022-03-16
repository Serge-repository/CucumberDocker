package cucumber_step_defs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

import static cucumber_step_defs.RunnerTest.driver;
import static cucumber_step_defs.RunnerTest.wait;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class FileUploadDef {

    @Given("user is on uploadFilePage")
    public void userIsOnUploadFilePage() {
        driver.navigate().to("http://demo.guru99.com/test/upload/");
    }

    @And("title of page is {string}")
    public void titleOfPageIs(String pageTitle) {
        assertEquals("Page title is File Upload Demo", pageTitle, driver.getTitle());
    }

    @When("user upload the File")
    public void userUploadTheFile() {
        wait.until(presenceOfElementLocated(By.id("uploadfile_0")));
        String fileSeparator = FileSystems.getDefault().getSeparator();
        Path path = Paths.get(System.getProperty("user.dir"));
        String pathAsString = path.toString();
        String filePath = pathAsString + fileSeparator + "src" + fileSeparator + "main"
                + fileSeparator + "resources" + fileSeparator + "test.txt";

        driver.findElement(By.cssSelector("input.upload_txt")).sendKeys(filePath);
    }

    @And("click {string} button")
    public void clickButton(String submitButton) {
        driver.findElement(By.cssSelector("button#submitbutton")).click();
    }

    @Then("success file upload message is shown")
    public void successFileUploadMessageIsShown() {
        wait.until(presenceOfElementLocated(By.xpath("//div[@class='formbuttons']/h3/center")));
        assertTrue(driver.findElement(By.xpath("//div[@class='formbuttons']/h3/center")).isDisplayed());
    }
}