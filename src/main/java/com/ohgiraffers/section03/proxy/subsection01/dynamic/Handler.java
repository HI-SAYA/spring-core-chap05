package com.ohgiraffers.section03.proxy.subsection01.dynamic;

import com.ohgiraffers.section03.proxy.common.OhgiraffersStudent;
import com.ohgiraffers.section03.proxy.common.Student;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* 호출에 따른 프록시에서 처리할 메소드를 구현하기 위해 InvocationHandler를 구현할 클래스를 작성 */
public class Handler implements InvocationHandler {

    /* 메소드 호출을 위해 타켓 인스턴스가 필요 */

    private final Student student;

    public Handler(Student student) { this.student = student; }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("========== 공부가 너무 하고 싶습니다. ==========");
        System.out.println("호출 대상 메소드 : " + method);
        for(Object arg : args) {
            System.out.println("전달 된 인자 : " + arg);
        }

        /* 타겟 메소드를 호출한다. 타겟 obj와 인자를 매개변수로 전달한다. */
        method.invoke(student, args);

        System.out.println("=========== 공부를 마치고 수면 학습을 시작합니다. ===========");

        return proxy;
    }
}
