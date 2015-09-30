<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<header class="header"> <%@ include
		file="/WEB-INF/jspf/imgHead.jspf"%> <%@ include
		file="/WEB-INF/jspf/login.jspf"%> <%@ include
		file="/WEB-INF/jspf/menu/menu_1.jspf"%> </header>

	<div class="middle_2">

		<table class="main">
			<tr>
				<th
					onclick="window.location.href='controller?command=servicesCatalog'"><img
					src="image/net.png" width="50" align="middle" />&nbsp;&nbsp;&nbsp;<fmt:message
						key="index.services" /></th>
				<th onclick="window.location.href='controller?command=registerPage'"><img
					src="image/register.png" width="50" align="middle" />&nbsp;&nbsp;&nbsp;<fmt:message
						key="index.register" /></th>
				<th onclick="window.location.href='controller?command=about'"><img
					src="image/about.png" width="50" align="middle" />&nbsp;&nbsp;&nbsp;<fmt:message
						key="index.about" /></th>
			</tr>
			<tr>
				<td><fmt:message key="index.services.text" /></td>
				<td><fmt:message key="index.register.text" /></td>
				<td><fmt:message key="index.about.text" /></td>
			</tr>
		</table>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</div>

</body>
</html>