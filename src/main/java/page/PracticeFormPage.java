package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class PracticeFormPage {
    protected SelenideElement userName = $("#firstName");
    protected SelenideElement lastName = $("#lastName");
    protected SelenideElement mobileNumberField = $("#userNumber");
}
