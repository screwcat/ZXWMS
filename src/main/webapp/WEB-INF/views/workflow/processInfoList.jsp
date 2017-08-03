<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.*,org.apache.commons.lang3.StringUtils,org.apache.commons.lang3.ObjectUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>流程信息列表</title>
</head>
<body>
	<table width="100%">
		<thead>
			<tr>
				<th>业务表单的ID</th>
				<th>所属流程</th>
				<th>当前审批人</th>
				<th>发起人</th>
				<th>当前任务</th>
				<th>流程状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${workflowInfoHelps}" var="taskinfo">
				<tr>
					<td>${taskinfo.businessKey}</td>
					<td>${taskinfo.processDefinitionName}</td>
					<td>${taskinfo.approvers }:${taskinfo.approverids }</td>
					<td>${taskinfo.startUser }:${taskinfo.startUserId }</td>
					<td><a target="_blank"
						href='<%=request.getContextPath() %>/workflow/graph?processInstanceId=${taskinfo.processInstanceId }'
						title="点击查看流程图">${taskinfo.taskName }</a></td>
					<%--<td>
						<c:if test="${empty taskinfo.assignee }">
							<a href='<%=request.getContextPath() %>/workflow/claimTask?taskId=${taskinfo.taskId}'>签收</a>
						</c:if>
						<c:if test="${not empty taskinfo.assignee }">
							<a href='<%=request.getContextPath() %>/workflow/get-form/task/${taskinfo.taskId}/${taskinfo.processInstanceId}/${processDefinitionKey}'>办理</a>
							<a href='<%=request.getContextPath() %>/workflow/task/delegateTask/${taskinfo.taskId}'>委派</a>
						</c:if>
					</td>--%>
					<td>${taskinfo.processStatus}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
