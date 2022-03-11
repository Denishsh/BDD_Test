package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.sleep;

public class TransferPage {
    private ElementsCollection depositButton = $$("[data-test-id=action-deposit]");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from] input");

    public TransferPage() {
    }

    public void transferToCard(int amount, String card, int order) {
        depositButton.get(order).click();
        amountField.setValue(String.valueOf(amount));
        fromField.setValue(card);
        transferButton.click();
    }
}
