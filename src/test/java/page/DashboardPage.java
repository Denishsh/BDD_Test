package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private ElementsCollection depositButton = $$("[data-test-id=action-deposit]");
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public TransferPage transfer(int order) {
        depositButton.get(order).click();
        return new TransferPage();
    }

    public int getCardBalance(int order) {
        val text = cards.get(order).text();
        return extractBalance(text);
    }

    public String getCardNumber(int order) {
        val text = cards.get(order).text();
        val last = text.substring(text.lastIndexOf("*") + 1, text.indexOf(balanceStart) - 2);
        return DataHelper.getCardNumber(last);
    }


    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
