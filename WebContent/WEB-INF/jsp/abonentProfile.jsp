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
				<fmt:message key="abonentProfile_jsp.title" />
			</div>
			<form action="controller" method="post">
				<input type="hidden" name="command" value="changeAbonent" />
				<input type="hidden" name="idAbonent" value="${abonent.id}" />

				<table class="register">
					<c:if test="${not empty message}">
						<tr>
							<th colspan="2"><font class="mesGreen"> <b><c:out
										value="${message}" /></b></font></th>
						</tr>
					</c:if>
					<c:if test="${not empty errorMessage}">
						<tr>
							<th colspan="2"><font id="log"> <b><c:out
										value="${errorMessage}" /></b></font></th>
						</tr>
					</c:if>

					<tr>
						<td id="text"><fmt:message key="abonentProfile_jsp.email" />* :</td>
						<td><input type="text" maxlength="45" name="email"
							value="${abonent.email}" /></td>
					</tr>
					<tr>
						<td colspan="2" id="text" class="help"><fmt:message
								key="abonentProfile_jsp.email.enter" /></td>
					</tr>
					<tr>
						<td id="text"><fmt:message key="abonentProfile_jsp.phone"/>*&nbsp;:</td>
						<td><input type="text" maxlength="45" name="phone"
							value="${abonent.contactPhone}" /></td>
					</tr>
					<tr>
						<td colspan="2" id="text" class="help"><fmt:message
								key="abonentProfile_jsp.phone.enter" /></td>
					</tr>

					<tr>
						<td id="text"><fmt:message key="abonentProfile_jsp.dob" />* :</td>
						<td><input type="text" maxlength="45" name="dob"
							value="${abonent.dob}" /></td>
					</tr>

					<tr>
						<td id="text" colspan="2" class="help"><fmt:message
								key="abonentProfile_jsp.dob.enter" /></td>
					</tr>

					<tr>
						<td id="text"><fmt:message key="abonentProfile_jsp.address" />* :</td>
						<td><input type="text" maxlength="45" name="address"
							value="${abonent.address}" /></td>
					</tr>
					<tr>
						<td colspan="2" id="text" class="help"><fmt:message
								key="abonentProfile_jsp.address.enter" /></td>
					</tr>
					<tr>
						<td colspan="2" class="middle"><input class="middle" type="submit"
							value="<fmt:message key="abonentProfile_jsp.password.save" />" /></td>
					</tr>
				</table>

			</form>
		</div>

		<div id="p">
			<div class="p">
				<fmt:message key="abonentProfile_jsp.password" />
			</div>
			<form action="controller" method="get">
				<input type="hidden" name="command" value="changeAbonentPassword" />
				<input type="hidden" name="idAbonent" value="${abonent.id}" />

				<c:if test="${not empty message_1}">
					<font class="mesGreen"> <b><c:out value="${message_1}" /></b></font>
				</c:if>
				<c:if test="${not empty errorMessage_1}">
					<font id="log"> <b><c:out value="${errorMessage_1}" /></b></font>
				</c:if>

				<table>

					<tr>
						<td><fmt:message key="abonentProfile_jsp.password.current" />
							:</td>
						<td><input type="password" maxlength="16" name="curPass" /></td>

					</tr>

					<tr>
						<td><fmt:message
								key="abonentProfile_jsp.password.new_password" /> :</td>
						<td><input type="password" maxlength="16" name="newPass" /></td>
					</tr>
					<tr>
						<td colspan="2"><input class="middle" type="submit"
							value="<fmt:message key="abonentProfile_jsp.password.save" />" /></td>
					</tr>
				</table>

			</form>
		</div>
		<c:remove var="message" scope="session" />
		<c:remove var="message_1" scope="session" />
		<c:remove var="errorMessage" scope="session" />
		<c:remove var="errorMessage_1" scope="session" />
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</div>


</body>
</html>