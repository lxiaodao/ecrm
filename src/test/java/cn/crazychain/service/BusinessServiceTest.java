/**
 * 
 */
package cn.crazychain.service;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weheros.test.GenericJunitDataInDBTest;

import cn.crazychain.article.repository.ArticleRepository;
import cn.crazychain.user.repository.UserRepository;


/**
 * @author yang
 *
 */
public class BusinessServiceTest extends GenericJunitDataInDBTest {
	
	protected Logger log = LoggerFactory.getLogger(BusinessServiceTest.class);
	
	@Autowired
	private BusinessService businessService;
	
	
	@Autowired
	@Qualifier("userRepository")
	 private UserRepository userRepo;
	 
	 @Autowired
	 @Qualifier("articleRepository")
	 private ArticleRepository articleRepo;
	 
	
	 
	 
	 private boolean isMockFail=false;
	 
	 private long begintime=0;
	
	 @Before
	 public void before(){
		 userRepo.delete("delete from user");
		 articleRepo.delete("delete from article");
		 
		 this.begintime=System.currentTimeMillis();
	 }
	 
	 @After
	 public void after(){
		 int result=isMockFail?0:1;
		 log.debug("------need time------"+(System.currentTimeMillis()-begintime));
		 log.debug("---this is test's after begin------"+result);
		
		 assertEquals(result, userRepo.queryCount("select count(*) from user", null) );
		 assertEquals(result, articleRepo.queryCount("select count(*) from article", null));
		 
	 }
	 
	   @Test
		public void test_success() {
			this.isMockFail=false;
			businessService.createAll();
			
		}
	 
	
	 
	   
	@Test(expected=Exception.class)
	public void test_insert_fail() {
		this.isMockFail=true;
		businessService.createArticle("", true);
	}
	
	

}
