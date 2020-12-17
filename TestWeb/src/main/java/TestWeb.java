
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestWeb {
    WebDriver driver;
    JavascriptExecutor jsx;
    String minRage;
    String maxRange;
    String macBook;

    public void goToWebsite(){
        System.setProperty("webdriver.chrome.driver","/Users/indrasimorangkir/TestWeb/chromedriver");
        driver = new ChromeDriver();
        jsx = (JavascriptExecutor) driver;
        minRage = "3000000";
        maxRange = "10000000";
        macBook = "Macbook";
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://www.ebay.com/");
    }

    @Test()
    public void testAccessProductByCategory(){
        //Open Web
        goToWebsite();

        //Check that web has been show
        WebElement logo = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@id='gh-logo']")));
        Assert.assertTrue(logo.isDisplayed());

        //Click Category Button
        WebElement categoryButton = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Shop by category')]")));
        categoryButton.click();

        //Click Electronics > Cell Phones & accessories
        WebElement categoryPhoneButton = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='gh-sbc']/tbody/tr/td/ul[2]/li[4]/a")));
        categoryPhoneButton.click();

        //Click Cell Phones & Smartphones
        WebElement cellPhoneAndSmartphoneButton = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id='s0-16-13_2-0-1[0]-0-0']/ul/li[3]/a")));
        cellPhoneAndSmartphoneButton.click();

        //Scroll down
        jsx.executeScript("window.scrollBy(0,5000)", "");

        //Click More refinements
        WebElement refinementsButton = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='s0-16-13-0-1[1]-0-6-2']/button")));
        refinementsButton.click();

        //Click Screen Size
        WebElement screenSizeButton = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='c3-mainPanel-Screen%20Size']/span")));
        screenSizeButton.click();

        //Click checkBox with size 4.0 - 4.4
        WebElement sizePhone1Button = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='c3-subPanel-Screen%20Size_4.0%20-%204.4%20in']/label/span")));
        sizePhone1Button.click();

        //Click checkBox with size 5.0 - 5.4
        WebElement sizePhone2Button = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='c3-subPanel-Screen%20Size_5.0%20-%205.4%20in']/label/span")));
        sizePhone2Button.click();

        //Click checkBox with size 6.0 in or More
        WebElement sizePhone3Button = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='c3-subPanel-Screen%20Size_6%20in%20or%20More']/label/span")));
        sizePhone3Button.click();

        //Click Price
        WebElement priceButton = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='c3-mainPanel']/div[21]")));
        priceButton.click();

        //Fill field minimum Range
        WebElement rangeButtonField = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='c3-subPanel-_x-price-textrange']/div/div/div/input")));
        rangeButtonField.sendKeys(minRage);

        //Fill field maximum Range
        WebElement rangeUpField = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='c3-subPanel-_x-price-textrange']/div/div[2]/div/input")));
        rangeUpField.sendKeys(maxRange);

        //Click Location
        WebElement locationButton = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='c3-mainPanel']/div[23]")));
        locationButton.click();

        //Click Radio Button
        WebElement locationBox = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='c3-subPanel-location_Asia']/label/span")));
        locationBox.click();

        //Click Apply Button
        WebElement applyButton = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='c3-footerId']/div[2]/button")));
        applyButton.click();

        //Scroll Down
        jsx.executeScript("window.scrollBy(0,2000)", "");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Check the checkbox size 4.0 - 4.4 is selected
        Boolean isCheckedSreenSizeOne = driver.findElement(By.xpath("//div[@id='s0-16-13-0-1[1]-0-6-0-0[0]-multiselect[]_36']/a/input")).isSelected();
        Assert.assertTrue(isCheckedSreenSizeOne);

        //Check the checkbox size 5.0 - 5.4 is selected
        Boolean isCheckedSreenSizeTwo = driver.findElement(By.xpath("//div[@id='s0-16-13-0-1[1]-0-6-0-0[0]-multiselect[]_37']/a/input")).isSelected();
        Assert.assertTrue(isCheckedSreenSizeTwo);

        //Check the checkbox size 6.0 in or More is selected
        Boolean isCheckedSreenSizeThree = driver.findElement(By.xpath("//div[@id='s0-16-13-0-1[1]-0-6-0-0[0]-multiselect[]_38']/a/input")).isSelected();
        Assert.assertTrue(isCheckedSreenSizeThree);

        //Check the field minimum Range is same with input
        String nimRangeField = driver.findElement(By.xpath("//div[@id='s0-16-13-0-1[1]-0-6-0-3[2]-textrange']/div/div/div/input")).getAttribute("value");
        nimRangeField = nimRangeField.replaceAll(",","");
        Assert.assertTrue(nimRangeField.equalsIgnoreCase(minRage));

        //Check the field maximum Range is same with input
        String maxRangeField = driver.findElement(By.xpath("//div[@id='s0-16-13-0-1[1]-0-6-0-3[2]-textrange']/div/div[2]/div/input")).getAttribute("value");
        maxRangeField = maxRangeField.replaceAll(",","");
        Assert.assertTrue(maxRangeField.equalsIgnoreCase(maxRange));

        driver.close();
    }

    @Test()
    public void testAccessProductBySearch(){
        //Open Web
        goToWebsite();

        //Check that web has been show
        WebElement logo = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@id='gh-logo']")));
        Assert.assertTrue(logo.isDisplayed());

        //Fill search field
        WebElement searchField = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gh-ac-box2']/input")));
        searchField.sendKeys(macBook);

        //Click Category dropdownlist
        WebElement categorySearchButton = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gh-cat-box']/select")));
        categorySearchButton.click();

        //Click the one category
        Select category = new Select(driver.findElement(By.xpath("//div[@id='gh-cat-box']/select")));
        category.selectByVisibleText("Computers/Tablets & Networking");

        //Click Seacrh button
        WebElement searchButton = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='gh-f']/table/tbody/tr/td[3]/input")));
        searchButton.click();


        WebElement resultSearch = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='srp-river-results']/ul/li/div/div[2]/a/h3/span")));

        //Check the result search with input
        Assert.assertTrue(resultSearch.isDisplayed());

        String oneNameInListResult = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='srp-river-results']/ul/li/div/div[2]/a/h3/span"))).getText();

        //Check the result one item in list with input
        Assert.assertTrue(oneNameInListResult.toLowerCase().contains(macBook.toLowerCase()));

        driver.close();
    }


}
