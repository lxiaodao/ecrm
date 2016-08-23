/**
 * 
 */
package com.billioncube.ecrm.organization.application;


import java.util.List;

import com.billioncube.ecrm.organization.domain.Position;
import com.billioncube.ecrm.organization.domain.User;
import com.billioncube.ecrm.organization.domain.Department;
import com.weheros.framework.core.front.Message;

/**
 * @author Yang
 *
 */
public interface IOrganizationService {
	
	public Integer insertDepartment(Department department);
	
	public Integer updateDepartment(Department department);
	
	public Integer deleteDepartment(Integer id);

	public Message queryDepartment(String pageSize, String pageCurrent, String name);

	public Department showDepartment(Integer id);

	public List<Department> findDepartmentsParents();

	public Message queryPosition(String pageSize, String pageCurrent, String name);

	public Position showPosition(Integer id);

	public void saveorupdate(Position position);

	public void deletePosition(Integer id);

	public User findUser(String name);
}
