package manager;

import com.google.common.io.Files;
import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperCar extends HelperBase {
    public HelperCar(WebDriver wd) {
        super(wd);
    }

    public void openCarForm() {
        pause(500);
        click(By.xpath("//a[text()=' Let the car work ']"));
    }

    public void fillCarForm(Car car) {
        typeLocation(car.getLocation());
        type(By.id("make"), car.getManufacture());
        type(By.id("model"), car.getModel());
        type(By.id("year"), car.getYear());
        select(By.id("fuel"), car.getFuel());
        type(By.id("seats"), String.valueOf(car.getSeats()));
        type(By.id("class"), car.getCarClass());
        type(By.id("serialNumber"), car.getCarRegNum());
        //type(By.id("price"), String.valueOf(car.getPrice()));
        type(By.id("price"), car.getPrice() + "");
        type(By.id("about"), car.getAbout());
    }

    private void select(By locator, String option) {
        Select select = new Select(wd.findElement(locator));
        select.selectByValue(option);
//        select.selectByIndex(5);
//        select.selectByValue("Gas");
//        select.selectByVisibleText(" Gas ");

    }

    private void typeLocation(String location) {
        type(By.id("pickUpPlace"), location);
        click(By.cssSelector("div.pac-item"));
    }

    public void returnToHomePage() {
        click(By.xpath("//button[text()='Search cars']"));

    }

    public void attachPhoto(String link) {
        wd.findElement(By.id("photos")).sendKeys(link);
    }

    public void searchCurrentMonth(String city, String dateFrom, String dateTo) {
        clearTextBox(By.id("city"));
        typeCity(city);
        clearTextBox(By.id("dates"));
        click(By.id("dates"));
        //"3/10/2025", "3/27/2025" 10  27

        String[] from = dateFrom.split("/");///["3"]["10"]["2025"]
        String locatorFrom = "//div[text()= ' " + from[1] + " ']";
        click(By.xpath(locatorFrom));

        String[] to = dateTo.split("/");///["3"]["27"]["2025"]
        click(By.xpath("//div[text()= ' " + to[1] + " ']"));
    }

    private void typeCity(String city) {
        clearTextBox(By.id("city"));
        type(By.id("city"), city);
        click(By.cssSelector("div.pac-item"));
    }

    public boolean isListOfCarsAppeared() {
        return isElementPresent(By.cssSelector("a.car-container"));
    }

    public void searchCurrentYear(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextBox(By.id("dates"));
        click(By.id("dates"));

        //"4/27/2025", "6/28/2025"
        LocalDate now = LocalDate.now();
        System.out.println(now); //2025-03-02
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();

        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dateTo,DateTimeFormatter.ofPattern("M/d/yyyy"));
//        System.out.println(from);
//        LocalDate from1 = LocalDate.parse("2013:23/05", DateTimeFormatter.ofPattern("yyyy:dd/MM"));
//        System.out.println(from1);

        int diffMonth = from.getMonthValue() - month;
        if (diffMonth>0)
            clickNextMonthBtn(diffMonth);

        click(By.xpath("//div[text()= ' " + from.getDayOfMonth() + " ']"));

        diffMonth = to.getMonthValue()- from.getMonthValue();
        if (diffMonth>0)
            clickNextMonthBtn(diffMonth);

        //"//div[text()= ' " + from[1] + " ']";
        String locator = String.format("//div[text()= ' %s ']",to.getDayOfMonth());
        click(By.xpath(locator));


    }

    private void clickNextMonthBtn(int diffMonth) {
        for (int i = 0; i < diffMonth; i++) {
            click(By.cssSelector("button[aria-label='Next month']"));

        }
    }

    public void searchAnyPeriod(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextBox(By.id("dates"));
        click(By.id("dates"));

        LocalDate now = LocalDate.now();
        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dateTo,DateTimeFormatter.ofPattern("M/d/yyyy"));

        int diffYear;
        int diffMonth;
        ///**from
        diffYear = from.getYear()- now.getYear();
        if(diffYear==0){ //2025=2025
            diffMonth = from.getMonthValue() - now.getMonthValue();//10-3=7
        }else //2025!=2026
            diffMonth = 12-now.getMonthValue()+from.getMonthValue(); //12-3+2=11

        clickNextMonthBtn(diffMonth);
        String locator = String.format("//div[text()= ' %s ']",from.getDayOfMonth());
        click(By.xpath(locator));

        ///**to
        diffYear = to.getYear()-from.getYear();
        if (diffYear==0) {
            diffMonth = to.getMonthValue() - from.getMonthValue();
        }else
            diffMonth = 12- from.getMonthValue()+ to.getMonthValue();
        clickNextMonthBtn(diffMonth);

        locator = String.format("//div[text()= ' %s ']",to.getDayOfMonth());
        click(By.xpath(locator));
    }

    public void navigateByLogo() {
        click(By.cssSelector("a.logo"));
    }
    public void getScreen(String link) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) wd;
        File tmp = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tmp, new File(link));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}