package solutions;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;

import java.time.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * - Она с кузнецом придёт
 * - А зачем нам кузнец? Нам кузнец не нужен
 * (с) Формула любви
 */
public class Blacksmith {

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
        $("#data").shouldHave(text("Иголка"),Duration.ofSeconds(5));
    }
}
