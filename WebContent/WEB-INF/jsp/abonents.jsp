<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<header class="header"> <%@ include
		file="/WEB-INF/jspf/imgHead.jspf"%> <%@ include
		file="/WEB-INF/jspf/login.jspf"%> <%@ include
		file="/WEB-INF/jspf/menu/menu_abon.jspf"%> </header>
	<div class="middle_abon">


		<div id="p">
			<div class="p">
				<fmt:message key="abonents_jsp.title" />
			</div>
			<table>
				<tr>
					<form action="controller" method="get">
						<input type="hidden" name="command" value="findAbonent" />
						<td><fmt:message key="abonents_jsp.find_login" /> :</td>
						<td><input type="text" maxlength="16" name="login"
							autocomplete="off" /></td>
						<td><input class="btn" type="submit"
							value="<fmt:message key="abonents_jsp.find" />" /></td>
						<c:if test="${not empty clear}">
							<td class="btn" rowspan="2"
								onclick="window.location.href='controller?command=abonent'"><fmt:message
									key="abonents_jsp.clear" /></td>
						</c:if>
					</form>
				</tr>
				<tr>
					<form action="controller" method="get">
						<input type="hidden" name="command" value="findAbonentById" />
					<td><fmt:message key="abonents_jsp.find_id" /> :</td>
					<td><input type="text" maxlength="45" name="idAbonent"
						autocomplete="off" /></td>
					<td><input class="btn" type="submit"
						value="<fmt:message key="abonents_jsp.find" />" /></td>
					</form>
				</tr>
			</table>
			<hr />

			<table class="info">
				<tr>
					<th><fmt:message key="abonents_jsp.table.id_abonent" /></th>
					<th><fmt:message key="abonents_jsp.table.id_payment" /></th>
					<th><fmt:message key="abonents_jsp.table.login" /></th>
					<th><fmt:message key="abonents_jsp.table.password" /></th>
					<th><fmt:message key="abonents_jsp.table.name" /></th>
					<th><fmt:message key="abonents_jsp.table.surname" /></th>
					<th><fmt:message key="abonents_jsp.table.phone" /></th>
					<th><fmt:message key="abonents_jsp.table.dob" /></th>
					<th><fmt:message key="abonents_jsp.table.address" /></th>
					<th><fmt:message key="abonents_jsp.table.email" /></th>
				</tr>

				<fmt:formatDate var="dob" value="${abonent.dob}" dateStyle="Full" />
				<c:out value="${dob}" />

				<c:forEach items="${abonents}" var="abonents">
					<tr>
						<td>${abonents.id}</td>
						<td>${abonents.idPayment}</td>
						<td>${abonents.login}</td>
						<td>${abonents.password}</td>
						<td>${abonents.name}</td>
						<td>${abonents.surname}</td>
						<td>${abonents.contactPhone}</td>
						<td><fmt:formatDate var="dob" value="${abonents.dob}"
								dateStyle="Full" /> <c:out value="${dob}" /></td>
						<td>${abonents.address}</td>
						<td>${abonents.email}</td>
					</tr>
				</c:forEach>

			</table>
			<c:if test="${not empty payment}">
				<table class="info">
					<tr>
						<th><fmt:message key="abonents_jsp.table.payment.id_payment" /></th>
						<th><fmt:message key="abonents_jsp.table.payment.balance" /></th>
						<th><fmt:message key="abonents_jsp.table.payment.status" /></th>
						<th><fmt:message
								key="abonents_jsp.table.payment.end_date_service" /></th>
						<th><fmt:message
								key="abonents_jsp.table.payment.last_date_of_withdrawal" /></th>
						<th id="round"></th>
					</tr>
					<c:forEach items="${payment}" var="payment">
						<tr>
							<td>${payment.id}</td>
							<td>${payment.balance}</td>
							<td><c:choose>
									<c:when test="${payment.status == 'true'}">
										<span class="trueIcon">&nbsp;&nbsp;</span>
									</c:when>
									<c:otherwise>
										<span class="falseIcon">&nbsp;&nbsp;</span>
									</c:otherwise>
								</c:choose></td>
							<fmt:formatDate var="eds" value="${payment.endDateService}"
								dateStyle="Full" />
							<td><c:out value="${eds}" /></td>
							<fmt:formatDate var="ldw" value="${payment.lastDateOfWithdrawal}"
								dateStyle="Full" />
							<td><c:out value="${ldw}" /></td>
							<td id="smallTd"><a class="mod"
							href="controller?command=deactivate&idPayment=${payment.id}">
								<img alt="Deactivate selected user" src="image/deactivate.png" width="40" height="40" />
						</a></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</div>
</body>
</html>