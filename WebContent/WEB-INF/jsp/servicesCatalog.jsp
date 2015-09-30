<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/Dtd/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<header class="header">
	<%@ include file="/WEB-INF/jspf/imgHead.jspf"%>
	<%@ include file="/WEB-INF/jspf/login.jspf"%> <%@ include
		file="/WEB-INF/jspf/menu/menu_4.jspf"%> </header>
	<div class="middle_1">

		<div id="p">
			<div class="p"><fmt:message key="serviceCatalog_jsp.title" />&nbsp;&nbsp;&nbsp;<a href="controller?command=savePdf"><img src="image/save_as.png" alt="" width="20" height="20"/></a></div>
			

			<c:forEach var="entry" items="${servicetariff}">
				<table class="tariff">
					<tr>
						<th class="key" colspan="4">${entry.key}</th>
					</tr>
					<c:forEach var="value" items="${entry.value}">
						<tr>
							<td id="name">${value.name}</td>
							<td>${value.description}</td>
							<td class=tariff_3>${value.price} UAH <br />
						</tr>
					</c:forEach>
				</table>
				<br />
			</c:forEach>
		
		</div>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</div>
</body>
</html>