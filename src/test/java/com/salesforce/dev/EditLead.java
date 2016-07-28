package com.salesforce.dev;

import com.salesforce.dev.framework.JSONMapper;
import com.salesforce.dev.framework.Objects.Campaign;
import com.salesforce.dev.framework.Objects.Lead;
import com.salesforce.dev.pages.Base.NavigationBar;
import com.salesforce.dev.pages.Common;
import com.salesforce.dev.pages.Home.HomePage;
import com.salesforce.dev.pages.Home.LoginPage;
import com.salesforce.dev.pages.Leads.LeadDetail;
import com.salesforce.dev.pages.Leads.LeadForm;
import com.salesforce.dev.pages.Leads.LeadsHome;
import com.salesforce.dev.pages.MainPage;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by jimmy vargas on 6/21/2015.
 */
public class EditLead {
    private static final Logger LOGGER = Logger.getLogger (EditLead.class.getName ());
    private HomePage homePage;
    private MainPage mainPage;
    private Campaign campaign;
    private NavigationBar navBar;

    private Lead lead,leadEditEnum;

    @BeforeMethod(groups = {"Acceptance"})
    public void setup(){

        lead = JSONMapper.getLead("src/test/resources/CreateLeadBase.json");
        leadEditEnum = JSONMapper.getLead("src/test/resources/EditLead.json");

        //Creating a lead
        Common.createLead(lead);

        mainPage = LoginPage.loginAsPrimaryUser ();
        navBar = mainPage.gotoNavBar ();
    }

    @Test(groups = {"Acceptance"})
    public void testEditLead(){
        mainPage = LoginPage.loginAsPrimaryUser();
        LeadsHome leadsHome = mainPage.gotoNavBar().gotToLeadsHome();
        LeadDetail leadDetail= leadsHome.openLead(lead.lastName);
        LeadForm leadForm = leadDetail.clickEditBtn();

        //editing some fields
            leadForm.setLastName(leadEditEnum.lastName);
            leadForm.setPhone(leadEditEnum.phone);
            leadForm.setWebsite(leadEditEnum.website);
            leadForm.selectLeadStatusByVisibleText(leadEditEnum.leadStatus);
            leadForm.setNumEmployees(leadEditEnum.numEmployees);
            leadForm.selectProductInterestByVisibleText(leadEditEnum.productInterest);
            leadForm.selectPrimaryByVisibleText(leadEditEnum.primary);
            leadForm.setDescription(leadEditEnum.description);
        leadDetail = leadForm.clickSaveBtn();

        //assertions
        Assert.assertTrue(leadDetail.getName().contains(leadEditEnum.lastName), "The actual name doesn't contain the lastname" + lead.lastName);
        Assert.assertEquals(leadDetail.getCompany(), leadEditEnum.company, "The company is not equal");
        Assert.assertEquals(leadDetail.getPhone(), leadEditEnum.phone, "The phone is not equal");
        Assert.assertEquals(leadDetail.getWebsite(), "http://"+leadEditEnum.website, "The website is not equal");
        Assert.assertEquals(leadDetail.getLeadStatus(), leadEditEnum.leadStatus, "The lead is not equal");
        Assert.assertEquals(leadDetail.getNumEmployees(), leadEditEnum.numEmployees, "The number of employees is not equal");
        Assert.assertEquals(leadDetail.getProductInterest(), leadEditEnum.productInterest, "The product interest is not equal");
        Assert.assertEquals(leadDetail.getPrimary(), leadEditEnum.primary, "The primary is not equal");
        Assert.assertEquals(leadDetail.getDescription(), leadEditEnum.description, "The description is not correct");
    }

    @AfterMethod(groups = {"Acceptance"})
    public void tearDown(){
        LeadsHome leadsHome = mainPage.gotoNavBar().gotToLeadsHome();
        LeadDetail leadDetail= leadsHome.openLead(leadEditEnum.lastName);
        leadDetail.deleteLead();
    }
}
