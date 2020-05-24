package com.example.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.function.Supplier;

public class Categories<T extends ElementsContainer> extends ElementsContainer {
    private Supplier<T> categoryItemConstructor;

    public Categories(SelenideElement el, Supplier<T> categoryItemConstructor) {
        this.categoryItemConstructor = categoryItemConstructor;
        setSelf(el);
    }

    public T getCategoryById(int id) {
        T categoryItem = categoryItemConstructor.get();
        categoryItem.setSelf(getSelf().find(String.format("[data-id=\"%d\"]", id)));
        return categoryItem;
    }

    public T getCategoryByName(String name) {
        T categoryItem = categoryItemConstructor.get();
        categoryItem.setSelf(getSelf().find(By.xpath(String.format(".//*[text()='%s']/../..", name))));
        return categoryItem;
    }

    public T getSelected() {
        T categoryItem = categoryItemConstructor.get();
        categoryItem.setSelf(getSelf().find(".selected"));
        return categoryItem;
    }

    public ElementsCollection getCategoriesCollection() {
        return getSelf().$$(".item");
    }
}
