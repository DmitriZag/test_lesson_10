package tests;

import org.junit.jupiter.api.*;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@DisplayName("Параметризованный тест на пополнение номера телефона через СБП")

public class ParametrizedRefillTest extends TestBase {

   @CsvSource(value = {
           "9092970542, 100, К оплате 100 ₽",
           "9092970542, 99, Минимальная сумма 100 ₽"

   })

    @ParameterizedTest(name = "Для корректной суммы {1} должно появляться окно с надписью {2}")
    @Tag("SMOKE")
   void fillNumberAndSumTest(String number, String sum, String inscription) {
       $(".dcQKu McK4M").setValue(number);
       $(".dcQKu").setValue(sum);
       $(".eDlPr jJtQV Gm5Jx qLn2C").click();

      $(".LtCbEs").shouldHave(text(inscription));
    }

}
