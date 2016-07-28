package com.salesforce.dev;

import com.salesforce.dev.framework.JSONMapper;
import com.salesforce.dev.framework.Objects.Opportunity;
import com.salesforce.dev.pages.Base.NavigationBar;
import com.salesforce.dev.pages.Home.HomePage;
import com.salesforce.dev.pages.MainPage;
import com.salesforce.dev.pages.Opportunities.OpportunitiesHome;
import com.salesforce.dev.pages.Opportunities.OpportunityBuilder;
import com.salesforce.dev.pages.Opportunities.OpportunityDetail;
import com.salesforce.dev.pages.Opportunities.OpportunityForm;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Jimmy Vargas on 6/10/2015.
 */
public class CreateOpportunity {

    private HomePage homePage;

    private MainPage mainPage;

    private NavigationBar navBar;

    private Opportunity oppEnum;

    @BeforeMethod(groups = {"Acceptance"})
    public void setUp() {
        homePage = new HomePage();
        mainPage = homePage.clickLoginBtn().loginAsPrimaryUser();
        navBar = mainPage.gotoNavBar();
        oppEnum = JSONMapper.getOpportunity("CreateOpportunity.json");
    }

    @Test(groups = {"Acceptance"})
    public void testCreateOpportunity() {
        OpportunitiesHome opTab = navBar.goToOpportunitiesHome();
        opTab.clickNewBtn();

        OpportunityForm opForm = new OpportunityBuilder(oppEnum.opportunityName, oppEnum.closeDate, oppEnum.stage)
                .setPrivate(oppEnum.privateChk)
                .setType(oppEnum.type)
                .setLeadSource(oppEnum.leadSource)
                .setAmount(oppEnum.amount)
                .setNextStep(oppEnum.nextStep)
                .setProbability(oppEnum.probability)
                .setOrderNumber(oppEnum.orderNumber)
                .setTrackingNumber(oppEnum.trackingNumber)
                .setMainCompetitors(oppEnum.mainCompetitors)
                .setDeliveryInstallationStatus(oppEnum.deliveryStatus)
                .setOpDescription(oppEnum.description)

                .build();
        OpportunityDetail opportunityDetails = opForm.clickSaveBtn();

        //Assertions
        Assert.assertEquals(opportunityDetails.getOpName(), oppEnum.opportunityName, "The name is not equal");
        Assert.assertEquals(opportunityDetails.getCloseDate(), oppEnum.closeDate, "The close date is not equal");
        Assert.assertEquals(opportunityDetails.getStage(), oppEnum.stage, "The stage is not equal");
        Assert.assertEquals(opportunityDetails.isPrivate(), oppEnum.privateChk, "The private check is not equal");
        Assert.assertEquals(opportunityDetails.getType(), oppEnum.type, "The type is not equal");
        Assert.assertEquals(opportunityDetails.getLeadSource(), oppEnum.leadSource, "The lead source is not equal");
        Assert.assertEquals(opportunityDetails.getNextStep(), oppEnum.nextStep, "The next step is not equal");
        Assert.assertTrue(opportunityDetails.getAmount().contains(oppEnum.amount), "The amount is not equal");
        Assert.assertEquals(opportunityDetails.getProbability(), oppEnum.probability + "%", "The probability is not equal");
        Assert.assertEquals(opportunityDetails.getOrderNumber(), oppEnum.orderNumber, "The order number is not equal");
        Assert.assertEquals(opportunityDetails.getTrackingNumber(), oppEnum.trackingNumber, "The tracking number is not equal");
        Assert.assertEquals(opportunityDetails.getLeadSource(), oppEnum.leadSource, "The lead source is not equal");
        Assert.assertEquals(opportunityDetails.getMainCompetitors(), oppEnum.mainCompetitors, "The main competitors is not equal");
        Assert.assertEquals(opportunityDetails.getDeliveryInstallationStatus(), oppEnum.deliveryStatus, "The delivery status is not equal");
        Assert.assertEquals(opportunityDetails.getOpDescription(), oppEnum.description, "The description is not equal");
    }

    @AfterMethod(groups = {"Acceptance"})
    public void tearDown() {
        OpportunitiesHome opHome = navBar.goToOpportunitiesHome();
        OpportunityDetail opDetail = opHome.openOpportunity(oppEnum.opportunityName);
        opDetail.deleteOpportunity();
    }
}
