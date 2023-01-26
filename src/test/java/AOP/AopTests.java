package AOP;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.aop.MyMath;

import lombok.extern.log4j.Log4j;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class AopTests {

	
	@Autowired //의존성 주입???? 찾아보기!!
	ApplicationContext ac; //root-context의 container에 만들어놓은걸 여기서 연결하는것임.
	
	@Test
	public void aopTests() {
		log.info("AC : " + ac);
		MyMath math = (MyMath)ac.getBean("myMath");
		math.add(10, 20);
		math.add(30, 40, 50);
	}
}
