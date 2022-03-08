package test;

import data.DataHelper;
import lombok.val;
import org.junit.jupiter.api.Test;
import page.LoginPageV1;
import page.LoginPageV2;
import page.LoginPageV3;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {
    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        open("http://localhost:9999");
        val loginPage = new LoginPageV1();
//    val loginPage = open("http://localhost:9999", LoginPageV1.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV2() {
        open("http://localhost:9999");
        val loginPage = new LoginPageV2();
//    val loginPage = open("http://localhost:9999", LoginPageV2.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV3() {
        val loginPage = open("http://localhost:9999", LoginPageV3.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void checkBalanceTest() {
        open("http://localhost:9999");
        val loginPage = new LoginPageV1();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboard = verificationPage.validVerify(verificationCode);
        val balance1 = dashboard.getFirstCardBalance();
        System.out.println(balance1);
        val balance2 = dashboard.getCardBalance(1);
        System.out.println(balance2);
    }

    @Test
    void transferTest() {
        open("http://localhost:9999");
        val loginPage = new LoginPageV1();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboard = verificationPage.validVerify(verificationCode);
        val balance1 = dashboard.getFirstCardBalance();
        val balance2 = dashboard.getCardBalance(1);
        val amount = 1000;
        dashboard.transferToCard(amount);
        assertEquals(balance1 + amount, dashboard.getFirstCardBalance());
        assertEquals(balance2 - amount, dashboard.getCardBalance(1));
    }

    @Test
    void transferSecondTest() {
        open("http://localhost:9999");
        val loginPage = new LoginPageV1();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboard = verificationPage.validVerify(verificationCode);
        val balance1 = dashboard.getFirstCardBalance();
        val balance2 = dashboard.getCardBalance(1);
        val amount = 1000;
        dashboard.transferToCard(amount, 1);
        assertEquals(balance2 + amount, dashboard.getCardBalance(1));
        assertEquals(balance1 - amount, dashboard.getFirstCardBalance());
    }
}

