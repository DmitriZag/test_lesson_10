package tests;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Параметризированный тест поиска видеокарт на сайте Онлайнтрейд")
public class ParametrizedSearchTest extends TestBase {
    @BeforeEach
    void beforeEach() {
        open("https://www.onlinetrade.ru");
    }
    @ValueSource(strings = {
            "видеокарта asus",
            "видеокарта palit"
    })
    @ParameterizedTest(name = "Результат поискового запроса {0} должен быть непустым")
    @Tag("SMOKE")
    void vgaSearchResult(String searchQuery) {
        $("[name=query]").setValue(searchQuery).pressEnter();
        $$(".indexGoods__item__flexCover").shouldBe(CollectionCondition.sizeGreaterThan(0));
    }

    @CsvFileSource(resources = "/data/search_list.csv")
    @ParameterizedTest(name = "Для поискового запроса {0} в первой карточке должен быть товар {1}")
    @Tag("SMOKE")
    void vgaSearchResultFirstGoods(String searchQuery, String searchResult) {
        $("[name=query]").setValue(searchQuery).pressEnter();
        $(".indexGoods__item__name").shouldHave(text(searchResult));
    }
}
