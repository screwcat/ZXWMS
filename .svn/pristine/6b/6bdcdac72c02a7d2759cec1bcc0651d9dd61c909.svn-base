<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.*,org.apache.commons.lang3.StringUtils,org.apache.commons.lang3.ObjectUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<title>审批表单</title>
</head>
<body>
	<table width="100%">
		<thead>
			<tr>
				<th>业务表单的ID</th>
				<th>单据状态</th>
				<th>审批人</th>
				<th>审批人角色（组）</th>
				<th>审批结果</th>
				<th>审批意见</th>
				<th>审批时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${workflowHistoryInfoHelps}" var="historicTaskinfo">
				<tr>
					<td>${historicTaskinfo.businessKey}</td>
					<td>${historicTaskinfo.taskName }</td>
					<td>${historicTaskinfo.approvers }:${historicTaskinfo.approverids }</td>
					<td>${historicTaskinfo.approversGroup }</td>
					<td><c:if test="${historicTaskinfo.approveResult eq 'true'}">同意</c:if>
						<c:if test="${historicTaskinfo.approveResult eq 'false'}">不同意</c:if>
						<c:if test="${historicTaskinfo.approveResult eq 'continue' }">继续审批</c:if>
					</td>
					<td>${historicTaskinfo.approveAdvice }</td>
					<td>${historicTaskinfo.approveTime }</td>
					<!-- <td><a target="_blank" href='<%=request.getContextPath() %>/workflow/graph?processInstanceId=${historicTaskinfo.processInstanceId }' title="点击查看流程图">流程图</a></td> -->
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<form
		action='<%=request.getContextPath() %>/workflow/task/complete/${taskId}'>
		${renderedTaskForm} <input type="submit" value="提交" />
	</form>
	<c:if test="${iscontinueenable eq 'true'}">
		<form
			action='<%=request.getContextPath() %>/workflow/task/completeNoForm/${taskId}'>
			<table align="center" border="1" cellpadding="0" cellspacing="2"
				width="100%" style="font-size: 10.5pt;">
				<tr>
					<td class="td_title" width="14%" rowspan="3" align="center">审批意见</td>
					<td><input type="radio" id="transition_pass" name="pass"
						value="continue" checked="checked" />继续审批</td>
				</tr>
				<tr>
					<td><textarea name="advice" rows="100"
							style="width: 100%; height: 150px; resize: none;">
									</textarea></td>
				</tr>
			</table>
			<input type="submit" value="继续审批" />
		</form>
	</c:if>

</body>
</html>