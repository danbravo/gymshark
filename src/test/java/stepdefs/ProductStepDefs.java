package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pageobjects.BagPage;
import pageobjects.ProductDisplayPage;
import stepdefs.hooks.Hooks;

import static pageobjects.ProductDisplayPage.openProductDisplayPage;

public class ProductStepDefs {

  private final WebDriver driver;
  private Long productId;

  public ProductStepDefs(){
    this.driver = Hooks.getDriver();
  }

  @Given("the user is on a product page")
  public void theUserIsOnAProductPage() {
    openProductDisplayPage(driver);
  }

  @Given("there are products in the bag")
  public void theAreProductsInTheBag() {
    productId = 39654522814667L;
    openProductDisplayPage(driver)
            .selectSmallSize()
            .selectAddToBag();
  }

  @When("adding the product to the Bag")
  public void addingTheProductToTheBag() {
    productId = 39654522814667L;
    new ProductDisplayPage()
            .selectSmallSize()
            .selectAddToBag();
  }

  @When("user removes a product")
  public void userRemovesAProduct() {
    new BagPage().removeProductFromTheBag(productId);
  }

  @When("User adds quantity")
  public void userAddsQuantity() {
    new BagPage().addProductQuantityForProduct();
  }

  @When("User decreases quantity")
  public void userDecreasesQuantity() {
    new BagPage().decreaseProductQuantityForProduct();
  }

  @Then("the product should appear in the Bag")
  public void theProductShouldAppearInTheBag() {
    new BagPage().productIsAddedToTheBag(productId);
  }

  @Then("the product is removed from the bag")
  public void productRemovedFromTheBag() {
    new BagPage().productBagIsEmpty();
  }

  @Then("product quantity is increased")
  public void productQuantityIsIncreased() {
    new BagPage().productQuantityIsIncreased();
  }

  @Then("product quantity is decreased")
  public void productQuantityIsDecreased() {
    new BagPage().productQuantityIsDecreased();
  }
}
