package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // 해당 어노테이션을 적어야지 AOP로 사용이 가능
@Component //스프링 빈으로 등록. config에서 등록을 더 선호
public class TimeTraceAop {

    //공통 관심사항 타겟팅 기능
    @Around("execution(* hello.hellospring..*(..))") //해당 패키지 하위에 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed(); //inline variable로 합치기 ctrl + t
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("End: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
