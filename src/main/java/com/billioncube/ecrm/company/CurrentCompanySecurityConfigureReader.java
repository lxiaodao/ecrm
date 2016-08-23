/**
 * 
 */
package com.billioncube.ecrm.company;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.weheros.framework.core.infrastructure.datasystem.IRelationalDataAccess;

/**
 * Read from pulibc key file to get the current company.such as id,name,code.
 * @author yang
 *
 */
@Service
public class CurrentCompanySecurityConfigureReader {
	
	@Autowired
	@Qualifier("relationalDataAccessService")
	IRelationalDataAccess relationalDataAccess;
	
	private static Company DEFAULT_COMPANY=null;
	@PostConstruct
	private void initializationCompany(){
		DEFAULT_COMPANY=new Company(2,"billioncube","四川亿立方科技有限公司");
	}
	
	public static Company getDefaultInitializationCompany(){
		//TODO： 1.系统启动第一次从加密的文件中读取参数（当前使用的公司名称和代码）；2。加密的文件是系统同亿立方公司远程通信获取？或者开发人员人工拷贝文件。
		//TODO: 3.公司初始化参数更新进入数据库表
		//return new Company("billioncube","四川亿立方科技有限公司");

		
		return DEFAULT_COMPANY;
	}

}
