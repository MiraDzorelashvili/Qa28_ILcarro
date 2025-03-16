package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class SearchCarTests extends TestBase{

    @Test
    public void searchCurrentMonthSuccess(){
        app.getHelperCar().searchCurrentMonth("Rehovot", "3/10/2025", "3/27/2025");
        app.getHelperCar().getScreen("src/test/screenhots/currentMonth.png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchCurrentYearSuccess(){
        app.getHelperCar().searchCurrentYear("Rehovot", "4/27/2025", "6/28/2025");
        app.getHelperCar().getScreen("src/test/screenhots/currentYear.png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchAnyPeriodSuccess(){
        app.getHelperCar().searchAnyPeriod("Rehovot", "11/15/2025", "2/10/2026");
        app.getHelperCar().getScreen("src/test/screenhots/any.png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }
    @Test
    public void searchCarWithOutCity(){
        app.getHelperCar().searchAnyPeriod("", "11/15/2025", "2/10/2026");
        app.getHelperCar().getScreen("src/test/screenhots/any.png");
        app.getHelperCar().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"City is required & Dates are required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void searchCarWithOutData(){
        app.getHelperCar().searchAnyPeriod("Rehovot", "", "");
        app.getHelperCar().getScreen("src/test/screenhots/any.png");
        app.getHelperCar().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Dates are required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @AfterMethod
    public void postCondition(){
        app.getHelperCar().navigateByLogo();
    }
}