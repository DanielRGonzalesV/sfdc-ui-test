package com.salesforce.dev;

import com.salesforce.dev.pages.Base.NavigationBar;
import com.salesforce.dev.pages.Home.HomePage;
import com.salesforce.dev.pages.MainPage;
import com.salesforce.dev.pages.Product.ProductBuilder;
import com.salesforce.dev.pages.Product.ProductDetails;
import com.salesforce.dev.pages.Product.ProductForm;
import com.salesforce.dev.pages.Product.ProductsHome;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by Monica Pardo on 6/13/2015.
 */
public class CreateProduct {

    HomePage homePage;
    MainPage mainPage;
    NavigationBar navigationBar;
    private ProductsHome productsHome;
    private ProductForm productForm;
    private ProductDetails productDetails;

    @BeforeMethod(groups = {"Acceptance"})
    public void setUp() {
        homePage = new HomePage();
        mainPage = homePage.clickLoginBtn().loginAsPrimaryUser();
        navigationBar = mainPage.gotoNavBar();

    }

    @Test(groups = {"Acceptance"}, dataProvider = "getProductValues")
    public void testCreateProduct(String productName, String prodCode, String prodDesc) {
        productsHome = navigationBar.goToProductsHome();
        productForm = productsHome.clickNewBtn();
        productForm = new ProductBuilder(productName)
                .setProductName(productName)
                .setProductCode(prodCode)
                .setProductDesc(prodDesc)
                .setProductActive(true).build();
        productDetails = productForm.saveProduct();
        Assert.assertTrue(productDetails.VerifyProduct(productName), "product Was not Created");

    }

    @AfterMethod(groups = {"Acceptance"})
    public void tearDown() {
        productDetails.clickDeleteBtn(true);
    }

    @DataProvider
    public Object[][] getProductValues() {
        return new Object[][]{{"New product", "Code", "this is an new product"}};
    }
}
