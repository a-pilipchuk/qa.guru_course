import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.visible;


public class StudentRegistrationFormTests {
    String  firstname = "Anna",
            lastname = "Pilipchuk",
            email = "name@domen.co",
            tel = "0123456789",
            birthday = "5 Jun 1996",
            subject = "e",
            address = "Street, 1";

    @BeforeEach
    void setup() {
        Configuration.browserSize = "1366x1000";
        open("https://demoqa.com/automation-practice-form");
    }

    @Test
    public void positiveTestRegistrationFormAllFields() {
        $("#firstName").setValue(firstname);
        $("#lastName").setValue(lastname);
        $("#userEmail").setValue(email);
        $("[for='gender-radio-3']").click();
        $("#userNumber").setValue(tel);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(5);
        $(".react-datepicker__year-select").selectOptionByValue("1996");
        $(".react-datepicker__day--001").click();
        $("#subjectsInput").setValue(subject);
        $("#react-select-2-option-0").click();
        $("[for='hobbies-checkbox-1']").click();
        $("#uploadPicture").uploadFile(new File("/Users/orange/Pictures/attention.jpg"));
        $("#currentAddress").setValue(address);
        $("#state .css-tlfecz-indicatorContainer").click();
        $("#react-select-3-option-0").click();
        $("#city .css-tlfecz-indicatorContainer").click();
        $("#react-select-4-option-0").click();
        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldBe(visible);
    }

    @Test
    public void positiveTestRegistrationFormRequiredFields() {
        $(By.id("firstName")).setValue(firstname);
        $(By.id("lastName")).setValue(lastname);
        $("[for='gender-radio-2']").click();
        $(By.id("userNumber")).setValue(tel);
        $(By.id("dateOfBirthInput")).setValue(birthday);
        $(By.id("submit")).click();
        $(By.className("modal-title")).shouldHave(visible.text("Thanks for submitting the form"));
    }

    @Test
    public void negativeTestRegistrationForm() {
        $(By.id("submit")).scrollIntoView(true);
        $(By.id("submit")).click();
        $(By.id("firstName")).shouldHave(cssValue("border-color","rgb(220, 53, 69)"));
        $(By.id("lastName")).shouldHave(cssValue("border-color","rgb(220, 53, 69)"));
        $("[for='gender-radio-1']").shouldHave(cssValue("border-color","rgb(220, 53, 69)"));
        $(By.id("userNumber")).shouldHave(cssValue("border-color","rgb(220, 53, 69)"));
        //String color = $("[for='gender-radio-1']").getCssValue("color");
        //System.out.println(color);
    }
}
