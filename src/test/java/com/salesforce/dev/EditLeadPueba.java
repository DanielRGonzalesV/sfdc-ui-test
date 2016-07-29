package com.salesforce.dev;

import com.salesforce.dev.framework.JSONMapper;
import com.salesforce.dev.framework.Objects.Lead;
import com.salesforce.dev.pages.Objects.LeadGenie;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by jimmy vargas on 6/21/2015.
 */
public class EditLeadPueba {
    private static final Logger LOGGER = Logger.getLogger (EditLeadPueba.class.getName ());

    private Lead lead;

    @BeforeMethod(groups = {"Acceptance"})
    public void setup () {
        //Creating a lead
        lead = JSONMapper.getLead("src/test/resources/CreateLeadBase.json");
        LeadGenie.createLead (lead);
    }

    @Test(groups = {"Acceptance"})
    public void testEditLead () {
        System.out.println ("pasa");
    }
}
