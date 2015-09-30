<%@ page pageEncoding="UTF-8"%>
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
				<fmt:message key="abonentAccount_jsp.info.status" />
			</div>
			<c:if test="${not empty errorMessage_1}">
				<p id="p_info_red">
					<font id="log"><b><c:out value="${errorMessage_1}" /></b></font>
				</p>

			</c:if>
			<b><fmt:message key="abonentAccount_jsp.info.contract_number" />:</b>
			<c:out value="${contract.number}" />
			<b><fmt:message key="abonentAccount_jsp.info.from" /></b>
			<fmt:formatDate var="from" value="${contract.startDate}"
				dateStyle="Full" />
			<c:out value="${from}" />
			<b><fmt:message key="abonentAccount_jsp.info.to" /></b>
			<fmt:formatDate var="to" value="${contract.endDate}" dateStyle="Full" />
			<c:out value="${to}" />
			<br /> <b><fmt:message key="abonentAccount_jsp.info.name" />:</b>
			<c:out value="${abonent.surname}" />
			<c:out value="${abonent.name}" />
			<br /> <b><fmt:message key="abonentAccount_jsp.info.birth" />:</b>
			<fmt:formatDate var="dob" value="${abonent.dob}" dateStyle="Full" />
			<c:out value="${dob}" />
			<br /> <b><fmt:message key="abonentAccount_jsp.info.address" />:</b>
			<c:out value="${abonent.address}" />
			<br />
			<hr />

			<c:if test="${countTariffs == '0'}">
				<table class="info">
					<tr>
						<th><fmt:message key="abonentAccount_jsp.payment.balance" /></th>
						<th><fmt:message key="abonentAccount_jsp.payment.status" /></th>
					</tr>
					<tr>
						<td><c:out value="${payment.balance}" /></td>
						<td><c:choose>
								<c:when test="${payment.status == 'true'}">
									<span class="trueIcon">&nbsp;&nbsp;</span>
								</c:when>
								<c:otherwise>
									<span class="falseIcon">&nbsp;&nbsp;</span>
								</c:otherwise>
							</c:choose></td>
					</tr>
				</table>
				<a class="goTo" href="controller?command=connection"> <b><fmt:message
							key="abonentAccount_jsp.error.connect" /></b>
				</a>

			</c:if>
			<c:if test="${countTariffs != '0'}">
				<table class="info">
					<tr>
						<th><fmt:message key="abonentAccount_jsp.payment.balance" /></th>
						<th><fmt:message key="abonentAccount_jsp.payment.status" /></th>
						<th><fmt:message
								key="abonentAccount_jsp.payment.end_date_service" /></th>
					</tr>
					<tr>
						<td><c:out value="${payment.balance}" /></td>
						<td><c:choose>
								<c:when test="${payment.status == 'true'}">
									<span class="trueIcon">&nbsp;&nbsp;</span>
								</c:when>
								<c:otherwise>
									<span class="falseIcon">&nbsp;&nbsp;</span>
								</c:otherwise>
							</c:choose></td>
						<td><fmt:formatDate var="end_date"
								value="${payment.endDateService}" dateStyle="Full" /> <c:out
								value="${end_date }" /></td>
					</tr>
				</table>

				<table class="info">
					<tr>
						<th><fmt:message
								key="abonentAccount_jsp.service_tariff.service" /></th>
						<th><fmt:message
								key="abonentAccount_jsp.service_tariff.tariff" /></th>
						<th><fmt:message
								key="abonentAccount_jsp.service_tariff.cost_uah" /></th>
					</tr>
					<c:forEach items="${serviceTariffContainer}" var="stc">
						<tr>
							<td><c:out value="${stc.serviceName}" /></td>
							<td><c:out value="${stc.tariffName}" /></td>
							<td><c:out value="${stc.tariffPrice}" /></td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="2" id="right"><fmt:message
								key="abonentAccount_jsp.service_tariff.total_price" /> :&nbsp;</td>
						<td><c:out value="${totalPrice}" /></td>
					</tr>
					<tr>
						<td colspan="2" id="greenPrice"><fmt:message
								key="abonentAccount_jsp.service_tariff.payment_for_day_uah" />:&nbsp;</td>
						<td><c:out value="${perDay}" /></td>
					</tr>

				</table>
			</c:if>
		</div>

		<c:if test="${countTariffs != '0'}">
			<div id="p">
				<div class="p">
					<fmt:message key="abonentAccount_jsp.pay.pay_online" />
				</div>
				<form action="controller" method="post">
					<table>
						<input type="hidden" name="command" value="pay" />
						<input type="hidden" name="idAbonent" value="${abonent.id}" />

						<c:if test="${not empty errorMessage}">
							<tr>
								<th colspan="3"><font id="log"> <b><c:out
											value="${errorMessage}" /></b></font></th>
							</tr>
						</c:if>
						<c:if test="${not empty message}">
							<tr>
								<th colspan="3"><font class="mesGreen"> <b><c:out
											value="${message}" /></b></font></th>
							</tr>
						</c:if>
						<tr>
							<td><b><fmt:message key="abonentAccount_jsp.pay.amount" />:</b></td>
							<td><input type="text" maxlength="7" name="amount"
								autocomplete="off" /></td>
							<td><input class="btn" type="submit"
								value="<fmt:message key="abonentAccount_jsp.pay.pay" />" /></td>
						</tr>
					</table>
				</form>
			</div>
		</c:if>

		<c:remove var="message" scope="session" />
		<c:remove var="errorMessage" scope="session" />
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</div>
</body>
</html>