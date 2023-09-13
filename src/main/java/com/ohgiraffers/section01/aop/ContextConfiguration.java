package com.ohgiraffers.section01.aop;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
/* AspectJ의 auto Proxy 사용에 관한 설정을 해주어야 Advice가 동작한다.
* proxyTargetClass=true 설정은 cglib(씨지엘아이비)를 이용한 프록시 생성 방식인데,
* Spring 3.2부터 스프링 프레임워크에 포함 되어 별도 라이브러리 설정을 하지 않고 사용할 수 있다.
* 성능 면에서 더 우수하다. */
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ContextConfiguration {
}
