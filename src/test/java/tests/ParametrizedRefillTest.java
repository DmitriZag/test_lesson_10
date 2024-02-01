package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@DisplayName("Параметризированный тест пополнения счёта номера телефона на сайте Мегафон")
public class ParametrizedRefillTest extends TestBase {
    @BeforeEach
    void beforeEach() {
        open("https://megafon.ru/pay/topup");
}
    @CsvSource(value = {
            "9271460636 , 9 , От 10 до 15000 ₽",
            "9271460636 , 15001 , От 10 до 15000 ₽"
    })

    @ParameterizedTest(name = "Для некорректной суммы пополнения {1} появляется текст {2}")
    @Tag("SMOKE")
    void fillIncorrectSumTest(String number, String sum, String inscription) {
        $("[name=ONLINE_PAYMENT_PHONE_NUMBER]").click();
        $("[name=ONLINE_PAYMENT_PHONE_NUMBER]").append(number);
        $("[name=ONLINE_PAYMENT_SUM]").setValue(sum).pressEnter();
        $(".gtm-payment-with-card").shouldHave(text(inscription));
    }

    @CsvSource(value = {
            "9271460636, 10 , От 10 до 15000 ₽",
            "9271460636, 15000 , От 10 до 15000 ₽"
    })
    @ParameterizedTest(name = "Для корректной суммы пополнения {1} не появляется текст {2}")
    @Tag("SMOKE")
    void fillCorrectSumTest(String number, String sum, String inscription) {
        $("[name=ONLINE_PAYMENT_PHONE_NUMBER]").click();
        $("[name=ONLINE_PAYMENT_PHONE_NUMBER]").append(number);
        $("[name=ONLINE_PAYMENT_SUM]").setValue(sum).pressEnter();
        $(".gtm-payment-with-card").shouldNotHave(text(inscription));
    }

}