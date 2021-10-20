package solutions;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;

import java.time.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Закидываем сети, то есть network
 */
public class Goldfish {

    static boolean responded;

    @BeforeAll
    static void configure() {
        Configuration.timeout = 2000;
        Configuration.proxyEnabled = true;
        open("http://localhost:8082/spinner.html");
        var proxy = WebDriverRunner.getSelenideProxy();
        proxy.addResponseFilter("catch response", (response, contents, messageInfo) -> {
            System.out.println(messageInfo.getUrl());
            if (messageInfo.getUrl().contains("data.json")) {
                System.out.println("Responded!");
                responded = true;
            }
        });
    }

    @BeforeEach
    void openPage() {
        open("http://localhost:8082/spinner_fast.html");
    }

    @RepeatedTest(10)
    void testOfTable() {
        responded = false;
        $("#search").click();
        while (!responded) {
            System.out.println("20ms");
            sleep(20);
        }
        $("#data").shouldHave(text("Иголка"), Duration.ofMillis(1));


    }
}
