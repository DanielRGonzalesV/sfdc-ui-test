package com.salesforce.dev;

import com.salesforce.dev.pages.Accounts.AccountDetail;
import com.salesforce.dev.pages.Accounts.AccountForm;
import com.salesforce.dev.pages.Accounts.AccountsHome;
import com.salesforce.dev.pages.Base.NavigationBar;
import com.salesforce.dev.pages.Home.HomePage;
import com.salesforce.dev.pages.Home.LoginPage;
import com.salesforce.dev.pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Walter on 13/06/2015.
 */
public class EditAccount {

    private LoginPage loginPage;

    private HomePage homePage;
    private MainPage mainPage;
    private AccountDetail accountDetail;
    private NavigationBar navigationBar;
    private AccountsHome accountsHome;
    private AccountForm accountForm;
    private String accountName = "AccountName";

    private String accountNameUpdated = "AccountUpdated";
    private String accountDesc = "Account Description";
    private String rating = "Hot"; //--None--, Hot, Warm, Cold
    private String ownership = "Private"; //--None--, Private, Public, Subsidiary, Other
    private String phone = "+(591)72356852";
    private String fax = "+(591)72356852";
    private String number = "123456";
    private String website = "http://test.jalasoft.com";
    private String accountSite = "accountTest";
    private String tickerSymbol = "Ticker Simbol";
    private String type = "Prospect"; //--None--, Prospect, Customer - Direct, Customer - Channel, Channel Partner / Reseller, Installation Partner, Technology Partner, Other
    private String industry = "Technology"; //Several items
    private Integer employees = 1234;
    private Integer annualRevenue = 4566;
    private String sicCode = "ABCDE";
    private String billingAddress = "Billing Address";
    private String shippingAddress = "Shipping Address";
    private String customerPriority = "High"; //Selected Item
    private String sla = "Gold"; //Selected Item
    private String upsellOpportunity = "Yes"; //Selected Item
    private String active = "Yes";
    private String slaExpirationDate = "12/31/2015";
    private String slaSerialNumber = "123-456-78";
    private Integer numberOfLocations = 789;


    @BeforeMethod(groups = {"Acceptance"})
    public void setUp() {
        homePage = new HomePage();
        mainPage = homePage.clickLoginBtn().loginAsPrimaryUser();
        navigationBar = mainPage.gotoNavBar();
        accountsHome = navigationBar.goToAccountsHome();
        accountForm = accountsHome.clickNewBtn();
        accountForm.setAccountNameFld(accountName);
        accountDetail = accountForm.clickSaveBtn();
        mainPage = accountDetail.gotoMainPage();
        navigationBar = mainPage.gotoNavBar();
    }

    @Test(groups = {"Acceptance"})
    public void testEditAccount() {
        accountsHome = navigationBar.goToAccountsHome();
        accountDetail = accountsHome.selectRecentItem(accountName);
        accountForm = accountDetail.clickEditBtn();
        accountForm.setAccountNameFld(accountNameUpdated);
        accountForm.setAccountRatingFld(rating);
        accountForm.setAccountOwnershipFld(ownership);
        accountForm.setAccountPhoneFld(phone);
        accountForm.setAccountFaxFld(fax);
        accountForm.setAccountNumberFld(number);
        accountForm.setAccountWebsiteFld(website);
        accountForm.setAccountSiteFld(accountSite);
        accountForm.setAccountThickerFld(tickerSymbol);
        accountForm.setAccountTypeFld(type);
        accountForm.setAccountIndustryFld(industry);
        accountForm.setAccountEmployeesFld(employees);
        accountForm.setAccountAnnualRevenueFld(annualRevenue);
        accountForm.setAccountSICCodeFld(sicCode);
        accountForm.setAccountBillingStreetFld(billingAddress);
        accountForm.setAccountShippingStreetFld(shippingAddress);
        accountForm.setAccountCustomerPriorityFld(customerPriority);
        accountForm.setAccountSLAFld(sla);
        accountForm.setAccountUpsellOpportunityFld(upsellOpportunity);
        accountForm.setAccountActiveFld(active);
        accountForm.setAccountSLAExpirationDateFld(slaExpirationDate);
        accountForm.setAccountSLASerialNumberFld(slaSerialNumber);
        accountForm.setAccountNumberLocationsFld(numberOfLocations);
        accountForm.setAccountDescriptionFld(accountDesc);

        accountDetail = accountForm.clickSaveBtn();

        Assert.assertTrue(accountDetail.validateAccountNameFld(accountNameUpdated));
        Assert.assertTrue(accountDetail.validateAccountRatingFld(rating));
        Assert.assertTrue(accountDetail.validateAccountOwnershipFld(ownership));
        Assert.assertTrue(accountDetail.validateAccountPhoneFld(phone));
        Assert.assertTrue(accountDetail.validateAccountFaxFld(fax));
        Assert.assertTrue(accountDetail.validateAccountNumberFld(number));
        Assert.assertTrue(accountDetail.validateAccountWebsiteFld(website));
        Assert.assertTrue(accountDetail.validateAccountSiteFld(accountSite));
        Assert.assertTrue(accountDetail.validateAccountTickerSymbolFld(tickerSymbol));
        Assert.assertTrue(accountDetail.validateAccountTypeFld(type));
        Assert.assertTrue(accountDetail.validateAccountIndustryFld(industry));
        Assert.assertTrue(accountDetail.validateAccountEmployeesFld(employees));
        Assert.assertTrue(accountDetail.validateAccountAnnualRevenueFld(annualRevenue));
        Assert.assertTrue(accountDetail.validateAccountSICCodeFld(sicCode));
        Assert.assertTrue(accountDetail.validateAccountBillingAddressFld(billingAddress));
        Assert.assertTrue(accountDetail.validateAccountShippingAddressFld(shippingAddress));
        Assert.assertTrue(accountDetail.validateAccountCustomPriorityFld(customerPriority));
        Assert.assertTrue(accountDetail.validateAccountSLAFld(sla));
        Assert.assertTrue(accountDetail.validateAccountUpSellOpportunityFld(upsellOpportunity));
        Assert.assertTrue(accountDetail.validateAccountActiveFld(active));
        Assert.assertTrue(accountDetail.validateAccountSLAExpirationDateFld(slaExpirationDate));
        Assert.assertTrue(accountDetail.validateAccountSerialNumberFld(slaSerialNumber));
        Assert.assertTrue(accountDetail.validateAccountNumberOfLocationsFld(numberOfLocations));
        Assert.assertTrue(accountDetail.validateAccountDescriptionFld(accountDesc));

    }

    @AfterMethod(groups = {"Acceptance"})
    public void tearDown() {
        accountDetail.clickDeleteBtn(true);

    }
}
