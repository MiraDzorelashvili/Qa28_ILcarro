package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged())
            app.getHelperUser().logout();
    }

    @Test
    public void registrationSuccess() {
        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        System.out.println(i);


        System.out.println(System.currentTimeMillis());
        int z = (int) ((System.currentTimeMillis() / 1000) % 3600);
        System.out.println(z);

        User user = new User()
                .setFirsName("Lisa")
                .setLastName("Snow")
                .setEmail("snow" + i + "@gmail.com")
                .setPassword("Snow123456$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"You are logged in success");


    }
    @Test
    public void RegistrationWrongEmail() {
        User user = new User()
                .setFirsName("Lisa")
                .setLastName("Snow")
                .setEmail("snow" + "gmail.com")
                .setPassword("Snow123456$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
       app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Wrong email format\n" +
                "Wrong email format");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void RegistrationWrongPassword() {
        User user = new User()
                .setFirsName("Lisa")
                .setLastName("Snow")
                .setEmail("snow"+ "@gmail.com")
                .setPassword("S123456");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Password must contain minimum 8 symbols\n" +
                "Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());

    }
    @Test
    public void RegistrationEmptyFirstName() {
        User user = new User()
                .setFirsName(" ")
                .setLastName("Snow")
                .setEmail("snow" + "@gmail.com")
                .setPassword("Snow123456$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"{\"firstName\":\"не должно быть пустым\"}");

    }
    @Test
    public void RegistrationEmptyLastName() {
        User user = new User()
                .setFirsName("Lisa")
                .setLastName(" ")
                .setEmail("snow" + "@gmail.com")
                .setPassword("Snow123456$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"{\"lastName\":\"не должно быть пустым\"}");


    }
    @Test
    public void RegistrationEmptyEmail() {
        User user = new User()
                .setFirsName("Lisa")
                .setLastName("Snow")
                .setEmail(" ")
                .setPassword("Snow123456$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Email is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void RegistrationEmptyPassword() {
        User user = new User()
                .setFirsName("Lisa")
                .setLastName("Snow")
                .setEmail("snow" + "@gmail.com")
                .setPassword("");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Password is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());

    }
    @Test
    public void RegistrationEmptyCheckPolicy() {
        User user = new User()
                .setFirsName("Lisa")
                .setLastName("Snow")
                .setEmail("snow" + "@gmail.com")
                .setPassword("Snow123456$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
      //  app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());

    }
    @AfterMethod
    public void postCondition(){
        app.getHelperUser().clickOkButton();
    }


}