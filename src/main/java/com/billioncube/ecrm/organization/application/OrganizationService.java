/**
 * 
 */
package com.billioncube.ecrm.organization.application;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.billioncube.ecrm.company.CurrentCompanySecurityConfigureReader;
import com.billioncube.ecrm.organization.dao.IOrganizationDao;
import com.billioncube.ecrm.organization.domain.Department;
import com.billioncube.ecrm.organization.domain.Position;
import com.billioncube.ecrm.organization.domain.User;
import com.weheros.framework.core.front.Message;
import com.weheros.framework.core.infrastructure.datasystem.IRelationalDataAccess;
import com.weheros.framework.core.pagination.Pagination;

/**
 * @author beijingzhongshangjiaogongyinglianguanliyouxiangongsi
 *
 */
@Transactional
@Service("organizationService")
public class OrganizationService implements IOrganizationService {

	@Autowired
	@Qualifier("organizationDao")
	IOrganizationDao organizationDao;

	@Override
	public Integer updateDepartment(Department department) {
		
		return this.organizationDao.updateDepartment(department);
	}

	@Override
	public Integer deleteDepartment(Integer id) {
		
		return this.organizationDao.delete(id);
	}

	@Override
	public Integer insertDepartment(Department department) {
		//TODO:登录后从session获取当前用户所属公司
		if(department.getCompanyId()==null){
			department.setCompanyId(CurrentCompanySecurityConfigureReader.getDefaultInitializationCompany().getId());
		}
		return organizationDao.createDepartment(department);
	}

	@Override
	public Message queryDepartment(String pageSize, String pageCurrent, String name) {
		Integer all=organizationDao.countDepartment(name);
		Pagination p=mapPagination(all,pageSize,pageCurrent);
		
		Message message=new Message(Message.VISIT_SUCCESS);
		
		message.setData(new Object[]{organizationDao.queryDeparment(p, name),p});
		
		return message;
	}

	@Override
	public Department showDepartment(Integer id) {
		
		return organizationDao.show(id, Department.class);
	}

	@Override
	public List<Department> findDepartmentsParents() {
	
		return organizationDao.findDepartmentsParents();
	}

	@Override
	public Message queryPosition(String pageSize, String pageCurrent, String name) {
		Integer all=organizationDao.countPosition(name);
	
		Pagination p=mapPagination(all,pageSize,pageCurrent);
	
		Message message=new Message(Message.VISIT_SUCCESS);
		//返回对象组装
		message.setData(new Object[]{organizationDao.queryPosition(p, name),p});
		
		return message;
	}

	private Pagination mapPagination(Integer all, String pageSize, String pageCurrent) {
		Integer page_size=StringUtils.isNotBlank(pageSize)?Integer.valueOf(pageSize):0;
		Integer page_current=StringUtils.isNotBlank(pageCurrent)?Integer.valueOf(pageCurrent):0;
		
		return new Pagination(all,page_size,page_current);
	}

	@Override
	public Position showPosition(Integer id) {
		return organizationDao.show(id,Position.class);
	}

	@Override
	public void saveorupdate(Position position) {
		if(position.getId()==null||position.getId()==0){
			position.setCompanyId(CurrentCompanySecurityConfigureReader.getDefaultInitializationCompany().getId());
		  this.organizationDao.createPosition(position);
		}else{
			organizationDao.updatePosition(position);
		}
		
	}

	@Override
	public void deletePosition(Integer id) {
		organizationDao.deleteObject(id,Position.class);
	}

	@Override
	public User findUser(String name) {
		
		return organizationDao.queryUser(name);
	}



}
