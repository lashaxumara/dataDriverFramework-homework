package steps;

import page.PracticeFormPage;

public class PracticeFormStep extends PracticeFormPage {
    public PracticeFormStep fillFirstName(String name){
        userName.setValue(name);
        return this;
    }

    public PracticeFormStep fillLastName(String name){
        lastName.setValue(name);
        return this;
    }

    public PracticeFormStep fillMobile(String mobile){
        mobileNumberField.setValue(mobile);
        return this;
    }
}
