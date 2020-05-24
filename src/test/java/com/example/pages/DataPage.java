package com.example.pages;

import com.example.components.Categories;
import com.example.components.CategoryItemDataPage;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.example.constants.Constants.PAGE_LOAD_TIME;

public class DataPage extends AbstractPage<DataPage> {
    private Categories<CategoryItemDataPage> categories = new Categories<>($("#categoriesList"), CategoryItemDataPage::new);

    public DataPage() {
        super();
        this.url = "https://data.mos.ru/opendata";
    }

    public DataPage navigate() {
        return super.navigate(this.getClass());
    }

    @Override
    public DataPage waitPageLoaded() {
        $(".loader-block").waitWhile(visible, PAGE_LOAD_TIME);
        $(".count.total").waitUntil(matchText("\\d+"), PAGE_LOAD_TIME);
        return this;
    }

    public CategoryItemDataPage getSelectedItem() {
        return categories.getSelected();
    }

    public DataPage select(String categoryName) {
        categories.getCategoryByName(categoryName).select();
        return this;
    }
}
