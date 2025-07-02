import io.qameta.allure.junit4.DisplayName;
import org.junit.ClassRule;
import org.junit.Test;
import praktikum.DriverRule;
import praktikum.pages.MainPage;

@DisplayName("Навигация по вкладкам конструктора бургеров")
public class MainPageTest {
    @ClassRule
    public static DriverRule driverRule = new DriverRule();

    @Test
    @DisplayName("Проверка нажатия вкладок 'Булки', 'Соусы', 'Начинки'")
    public void checkTabs() {
        new MainPage(driverRule.getDriver())
                .openPage()
                .waitMainPage()
                .clickSaucesTab()
                .currentTabSauces()
                .scrollToSauces()
                .clickIngredientsTab()
                .currentTabIngredients()
                .scrollToIngredients()
                .clickBunsTab()
                .currentTabBuns()
                .scrollToBuns();
    }
}