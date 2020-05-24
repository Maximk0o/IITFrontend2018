package com.example.tests;

import com.codeborne.selenide.testng.annotations.Report;
import com.example.BaseTest;
import com.example.pages.DataPage;
import com.example.steps.MainPageSteps;
import com.example.steps.SearchPageSteps;
import org.hamcrest.Matcher;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.startsWith;

@Test
@Report
public class DataMosTest extends BaseTest {
    // iit-235
    @Test(groups = "regression")
    public void dataPage()  {
        DataPage page = new DataPage().navigate().shouldBeOpened();
        page.select("Государственные услуги");
        page.getSelectedItem().getTextElement().shouldHave(text("Государственные услуги"));
        page.getSelectedItem().getCountElement().shouldHave(text("8"));
    }

    @DataProvider(name = "search")
    public Object[][] createData1() {
        return new Object[][] {
                { "Имена", startsWith("им")},
                { "Дети", anyOf(startsWith("дет"), startsWith("реб"))},
        };
    }

    // iit-234
    @Test(groups = "regression", dataProvider = "search")
    public void search(String searchText, Matcher<String> highlighted)  {
        MainPageSteps mainPageSteps = new MainPageSteps();
        mainPageSteps.openMainPage()
                .checkSearchInputPlaceholder("Поиск по \\d+ наборам данных и материалам портала")
                .searchFor(searchText);

        SearchPageSteps searchSteps = new SearchPageSteps();
        searchSteps.getPage().shouldBeOpened();
        searchSteps.checkSearchInputValue(searchText)
                .checkOverallNumberExist()
                .checkSearchTypeExists("По объектам в наборах данных")
                .checkSearchTypeExists("Материалы портала")
                .checkSearchTypeExists("По наборам данных")
                .checkSearchTypeSelected("По наборам данных")
                .checkElementsHighlighted(highlighted);
    }
}
