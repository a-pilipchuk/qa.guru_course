package hometask3_Alfabank;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AlfaTests {


    @Test
    public void archiveDepositsTest() {
        open("https://alfabank.ru/make-money/");
        $("a[href='/make-money/deposits/']>button").click();
        $("[data-test-id='button']").click();
        $("[data-test-id='tabs-list-tabTitle-1']").click();
        $$("div[data-widget-name='CatalogCard']").shouldHaveSize(5);
    }

    @Test
    public void goToDepositInsurance() {
        //Запрограммируйте тест перехода на страницу Вклады->Страхование вкладов,
        //используя для поиска sibling(), preceding(), parent(), closest()
        open("https://alfabank.ru/");
        $("a[title='Вклады']").closest("span").click(); //ближайший элемент вверх по дереву с тегом
        $(byText("Страхование вкладов")).parent().click();
        $("[data-test-id='accordion-item-1']").sibling(0).shouldHave(text("Страхованию подлежат"));
        $("[data-test-id='accordion-item-2']").preceding(1).shouldHave(text("является участником системы"));
    }
}
