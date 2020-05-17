package example

import org.leadium.core.AbstractProperties
import utils.DirectoryWalker

abstract class AbstractExampleProperties(relativePath: String)
    : AbstractProperties(DirectoryWalker("/resources").findByRelativePath(relativePath))

object AuthProperties : AbstractExampleProperties("auth.property") {

    val baseurl = p.getProperty("baseurl")
}

object InitProperties : AbstractExampleProperties("init.property")  {

    val highlighterEnabled = p.getProperty("highlighterEnabled", "true")!!.toBoolean()
    val gitLabMaster = p.getProperty("gitLabMaster")!!
}

object AllureProperties : AbstractExampleProperties("allure.properties") {

    val allureResultsDir = p.getProperty("allure.results.directory", "build/allure-results").toString()
}
