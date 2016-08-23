package com.billioncube.ecrm.organization.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.billioncube.ecrm.company.CurrentCompanySecurityConfigureReader;
import com.billioncube.ecrm.organization.domain.Department;
import com.billioncube.ecrm.organization.domain.Position;
import com.billioncube.ecrm.organization.domain.User;
import com.weheros.framework.core.infrastructure.datasystem.IRelationalDataAccess;
import com.weheros.framework.core.pagination.Pagination;

@Service("organizationDao")
public class OrganizationDao implements IOrganizationDao {

	@Autowired
	@Qualifier("relationalDataAccessService")
	IRelationalDataAccess relationalDataAccess;
	
	@Override
	public List<Department> queryDeparment(Pagination pagination, String name) {
		boolean isEmpty=StringUtils.isBlank(name);
		String sql2= (isEmpty?" ":" where name=?");
		String sql="select *,(select name from department p where p.id=d.pid) as pname from department d "+sql2+" order by id asc limit ?,?";
		Object[] paras=isEmpty?new Object[]{pagination.getBeginIndex(),pagination.getPageSize()}:new Object[]{name,pagination.getBeginIndex(),pagination.getPageSize()};
		return this.relationalDataAccess.queryByMapper(sql, paras,new RowMapper<Department>(){

			@Override
			public Department mapRow(ResultSet rs, int arg1) throws SQLException {
				Department depart= mapDepart(rs);
				depart.setPname(rs.getString("pname"));
				return depart;
			}});
	}

	@Override
	public Integer createDepartment(Department department) {
	
		Number number=relationalDataAccess.insertAndReturnKey("insert into department (company_id,pid,name,level) values (?,?,?,?)", new Object[]{department.getCompanyId(),department.getPid(),department.getName(),department.getLevel()});
		return number.intValue();
	}

	@Override
	public Integer countDepartment(String name) {
		boolean isEmpty=StringUtils.isBlank(name);
		String sql="select count(id) from department "+ (isEmpty?" ":" where name=?");
		
		return relationalDataAccess.queryCount(sql, isEmpty?new Object[]{}:new Object[]{name});
	}

	@Override
	public Integer delete(Integer id) {
	return this.relationalDataAccess.delete("delete from department where id=?", new Object[]{id});
		
	}

	@Override
	public Integer updateDepartment(Department department) {
		String sql="update department set pid=?,name=?,level=? where id=? ";
		Object[] values=new Object[]{department.getPid(),department.getName(),department.getLevel(),department.getId()};
		return this.relationalDataAccess.update(sql, values);
	}

	@Override
	public <T> T show(Integer id, Class<T> classt) {
		if(classt == Department.class){
			return (T) findDepartment(id);
		}
		else if(classt == Position.class){
			return (T)findPosition(id);
		}
		return null;
	}


	private Department findDepartment(Integer id) {
		
		String sql="select * from department where id=?";
		return relationalDataAccess.getJdbcTemplate().queryForObject(sql, new Object[]{id},new RowMapper<Department>(){

			@Override
			public Department mapRow(ResultSet arg0, int arg1) throws SQLException {
				return mapDepart(arg0);
			}});
	}

	protected Department mapDepart(ResultSet rs) throws SQLException {
		Department depart=new Department();
		depart.setId(rs.getInt("id"));
		depart.setCompanyId(rs.getInt("company_id"));
		depart.setName(rs.getString("name"));
		depart.setPid(rs.getInt("pid"));
		depart.setLevel(rs.getInt("level"));
	
		return depart;
	}

	@Override
	public List<Department> findDepartmentsParents() {
		String sql="select * from department order by id asc";
		return relationalDataAccess.getJdbcTemplate().query(sql,new RowMapper<Department>(){

			@Override
			public Department mapRow(ResultSet arg0, int arg1) throws SQLException {
				return mapDepart(arg0);
			}});
	}

