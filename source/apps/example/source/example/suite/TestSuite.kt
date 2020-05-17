package example.suite

import com.codeborne.selenide.Configuration
import example.AllureProperties
import example.AuthProperties
import example.InitProperties
import org.apache.log4j.BasicConfigurator
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.RegisterExtension
import org.leadium.core.close
import org.leadium.core.open
import org.leadium.report.Highlighter
import org.leadium.report.StepProvider
import org.leadium.report.adapter.allure.AllureAfterTestExecutionCallback
import org.leadium.report.adapter.allure.FailedTestsAggregator
import org.leadium.report.adapter.allure.Selenoid
import org.leadium.report.logevent.LogEventProvider
import utils.DirectoryWalker

abstract class TestSuite {

    val resourcesPath: String = "/source/apps/example/resources"
    val categoriesJson = DirectoryWalker().findByRelativePath("categories.json")
    val userDir = System.getProperty("user.dir")
    fun findResource(testData: String, relativePath: String) = DirectoryWalker("$resourcesPath/$testData/").findByRelativePath(relativePath)
    fun findResource(relativePath: String) = DirectoryWalker("$resourcesPath/").findByRelativePath(relativePath)

    /**
     * @suppress
     */
    companion object {

        val allureReportAdapter = org.leadium.report.adapter.allure.ReportAdapter()

        init {
            StepProvider.register(allureReportAdapter)
            LogEventProvider.register(allureReportAdapter)
            LogEventProvider.register(Highlighter)

            BasicConfigurator.configure()
            Logger.getRootLogger().level = Level.OFF
        }
    }

    @JvmField
    @RegisterExtension
    var failedTestsAggregator = FailedTestsAggregator(userDir)

    @JvmField
    @RegisterExtension
    var allure = AllureAfterTestExecutionCallback(AuthProperties.baseurl, categoriesJson, "$userDir/${AllureProperties.allureResultsDir}")

    @JvmField
    @RegisterExtension
    var selenoidAllure = Selenoid.Allure

    @BeforeEach
    fun beforeEach() {
        Configuration.browser = InitProperties.p.getProperty("browser", "chrome")
        if (System.getProperty("parallel", "false")!!.toBoolean())
            Configuration.browser = Selenoid::class.java.canonicalName
        else
            Configuration.driverManagerEnabled = InitProperties.p.getProperty("driverManagerEnabled", "true")!!.toBoolean()
        Configuration.driverManagerEnabled = InitProperties.p.getProperty("driverManagerEnabled", "true")!!.toBoolean()
        Configuration.headless = System.getProperty("headless", "false")!!.toBoolean()
        Configuration.startMaximized = InitProperties.p.getProperty("startMaximized", "true")!!.toBoolean()
        Configuration.holdBrowserOpen = InitProperties.p.getProperty("holdBrowserOpen", "false")!!.toBoolean()
        Configuration.savePageSource = InitProperties.p.getProperty("savePageSource", "false")!!.toBoolean()
        Configuration.screenshots = InitProperties.p.getProperty("screenshots", "false")!!.toBoolean()
        Configuration.timeout = InitProperties.p.getProperty("timeout", "30000").toLong()
        open(AuthProperties.baseurl)
    }

    @AfterEach
    open fun afterEach() {
        close()
    }
}