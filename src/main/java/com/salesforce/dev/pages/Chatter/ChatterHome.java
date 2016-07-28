package com.salesforce.dev.pages.Chatter;

import com.salesforce.dev.framework.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Monica Pardo on 6/13/2015.
 *  *
 */
public class ChatterHome {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(className = "publisherattachtext ")
    WebElement postLink;

    //@FindBy(id = "publishereditablearea")
    @FindBy(css = ".chatterPublisherRTE.cke_editable.cke_editable_themed.cke_contents_ltr.cke_show_borders.placeholder")
    WebElement postDescField;

    @FindBy(id = "publishersharebutton")
    WebElement shareBtn;


    @FindBy(xpath = "//textarea[@title='Write a comment...']")
    WebElement commentField;

    @FindBy(xpath = "//input[@title='Comment']")
    WebElement commentBtn;

    @FindBy(xpath ="//a[@title='More Actions']")
    WebElement postMenu;

    @FindBy(xpath = "//a[@title='Delete this post']")
    WebElement deletePostOption;

    @FindBy(xpath = "//a[@title='Edit this post']")
    WebElement editPostOption;

    @FindBy(className = "publisherattachtext ")
    WebElement postSection;

    @FindBy(id = "quickActionFeedBodyInput")
    WebElement editPostTextArea;

    @FindBy(name = "quickActionSubmitButton")
    WebElement saveEditBtn;

    public ChatterHome(WebDriver driver){
        this.driver=driver;
        this.wait= DriverManager.getInstance().getWait();
        PageFactory.initElements(driver, this);
    }
    public ChatterHome clickPost(){
        wait.until(ExpectedConditions.visibilityOf(postLink));
        postLink.click();
        return this;
    }
    
    @FindBy(css = ".cke_wysiwyg_frame.cke_reset")
    private WebElement richTextEditorFrame;
    
    public ChatterHome setPost(String PostDesc){
        driver.switchTo().frame(richTextEditorFrame);
        wait.until(ExpectedConditions.visibilityOf(postDescField));
        postDescField.clear();
        postDescField.sendKeys(PostDesc);
        driver.switchTo().defaultContent();
        return  this;
    }

    public ChatterHome editPost(String updatadePost){
        //postMenu.click();
        //feeditembodyandfooter
        driver.findElement(By.xpath("//div[@class='feeditembodyandfooter']/descendant::span[contains(.,'" + updatadePost +"')]/following::a[contains(.,'More Action')]")).click();
        editPostOption.click();
        editPostTextArea.sendKeys(updatadePost);
        saveEditBtn.click();
        return  this;
    }

    public ChatterHome clickShareBtn(){
        shareBtn.click();
        return this;
    }
    public ChatterHome clickCommentForPost(String PostValue){

        driver.findElement(By.xpath("//div[@id='feedwrapper']/descendant::span[contains(.,'" + PostValue +"')]/following::a[contains(.,'Comment')]")).click();
        return this;
    }
    public ChatterHome setComment(String comment){
        wait.until(ExpectedConditions.visibilityOf(commentField));
        commentField.sendKeys(comment);
        return this;

    }
    public ChatterHome clickCommentBtn(){
        commentBtn.click();
        return this;
    }

    public ChatterHome DeletePost(){
        postMenu.click();
        deletePostOption.click();
        Alert alert2= driver.switchTo().alert();
        alert2.accept();
        return this;
    }
    public boolean VerifyPostCreated(String post){
        try{
            //feeditemtext cxfeeditemtext
            WebElement postValuePage= driver.findElement(By.xpath("//div[@id='feedwrapper']/descendant::span[contains(.,'" + post + "')]"));
                    wait.until(ExpectedConditions.visibilityOf(postValuePage));
            return true;
        }
        catch (WebDriverException e){
            return false;
        }

    }
    public boolean VerifyCommentCreated(String comment){
        try{

            WebElement commentValuePage= driver.findElement(By.xpath("//div[@class='feeditemcommentbody']/descendant::span[contains(.,'" + comment + "')]"));
            wait.until(ExpectedConditions.visibilityOf(commentValuePage));
            return true;
        }
        catch (WebDriverException e){
            return false;
        }

    }
    public boolean IsUserInChatterTab(){
        try{

            wait.until(ExpectedConditions.visibilityOf(postSection));
            return true;
        }
        catch (WebDriverException e){
            return false;
        }
    }
}
