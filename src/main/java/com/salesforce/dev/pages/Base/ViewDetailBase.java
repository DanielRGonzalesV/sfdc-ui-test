package com.salesforce.dev.pages.Base;

import com.salesforce.dev.framework.CommonOperation;
import com.salesforce.dev.pages.AbstractBasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Administrator on 8/20/2015.
 */
public abstract class ViewDetailBase extends AbstractBasePage {

    private static final Logger LOGGER = Logger.getLogger(ViewDetailBase.class.getName());

    @FindBy(linkText = "Edit")
    protected WebElement editLnk;

    @FindBy(linkText = "Delete")
    protected WebElement deleteLnk;

    @FindBy(linkText = "Create New View")
    protected WebElement newViewBtn;

    @FindBy(name = "fcf")
    protected WebElement viewSelected;

    protected void clickEditLink() {
        try {
            WAIT.until(ExpectedConditions.visibilityOf(editLnk));
            editLnk.click();
           LOGGER.info("Edit link was clicked");
        }
        catch(WebDriverException e){
            LOGGER.fatal("The Edit link couldn't be found",e);
        }
    }

    protected void clickDeleteLink(boolean confirmDeletion) {
        CommonOperation.clickWebElement(deleteLnk);
        Alert alert;

        try{
            alert = driver.switchTo().alert();

            if (confirmDeletion){
                alert.accept();
            }
            alert.dismiss();
           LOGGER.info("Delete link was clicked");
        }
        catch(WebDriverException e){
            LOGGER.fatal("The Delete link couldn't be found", e);
        }
    }

    protected abstract Object clickEditLnk();

    /**
     * Returns next view
    *
     */
    protected abstract Object clickDeleteLnk(boolean confirmDeletion);



    public String getViewSelected(){
        WAIT.until(ExpectedConditions.visibilityOf(viewSelected));
        Select select = new Select(viewSelected);
        return select.getFirstSelectedOption().getText();
    }
    public boolean validateNameView(String nameView){
        WAIT.until(ExpectedConditions.visibilityOf(viewSelected));
        Select select = new Select(viewSelected);

        String nameV = select.getFirstSelectedOption().getText();
        return nameV.equals(nameView);

    }

    public boolean validateFieldDisplayed(String field){
        By fieldDisplayed = By.xpath("//div[@title='" + field+"']");
        WAIT.until(ExpectedConditions.visibilityOfElementLocated(fieldDisplayed));
        return driver.findElement(fieldDisplayed).isDisplayed();

    }
}
