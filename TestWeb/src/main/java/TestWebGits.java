import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestWebGits {
    WebDriver driver;
    JavascriptExecutor jsx;
    private String email = "yihic42310@ahhtee.com";
    private String passwd = "TestWarung123$";
    private String titleGists = "Gists Test";
    private String fileNameGists = "gist.java";
    private String bodyGists = "This is the test";

    public void goToWebsite(){
        System.setProperty("webdriver.chrome.driver","/Users/indrasimorangkir/TestWeb/chromedriver");
        driver = new ChromeDriver();
        jsx = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://gist.github.com/discover");
    }

    public void loginGithub(){
        goToWebsite();

        WebElement signInButton = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Sign in')]")));
        signInButton.click();

        WebElement emailField = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='login']/div[3]/form/input[2]")));
        emailField.sendKeys(email);

        WebElement passwdField = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='login']/div[3]/form/input[3]")));
        passwdField.sendKeys(passwd);

        WebElement loginButton = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='login']/div[3]/form/input[14]")));
        loginButton.click();
    }

    @Test
    public void createGists(){
        loginGithub();

        WebElement profileDropMenu = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='user-links']/details/summary")));
        profileDropMenu.click();

        WebElement yourGistsMenu = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='user-links']/details/details-menu/a")));
        yourGistsMenu.click();

        WebElement addGistsButton = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".octicon-plus:nth-child(2)")));
        addGistsButton.click();

        WebElement titleField = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='gist[description]']")));
        titleField.sendKeys(titleGists);

        WebElement filenameField = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gists']/div[2]/div/div/div/input[2]")));
        filenameField.sendKeys(fileNameGists);

        WebElement bodyField = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gists']/div[2]/div/div[2]/div/div[5]/div/div/div/div/div[5]")));
        bodyField.sendKeys(bodyGists);

        WebElement bottomDrop = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='new_gist']/div/div[2]/div/details/summary")));
        bottomDrop.click();

        WebElement createpublicButton = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='new_gist']/div/div[2]/div/details/details-menu/label[2]/div/span")));
        createpublicButton.click();

        WebElement createButton = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='new_gist']/div/div[2]/div/button")));
        createButton.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement titleText = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//main[@id='gist-pjax-container']/div[2]/div/div/div")));
        String getTitle = titleText.getText();

        Assert.assertTrue(titleGists.equalsIgnoreCase(getTitle));

        WebElement fileNameText = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='file-gist-java']/div/div[2]/a/strong")));
        String getFileNameTitle = fileNameText.getText();

        Assert.assertTrue(fileNameGists.equalsIgnoreCase(getFileNameTitle));

        WebElement titleBodyText = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@id='file-gist-java-LC1']")));
        String getBodyTitle = titleBodyText.getText();

        Assert.assertTrue(bodyGists.equalsIgnoreCase(getBodyTitle));
    }

    @Test
    public void editGists(){
        createGists();

        WebElement editIconButton = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//main[@id='gist-pjax-container']/div/div/div/ul/li/a")));
        editIconButton.click();

        WebElement titleField = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='gist[description]']")));
        titleField.clear();
        titleField.sendKeys("Gists Test Edit");

        WebElement filenameField = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gists']/div[2]/div/div/div/input[2]")));
        filenameField.clear();
        filenameField.sendKeys("gist-edit.java");

        WebElement bodyField = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gists']/div[2]/div/div[2]/div/div[5]/div/div/div/div/div[5]")));
        bodyField.clear();
        bodyField.sendKeys("This is the test edit");

        WebElement editButton = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@type='submit'])[5]")));
        editButton.click();

        WebElement titleText = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//main[@id='gist-pjax-container']/div[2]/div/div/div")));
        String getTitle = titleText.getText();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertFalse(titleGists.equalsIgnoreCase(getTitle));

        WebElement fileNameText = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='file-gist-edit-java']/div/div[2]/a/strong")));
        String getFileNameTitle = fileNameText.getText();

        Assert.assertFalse(fileNameGists.equalsIgnoreCase(getFileNameTitle));

        WebElement titleBodyText = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='file-gist-edit-java']/div[2]/table/tbody/tr/td[2]")));
        String getBodyTitle = titleBodyText.getText();

        Assert.assertFalse(bodyGists.equalsIgnoreCase(getBodyTitle));

        driver.close();
    }

    @Test
    public void deleteGists(){
        createGists();

        WebElement deleteButton = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//main[@id='gist-pjax-container']/div/div/div/ul/li[2]/form/button")));
        deleteButton.click();

        driver.switchTo().alert().accept();

        WebElement titleConfirmation = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='js-flash-container']/div/div")));
        String getTitleConfirmation = titleConfirmation.getText();

        System.out.println(getTitleConfirmation);
        Assert.assertTrue(getTitleConfirmation.equalsIgnoreCase("Gist deleted successfully."));

        driver.close();
    }

    @Test
    public void listGists(){
        loginGithub();

        WebElement profileDropMenu = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='user-links']/details/summary")));
        profileDropMenu.click();

        WebElement yourGistsMenu = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='user-links']/details/details-menu/a")));
        yourGistsMenu.click();

        List<WebElement> listGists = driver.findElements(By.xpath("//div[@class='col-9 col-md-9 col-12']/div[@class='gist-snippet']"));
        int xpathCount = listGists.size();

        WebElement numberListGists = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//main[@id='gist-pjax-container']/div/div/div[2]/div/nav/div/a/span")));
        int numberList = Integer.parseInt(numberListGists.getText());

        if(xpathCount > 0){
            Assert.assertTrue(true);
        }else {
            Boolean result = numberList == 0;
            Assert.assertTrue(result);
        }

        driver.close();
    }
}
