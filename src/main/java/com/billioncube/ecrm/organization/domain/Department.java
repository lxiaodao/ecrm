/**
 * 
 */
package com.billioncube.ecrm.organization.domain;

/**
 * @author beijingzhongshangjiaogongyinglianguanliyouxiangongsi
 *
 */
public class Department {
	
	private Integer id;
	private Integer companyId;
	private Integer pid;
	private String name;
	private Integer level;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	//不与数据库字段对应的数据
	private String pname; //上级部门名称

	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	

	

}
