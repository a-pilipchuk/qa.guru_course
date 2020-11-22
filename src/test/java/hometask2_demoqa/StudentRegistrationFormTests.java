package hometask2_demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class StudentRegistrationFormTests {
    String  firstName = "Anna",
            lastName = "Pilipchuk",
            email = "name@domen.co",
            tel = "0123456789",
            birthday = "5 Jun 1996",
            subject = "e",
            address = "Street, 1",
            day = "001",
            year = "1970";
    int month = 5;

    @BeforeEach
    void setup() {
        Configuration.browserSize = "1280x720";
        open("https://demoqa.com/automation-practice-form");
    }

    @Test
    public void positiveTestRegistrationFormAllFields() {
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("[for='gender-radio-3']").click();
        String chosenGender = $("[for=gender-radio-3]").getOwnText();
        $("#userNumber").setValue(tel);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        String chosenMonth = $(String.format(".react-datepicker__month-select>[value='%d']",month)).getOwnText();
        $(".react-datepicker__year-select").selectOption(year);
        $(String.format(".react-datepicker__day--%s",day)).click();
        $("#subjectsInput").setValue(subject);
        $("#react-select-2-option-0").click();
        String chosenSubject = $(".subjects-auto-complete__multi-value__label").getOwnText();
        $("[for='hobbies-checkbox-1']").click();
        String chosenHobby = $("[for='hobbies-checkbox-1']").getText();
        $("#uploadPicture").uploadFile(new File("/Users/orange/Pictures/attention.jpg"));
        $("#currentAddress").setValue(address);
        $("#state .css-tlfecz-indicatorContainer").click();
        $("#react-select-3-option-0").click();
        String chosenState = $("#state [class*='singleValue']").getOwnText();
        $("#city .css-tlfecz-indicatorContainer").click();
        $("#react-select-4-option-0").click();
        String chosenCity = $("#city [class*='singleValue']").getOwnText();
        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldBe(visible);
        $("tbody>:nth-child(1)>:last-child").shouldHave(text (firstName), text(lastName));
        $("tbody>:nth-child(2)>:last-child").shouldHave(text (email));
        $("tbody>:nth-child(3)>:last-child").shouldHave(text (chosenGender));
        $("tbody>:nth-child(4)>:last-child").shouldHave(text (tel));
        $("tbody>:nth-child(5)>:last-child").shouldHave(text((String) day.subSequence(1,3)),
                text (chosenMonth), text(year));
        $("tbody>:nth-child(6)>:last-child").shouldHave(text (chosenSubject));
        $("tbody>:nth-child(7)>:last-child").shouldHave(text (chosenHobby));
        $("tbody>:nth-child(8)>:last-child").shouldHave(text ("attention.jpg"));
        $("tbody>:nth-child(9)>:last-child").shouldHave(text (address));
        $("tbody>:nth-child(10)>:last-child").shouldHave(text (chosenState),
                text(chosenCity));
    }

    @Test
    public void positiveTestRegistrationFormRequiredFields() {
        $(("#firstName")).setValue(firstName);
        $(("#lastName")).setValue(lastName);
        $("[for='gender-radio-2']").click();
        $(("#userNumber")).setValue(tel);
        $(("#dateOfBirthInput")).setValue(birthday);
        $(("#submit")).click();
        $((".modal-title")).shouldHave(visible.text("Thanks for submitting the form"));
    }

    @Test
    public void negativeTestRegistrationForm() {
        $(("#submit")).scrollIntoView(true);
        $(("#submit")).click();
        $(("#firstName")).shouldHave(cssValue("border-color","rgb(220, 53, 69)"));
        $(("#lastName")).shouldHave(cssValue("border-color","rgb(220, 53, 69)"));
        $("[for='gender-radio-1']").shouldHave(cssValue("border-color","rgb(220, 53, 69)"));
        $(("#userNumber")).shouldHave(cssValue("border-color","rgb(220, 53, 69)"));
    }
}
