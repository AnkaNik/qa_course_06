package Form;

import com.codeborne.selenide.Configuration;
import formFields.BaseValue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;


import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class Form  extends BaseValue {
    
    String URL = "https://www.google.com/";

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void fillForm() {
        // open form
        open(URL);

        $(".main-header").shouldHave(text("Practice Form"));

        $("[id='firstName']").setValue(firstName);
        $("[id='lastName']").setValue(lastName);
        $("[id='userEmail']").setValue(userEmail);
        $("[id='userNumber']").setValue(phone);
        $("[id='subjectsInput']").setValue(subjectsInput).pressEnter();
        $("[id='currentAddress']").setValue(currentAddress);
        $("[id='uploadPicture']").uploadFile(new File("src/test/upload/picture.jpg"));

        $("[id='dateOfBirthInput']").click();
        $(".react-datepicker__year-select").selectOptionByValue("1978");
        $(".react-datepicker__month-select").selectOptionByValue("3");
        $(".react-datepicker__day.react-datepicker__day--013").click();

        $(("label[for='gender-radio-2']")).click();
        $(("label[for='hobbies-checkbox-1']")).click();
        $("[id='react-select-3-input']").setValue(state).pressEnter();
        $("[id='react-select-4-input']").setValue(city).pressEnter();


        $("#submit").click();

        // Check Submit
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(By.xpath("//*[@class='table-responsive']//tr[1]//td[2]")).shouldHave(text("Maria Ivanova"));
        $(By.xpath("//*[@class='table-responsive']//tr[2]//td[2]")).shouldHave(text(userEmail));
        $(By.xpath("//*[@class='table-responsive']//tr[3]//td[2]")).shouldHave(text("Female"));
        $(By.xpath("//*[@class='table-responsive']//tr[4]//td[2]")).shouldHave(text(phone));
        $(By.xpath("//*[@class='table-responsive']//tr[5]//td[2]")).shouldHave(text("13 April,1978"));
        $(By.xpath("//*[@class='table-responsive']//tr[6]//td[2]")).shouldHave(text(subjectsInput));
        $(By.xpath("//*[@class='table-responsive']//tr[7]//td[2]")).shouldHave(text("Sports"));
        $(By.xpath("//*[@class='table-responsive']//tr[8]//td[2]")).shouldHave(text("picture.jpg"));
        $(By.xpath("//*[@class='table-responsive']//tr[9]//td[2]")).shouldHave(text(currentAddress));
        $(By.xpath("//*[@class='table-responsive']//tr[10]//td[2]")).shouldHave(text(state + " " + city));
        $("[id='closeLargeModal']").click();


    }
}
