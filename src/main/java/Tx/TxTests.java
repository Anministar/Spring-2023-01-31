package Tx;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.test.dao.TestDao;
import com.test.dto.TestDto;

import lombok.extern.log4j.Log4j;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TxTests {

	@Autowired
	TestDao dao;
	
	
//	@Test
//	public void func1() {
//		log.info("DAO : " + dao);
//		dao.insert(new TestDto(2, "bb"));
//	}
	
	@Autowired
	DataSource ds;
	
	@Test
	public void func2() {
		//TxManager생성
		PlatformTransactionManager tm = new DataSourceTransactionManager(ds);
		//TxStatus생성(상태정보 기본값 저장)
		TransactionStatus status = tm.getTransaction(new DefaultTransactionDefinition()); // 트랜잭션 상태를 저장해주는 단위 status
		try {
			dao.insert(new TestDto(3, "cc"));
			dao.insert(new TestDto(3, "cc")); // id가 primary key이기 때문에 중복 허용x
			tm.commit(status); 	//commit
		} catch (Exception e) {
			e.printStackTrace();
			tm.rollback(status);	//rollback
		}
		
		
	}

}
