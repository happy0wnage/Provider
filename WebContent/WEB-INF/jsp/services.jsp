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
			<div class="p"><fmt:message key="service_jsp.title" /></div>
			<c:if test="${not empty back}">
				<a class="goTo" href="controller?command=tariff">
					<fmt:message key="service_jsp.back" />
				</a>
			</c:if>
			
			<table class="info">
				<tr>
					<th><fmt:message key="service_jsp.id_service" /></th>
					<th><fmt:message key="service_jsp.name" /></th>
				</tr>
				<c:forEach items="${services}" var="services">
					<tr>
						<td>${services.id}</td>
						<td>${services.name}</td>
					</tr>
				</c:forEach>

			</table>
		</div>
		<c:remove var="back" scope="session"/>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</div>
</body>
</html>