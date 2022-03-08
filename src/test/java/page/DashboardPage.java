package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    private SelenideElement depositButton = $("[data-test-id=action-deposit]");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from] input");

    public DashboardPage() {
        heading.shouldBe(visible);
        depositButton.shouldBe(visible);
    }

    public int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }

    public int getCardBalance(int order) {
        val text = cards.get(order).text();
        return extractBalance(text);
    }

    public void transferToCard(int amount) {
        depositButton.click();
        amountField.setValue(String.valueOf(amount));
        fromField.setValue("5559 0000 0000 0002");
        transferButton.click();
    }

    public void transferToCard(int amount, int card) {
        sleep(2000);
        if (card == 1) {
            $$("[data-test-id=action-deposit]").last().click();
        } else {
            depositButton.click();
        }
        amountField.setValue(String.valueOf(amount));
        fromField.setValue("5559 0000 0000 000" + card);
        transferButton.click();
        sleep(2000);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
