package com.ohgiraffers.section01.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Map;

/* @Aspect : pointcut과 advice를 하나의 클래스 단위로 정의하기 위한 어노테이션이다. */
@Aspect
@Component
public class LoggingAspect {
    // 로그를 남긴다.

    /* @Pointcut : 여러 조인 포인트를 매치하기 위해 지정한 표현식 */
    @Pointcut("execution(* com.ohgiraffers.section01.aop.*Service.*(..))")
    // 패키지 뒤에 *Service(모든 Service 클래스) 밑에 뭔가의 메소드 *(..)를 pointcut으로 잡겠다.
    // execution - AOP에서 가장 많이 사용되는 포인트컷 표현식 중 하나
    // set*(java.lang.String) set 메소드 하위에 String, 라고 알 수 있다.

    public void logPointcut() {
    }

    /* JoinPoint : 포인트 컷으로 매치한 실행 시점
     * 이렇게 매치 된 조인 포인트에서 해야 할 일이 어드바이스이다.
     * 매개변수로 전달한 JoinPoint 객체는 현재 조인 포인트의 메소드명, 인수값 등의 자세한 정보를 액세스 할 수 있다. */
    // 클래스명.메소드명()
    @Before("LoggingAspect.logPointcut()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before joinPoint.getTarget() : " + joinPoint.getTarget());
        System.out.println("Before joinPoint.getSignature() : " + joinPoint.getSignature());
        if (joinPoint.getArgs().length > 0) {
            System.out.println("Before joinPoint.getArgs()[0] : " + joinPoint.getArgs()[0]);
        }

        // target Object(MemberService의 메소드들) 를 감싸는 proxy라는 대리인 객체를 만든다.
        // 이전 시점인 before 이후에 target obj를 호출하기 위한 과정을 작성한 것이다.
        // 대상이 누구냐, 시그니처(호출되는 메소드)가 누구냐, Args()는 갯수를 모르니까 배열로.
        // 로그라는게 잘 출력되는지 알아보는게 목적 1, 포인트컷이 잘 적용됐는지 알아보는게 목적 2
    }

    /* 포인트 컷을 동일한 클래스 내에서 사용한다면 클래스명을 생략할 수 있다.
     단, 패키지가 다르면 패키지를 포함한 클래스명을 기술해야 한다.*/
    @After("logPointcut()")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("After joinPoint.getTarget() : " + joinPoint.getTarget());
        System.out.println("After joinPoint.getSignature() : " + joinPoint.getSignature());
        if (joinPoint.getArgs().length > 0) {
            System.out.println("After joinPoint.getArgs()[0] : " + joinPoint.getArgs()[0]);
        }
    }

    /* returning 속성은 리턴 값으로 받아올 오브젝트의 매개변수 이름과 동일해야 한다.
    * 또한 joinPoint는 반드시 첫 번째 매개변수로 선언해야 한다. */
    @AfterReturning(pointcut = "logPointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("After Returning result : " + result);
        /* 리턴할 결과 값을 변경해줄 수도 있다. */
        if(result != null && result instanceof Map) {
            ((Map<Long, MemberDTO>) result).put(100L, new MemberDTO(100L, "반환 값 가공"));
        }
    }

    /* throwing 속성의 이름과 매개변수의 이름이 동일해야 한다.*/
    @AfterThrowing(pointcut="logPointcut()", throwing = "exception")
    public void logAfterThrowing(Throwable exception) {
        System.out.println("After Throwing exception : " + exception);
    }


    /* Around Advice는 가장 강력한 어드바이스이다. 이 어드바이스는 조인 포인트를 완전히 장악하기 때문에
    * 앞에 살펴 본 어드바이스를 모두 Around 어드바이스로 조립할 수 있다.
    * 원본 조인 포인트 실행 여부까지 제어할 수 있다.
    * 조인 포인트를 진행하는 호출을 잊는 경우도 발생할 수 있기 때문에 주의해야 하며
    * 최소한의 요건을 충족하면서도 가장 기능이 약한 어드바이스를 쓰는 것이 바람직하다. */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("Around Before : " + joinPoint.getSignature());

        /* 원본 조인포인트 실행 코드 */
        Object result = joinPoint.proceed();

        System.out.println("Around After : " + joinPoint.getSignature());

        /* 원본 조인 포인트를 호출한 쪽 혹은 다른 어드바이스가 다시 실행할 수 있도록 결과를 반환한다. */
        return result;
    }

}
