<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<div class="bjui-pageContent">
    <form method="post" action="${ctx}/organization/department_create.do" id="j_custom_form" data-toggle="validate" data-alertmsg="false">
        <input type="hidden" name="id" value="">
         <input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>"/>
        <table class="table table-condensed table-hover" width="100%">
            <tbody>
                <tr>
                    <td>
                        <label for="j_custom_sale" class="control-label x85">上级部门：</label>
                        <select name="pid" id="j_custom_sale" data-toggle="selectpicker" data-rule="">
                               <option value="">顶级</option>
                               <c:forEach items="${parents}" var="parent"> 
                                 <option value="${parent.id }"   >${parent.name}</option>
                                
                               </c:forEach>
                        </select>
                    </td>
                   <td>
                        <label for="j_custom_name" class="control-label x85">部门名称：</label>
                        <input type="text" name="name" id="j_custom_name" value="" data-rule="required" size="15">
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