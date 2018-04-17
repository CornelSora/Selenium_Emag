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
public class LoginNotSuccessful {

    int countNS = 0;

    @Test
    void LoginNotSuccessful() throws InterruptedException {
        String uname = "test-emag@gmail.com";
        String pass = "test12";
        if (MyListener.accounts.keySet().size() > 0) {
            uname = (new ArrayList<String>(MyListener.accountsNS.keySet())).get(countNS);
            pass = MyListener.accountsNS.get(uname);
        }
        countNS++;
        boolean isSuccessful = HomePage.GoToMyAccount();
        Assert.assertTrue(isSuccessful, "Verificare daca a reusit sa se duca pe pagina de login");
        LoginPage.SetUsername(uname);
        LoginPage.ClickContinue();
        LoginPage.SetPassword(pass);
        isSuccessful = LoginPage.ClickContinue();
        Assert.assertEquals(isSuccessful, true, "Verificare daca s-a reusit click continue");
        isSuccessful = LoginPage.GetErrorText().equals("Ai introdus gresit parola sau adresa de email. Te rog completeaza din nou.");
        Assert.assertTrue(isSuccessful, "Verificare daca a aparut text-ul de eroare");
    }

    @BeforeMethod
    public void beforeMethod () throws InterruptedException {
        System.out.println("----------Starting: " + (countNS + 1) + " LoginNotSuccessful test-------------");

        new Config("chrome");
        Config.loadURL(Config.emagURL);
    }

    @AfterMethod
    public void afterMethod (ITestResult result){
        Config.closeDriver();
        System.out.println("----------After: " + (countNS + 1) + " LoginNotSuccessful test-------------");
    }

    @BeforeTest
    public void beforeTest() {
        /*if (MyListener.accounts.size() == 0) {
            MyListener.accounts.put("test-emag@gmail.com", "test12");
            MyListener.accounts.put("testemag2@gmail.com", "test12");
            MyListener.accounts.put("testemag3@gfhd.com", "test12");
            MyListener.accountsNS.put("testemag3@gmail.com", "test132");
        }*/

    }

}
