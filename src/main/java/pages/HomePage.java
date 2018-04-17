package pages;

import org.openqa.selenium.WebElement;
import utils.WebActions;

public class HomePage {

    public static void GoToHomePage() throws InterruptedException {
        WebElement goToHome = WebActions.findElement(".back-button", "css", 10);
        if (goToHome != null) {
            WebActions.clickElement(goToHome, 10);
        }
    }

    public static boolean ExistsSearchProduct() throws InterruptedException {
        WebElement search = WebActions.findElement("searchboxTrigger","id",60);
        return search != null;
    }

    public static boolean GoToMyAccount() throws InterruptedException {
        WebElement goToMyAccount = WebActions.findElement("my_account", "id", 60);
        if (goToMyAccount != null) {
            WebActions.clickElement(goToMyAccount, 60);
        }
        return goToMyAccount != null;
    }

    public static boolean HoverMyAccount() {
        WebElement goToMyAccount = WebActions.findElement("my_account", "id", 60);
        if (goToMyAccount != null) {
            WebActions.hoverElement(goToMyAccount, 60);
        }
        return goToMyAccount != null;
    }

    public static boolean GoToLogout() {
        WebElement goToLogout = WebActions.findElement("[href=\"/user/logout?ref=ua_logout\"]", "css", 60);
        if (goToLogout != null) {
            WebActions.clickElement(goToLogout, 60);
        }
        return goToLogout != null;
    }

    public static boolean VerifyLogout() {
        HoverMyAccount();
        WebElement goToAccount = WebActions.findElement("[href=\"/user/login?ref=hdr_login_btn\"]", "css", 60);
        return goToAccount.getText().equals("Intra in cont");
    }

    public static void GoToUnameField() {
        WebElement goToLogout = WebActions.findElement("[href=\"/user/login?not-keep=true\"]", "css", 10);
        if (goToLogout != null) {
            WebActions.clickElement(goToLogout, 10);
        }
    }
}
