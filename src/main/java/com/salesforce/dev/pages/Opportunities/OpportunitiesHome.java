package com.salesforce.dev.pages.Opportunities;

import com.salesforce.dev.pages.Base.*;
import com.salesforce.dev.framework.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by Jimmy Vargas on 6/10/2015.
 */
public class OpportunitiesHome extends HomeBase {
    @FindBy(xpath = "//h1[contains(.,'Opportunities:')]")
    @CacheLookup
    WebElement opportunitiesSection;

    public OpportunitiesHome(WebDriver driver){
        super.driver = driver;
        super.wait = DriverManager.getInstance().getWait();
        PageFactory.initElements(super.driver, this);
    }

     public OpportunityForm clickNewBtn(){
        super.wait.until(ExpectedConditions.visibilityOf(newBtn));
        newBtn.click();

        return new OpportunityForm(this.driver);
    }

    @Override
    protected Object clickNewViewLnk() {
        return null;
    }

    @Override
    protected Object clickEditViewLnk(String value) {
        return null;
    }

    public OpportunityDetail selectRecentItem(String opportunity){
        super.clickRecentItem(opportunity);
        return new OpportunityDetail(this.driver);
    }

    public OpportunityDetail openOpportunity(String opportunity){
        WebElement linkOpportunity = driver.findElement(By.linkText(opportunity));
        wait.until(ExpectedConditions.elementToBeClickable(linkOpportunity));

        linkOpportunity.click();
        return new OpportunityDetail(this.driver);
    }


    public boolean IsUserInOpportunitiesTab(){
        try{

            wait.until(ExpectedConditions.visibilityOf(opportunitiesSection));
            return true;
        }
        catch (WebDriverException e){
            return false;
        }
    }





}

