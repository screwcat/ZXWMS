<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page
	import="java.util.*,org.apache.commons.lang3.StringUtils,org.apache.commons.lang3.ObjectUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>流程列表</title>
</head>
<body>
	部署新流程
	<div>
		<b>支持文件格式：</b>zip、bar、bpmn、bpmn20.xml
	</div>
	<form action="<%=request.getContextPath() %>/workflow/deploy"
		method="post" enctype="multipart/form-data">
		<input type="file" name="file" /> <input type="submit" value="Submit" />
	</form>
	<%--<form action="<%=request.getContextPath()%>/workflow/startP" method="post">
		<input type="submit" value="发起流程" />--%>
	</form>
	<table width="100%" class="need-border">
		<thead>
			<tr>
				<th>ProcessDefinitionId</th>
				<th>DeploymentId</th>
				<th>名称</th>
				<th>KEY</th>
				<th>版本号</th>
				<th>XML</th>
				<th>图片</th>
				<th>部署时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result }" var="object">
				<c:set var="process" value="${object[0] }" />
				<c:set var="deployment" value="${object[1] }" />
				<tr>
					<td>${process.id }</td>
					<td>${process.deploymentId }</td>
					<td>${process.name }</td>
					<td>${process.key }</td>
					<td>${process.version }</td>
					<td><a target="_blank"
						href='<%=request.getContextPath() %>/workflow/resource/read?processDefinitionId=${process.id}&resourceType=xml'>${process.resourceName }</a></td>
					<td><a target="_blank"
						href='<%=request.getContextPath() %>/workflow/resource/read?processDefinitionId=${process.id}&resourceType=image'>${process.diagramResourceName }</a></td>
					<td>${deployment.deploymentTime }</td>
					<td><a
						href='<%=request.getContextPath() %>/workflow/process/delete?deploymentId=${process.deploymentId}'>删除</a>
						<!-- <a href='<%=request.getContextPath() %>/workflow/process/convert-to-model/${process.id}'>转换为Model</a> -->
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<tags:pagination page="${page}" paginationSize="${page.pageSize}" />
</body>
</html>