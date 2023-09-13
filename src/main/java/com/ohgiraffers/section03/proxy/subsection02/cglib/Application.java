package com.ohgiraffers.section03.proxy.subsection02.cglib;

import com.ohgiraffers.section03.proxy.common.OhgiraffersStudent;
import org.springframework.cglib.proxy.Enhancer;


public class Application {

    public static void main(String[] args) {

        OhgiraffersStudent student = new OhgiraffersStudent();
        Handler handler = new Handler(student);

        OhgiraffersStudent proxy = (OhgiraffersStudent) Enhancer.create(OhgiraffersStudent.class, handler);

        proxy.study(20);
    }
}
