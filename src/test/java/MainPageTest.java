import io.qameta.allure.junit4.DisplayName;
import org.junit.ClassRule;
import org.junit.Test;
import praktikum.DriverRule;
import praktikum.pages.MainPage;

/**
 * Проверь, что работают переходы к разделам:
 * «Булки»,
 * «Соусы»,
 * «Начинки».
 */

@DisplayName("Навигация по табам конструктора бургеров")
public class MainPageTest {
    @ClassRule
    public static DriverRule driverRule = new DriverRule();

    @Test
    @DisplayName("Клик по табам: 'Булки'-'Соусы'-'Начинки'")
    public void tabClicksBunsSaucesIngredientsTest() {
        new MainPage(driverRule.getDriver())
                .openPage()
                .waitPage()
                .clickSaucesTab()
                .currentTabSauces()
                .scrollToSauces()
                .clickIngredientsTab()
                .currentTabIngredients()
                .scrollToIngredients();
    }

    @Test
    @DisplayName("Клик по табам: 'Булки'-'Соусы'-'Начинки'")
    public void tabClicksBunsSaucesBunsTest() {
        new MainPage(driverRule.getDriver())
                .openPage()
                .waitPage()
                .clickSaucesTab()  // сначала последовательно слева направо
                .currentTabSauces()
                .clickBunsTab()
                .currentTabBuns()
                .scrollToBuns();
    }

    @Test
    @DisplayName("Клик по табам: 'Булки'-'Начинки'-'Булки'")
    public void tabClicksBunsIngredientsBunsTest() {
        new MainPage(driverRule.getDriver())
                .openPage()
                .waitPage()
                .clickIngredientsTab()
                .currentTabIngredients()
                .scrollToIngredients()
                .clickBunsTab()
                .currentTabBuns()
                .scrollToBuns();
    }

    @Test
    @DisplayName("Клик по табам: 'Булки'-'Начинки'-'Соусы'")
    public void tabClicksBunsIngredientsSaucesTest() {
        new MainPage(driverRule.getDriver())
                .openPage()
                .waitPage()
                .clickIngredientsTab()
                .currentTabIngredients()
                .scrollToIngredients()
                .clickSaucesTab()  // потом слева направо
                .currentTabSauces()
                .scrollToSauces()
                .clickBunsTab()
                .currentTabBuns()
                .scrollToBuns();
    }
}