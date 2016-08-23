/**
 * 
 */
package com.billioncube.ecrm.organization;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.billioncube.ecrm.alert.AjaxAlert;
import com.billioncube.ecrm.organization.application.IOrganizationService;
import com.billioncube.ecrm.organization.domain.Department;
import com.billioncube.ecrm.organization.domain.Position;
import com.weheros.framework.core.front.AbstractController;
import com.weheros.framework.core.front.Message;
import com.weheros.framework.core.pagination.Pagination;
import com.weheros.framework.core.utils.ToJson;

/**
 * @author beijingzhongshangjiaogongyinglianguanliyouxiangongsi
 *
 */
@Controller
@RequestMapping("/organization/*")
public class DepartmentController extends AbstractController {
	
	
	@Autowired
	@Qualifier("organizationService")
	IOrganizationService organizationService;
	
	@RequestMapping(value="department_list")	
	 public String department_list(HttpServletRequest request, HttpServletResponse response,ModelMap model){
	   String name=request.getParameter("name");
		Message message=organizationService.queryDepartment(request.getParameter("pageSize"),request.getParameter("pageCurrent"),name);
		Object[] arr=(Object[]) message.getData();
		model.addAttribute("list", arr[0]);
		model.addAttribute("pagination", arr[1]);
		model.addAttribute("name", name); //查询条件
		return "organization/department_list";
		
	}
	
	

	@RequestMapping(value="department_new")	
	 public String new_department(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		List<Department> parents=organizationService.findDepartmentsParents();
		model.addAttribute("parents", parents);
		return "organization/department_new";
		
	}
	//@RequestBody Department department,
	@RequestMapping(value="department_create")	
	
	public void create_department(Department department,HttpServletRequest request, HttpServletResponse response,ModelMap model){
		organizationService.insertDepartment(department);
		//AjaxAlert("200","操作成功！","",true);
		this.responseAjax(response, ToJson.toJson(new AjaxAlert("200","操作成功！","organization/department/department_list",true)));
		
	}
	@RequestMapping(value="department_show")	
	 public String department_show(Integer id,HttpServletRequest request, HttpServletResponse response,ModelMap model){
		Department depart=organizationService.showDepartment(id);
		List<Department> parents=organizationService.findDepartmentsParents();
		model.addAttribute("department", depart);
		model.addAttribute("parents", parents);
		return "organization/department_show";
		
	}
	@RequestMapping(value="department_update")	
	 public void department_update(Department department,HttpServletRequest request, HttpServletResponse response,ModelMap model){
		this.organizationService.updateDepartment(department);
		//return "organization/department_update";
		this.responseAjax(response, ToJson.toJson(new AjaxAlert("200","操作成功！","organization/department/department_list",true)));
		
	}
	@RequestMapping(value="department_delete")	
	 public void department_delete(Integer id,HttpServletRequest request, HttpServletResponse response,ModelMap model){
		this.organizationService.deleteDepartment(id);
		this.responseAjax(response, ToJson.toJson(AjaxAlert.SUCCESS));
		
	}
	//-----------------------------------------//
	//职位，相当于角色
	//-----------------------------------------//
	@RequestMapping(value="position_list")	
	 public String position_list(HttpServletRequest request, HttpServletResponse response,ModelMap model){
	   String name=request.getParameter("name");
		Message message=organizationService.queryPosition(request.getParameter("pageSize"),request.getParameter("pageCurrent"),name);
		Object[] arr=(Object[]) message.getData();
		model.addAttribute("list", arr[0]);
		model.addAttribute("pagination", arr[1]);
		model.addAttribute("name", name); //查询条件
		return "organization/position_list";
		
	}
	@RequestMapping(value="position_new")	
	 public String position_new(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		List<Department> parents=organizationService.findDepartmentsParents();
		model.addAttribute("parents", parents);
		return "organization/position_edit";
		
	}
	@RequestMapping(value="position_show")	
	 public String position_show(Integer id,HttpServletRequest request, HttpServletResponse response,ModelMap model){
		
		Position p=organizationService.showPosition(id);
		List<Department> parents=organizationService.findDepartmentsParents();
		model.addAttribute("position", p);
		model.addAttribute("parents", parents);
		return "organization/position_edit";
		
	}
	@RequestMapping(value="position_saveorupdate")	
	 public void position_saveorupdate(Position position,HttpServletRequest request, HttpServletResponse response,ModelMap model){
		this.organizationService.saveorupdate(position);
		//return "organization/department_update";
		this.responseAjax(response, ToJson.toJson(new AjaxAlert("200","操作成功！","organization/position_list",true)));
		
	}
	@RequestMapping(value="position_delete")	
	 public void position_delete(Integer id,HttpServletRequest request, HttpServletResponse response,ModelMap model){
		this.organizationService.deletePosition(id);
		this.responseAjax(response, ToJson.toJson(AjaxAlert.SUCCESS));
		
	}

}
