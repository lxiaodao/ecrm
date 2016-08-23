/**
 * 
 */
package com.billioncube.ecrm.organization.domain;

/**
 * @author beijingzhongshangjiaogongyinglianguanliyouxiangongsi
 *
 */
public class Position {
	
	  private Integer id;
	  private Integer companyId;
	  private Integer departmentId;
	  private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	  
	//不与数据库字段对应的数据
	private String pname; //所属部门
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	

}
