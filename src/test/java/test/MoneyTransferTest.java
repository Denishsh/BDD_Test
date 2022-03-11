package test;

import data.DataHelper;
import lombok.val;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {
    @Test
    void verificationTest() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void checkBalanceTest() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboard = verificationPage.validVerify(verificationCode);
        dashboard.getFirstCardBalance();
        dashboard.getCardBalance(1);
    }

    @Test
    void transferTest() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboard = verificationPage.validVerify(verificationCode);
        val balance1 = dashboard.getFirstCardBalance();
        val balance2 = dashboard.getCardBalance(1);
        val amount = 1000;
        val transferPage = dashboard.transfer();
        val cardNumber = dashboard.getCardNumber(1);
        transferPage.transferToCard(amount, cardNumber, 0);
        assertEquals(balance1 + amount, dashboard.getFirstCardBalance());
        assertEquals(balance2 - amount, dashboard.getCardBalance(1));
    }

    @Test
    void transferSecondTest() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboard = verificationPage.validVerify(verificationCode);
        val balance1 = dashboard.getFirstCardBalance();
        val balance2 = dashboard.getCardBalance(1);
        val amount = 1000;
        val transferPage = dashboard.transfer();
        val cardNumber = dashboard.getCardNumber(0);
        transferPage.transferToCard(amount, cardNumber, 1);
        assertEquals(balance2 + amount, dashboard.getCardBalance(1));
        assertEquals(balance1 - amount, dashboard.getFirstCardBalance());
    }
}

