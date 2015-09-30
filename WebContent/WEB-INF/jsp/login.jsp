<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<header class="header">
	<%@ include file="/WEB-INF/jspf/imgHead.jspf"%>
	<%@ include file="/WEB-INF/jspf/menu/menu_.jspf"%>
	</header>


	<div class="middleLogin">
		<div id="p">
			<table>
				<tr>
					<th colspan="2"><font class="log"> <b><fmt:message key="login_jsp.title" /></b></font></th>
				</tr>
				<form class="login" action="controller" method="post">
					<tr>
						<input type="hidden" name="command" value="login" />
						<td><fmt:message key="login_jspf.login" /> :</td>
						<td><input type="text" maxlength="16" name="login" /></td>
					</tr>
					<tr>
						<td><fmt:message key="login_jspf.password" /> :</td>
						<td><input type="password" maxlength="16" name="password" /></td>
					</tr>
					<tr>
						<td class="middle" colspan="2"><input class="middle"
							type="submit" value="<fmt:message key="login_jspf.sign_up" />" /></td>
					</tr>
				</form>

				<tr>
					<form id="line" action="controller" method="post">
						<input type="hidden" name="command" value="registerPage" />
						<td class="middle" colspan="2"><input class="middle"
							type="submit" value="<fmt:message key="login_jspf.register" />" /></td>
					</form>
				</tr>

			</table>
		</div>
	</div>
</body>
</html>