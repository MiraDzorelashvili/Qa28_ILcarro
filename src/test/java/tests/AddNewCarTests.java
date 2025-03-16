package tests;

import manager.ApplicationManager;
import models.Car;
import models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddNewCarTests extends TestBase {
    Logger logger = LoggerFactory.getLogger(AddNewCarTests.class);

    @BeforeClass
    public void preCondition(){
        if (!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().setEmail("margo@gmail.com").setPassword("Mmar123456$"));
            logger.info("Logout complete");
        }
    }



    @Test
    public void addNewCarSuccessAllFields(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;

        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("2024")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNum("678-900-"+i)
                .price(50)
                .about("My car")
                .build();
        logger.info("Test start with test data --->" + car.toString());
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().attachPhoto("D:\\Qa28\\Qa_28_IlCarro\\foto-bugatti-veyron_10-650x433.jpg");
        app.getHelperCar().submit();


        Assert.assertTrue(app.getHelperCar().getMessage().contains("added successful"));
        Assert.assertEquals(app.getHelperCar().getMessage(),car.getManufacture()+ " " +car.getModel()+" added successful" );

    }

    @Test
    public void addNewCarSuccessReq(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;

        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("KIA")
                .model("Sportage")
                .year("2020")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNum("555-970-"+i)
                .price(50)
                .build();
        logger.info("Test start with test data --->" + car.toString());
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();
        app.getHelperCar().pause(5000);
        Assert.assertTrue(app.getHelperCar().getMessage().contains("added successful"));
        Assert.assertEquals(app.getHelperCar().getMessage(),car.getManufacture()+ " " +car.getModel()+" added successful" );

    }

    @AfterMethod
    public void postCondition(){
        app.getHelperCar().returnToHomePage();
    }
}