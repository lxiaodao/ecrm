<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${ctx}/organization/department_list.do" method="post">
        <input type="hidden" name="pageSize" value="${pagination.pageSize}">
        <input type="hidden" name="pageCurrent" value="${pagination.pageCurrent}">
       
         <input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>"/>
        <div class="bjui-searchBar">
         
            <label>部门名称：</label><input type="text" id="department_name" value="${name} " name="name" class="form-control" size="10">&nbsp;
          
            <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
      
          
             <div class="pull-right">
             <!--  
                <button type="button" class="btn-blue" data-url="${ctx}/organization/department_new.do" data-toggle="navtab" data-icon="plus" title="新增">新增部门</button>&nbsp;
                <a href="${ctx}/organization/department_new.do" class="btn btn-blue" data-toggle="navtab" data-icon="plus" data-id="form"  data-title="新增">新增部门</a>
                -->
              
                    <a href="${ctx}/organization/department_new.do" class="btn btn-green" data-toggle="dialog" data-width="600" data-height="300" data-id="newdepartment-dialogmask" data-mask="true" ><i class="fa fa-plus"></i> 新增部门</a>
                
                
                <a></a>
            </div>
          
          
        </div>
        
        
    
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table class="table table-bordered table-hover table-striped table-top" data-selected-multi="true">
        <thead>
            <tr>
                <th width="20"></th>
                <th width="100">序号</th>
                <th width="100">上级部门</th>
                <th width="100">部门名称</th>
     
                <th width="200">操作</th>
            </tr>
        </thead>
        
        <tbody>
        
       <c:forEach items="${list}" var="department"> 
            <tr data-id="${department.id}">
               <td></td>
                <td>${department.id}</td>
                <td>${department.pname}</td>
                <td>${department.name}</td>
                
               
                <td>
                 
                   <a href="${ctx}/organization/department_show.do?id=${department.id}&${_csrf.parameterName}=${_csrf.token}" class="btn btn-blue" data-toggle="dialog" data-width="600" data-height="300" data-id="edit_department-dialogmask" data-mask="true" >编辑</a>
                
                    
                    <a href="${ctx}/organization/department_delete.do?id=${department.id}&${_csrf.parameterName}=${_csrf.token}" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除该行信息吗？">删除</a>
                </td>
            </tr>
        
        </c:forEach>
           
      
           
        </tbody>
    </table>
</div>
<div class="bjui-pageFooter">
    <div class="pages">
        <span>每页&nbsp;</span>
        <div class="selectPagesize">
            <select data-toggle="selectpicker" data-toggle-change="changepagesize">
                <option value="30">20</option>
                <option value="60">50</option>
           
            </select>
        </div>
        <span>&nbsp;条，共 ${pagination.totalResults} 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="${pagination.totalResults }" data-page-size="${pagination.pageSize}" data-page-current="${pagination.pageCurrent}">
    </div>
</div>