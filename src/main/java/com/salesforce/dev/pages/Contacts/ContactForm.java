package com.salesforce.dev.pages.Contacts;

import com.salesforce.dev.pages.Base.FormBase;
import com.salesforce.dev.pages.Base.SearchLookupBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;


/**
 * Created by Marcelo.Vargas on 6/12/2015.
 */
public class ContactForm extends FormBase {

    //Contact Information

    @FindBy(name = "name_salutationcon2")
    private WebElement firstNameRoleSelect;

    @FindBy(name = "name_firstcon2")
    private WebElement firstNameFld;

    @FindBy(name = "name_lastcon2")
    private WebElement lastNameFld;

    @FindBy(id = "con4_lkwgt")
    @CacheLookup
    WebElement lookupAccountImg;

    @FindBy(name = "con4") // lookup
    private WebElement accountNameFld;

    @FindBy(id = "con5")
    private WebElement titleFld;

    @FindBy(id = "con6")
    private WebElement departmentFld;

    @FindBy(id = "con7") // calendar
    private WebElement birthDayFld;

    @FindBy(id = "con8_lkwgt")
    @CacheLookup
    WebElement lookupReportsToImg;

    @FindBy(id = "con8") //lookup
    private WebElement reportsToFld;

    @FindBy(id = "con9")
    private WebElement leadSourceSelect;

    @FindBy(id = "con10")
    private WebElement phoneFld;

    @FindBy(id = "con13")
    private WebElement homePhoneFld;

    @FindBy(id = "con12")
    private WebElement mobileFld;

    @FindBy(id = "con14")
    private WebElement otherPhoneFld;

    @FindBy(id = "con11")
    private WebElement faxFld;

    @FindBy(id = "con15")
    private WebElement emailFld;

    @FindBy(id = "con16")
    private WebElement assistantFld;

    @FindBy(id = "con17")
    private WebElement assistantPhoneFld;

    //Address Information

    @FindBy(id = "con19street")
    private WebElement mailingStreetFld;

    @FindBy(id = "con19city")
    private WebElement mailingCityFld;

    @FindBy(id = "con19state")
    private WebElement mailingStateFld;

    @FindBy(id = "con19zip")
    private WebElement mailingZipFld;

    @FindBy(id = "con19country")
    private WebElement mailingCountryFld;

    @FindBy(id = "con18street")
    private WebElement otherStreetFld;

    @FindBy(id = "con18city")
    private WebElement otherCityFld;

    @FindBy(id = "con18state")
    private WebElement otherStateFld;

    @FindBy(id = "con18zip")
    private WebElement otherZipFld;

    @FindBy(id = "con18country")
    private WebElement otherCountryFld;

    // Additional Information

    @FindBy(xpath = "//label[contains(.,'Languages')]/following::input")
    private WebElement languagesFld;

    @FindBy(xpath = "//label[contains(.,'Level')]/following::select")
    private WebElement levelSelect;

    // Description Information

    @FindBy(id = "con20")
    private WebElement descriptionFld;

    @FindBy(name = "save")
    @CacheLookup
    private WebElement saveBtn;

    @Override
    public Object clickSaveNewBtn() {
        return null;
    }

    @Override
    public Object clickCancelBtn() {
        return null;
    }

    @Override
    public ContactDetail clickSaveBtn() {
        clickSaveButton();
        return new ContactDetail();
    }

    public SearchLookupBase clickLookupAccount() {
        lookupAccountImg.click();
        return new SearchLookupBase();
    }

    public ContactForm setFirstNameRole(String text) {
        selectItemComboBox(firstNameRoleSelect, text);
        return this;
    }

    public ContactForm setFirstName(String text) {
        fillTextBox(firstNameFld, text);
        return this;
    }

    public ContactForm setLastName(String text) {
        fillTextBox(lastNameFld, text);
        return this;
    }

    public ContactForm setTitle(String text) {
        fillTextBox(titleFld, text);
        return this;
    }

    public ContactForm setDepartment(String text) {
        fillTextBox(departmentFld, text);
        return this;
    }

    public ContactForm setBirthDate(Integer month, Integer day, Integer year) {
        birthDayFld.click();
        selectDatePicker(month, day, year);
        return this;
    }

    public SearchLookupBase clickLookupReportsTo() {
        lookupReportsToImg.click();
        return new SearchLookupBase();
    }

    public ContactForm setLeadSource(String text) {
        selectItemComboBox(leadSourceSelect, text);
        return this;
    }

    public ContactForm setPhone(String text) {
        fillTextBox(phoneFld, text);
        return this;
    }

    public ContactForm setHomePhone(String text) {
        fillTextBox(homePhoneFld, text);
        return this;
    }

    public ContactForm setMobile(String text) {
        fillTextBox(mobileFld, text);
        return this;
    }

    public ContactForm setOtherPhone(String text) {
        fillTextBox(otherPhoneFld, text);
        return this;
    }

    public ContactForm setFax(String text) {
        fillTextBox(faxFld, text);
        return this;
    }

    public ContactForm setEmail(String text) {
        fillTextBox(emailFld, text);
        return this;
    }

    public ContactForm setAssistant(String text) {
        fillTextBox(assistantFld, text);
        return this;
    }

    public ContactForm setAssistantPhone(String text) {
        fillTextBox(assistantPhoneFld, text);
        return this;
    }


    public ContactForm setMailingStreet(String text) {
        fillTextBox(mailingStreetFld, text);
        return this;
    }

    public ContactForm setMailingCity(String text) {
        fillTextBox(mailingCityFld, text);
        return this;
    }

    public ContactForm setMailingState(String text) {
        fillTextBox(mailingStateFld, text);
        return this;
    }

    public ContactForm setMailingZip(String text) {
        fillTextBox(mailingZipFld, text);
        return this;
    }

    public ContactForm setMailingCountry(String text) {
        fillTextBox(mailingCountryFld, text);
        return this;
    }

    public ContactForm setOtherStreet(String text) {
        fillTextBox(otherStreetFld, text);
        return this;
    }

    public ContactForm setOtherCity(String text) {
        fillTextBox(otherCityFld, text);
        return this;
    }

    public ContactForm setOtherState(String text) {
        fillTextBox(otherStateFld, text);
        return this;
    }

    public ContactForm setOtherZip(String text) {
        fillTextBox(otherZipFld, text);
        return this;
    }

    public ContactForm setOtherCountry(String text) {
        fillTextBox(otherCountryFld, text);
        return this;
    }

    public ContactForm setLanguages(String text) {
        fillTextBox(languagesFld, text);
        return this;
    }

    public ContactForm setLevel(String text) {
        selectItemComboBox(levelSelect, text);
        return this;
    }

    public ContactForm setDescription(String text) {
        fillTextBox(descriptionFld, text);
        return this;
    }
}