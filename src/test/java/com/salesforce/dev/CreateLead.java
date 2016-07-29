package com.salesforce.dev;

import com.salesforce.dev.framework.JSONMapper;
import com.salesforce.dev.framework.Objects.Campaign;
import com.salesforce.dev.framework.Objects.Lead;
import com.salesforce.dev.pages.Base.NavigationBar;
import com.salesforce.dev.pages.Campaigns.CampaignDetail;
import com.salesforce.dev.pages.Campaigns.CampaignsHome;
import com.salesforce.dev.pages.Home.HomePage;
import com.salesforce.dev.pages.Leads.LeadBuilder;
import com.salesforce.dev.pages.Leads.LeadDetail;
import com.salesforce.dev.pages.Leads.LeadForm;
import com.salesforce.dev.pages.Leads.LeadsHome;
import com.salesforce.dev.pages.MainPage;
import com.salesforce.dev.pages.Objects.CampaignGenie;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Jimmy Vargas on 6/15/2015.
 */
public class CreateLead {

    private NavigationBar navBar;

    private Lead lead;

    private CampaignsHome campaignsHome;

    private CampaignDetail campaignDetail;

    private String parentCampaign = "CampaignParent";

    private HomePage homePage;

    private MainPage mainPage;

    private NavigationBar navigationBar;

    @BeforeMethod(groups = {"Acceptance"})
    public void setUp() {
        homePage = new HomePage();
        navBar = mainPage.gotoNavBar();

        lead = JSONMapper.getLead("src\\test\\resources\\CreateLead.json");

        ///create campaign
        Campaign campaign = CampaignGenie.getCampaign();
        //create parent Campaign
        CampaignGenie.createParentCampaign(campaign.getParentCampaign());
    }

    @Test(groups = {"Acceptance"})
    public void testCreateLead() {
        LeadsHome leadsHome = navBar.gotToLeadsHome();
        leadsHome.clickNewBtn();

        LeadForm leadForm = new LeadBuilder(lead.lastName, lead.company, lead.leadStatus)
                .setCampaign(lead.campaignLookup)
                .setSalutation(lead.nameSalutation)
                .setFirstName(lead.firstName)
                .setTitle(lead.title)
                .setLeadSource(lead.leadSource)
                .setIndustry(lead.industry)
                .setAnnualRevenue(lead.annualRevenue)
                .setPhone(lead.phone)
                .setMobile(lead.mobile)
                .setFax(lead.fax)
                .setEmail(lead.email)
                .setWebsite(lead.website)
                .setLeadStatus(lead.leadStatus)
                .setRating(lead.rating)
                .setNumEmployees(lead.numEmployees)
                .setStreet(lead.street)
                .setCity(lead.city)
                .setStateProvince(lead.stateProvince)
                .setZipPostalCode(lead.zipCode)
                .setCountry(lead.country)
                .setProductInterest(lead.productInterest)
                .setSICcode(lead.SICCode)
                .setNumberLocations(lead.numberLocations)
                .setCurrentGenerators(lead.currentGenerators)
                .setPrimary(lead.primary)
                .setDescription(lead.description)
                .build();
        LeadDetail leadDetail = leadForm.clickSaveBtn();

        //Assertions
        Assert.assertTrue(leadDetail.getName().contains(lead.lastName), "The actual name doesn't contain the lastname" + lead.lastName);
        Assert.assertEquals(leadDetail.getCompany(), lead.company, "The company are not equal");
        Assert.assertEquals(leadDetail.getTitle(), lead.title, "The title is not correct");
        Assert.assertEquals(leadDetail.getLeadSource(), lead.leadSource, "The lead source is not correct");
        Assert.assertEquals(leadDetail.getIndustry(), lead.industry, "The industry is not correct");
        Assert.assertEquals(leadDetail.getPhone(), lead.phone, "The phone is not correct");
        Assert.assertEquals(leadDetail.getMobile(), lead.mobile, "The mobile is not correct");
        Assert.assertEquals(leadDetail.getFax(), lead.fax, "The fax is not correct");
        Assert.assertEquals(leadDetail.getEmail(), lead.email, "The email is not correct");
        Assert.assertEquals(leadDetail.getWebsite(), "http://" + lead.website, "The website is not correct");
        Assert.assertEquals(leadDetail.getLeadStatus(), lead.leadStatus, "The lead status is not correct");
        Assert.assertEquals(leadDetail.getRating(), lead.rating, "The rating is not correct");
        Assert.assertEquals(leadDetail.getNumEmployees(), lead.numEmployees, "The number of employees is not correct");
        Assert.assertEquals(leadDetail.getAddress(), lead.street + "\n" +
                lead.city + ", " +
                lead.stateProvince + " " +
                lead.zipCode + "\n" +
                lead.country, "The address is not correct");//needs to review

        Assert.assertEquals(leadDetail.getProductInterest(), lead.productInterest, "The product interest is not correct");
        Assert.assertEquals(leadDetail.getSICcode(), lead.SICCode, "The SICCode is not correct");
        Assert.assertEquals(leadDetail.getNumLocations(), lead.numberLocations, "The number of locations is not correct");
        Assert.assertEquals(leadDetail.getDescription(), lead.description, "The description is not correct");
        Assert.assertEquals(leadDetail.getCurrentGenerators(), lead.currentGenerators, "The current generators is not correct");
        Assert.assertEquals(leadDetail.getPrimary(), lead.primary, "The primary is not correct");
    }

    @AfterMethod(groups = {"Acceptance"})
    public void tearDown() {
        LeadsHome leadsHome = mainPage.gotoNavBar().gotToLeadsHome();
        LeadDetail leadDetail = leadsHome.openLead(lead.lastName + ", " + lead.firstName);
        leadDetail.deleteLead();
        navigationBar = mainPage.gotoNavBar();
        campaignsHome = navigationBar.goToCampaignsHome();
        campaignDetail = campaignsHome.selectRecentItem(parentCampaign);
        campaignDetail.clickDeleteBtn(true);
    }
}
