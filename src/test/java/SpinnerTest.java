import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;

import java.time.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SpinnerTest {

    @BeforeAll
    static void configure() {
        Configuration.timeout = 2000;
    }

    @BeforeEach
    void openPage() {
        open("http://localhost:8082/spinner.html");
    }

    @RepeatedTest(10)
    void testOfTable() {
        $("#search").click();
        $("#spinner").shouldBe(visible);
        $("#spinner").shouldBe(hidden,Duration.ofSeconds(5000));
        $("#data").shouldHave(text("Иголка"));

    }
}
