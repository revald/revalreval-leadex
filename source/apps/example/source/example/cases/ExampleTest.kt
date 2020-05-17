package example.cases

import com.codeborne.selenide.Condition.*
import example.data.Link
import example.data.Wiki
import example.steps.pages.DuckDuckGo
import example.steps.pages.Wikipedia
import example.steps.pages.YandexMarketMonitors
import example.steps.pages.GoogleCheck
import org.leadium.core.TestCase
import org.leadium.core.get
import org.leadium.core.minus
import org.leadium.data.ByText
import org.openqa.selenium.By

class ExampleTest : TestCase {

    lateinit var searchRequest: String
    lateinit var expectedResult: String
    lateinit var gSearchRequestResult: String

    override fun begin() {

        YandexMarketMonitors() - {

            "SearchBox"[By.xpath("//*[@id=\'header-search']")].click()
            Link.computers1[ByText()].click()
            Link.monitors1[ByText()].click()
            navigateToMonitors()

        }

//  to run this test need to change baseUrl in auth.properties to https://www.google.com/ and uncomment GoogleCheck
        // also need to comment out YandexMarketMonitors

 //       GoogleCheck() - {
//
 //           setGSearchBox(gSearchRequestResult)
 //           gCheckResults(gSearchRequestResult)

 //       }
    }

    }


