package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.SeleniumCommands.getCommands;
import static utils.StringUtils.extractVariantIDFromString;

public class BagPage {

  private static final By BAG_PAGE = By.cssSelector("[data-locator-id='miniBag-component']");
  private static final By BAG_ITEMS = By.cssSelector("[data-locator-id^='miniBag-miniBagItem']");
  private static String REMOVE_PRODUCT_LOCATOR = "[data-locator-id='miniBag-remove-%d-remove']";
  private static final By EMPTY_BAG_TEXT = By.cssSelector("[data-locator-id='miniBag-miniBagEmpty-read']");
  private static final By PRODUCT_QUANTITY = By.cssSelector(".qty-selector_text__4uAGo");
  public static final String GS_LOCATOR_ATTRIBUTE = "data-locator-id";

  public BagPage() {
    getCommands().waitForAndGetVisibleElementLocated(BAG_PAGE);
  }

  public BagPage productIsAddedToTheBag(Long productId) {
    List<Long> variantIds = getVariantIdsInBag();
    assertThat(variantIds).as("Expected product is in Bag").contains(productId);
    return this;
  }

  public BagPage removeProductFromTheBag(Long productId) {
    getCommands().waitForAndClickOnElementLocated(By.cssSelector(String.format(REMOVE_PRODUCT_LOCATOR, productId)));
    return this;
  }

  public BagPage productBagIsEmpty() {
    getCommands().waitForAndGetVisibleElementLocated(EMPTY_BAG_TEXT);
    return this;
  }

  public BagPage addProductQuantityForProduct() {
    getCommands().selectByIndex(
            By.cssSelector(".qty-selector_dropdown__R7OIE select"),
            2
    );
    return this;
  }

  public BagPage decreaseProductQuantityForProduct() {
    getCommands().selectByIndex(
            By.cssSelector(".qty-selector_dropdown__R7OIE select"),
            0
    );
    return this;
  }

  public BagPage productQuantityIsIncreased() {
    getCommands().waitForAndContainsText(PRODUCT_QUANTITY, "Qty: 3");
    return this;
  }

  public BagPage productQuantityIsDecreased() {
    getCommands().waitForAndContainsText(PRODUCT_QUANTITY, "Qty: 1");
    return this;
  }

  public List<Long> getVariantIdsInBag() {
    return getBagItems().stream()
      .map(this::getVariantId)
      .collect(Collectors.toList());
  }

  private List<WebElement> getBagItems() {
    return getCommands().waitForAndGetAllVisibleElementsLocated(BAG_ITEMS);
  }

  private long getVariantId(WebElement bagItem) {
    return extractVariantIDFromString(getCommands().getAttributeFromElement(bagItem, GS_LOCATOR_ATTRIBUTE));
  }
}
