package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperBase {

    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void type(By locator, String text){
        WebElement element = wd.findElement(locator);
        element.click();
        element.clear();
        clearNew(element);
        if (text !=null){
            element.sendKeys(text);
        }
    }

    public void clearNew(WebElement element){
        element.sendKeys(" ");
        element.sendKeys(Keys.BACK_SPACE);
    }


    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isElementPresent(By locator) {
        return wd.findElements(locator).size()>0;
    }


    public boolean isYallaButtonNotActive() {
        boolean res = isElementPresent(By.cssSelector("button[disabled]"));

        WebElement element = wd.findElement(By.cssSelector("button[type='submit']"));
        boolean result = element.isEnabled();

        return res && !result;
    }

    public void submit() {
        click(By.xpath("//button[@type='submit']"));
    }

    public String getMessage() {
//        WebElement element = wd.findElement(By.cssSelector(".dialog-container>h2"));
//        String text = element.getText();
//        return text;
        //pause(2000);
        return wd.findElement(By.cssSelector(".dialog-container>h2")).getText();
    }

    public void clearTextBox(By locator) {
        WebElement el = wd.findElement(locator);
        String os = System.getProperty("os.name");
        System.out.println(os);

        if (os.startsWith("Win")) {
            el.sendKeys(Keys.CONTROL, "a");
        } else
            el.sendKeys(Keys.COMMAND, "a");
        el.sendKeys(Keys.DELETE);
    }
}