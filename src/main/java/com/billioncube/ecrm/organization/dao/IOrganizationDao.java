/**
 * 
 */
package com.billioncube.ecrm.organization.dao;

import java.util.List;

import com.billioncube.ecrm.organization.domain.Department;
import com.billioncube.ecrm.organization.domain.Position;
import com.billioncube.ecrm.organization.domain.User;
import com.weheros.framework.core.pagination.Pagination;

/**
 * @author beijingzhongshangjiaogongyinglianguanliyouxiangongsi
 *
 */
public interface IOrganizationDao {
	
	
	public List<Department> queryDeparment(Pagination pagination,String name);

	public Integer createDepartment(Department department);

	public Integer countDepartment(String name);

	public Integer delete(Integer id);

	public Integer updateDepartment(Department department);

	public <T> T show(Integer id, Class<T> classt);

	public List<Department> findDepartmentsParents();

	public Integer countPosition(String name);

	public List<Position> queryPosition(Pagination p, String name);

	public Integer createPosition(Position position);

	public Integer updatePosition(Position position);

	public Integer deleteObject(Integer id, Class<Position> class1);

	public User queryUser(String name);

	

}
