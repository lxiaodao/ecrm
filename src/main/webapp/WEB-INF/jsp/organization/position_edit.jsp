<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<div class="bjui-pageContent">
    <form method="post" action="${ctx}/organization/position_saveorupdate.do" id="j_custom_form" data-toggle="validate" data-alertmsg="false">
        <input type="hidden" name="id" value="${position.id}">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <table class="table table-condensed table-hover" width="100%">
            <tbody>
                <tr>
                    <td>
                        <label for="department_id" class="control-label x85">所属部门：</label>
                        <select name="departmentId" id="department_id" data-toggle="selectpicker" >
                        
                            <option value="">选择</option>
                               <c:forEach items="${parents}" var="parent"> 
                                 <option value="${parent.id }"  <c:if test="${parent.id==position.departmentId}">selected="selected"</c:if> >${parent.name}</option>
                                
                               </c:forEach>
                          
                        </select>
                    </td>
                   <td>
                        <label for="position_name" class="control-label x85">职位名称：</label>
                        <input type="text" name="name" id="position_name" value="${position.name}" data-rule="required" size="15">
                    </td>
                    
                 
                    
                </tr>
               
            </tbody>
        </table>
    </form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">取消</button></li>
        <li><button type="submit" class="btn-default" data-icon="save">保存</button></li>
    </ul>
</div>