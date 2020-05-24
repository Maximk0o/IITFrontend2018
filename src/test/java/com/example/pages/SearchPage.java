package com.example.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.example.constants.Constants.PAGE_LOAD_TIME;

public class SearchPage extends AbstractPage<SearchPage> {
    public SearchPage() {
        super();
        this.url = "https://data.mos.ru/Search";
    }

    public SearchPage navigate() {
        return super.navigate(this.getClass());
    }

    @Override
    public SearchPage waitPageLoaded() {
        $(".result-list").waitUntil(visible, PAGE_LOAD_TIME);
        return this;
    }

    public SelenideElement getSearchField() {
        return $("input#text");
    }

    public SelenideElement getOverallNumberElement() {
        return getSearchTypeElement("Всего найдено:").$("strong");
    }

    public SelenideElement getSearchTypeElement(String text) {
        return $(byText(text));
    }

    public ElementsCollection getHighlightedElements() {
        return $$("em");
    }
}
