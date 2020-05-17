package example.steps.pages

import com.codeborne.selenide.Condition
import example.data.Link
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.leadium.core.AbstractStep
import org.leadium.core.get
import org.leadium.data.ByText
import org.openqa.selenium.By

class GoogleCheck : AbstractStep() {

    fun setGSearchBox(gSearchRequestResult: String) {
        "Search Box"[By.xpath("//input[@name='q']")].sendKeys(gSearchRequestResult)
        "Search Button"[By.xpath("//input[@name='btnK']")].click()
    }



    fun gCheckResults(gSearchRequestResult: String) {
         println(gSearchRequestResult)
         assertTrue(gSearchRequestResult[ByText()].has(Condition.appears))
         "Monkey Result"[By.xpath("//*[@id='rso']/div[3]/div/div[1]/a")].click()
    }


}
