package hr.dario.protulipac.photoapp.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PictureTitleAspect {
    @Pointcut(value = "execution(* hr.dario.protulipac.photoapp.controller.HomeController.home(..))")
    public void homePage() {}

    @Before(value = "homePage()")
    public void printAnnotationBefor() {
        System.out.println("Hello Aspect Before");
    }

    @After(value = "homePage()")
    public void printAnnotationAfter() {
        System.out.println("Hello Aspect After");
    }
}
