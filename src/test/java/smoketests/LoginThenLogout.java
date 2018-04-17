package smoketests;

import config.Config;
import listeners.MyListener;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;

import java.util.ArrayList;

@Listeners({ MyListener.class })
public class LoginThenLogout {

    int count = 1;
    int nrTest = 1;

    //@Test (invocationCount = 3)
    @Test(invocationCount = 4)
    public void LoginThenLogout() throws InterruptedException {
        String uname = "testemag2@gmail.com";
        String pass = "test12";
        if (MyListener.accounts.keySet().size() > 0) {
            uname = (new ArrayList<String>(MyListener.accounts.keySet())).get(count - 1);
            pass = MyListener.accounts.get(uname);
        }
        count++;
        System.out.println(uname + " " + pass);
        //String pass = accounts.get(uname);
        //HomePage.GoToHomePage();
        HomePage.GoToMyAccount();
        //HomePage.GoToUnameField();
        LoginPage.SetUsername(uname);
        boolean isSuccessful = LoginPage.ClickContinue();
        Assert.assertTrue(isSuccessful, "Verificare daca a reusit sa mearga mai departe de uname");
        Assert.assertEquals(LoginPage.GetUNameInserted(), uname, "Verificare daca uname-ul introdus este acelasi");
        LoginPage.SetPassword(pass);
        LoginPage.ClickContinue();
        isSuccessful = HomePage.HoverMyAccount();
        Assert.assertTrue(isSuccessful, "Verificare daca a reusit hover");
        isSuccessful = HomePage.GoToLogout();
        System.out.println("Login successful");
        Assert.assertTrue(isSuccessful, "Verificare daca a gasit butonul de logout");
        //HomePage.GoToMyAccount();
        Assert.assertTrue(HomePage.VerifyLogout(), "Verificare daca apare butonul de intra in cont dupa logout");
        System.out.println("Logout successful");
    }
//threadPoolSize = 3, invocationCount = 2

    @BeforeMethod
    public void beforeMethod () throws InterruptedException {
        System.out.println("----------Starting: " + nrTest++ + " LoginThenLogout test-------------");

        new Config("chrome");
        Config.loadURL(Config.emagURL);

    }

    @AfterMethod
    public void afterMethod (ITestResult result){
        Config.closeDriver();
        System.out.println("----------After: " + count + " LoginThenLogout test-------------");
    }

    @BeforeTest
    public void beforeTest() {
        if (MyListener.accounts.size() == 0) {
            MyListener.accounts.put("test-emag@gmail.com", "test12");
            MyListener.accounts.put("testemag2@gmail.com", "test12");
            MyListener.accounts.put("testemag3@gfhd.com", "test12");
            MyListener.accounts.put("testemag4@yahoo.com", "test12");
            //MyListener.accountsNS.put("testemag4@gmail.com", "test132");
        }

    }
}
