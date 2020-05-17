package example.steps.pages

import com.codeborne.selenide.Condition.be
import com.codeborne.selenide.Condition.visible
import example.data.Link
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.fail
import org.leadium.core.AbstractStep
import org.leadium.core.get
import org.leadium.data.ByText
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import kotlin.system.exitProcess

class YandexMarketMonitors : AbstractStep() {

    fun navigateToMonitors() {

        var minPrice: String = "10000"
        var maxPrice: String = "20000"

        "PickUp"[By.xpath("//*[@id='search-prepack']")].click()

        Link.selfPickUp[ByText()].click()
        Link.acer[ByText()].click()
        Link.dell[ByText()].click()

        // this is pretending that text from site was captured.
        var listedResult: String = "15 740"
        println("initial listedResult = $listedResult")
        var filteredListedResult: Double = listedResult.replace(" ", "").toDouble()
        println("initial filtered listedResult = $filteredListedResult")

        println(filteredListedResult)
        var noMoreThan: Double = 10.0

        println("nomore that= $noMoreThan")

        if (filteredListedResult > noMoreThan) {
            println("now in if statement - filteredListedResult < noMoreThan ")
//            AssertionError("Price on site for 3d item is more than 100 000 P")
//            System.exit(0)
            println("The price of third item for Monitors is $listedResult which is more than expected $noMoreThan")
            fun fail(message: String? = null): String {
                return "0"
            }

            //       fun `Test exception`() {
//            val exception: Exception = assertThrows(IllegalArgumentException::class.java, {
            //               throw IllegalArgumentException("exception message")
            //           })
            //           assertEquals("exception message", exception.message)
            //       }
            if (filteredListedResult > noMoreThan) run {
                println("now in if statement - filteredListedResult > noMoreThan ")
                fun fail(message: String? = "Price on site for 3d item is more than 100 000 P") {}
            }


            //this section i tried to get values from product listing but could not find xpath of cssselector or id that was
            // consistent
            //      println("initial listedResult = $listedResult")
            //       listedResult[By.xpath("//div[@class='n-snippet-card2__main-price-wrapper']//div[@class='price']")].getText()

            //       listedResult[By.xpath("//[@id="product-7349236"]/div[5]/div[1]/div[1]/div/div/a/div")].getText()
            //       println("result of getText = $listedResult")

            //       listedResult[By.xpath("//div[@class='n-snippet-card2__main-price-wrapper']//div[@class='price']")].getValue()
            //       listedResult[By.xpath("//div[@id="product-7349236"]/div[5]/div[1]/div[1]/div/div/a/div")].getText()
            //       println("result of getValue = $listedResult")
//        listedResult[By.xpath("//div[3]/div[5]/div[1]/div[1]/div/div/a/div]")].getValue()
            //       println("result of getValue = $listedResult")
            //       listedResult[By.cssSelector("div.n-snippet-card2:nth-child(3) > div:nth-child(5) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(1)")].getText()
            //       println("result of css = $listedResult")
        }
        /*   fun getPriceListed () : String {
            lateinit var listedResult: String
            //listedResult[By.xpath("//div[3]/div[5]/div[1]/div[1]/a/div/span/span[1]]")].text()
            listedResult[By.xpath("//div[3]/div[5]/div[1]/div[1]/a/div/span/span[1]")].click()
            println(listedResult)
            return listedResult
        }

      */

        fun setMinPrice(minPrice: String) {
            "Min Price"[By.xpath("//input[@id='glpricefrom']")].set(minPrice)

        }

        fun setMaxPrice(maxPrice: String) {
            "Min Price"[By.xpath("//input[@id='glpriceto']")].set(maxPrice)

        }


    }
}

