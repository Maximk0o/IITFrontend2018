package com.example.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.example.constants.Constants.PAGE_LOAD_TIME;


public class MainPage extends AbstractPage<MainPage> {

    public MainPage() {
        super();
        this.url = "https://data.mos.ru/";
    }

    public MainPage navigate() {
        return super.navigate(this.getClass());
    }

    @Override
    public MainPage waitPageLoaded() {
        $(By.className("banner-items")).waitUntil(visible, PAGE_LOAD_TIME);
        return this;
    }

    public SelenideElement getSearchField() {
        return $("input#text");
    }

    public SelenideElement getSearchButton() {
        return $("input[type=submit]");
    }
}
