<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/Dtd/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<header class="header"> <%@ include
		file="/WEB-INF/jspf/imgHead.jspf"%> <%@ include
		file="/WEB-INF/jspf/login.jspf"%> <%@ include
		file="/WEB-INF/jspf/menu/menu_3.jspf"%> </header>
	<div class="middle_1">

		<div id="p">
			<div class="p"><fmt:message	key="connection_jsp.title" /></div>

			<p id="p_info"><fmt:message key="connection_jsp.title.text" /></p>

			<c:if test="${not empty message}">
				<p id="p_info_green">
					<font class="mesGreen"><b><c:out value="${message}" /></b></font>
				</p>
				<a class="goTo" href="controller?command=abonent_account">
					<fmt:message key="connection_jsp.link" />
				</a>
			</c:if>
			<c:if test="${not empty errormessage}">
				<p id="p_info_red">
					<font id="log"><b><c:out value="${errormessage}" /></b></font>
				</p>

			</c:if>
			<hr />
			<br />
			<c:forEach var="entry" items="${servicetariff}">
				<table class="tariff">
					<tr>
						<th class="key" colspan="4">${entry.key}</th>
					</tr>
					<c:forEach var="value" items="${entry.value}">
						<tr>
							<td id="name">${value.name}</td>
							<td>${value.description}</td>
							<td class=tariff>${value.price} UAH <br />
								<form id="line" action="controller" method="get">
									<input type="hidden" name="command" value="connectChoice" /> <input
										type="hidden" name="idTariff" value="${value.id}" /> <input
										class="tariffBtn" type="submit" value="<fmt:message key="connection_jsp.select" />" />
								</form></td>
						</tr>
					</c:forEach>
				</table>
				<br/>
			</c:forEach>
		</div>
		<c:remove var="message" scope="session" />
		<c:remove var="errormessage" scope="session" />
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</div>
</body>
</html>