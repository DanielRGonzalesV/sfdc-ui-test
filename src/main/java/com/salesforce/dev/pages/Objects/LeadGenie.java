package com.salesforce.dev.pages.Objects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.salesforce.dev.framework.APIConnector;
import com.salesforce.dev.framework.DataDrivenManager;
import com.salesforce.dev.framework.Objects.Lead;
import com.salesforce.dev.framework.Objects.ViewSalesForce;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.ConnectionException;
import org.apache.log4j.Logger;

/**
 * Created by Ariel Mattos on 07/09/2015.
 */
public class LeadGenie {

    private static final Logger LOGGER = Logger.getLogger (CampaignGenie.class.getName ());

    public static ViewSalesForce getLeadsView (String jsonFile) {
        PartnerConnection connection = APIConnector.getInstance ().getConnection ();
        DataDrivenManager dataDrivenManager = new DataDrivenManager ();
        Iterator<ViewSalesForce[]> iteratorViewData = dataDrivenManager.getDataView (jsonFile);
        List<ViewSalesForce[]> listData = new ArrayList<ViewSalesForce[]> ();
        while (iteratorViewData.hasNext ()) {
            listData.add (iteratorViewData.next ());
        }
        ViewSalesForce viewSalesForce = listData.get (0)[0];
        SObject account = new SObject ();
        return viewSalesForce;
    }

    public static Lead getDataLead () {
        DataDrivenManager dataDrivenManager = new DataDrivenManager ();
        Iterator<Lead[]> iteratorLeadData = dataDrivenManager.getLead ("CreateMandatoryLead.json");
        List<Lead[]> listData = new ArrayList<> ();
        while (iteratorLeadData.hasNext ()) {
            listData.add (iteratorLeadData.next ());
        }
        Lead lead = listData.get (0)[0];
        System.out.println (lead);
        return lead;
    }

    public static void createLead (Lead lead) {
        PartnerConnection connection = APIConnector.getInstance ().getConnection ();

        SObject objectSales = new SObject ();
 //       objectSales.setType ("Lead");
        objectSales.setField ("lastName", lead.getLastName ());
        objectSales.setField ("company", lead.getCompany ());
        objectSales.setField ("leadStatus", lead.getLeadStatus ());
        try {
            connection.create (new SObject[]{objectSales});
        } catch (ConnectionException e) {
            LOGGER.error ("Error on Create lead by Api :", e);
        }
    }
//
//    public Lead createLead2 () {
//        PartnerConnection connection = APIConnector.getInstance ().getConnection ();
//        Lead lead = new Lead ();
//        try {
//            // Create two leads to convert
//
//            lead.setLastName ("Daniel");
//            lead.setCompany ("NASA");
//            lead.setLeadStatus ("Open - Not Contacted");
//        }catch (ConnectionException e) {
//            LOGGER.error ("Error on Create lead by Api :", e);
//        }
//        return connection.create (lead);
//    }
}