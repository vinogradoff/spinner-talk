package solutions;

import com.codeborne.selenide.*;
import com.codeborne.selenide.ex.*;
import org.junit.jupiter.api.*;

import java.time.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Слон в посудной лавке
 * <p>
 * (ловим исключения, добавляем if и тп)
 */
public class Elephant {

    @BeforeAll
    static void configure() {
        Configuration.timeout = 2000;
    }

    @BeforeEach
    void openPage() {
        open("http://localhost:8082/spinner_fast.html");
    }

    @RepeatedTest(10)
    void testOfTable() {
        $("#search").click();
        try {
            $("#spinner").should(visible);
        } catch (UIAssertionError exception) {
            System.out.println("Почему-то он не появился. Разобраться потом");
        }
        $("#spinner").shouldBe(hidden, Duration.ofSeconds(8));
        $("#data").shouldHave(text("Иголка"));
    }
}
