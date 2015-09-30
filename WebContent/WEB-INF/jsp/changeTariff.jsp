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
		<form class="login" action="controller" method="post">
			<table>
				<tr>
					<th colspan="2"><font class="log"> <b><fmt:message
								key="changeTariff_jsp.title" /></b></font></th>
				</tr>
				<c:if test="${not empty errorMessage}">
					<tr>
						<th colspan="2"><font id="log"> <b><c:out
									value="${errorMessage}" /></b></font></th>
					</tr>
				</c:if>
				<tr>
					<td><fmt:message key="changeTariff_jsp.name" />:</td>
					<td><input id="tar_change" type="text" maxlength="45" name="name"
						value="${tariff.name}" autocomplete="off" /></td>
				</tr>
				<tr>
					<td><fmt:message key="changeTariff_jsp.price" />:</td>
					<td><input id="tar_change" type="text" maxlength="45" name="price"
						value="${tariff.price}" autocomplete="off" /></td>
				</tr>
				<tr>
					<td><fmt:message key="changeTariff_jsp.description" />:</td>
					<td><textarea name="description" rows="10" cols="55"
							autocomplete="off">${tariff.description}</textarea>
				</tr>
				<tr>
					<td class="middle" colspan="2"><input type="hidden"
						name="idModify" value="${tariff.id}" /> <input type="hidden"
						name="command" value="modify" /> <input class="middle"
						type="submit"
						value="<fmt:message key="changeTariff_jsp.confirm" />" /></td>
				</tr>
			</table>
		</form>
		<c:remove var="errorMessage" scope="session" />
	</div>


</body>
</html>