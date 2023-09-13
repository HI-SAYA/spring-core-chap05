package com.ohgiraffers.section01.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext("com.ohgiraffers.section01.aop");

        MemberService memberService = context.getBean("memberService", MemberService.class);
        // @Service 생략 앞글자 소문자로 가져온다 => memberService

        System.out.println("========== selectMembers ==========");
        System.out.println(memberService.selectMembers());
        System.out.println("========== selectMember ==========");
        System.out.println(memberService.selectMember(3L));

        // 정상 수행시에만 AfterReturning이 발생한다.
    }
}
