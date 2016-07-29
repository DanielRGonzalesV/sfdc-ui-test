package com.salesforce.dev.framework.Objects;

import com.salesforce.dev.framework.JSONMapper;

/**
 * Created by jimmy vargas on 6/24/2015.
 */
public class Lead {
    String file;
    JSONMapper jsonMapper;

    /*Lead Information*/
    public String nameSalutation;
    public String firstName;
    public String lastName;
    public String company;
    public String title;
    public String leadSource;
    public String campaign;
    public String campaignLookup;
    public String industry;
    public String annualRevenue;
    public String phone;
    public String mobile;
    public String fax;
    public String email;
    public String website;
    public String leadStatus;
    public String rating;
    public String numEmployees;

    /*Address Information*/
    public String street;
    public String city;
    public String stateProvince;
    public String zipCode;
    public String country;

    /*additional information*/
    public String productInterest;
    public String SICCode;
    public String numberLocations;
    public String currentGenerators;
    public String primary;

    /*Others*/
    public String description;
    public String assignRule;

    public Lead(){}

    public String getLastName(){return this.lastName;}
    public String getCompany(){return this.company;}
    public String getLeadStatus(){return this.leadStatus;}
}
