package com.salesforce.dev.framework;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesforce.dev.framework.Objects.Account;
import com.salesforce.dev.framework.Objects.Contact;
import com.salesforce.dev.framework.Objects.Lead;
import com.salesforce.dev.framework.Objects.Opportunity;

import java.io.File;
import java.io.IOException;

/**
 * Created by jimmy vargas on 6/22/2015.
 */
public class JSONMapper {

    public static Opportunity getOpportunity(String fileJson){
        Opportunity opportunity = new Opportunity();
        final String pathFileJson = "src/test/resources/" + fileJson;
        try {
            ObjectMapper mapper = new ObjectMapper();
            opportunity = mapper.readValue(new File(pathFileJson), Opportunity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return opportunity;
    }

    public static Lead getLead(String jsonFile){
        Lead lead = new Lead();
        try {
            ObjectMapper mapper = new ObjectMapper();
            lead = mapper.readValue(new File(jsonFile), Lead.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lead;
    }

    public static Account getAccountBase(){
        Account account = new Account();
        try {
            ObjectMapper mapper = new ObjectMapper();
            account = mapper.readValue(new File("src/test/resources/CreateAccountBase.json"), Account.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return account;
    }
    public static Contact getContact(){
        Contact contact = new Contact();
        try {
            ObjectMapper mapper = new ObjectMapper();
            contact = mapper.readValue(new File("src/test/resources/CreateContact.json"), Contact.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contact;
    }
    public static Contact getContactToUpdate(){
        Contact contact = new Contact();
        try {
            ObjectMapper mapper = new ObjectMapper();
            contact = mapper.readValue(new File("src/test/resources/EditContact.json"), Contact.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contact;
    }


}
