<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<header class="header"> <%@ include
		file="/WEB-INF/jspf/imgHead.jspf"%> <%@ include
		file="/WEB-INF/jspf/login.jspf"%> <%@ include
		file="/WEB-INF/jspf/menu/menu_2.jspf"%> </header>
	<div class="middle_1">
		<div id="p">
			<div class="p">
				<fmt:message key="tariff_jsp.title" />
			</div>
			<table class="info">
				<tr>
					<th><fmt:message key="tariff_jsp.id_tariff" /><a
						id="nondecoration" href="controller?command=sortId"><span
							class="tbs-icon">&nbsp;&nbsp;</span>&nbsp;</a></th>
					<th colspan="2"><fmt:message key="tariff_jsp.id_service" /></th>
					<th><fmt:message key="tariff_jsp.name" /><a
						id="nondecoration" href="controller?command=sortName"><span
							class="tbs-icon">&nbsp;&nbsp;</span>&nbsp;</a></th>
					<th><fmt:message key="tariff_jsp.description" /></th>
					<th><fmt:message key="tariff_jsp.cost" /><a
						id="nondecoration" href="controller?command=sortPrice"><span
							class="tbs-icon">&nbsp;&nbsp;</span>&nbsp;</a></th>
					<th id="round" colspan="2">&nbsp;</th>
				</tr>
				<c:forEach items="${tariffs}" var="tariffs">
					<tr>
						<td>${tariffs.id}</td>
						<td>${tariffs.idService}</td>
						<td id="info" onclick="window.location.href='controller?command=findServiceById&idService=${tariffs.idService}'">i</td>
						<td>${tariffs.name}</td>
						<td id="smallDesc">${tariffs.description}</td>
						<td>${tariffs.price}</td>
						<td><a class="mod"
							href="controller?command=changeTariff&idModify=${tariffs.id}">
								<img alt="Edit selected Tariff" src="image/edit.png" width="40" height="40" />
						</a></td>
						<td><a class="mod"
							href="controller?command=deleteTariff&idDelete=${tariffs.id}">
								<img alt="Delete selected Tariff" src="image/delete.png" width="40" height="40" />
						</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div id="p">
			<div class="p">
				<fmt:message key="tariff_jsp.new" />
			</div>
			<form action="controller" method="post">
				<table>
					<tr>
						<th colspan="3"><font class="log"> <b><fmt:message
									key="register_jsp.title" /></b></font></th>
					</tr>
					<c:if test="${not empty errorMessage_1}">
						<tr>
							<th colspan="3"><font id="log"> <b><c:out
										value="${errorMessage_1}" /></b></font></th>
						</tr>
					</c:if>
					<c:if test="${not empty message_1}">
						<tr>
							<th colspan="3"><font class="mesGreen"> <b><c:out
										value="${message_1}" /></b></font></th>
						</tr>
					</c:if>
					<tr>
						<td><fmt:message key="tariff_jsp.name" />:</td>
						<td><input id="tar_change" type="text" maxlength="45" name="name" autocomplete="off" /></td>
					</tr>
					<tr>
						<td><fmt:message key="tariff_jsp.cost" />:</td>
						<td><input id="tar_change" type="text" maxlength="45" name="price" autocomplete="off" /></td>
					</tr>
					<tr>
						<td><fmt:message key="tariff_jsp.id_service" />:</td>
						<td><input id="tar_change" type="text" maxlength="45" name="service" autocomplete="off" /></td>
					</tr>
					<tr>
						<td><fmt:message key="tariff_jsp.description" />:</td>
						<td><textarea name="description" rows="10" cols="55" autocomplete="off"></textarea></td>
					</tr>
					<tr>
						<td class="middle" colspan="3"><input type="hidden"
							name="command" value="newTariff" /> <input class="middle"
							type="submit" value="<fmt:message key="tariff_jsp.confirm" />" /></td>
					</tr>
				</table>
			</form>
		</div>
		<c:remove var="errorMessage_1" scope="session" />
		<c:remove var="message_1" scope="session" />
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</div>
</body>
</html>