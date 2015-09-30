<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<header class="header"> <%@ include
		file="/WEB-INF/jspf/imgHead.jspf"%> <%@ include
		file="/WEB-INF/jspf/menu/menu_.jspf"%> </header>

	<div class="middle_4">
		<form class="login" action="controller" method="post">
			<input type="hidden" name="command" value="register" />
			<table class="register">
				<tr>
					<th colspan="2"><font class="log"> <b><fmt:message
									key="register_jsp.title" /></b></font></th>
				</tr>
				<c:if test="${not empty errorMessage}">
					<tr>
						<th colspan="2"><font id="log"><b><c:out
										value="${errorMessage}" /></b></font></th>
					</tr>
				</c:if>
				<c:if test="${not empty message}">
					<tr>
						<th colspan="2"><font class="mesGreen"><b><c:out
										value="${message}" /></b></font></th>
					</tr>
				</c:if>
				<tr>
					<td id="text"><fmt:message key="register_jsp.login" />:</td>
					<td><input type="text" maxlength="16" value="${abonent.login}" name="login" /></td>
				</tr>
				<tr>
					<td colspan="2" id="text" class="help"><fmt:message
							key="register_jsp.login.help" /></td>
				</tr>
				<tr>
					<td id="text"><fmt:message key="register_jsp.password" /> :</td>
					<td><input type="password" maxlength="16" value="${abonent.password}" name="password" /></td>
				</tr>
				<tr>
					<td colspan="2" id="text" class="help"><fmt:message
							key="register_jsp.password.help" /></td>
				</tr>
				<tr>
					<td id="text"><fmt:message key="register_jsp.name" />:</td>
					<td><input type="text" maxlength="45" value="${abonent.name}" name="name" /></td>
				</tr>
				<tr>
					<td colspan="2" id="text" class="help"><fmt:message
							key="register_jsp.name.help" /></td>
				</tr>
				<tr>
					<td id="text"><fmt:message key="register_jsp.surname" />:</td>
					<td><input type="text" maxlength="45" value="${abonent.surname}" name="surname" /></td>
				</tr>
				<tr>
					<td colspan="2" id="text" class="help"><fmt:message
							key="register_jsp.surname.help" /></td>
				</tr>
				<tr>
					<td id="text"><fmt:message key="register_jsp.phone" />:</td>
					<td><input type="text" maxlength="45" value="${abonent.contactPhone}" name="phone" /></td>
				</tr>
				<tr>
					<td colspan="2" id="text" class="help"><fmt:message
							key="abonentProfile_jsp.phone.enter" /></td>
				</tr>

				<tr>
					<td id="text"><fmt:message key="register_jsp.dob" />:</td>
					<td><input type="text" maxlength="45" value="${abonent.dobS}"  name="dob" /></td>
				</tr>
				<tr>
					<td colspan="2" id="text" class="help"><fmt:message
							key="abonentProfile_jsp.dob.enter" /></td>
				</tr>
				<tr>
					<td id="text"><fmt:message key="register_jsp.address" />:</td>
					<td><input type="text" maxlength="45" value="${abonent.address}" name="address" /></td>
				</tr>
				<tr>
					<td colspan="2" id="text" class="help"><fmt:message
							key="abonentProfile_jsp.address.enter" /></td>
				</tr>
				<tr>
					<td id="text"><fmt:message key="register_jsp.email" />:</td>
					<td><input type="text" maxlength="45" value="${abonent.email}" name="email" /></td>
				</tr>
				<tr>
					<td colspan="2" id="text" class="help"><fmt:message
							key="abonentProfile_jsp.email.enter" /></td>
				</tr>
				<tr>
					<td class="middle" colspan="2"><input class="middle"
						type="submit" value="<fmt:message key="index.register" />" /></td>
				</tr>
			</table>
		</form>
		
		<c:remove var="message" scope="session" />
		<c:remove var="abonent" scope="session" />
		<c:remove var="errorMessage" scope="session" />
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</div>
</body>
</html>