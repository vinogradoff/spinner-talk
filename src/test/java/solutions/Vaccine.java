package solutions;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;

import java.time.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * JavaScript Injection
 */
public class Vaccine {

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
        var js1 = "respondedJS=false; $('#container').bind('DOMSubtreeModified',function(){\n" +
                " if ($('#spinner').length==0) respondedJS=true; \n" +
                // " if (!$('#spinner').is(':visible')) respondedJS=true; \n" +
                "});";
        executeJavaScript(js1);
        $("#search").click();
        var responded = false;
        while (!responded) {
            responded = executeJavaScript("return respondedJS;");
        }
        $("#data").shouldHave(text("Иголка"), Duration.ofMillis(1));
    }
}
