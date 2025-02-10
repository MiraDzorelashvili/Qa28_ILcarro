package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition(){
        if (app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }

    @Test
    public void loginSuccess() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("mira@gmail.com", "Mmira1234$");
        app.getHelperUser().submitLogin();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
        //app.getHelperUser().clickOKButton();
    }

    @Test
    public void loginSuccess1() {
        User user = new User().setEmail("mira@gmail.com").setPassword("Mmira1234$");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");

    }

    @Test
    public void loginSuccessModel() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("mira@gmail.com", "Mmira1234$");
        app.getHelperUser().submitLogin();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");

    }

    @Test
    public void loginWrongEmail() {
        User user = new User().setEmail("miragmail.com").setPassword("Mmira1234$");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submitLogin();
        //Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");

    }
    @Test
    public void loginWrongPassword() {
        User user = new User().setEmail("mira@gmail.com").setPassword("Mmir1234$");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submitLogin();
        //Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");

    }
    @Test
    public void loginUnregisteredUser(){
        User user = new User().setEmail("mira1@gmail.com").setPassword("Mmira1234$");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submitLogin();
        //Assert.assertTrue(app.getHelperUser().isAlertPresent("Login or Password incorrect"));

    }

    @Test
    public void loginEmptyUser(){
        User user = new User().setEmail(" ").setPassword("Mmira1234$");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submitLogin();
        //Assert.assertTrue(app.getHelperUser().isAlertPresent(" Email is required "));

    }
    @Test
    public void loginEmptyPassword(){
        User user = new User().setEmail("mira1@gmail.com").setPassword(" ");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submitLogin();
        //Assert.assertTrue(app.getHelperUser().isAlertPresent(" Email is required "));

    }
        @AfterMethod
   public void postCondition(){
        app.getHelperUser().clickOKButton();
   }
}