	@Override
	public Integer countPosition(String name) {
		boolean isEmpty=StringUtils.isBlank(name);
		String sql="select count(id) from position "+ (isEmpty?" ":" where name=?");
		
		return relationalDataAccess.queryCount(sql, isEmpty?new Object[]{}:new Object[]{name});
	}

	@Override
	public List<Position> queryPosition(Pagination pagination, String name) {
		boolean isEmpty=StringUtils.isBlank(name);
		String sql2= (isEmpty?" ":" where name=?");
		String sql="select *,(select name from department d where d.id=p.department_id) as pname from position p "+sql2+" order by id asc limit ?,?";
		Object[] paras=isEmpty?new Object[]{pagination.getBeginIndex(),pagination.getPageSize()}:new Object[]{name,pagination.getBeginIndex(),pagination.getPageSize()};
		return this.relationalDataAccess.queryByMapper(sql, paras,new RowMapper<Position>(){

			@Override
			public Position mapRow(ResultSet rs, int arg1) throws SQLException {
				Position position= mapPosition(rs);
				position.setPname(rs.getString("pname"));
				return position;
			}});
		
	}

	protected Position mapPosition(ResultSet rs) throws SQLException {
		Position p=new Position();
		p.setId(rs.getInt("id"));
		p.setCompanyId(rs.getInt("company_id"));
		p.setName(rs.getString("name"));
	    p.setDepartmentId(rs.getInt("department_id"));
	  return p;
	}
	

	private Position findPosition(Integer id) {
		
		String sql="select * from position where id=?";
		return relationalDataAccess.getJdbcTemplate().queryForObject(sql, new Object[]{id},new RowMapper<Position>(){

			@Override
			public Position mapRow(ResultSet arg0, int arg1) throws SQLException {
				return mapPosition(arg0);
			}});
	}

	@Override
	public Integer createPosition(Position position) {
		Number number=relationalDataAccess.insertAndReturnKey("insert into position (company_id,department_id,name) values (?,?,?)", 
				new Object[]{position.getCompanyId(),position.getDepartmentId(),position.getName()});
		return number.intValue();
		
	}

	@Override
	public Integer updatePosition(Position position) {
		String sql="update position set department_id=?,name=? where id=? ";
		Object[] values=new Object[]{position.getDepartmentId(),position.getName(),position.getId()};
		return this.relationalDataAccess.update(sql, values);
	}

	@Override
	public Integer deleteObject(Integer id, Class classt) {
		
		if(classt == Department.class){
			return this.relationalDataAccess.delete("delete from department where id=?", new Object[]{id});
		}
		else if(classt == Position.class){
			return this.relationalDataAccess.delete("delete from position where id=?", new Object[]{id});
		}
	   return null;
	}

	@Override
	public User queryUser(String name) {
		//boolean isEmpty=StringUtils.isBlank(name);
		return this.relationalDataAccess.queryOne("select u.*,p.name as positionName,d.name as departmentName,c.name as companyName from user u"
				+" left join position p on p.id=u.position_id"
				+" left join department d on u.department_id=d.id"
				+" left join company c on u.company_id=c.id"
				+ " where u.username=?", new Object[]{name}, new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				User user= mapUser(rs);
				user.setDepartmentName(rs.getString("departmentName"));
				user.setPositionName(rs.getString("positionName"));
				user.setCompanyName(rs.getString("companyName"));
				return user;
			}});
	}

	protected User mapUser(ResultSet rs) throws SQLException {
		User user=new User();
		user.setId(rs.getInt("id"));
		user.setCompanyId(rs.getInt("company_id"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setFullname(rs.getString("fullname"));
		user.setDepartmentId(rs.getInt("department_id"));
		user.setPositionId(rs.getInt("position_id"));
		user.setMobilephone(rs.getString("mobilephone"));
		user.setTelephone(rs.getString("telephone"));
		user.setEmail(rs.getString("email"));
		user.setWeixin(rs.getString("weixin"));
		user.setQq(rs.getString("QQ"));
		user.setGender(rs.getInt("gender"));
		
		return user;
	}


	
}
