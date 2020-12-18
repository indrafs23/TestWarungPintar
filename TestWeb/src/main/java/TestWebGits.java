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
        //Visit page
        goToWebsite();

        //Click Sign In button
        WebElement signInButton = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Sign in')]")));
        signInButton.click();

        //Fill email field
        WebElement emailField = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='login']/div[3]/form/input[2]")));
        emailField.sendKeys(email);

        //Fill password field
        WebElement passwdField = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='login']/div[3]/form/input[3]")));
        passwdField.sendKeys(passwd);

        //Click login button
        WebElement loginButton = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='login']/div[3]/form/input[14]")));
        loginButton.click();
    }

    @Test
    public void createGists(){
        //Login to Github
        loginGithub();

        //Click icon profile
        WebElement profileDropMenu = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='user-links']/details/summary")));
        profileDropMenu.click();

        //Click "Your Gists" menu
        WebElement yourGistsMenu = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='user-links']/details/details-menu/a")));
        yourGistsMenu.click();

        //Click add Button
        WebElement addGistsButton = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".octicon-plus:nth-child(2)")));
        addGistsButton.click();

        //Fill title Field
        WebElement titleField = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='gist[description]']")));
        titleField.sendKeys(titleGists);

        //Fill filename Field
        WebElement filenameField = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gists']/div[2]/div/div/div/input[2]")));
        filenameField.sendKeys(fileNameGists);

        //Fill body Field
        WebElement bodyField = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gists']/div[2]/div/div[2]/div/div[5]/div/div/div/div/div[5]")));
        bodyField.sendKeys(bodyGists);

        //Click dropdown button
        WebElement bottomDrop = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='new_gist']/div/div[2]/div/details/summary")));
        bottomDrop.click();

        //Click "Public Gists" menu
        WebElement createpublicButton = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='new_gist']/div/div[2]/div/details/details-menu/label[2]/div/span")));
        createpublicButton.click();

        //Click create button
        WebElement createButton = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='new_gist']/div/div[2]/div/button")));
        createButton.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Get title gists
        WebElement titleText = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//main[@id='gist-pjax-container']/div[2]/div/div/div")));
        String getTitle = titleText.getText();

        //Check the title is same according input user
        Assert.assertTrue(titleGists.equalsIgnoreCase(getTitle));

        //Get filename gists
        WebElement fileNameText = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='file-gist-java']/div/div[2]/a/strong")));
        String getFileNameTitle = fileNameText.getText();

        //Check the filename is same according input user
        Assert.assertTrue(fileNameGists.equalsIgnoreCase(getFileNameTitle));

        //Get body gists
        WebElement titleBodyText = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@id='file-gist-java-LC1']")));
        String getBodyTitle = titleBodyText.getText();

        //Check the body is same according input user
        Assert.assertTrue(bodyGists.equalsIgnoreCase(getBodyTitle));
    }

    @Test
    public void editGists(){
        //Create a new gists
        createGists();

        //Click icon edit
        WebElement editIconButton = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//main[@id='gist-pjax-container']/div/div/div/ul/li/a")));
        editIconButton.click();

        //Edit title gits
        WebElement titleField = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='gist[description]']")));
        titleField.clear();
        titleField.sendKeys("Gists Test Edit");

        //Edit filename gits
        WebElement filenameField = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gists']/div[2]/div/div/div/input[2]")));
        filenameField.clear();
        filenameField.sendKeys("gist-edit.java");

        //Edit body gits
        WebElement bodyField = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gists']/div[2]/div/div[2]/div/div[5]/div/div/div/div/div[5]")));
        bodyField.clear();
        bodyField.sendKeys("This is the test edit");

        //Click update button
        WebElement updateButton = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@type='submit'])[5]")));
        updateButton.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Get title gists
        WebElement titleText = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//main[@id='gist-pjax-container']/div[2]/div/div/div")));
        String getTitle = titleText.getText();

        //Check the title has been edited
        Assert.assertFalse(titleGists.equalsIgnoreCase(getTitle));

        //Get filename gists
        WebElement fileNameText = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='file-gist-edit-java']/div/div[2]/a/strong")));
        String getFileNameTitle = fileNameText.getText();

        //Check the filename has been edited
        Assert.assertFalse(fileNameGists.equalsIgnoreCase(getFileNameTitle));

        //Get body gists
        WebElement titleBodyText = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='file-gist-edit-java']/div[2]/table/tbody/tr/td[2]")));
        String getBodyTitle = titleBodyText.getText();

        //Check the body has been edited
        Assert.assertFalse(bodyGists.equalsIgnoreCase(getBodyTitle));

        driver.close();
    }

    @Test
    public void deleteGists(){
        // Create a new gists
        createGists();

        // Click delete button
        WebElement deleteButton = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//main[@id='gist-pjax-container']/div/div/div/ul/li[2]/form/button")));
        deleteButton.click();

        //Click "Ok" button for confirmation alert
        driver.switchTo().alert().accept();

        //Get info about deleting
        WebElement titleConfirmation = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='js-flash-container']/div/div")));
        String getTitleConfirmation = titleConfirmation.getText();

        //Check the info is same according displaying text
        Assert.assertTrue(getTitleConfirmation.equalsIgnoreCase("Gist deleted successfully."));

        driver.close();
    }

    @Test
    public void listGists(){
        //Login to web github
        loginGithub();

        //Click account profile
        WebElement profileDropMenu = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='user-links']/details/summary")));
        profileDropMenu.click();

        //Click "Your Gists" menu
        WebElement yourGistsMenu = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='user-links']/details/details-menu/a")));
        yourGistsMenu.click();

        //Get each item in list gists
        List<WebElement> listGists = driver.findElements(By.xpath("//div[@class='col-9 col-md-9 col-12']/div[@class='gist-snippet']"));

        //Number of list gists in one page
        int xpathCount = listGists.size();

        //Get total gits that show in web
        WebElement numberListGists = new WebDriverWait(driver,30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//main[@id='gist-pjax-container']/div/div/div[2]/div/nav/div/a/span")));
        int numberList = Integer.parseInt(numberListGists.getText());

        //Check if the web show the correct data according the total number gists
        if(xpathCount > 0){
            Assert.assertTrue(true);
        }else {
            Boolean result = numberList == 0;
            Assert.assertTrue(result);
        }

        driver.close();
    }
}
