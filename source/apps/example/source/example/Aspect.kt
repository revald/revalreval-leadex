package example

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.*
import org.junit.jupiter.api.Assertions
import org.leadium.core.get
import org.leadium.report.StepProvider
import org.leadium.report.adapter.allure.Attachment
import org.leadium.utils.Encoder
import org.leadium.utils.replaceCamelCaseWithSpaces
import org.openqa.selenium.By
import utils.DirectoryWalker
import java.io.FileNotFoundException
import java.util.*
import kotlin.collections.ArrayList

@Aspect
class StepAspect {

    var uuid = ArrayList<String>()

    @Pointcut("execution(* *..core.AbstractStep+.*(..))")
    fun step() {
        //
    }

    @Before("step()")
    fun before(jp: JoinPoint) {
        uuid.add(UUID.randomUUID().toString())
//        val inscription = (jp.signature as MethodSignature).method.getAnnotation(Step::class.java)
//        if (inscription.value.isNotEmpty()) {
//            signatureName += " ${inscription.value}"
//        }
        if (isNotDefault(jp))
            StepProvider.onStart(uuid.last(), jp.signature.name.replaceCamelCaseWithSpaces().capitalize())
    }

    @AfterReturning("step()")
    fun afterReturning(jp: JoinPoint) {
        if (isNotDefault(jp))
            StepProvider.onTry(uuid.last())
    }

    @AfterThrowing("step()", throwing = "e")
    fun afterThrowing(jp: JoinPoint, e: Throwable) {
        if (isNotDefault(jp))
            StepProvider.onCatch(uuid.last(), e)
        throw e
    }

    @After("step()")
    fun after(jp: JoinPoint) {
        if (isNotDefault(jp))
            StepProvider.onFinally(uuid.last())
        uuid.remove(uuid.last())
    }
}

@Aspect
class CheckAlertAspect {

    @Pointcut("execution(* *..core.AbstractStep+.*(..)) && @annotation(org.leadium.aspect.CheckAlert)")
    fun withCheckAlertAnnotation() {
//
    }

    @Before("withCheckAlertAnnotation()")
    fun before(jp: JoinPoint) {
        if (isNotDefault(jp))
            checkAlert()
    }

    @After("withCheckAlertAnnotation()")
    fun after(jp: JoinPoint) {
        if (isNotDefault(jp))
            checkAlert()
    }

    fun checkAlert() {
        val alert = "Alert"[By.xpath("(//*[@role='alert' and not(contains(@class, 'info'))])[last()]")]
        if (alert.exists())
            Assertions.fail<String>(alert.attr("innerText"))
    }
}

private fun isNotDefault(jp: JoinPoint): Boolean {
    val signatureName = jp.signature.name.replaceCamelCaseWithSpaces().capitalize()
    return !signatureName.contains("\$default")
}

@Aspect
class VideoEncoderAspect {

    @Pointcut("execution(* *(..)) && @annotation(com.automation.remarks.junit5.Video)")
    fun withVideoAnnotation() {
//
    }

    @Pointcut("execution(* *(..)) && @annotation(org.junit.jupiter.api.AfterEach)")
    fun withAfterEachAnnotation() {
//
    }

    @After("withAfterEachAnnotation()")
    fun after(jp: JoinPoint) {
        try {
            val video = DirectoryWalker().findByRelativePath(".avi")
            Attachment.attachVideo(Encoder().encodeToMP4(video))
        } catch (e: FileNotFoundException) {
            //
        }
    }
}