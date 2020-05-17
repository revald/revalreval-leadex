package example.steps.pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Condition.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.leadium.core.AbstractStep
import org.leadium.core.get
import org.leadium.data.ByText
import org.openqa.selenium.By

class Wikipedia : AbstractStep() {

    fun checkResult(expectedResult: String) {
        assertTrue(expectedResult[ByText()].has(hidden))
    }

    fun checkLogoTitle(logoTitle: String) {
        "Logo"[By.xpath("//a[@class='mw-wiki-logo']")]
                .should(have(attribute("title", logoTitle)))
    }

    fun checkLogoCondition(condition: Condition) {
        "Logo"[By.xpath("//a[@class='mw-wiki-logo']")]
                .should(condition)
    }

    fun checkSearchInput() {
        "Search Input"[By.xpath("//input[@type='search']")]
                .should(be(enabled))
                .should(be(empty))
    }
}