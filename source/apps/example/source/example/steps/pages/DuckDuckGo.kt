package example.steps.pages

import com.codeborne.selenide.Condition.be
import com.codeborne.selenide.Condition.visible
import org.leadium.core.AbstractStep
import org.leadium.core.get
import org.leadium.data.ByText
import org.openqa.selenium.By
import org.openqa.selenium.Keys

class DuckDuckGo : AbstractStep() {

    fun setSearchBox(searchRequest: String) {
        "Search Box"[By.xpath("//input[@id='search_form_input_homepage']")].set(searchRequest)
    }

    fun enter() {
        "Search Box"[By.xpath("//input[@id='search_form_input_homepage']")].sendKeys(Keys.ENTER)
    }

    fun checkResult(expectedResult: String) {
        expectedResult[ByText(withText = true)].should(be(visible))
    }

    fun clickResult() {
        "Result"[By.xpath("//*[text()='More at Wikipedia ']")].click()
    }
}