package solutions;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;

import java.time.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Сколько психотерапевтов нужно, чтобы поменять лампочку
 * Один, но лампочка должна захотеть меняться
 * <p>
 * (меняем спиннер, не меняем тест)
 */
public class PsychoLightbulb {

    @BeforeAll
    static void configure() {
        Configuration.timeout = 2000;
    }

    @BeforeEach
    void openPage() {
        open("http://localhost:8082/spinner_lightbulb.html");
    }

    @RepeatedTest(10)
    void testOfTable() {
        $("#search").click();
        $("#spinner").should(exist); // visible or not visible
        $("#spinner").should(hidden, Duration.ofSeconds(5));
        $("#data").shouldHave(text("Иголка"), Duration.ofMillis(1));
    }
}
