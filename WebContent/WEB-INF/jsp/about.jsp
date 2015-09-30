<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<header class="header"> <%@ include
		file="/WEB-INF/jspf/imgHead.jspf"%> <%@ include
		file="/WEB-INF/jspf/login.jspf"%> <%@ include
		file="/WEB-INF/jspf/menu/menu_about.jspf"%> </header>
	<div class="middle_1">

		<div id="p">
			<div class="p">
				<fmt:message key="about_jsp.title" />
			</div>
			<fmt:message key="about_jsp.title.text" />
			<br />
			<hr />
			<fmt:message key="about_jsp.title.text.help" />:

			<c:forEach items="${admins}" var="admins">
				<br />
				<b>${admins.contactPhone}</b>
			</c:forEach>
			<br /> <fmt:message key="about_jsp.title.text.help_email" />:
			<c:forEach items="${admins}" var="admins">
				<br />
				<b>${admins.email}</b>
			</c:forEach>

		</div>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</div>
</body>
</html>