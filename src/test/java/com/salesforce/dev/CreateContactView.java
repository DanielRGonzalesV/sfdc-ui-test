package com.salesforce.dev;

import com.salesforce.dev.framework.DataDrivenManager;
import com.salesforce.dev.framework.Objects.FieldToDisplayView;
import com.salesforce.dev.framework.Objects.FilterView;
import com.salesforce.dev.framework.Objects.ViewSalesForce;
import com.salesforce.dev.framework.RamdonGenerator;
import com.salesforce.dev.pages.Base.NavigationBar;
import com.salesforce.dev.pages.Campaigns.CampaignForm;
import com.salesforce.dev.pages.Campaigns.CampaignView;
import com.salesforce.dev.pages.Campaigns.CampaignViewDetail;
import com.salesforce.dev.pages.Campaigns.CampaignsHome;
import com.salesforce.dev.pages.Contacts.ContactView;
import com.salesforce.dev.pages.Contacts.ContactViewDetail;
import com.salesforce.dev.pages.Contacts.ContactsHome;
import com.salesforce.dev.pages.Home.HomePage;
import com.salesforce.dev.pages.MainPage;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertFalse;

/**
 * Created by Alexander Apaza on 6/12/2015.
 */
public class CreateContactView {

    private static final Logger LOGGER = Logger.getLogger(CreateContactView.class.getName());

    private ContactsHome contactHome;

    private ContactView contactView;

    private HomePage homePage;

    private ContactViewDetail contactViewDetail;

    private CampaignsHome campaignsHome;

    private MainPage mainPage;

    private NavigationBar navigationBar;

    private String campaignName;

    private CampaignForm campaignForm;

    @DataProvider(name = "dataDriven")
    public Iterator<ViewSalesForce[]> getValues() {
        DataDrivenManager dataDrivenManager = new DataDrivenManager();
        return dataDrivenManager.getDataView("CreateContactView.json");
    }

    @BeforeMethod(groups = {"Acceptance"})
    public void setUp() {
        campaignName = "Camp" + RamdonGenerator.getInstance().getRamdonString();
        homePage = new HomePage();
        mainPage = homePage.clickLoginBtn().loginAsPrimaryUser();
        navigationBar = mainPage.gotoNavBar();
        campaignsHome = navigationBar.goToCampaignsHome();
        campaignForm = campaignsHome.clickNewBtn();
        campaignForm.setCampaignName(campaignName);
        campaignForm.checkActiveCheckbox();
        campaignForm.clickSaveBtn();
    }

    @Test(groups = {"Acceptance"}, dataProvider = "dataDriven")
    public void testCreateContactView(ViewSalesForce viewSalesForce) {
        contactHome = navigationBar.goToContactsHome();
        contactView = contactHome.clickNewViewLnk()
                .setViewName(viewSalesForce.getViewName())
                .setUniqueViewName(viewSalesForce.getUniqueViewName())
                .checkFilterByOwnerAll()
                .checkFilterByOwnerMy()
                .setFilterByCampaign(campaignName)
                .selectRestrictVisibility(viewSalesForce.getRestrictVisibility());

        List<FilterView> additionalField = viewSalesForce.getAdditionalFields();
        int count = 1;
        for (FilterView addFilter : additionalField) {
            contactView = contactView.addAdditionalFiltersByField(count, addFilter.getFieldFilter(),
                    addFilter.getOperatorFilter(), addFilter.getValueFilter());
            count++;
        }
        List<FieldToDisplayView> fieldToDisplayViews = viewSalesForce.getFieldsDisplay();
        for (FieldToDisplayView fields : fieldToDisplayViews)
            contactView = contactView.addNewFieldToDisplay(fields.getFieldToDisplay());

        contactViewDetail = contactView.clickSaveBtn();
        LOGGER.info("Contact was created");
        assertFalse(contactViewDetail.validateNameView("AnyName"));
    }

    @AfterMethod(groups = {"Acceptance"})
    public void tearDown() {
        contactViewDetail.clickDeleteLnk(true);
        LOGGER.info("Contact View was deleted");
    }
}
