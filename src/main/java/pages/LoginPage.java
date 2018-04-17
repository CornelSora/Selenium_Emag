package pages;

import org.openqa.selenium.WebElement;
import utils.WebActions;

import java.util.Arrays;

public class LoginPage {

    public static void SetUsername(String email) throws InterruptedException {
        WebElement txtEmail = WebActions.findElement("email", "id", 60);
        txtEmail.sendKeys(email);
        //WebActions.clickElement(txtEmail, 60);
    }

    public static void SetPassword(String password) throws InterruptedException {
        WebElement txtPassword = WebActions.findElement("password", "id", 60);
        txtPassword.sendKeys(password);
        //WebActions.clickElement(txtEmail, 60);
    }

    public static boolean ClickContinue() throws InterruptedException {
        WebElement btnContinue = WebActions.findElement("auth-submit", "class", 60);
        if (btnContinue == null) {
            return false;
        }
        WebActions.clickElement(btnContinue, 60);
        return true;
    }

    public static String GetUNameInserted() {
        WebElement txtEmail = WebActions.findElement("step2_email", "id", 60);
        return txtEmail == null ? "" : txtEmail.getAttribute("value");
    }

    public static String GetErrorText() {
        WebElement txtError = WebActions.findElement("/html/body/form/div[2]/div[3]/div/span", "xpath", 60);
        WebActions.clickElement(txtError, 20);
        if (txtError == null) {
            txtError = WebActions.findElement("/html/body/form/div[2]/div[4]/div/span", "xpath", 60);
        }
        //WebActions notRobot = WebActions.findElement("[@id=\"recaptcha-anchor\"]/div[5]", "xpath", 60);
        //WebActions.clickElement(notRobot);
        //WebElement notRobot = WebActions.findElement(".recaptcha-checkbox-checkmark", "css", 20);
        //WebActions.clickElement(notRobot, 20);
        return txtError.getText();
    }
}
