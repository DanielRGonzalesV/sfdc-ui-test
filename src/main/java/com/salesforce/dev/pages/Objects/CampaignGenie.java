package com.salesforce.dev.pages.Objects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.salesforce.dev.framework.APIConnector;
import com.salesforce.dev.framework.DataDrivenManager;
import com.salesforce.dev.framework.Objects.Campaign;
import com.salesforce.dev.framework.Objects.ViewSalesForce;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.ConnectionException;
import org.apache.log4j.Logger;

/**
 * Created by Veronica Prado on 8/25/2015.
 * Class to get data related to Campaign
 */
public class CampaignGenie {
    private static final Logger LOGGER = Logger.getLogger(CampaignGenie.class.getName());

    public static ViewSalesForce getCampaignView(String jsonFile) {
        PartnerConnection connection = APIConnector.getInstance().getConnection();
        DataDrivenManager dataDrivenManager = new DataDrivenManager();
        Iterator<ViewSalesForce[]> iteratorViewData = dataDrivenManager.getDataView(jsonFile);
        List<ViewSalesForce[]> listData = new ArrayList<ViewSalesForce[]>();
        while (iteratorViewData.hasNext()) {
            listData.add(iteratorViewData.next());
        }
        ViewSalesForce viewSalesForce = listData.get(0)[0];
        SObject account = new SObject();
        return viewSalesForce;
    }

    public static Campaign getCampaign() {
        DataDrivenManager dataDrivenManager = new DataDrivenManager();
        Iterator<Campaign[]> iteratorCampaignData = dataDrivenManager.getCampaign("CreateCampaign.json");
        List<Campaign[]> listData = new ArrayList<Campaign[]>();
        while (iteratorCampaignData.hasNext()) {
            listData.add(iteratorCampaignData.next());
        }
        Campaign campaign = listData.get(0)[0];
        System.out.println(campaign);
        return campaign;
    }

    public static void createCampaign(Campaign campaign) {
        PartnerConnection connection = APIConnector.getInstance().getConnection();
        //get parentCampaignId
        ConnectorSalesForceDB query = new ConnectorSalesForceDB();
        String parentId = null;
        SObject[] records = query.executeQuery("SELECT id from Campaign where name='" + campaign.getParentCampaign() + "'");
        if (records != null) {
            parentId = records[0].getField("Id").toString();
        }

        SObject objectSales = new SObject();
        objectSales.setType("Campaign");
        objectSales.setField("Name", campaign.getCampaignName());
        objectSales.setField("IsActive", true);
        objectSales.setField("Type", campaign.getCampaignType());
        objectSales.setField("Status", campaign.getCampaignStatus());
        objectSales.setField("StartDate", campaign.getStartDate());
        objectSales.setField("EndDate", campaign.getEndDate());
        objectSales.setField("ExpectedRevenue", campaign.getExpectedRevenue());
        objectSales.setField("BudgetedCost", campaign.getBudgetedCost());
        objectSales.setField("ActualCost", campaign.getActualCost());
        objectSales.setField("ExpectedResponse", campaign.getExpectedResponse());
        objectSales.setField("NumberSent", campaign.getNumSent());
        objectSales.setField("ParentId", parentId);
        try {
            connection.create(new SObject[]{objectSales});
        } catch (ConnectionException e) {
            LOGGER.error("Error on Create campaign by Api :", e);
        }
    }

    public static void createParentCampaign(String nameCampaign) {
        PartnerConnection connection = APIConnector.getInstance().getConnection();
        SObject objectSales = new SObject();
        objectSales.setType("Campaign");
        objectSales.setField("Name", nameCampaign);
        objectSales.setField("IsActive", true);
        try {
            connection.create(new SObject[]{objectSales});
        } catch (ConnectionException e) {
            LOGGER.error("Error on Create parent campaign by Api :", e);
        }
    }

}
