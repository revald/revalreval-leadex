package example.suite

import com.automation.remarks.junit5.Video
import example.InitProperties.gitLabMaster
import example.cases.ExampleTest
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DisplayNameGeneration
import org.junit.jupiter.api.Test
import org.leadium.core.initFieldsByPropertyFile
import org.leadium.junit5.displayname.DisplayNameGenerator
import org.leadium.junit5.displayname.TestCaseDisplayName
import org.leadium.report.adapter.allure.description.DescriptionBuilder

@Epic("EXAMPLE EPIC")
@DisplayName("Example Suite | positive")
@DisplayNameGeneration(DisplayNameGenerator::class)
class ExampleSuite : TestSuite() {

    @Test
    @Video
    @Feature("POSITIVE")
    @Story("EXAMPLE STORY")
    @TestCaseDisplayName(ExampleTest::class)
    fun wikiTest() {
        val testCase = ExampleTest()
        with(DescriptionBuilder(testCase, userDir, gitLabMaster)) {
            appendPropertyFile(findResource("example.property"))
            apply()
        }
        testCase.initFieldsByPropertyFile(findResource("example.property"))
                .begin()
    }

    @AfterEach
    override fun afterEach() {
        super.afterEach()
    }
}