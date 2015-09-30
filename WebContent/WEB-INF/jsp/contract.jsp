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
				<fmt:message key="contract_jsp.title" />
			</div>
			<table class="info">
				<tr>
					<th><fmt:message key="contract_jsp.id_contract" /></th>
					<th><fmt:message key="contract_jsp.id_tariff" /></th>
					<th id="round"></th>
				</tr>
				<c:forEach items="${tariffContract}" var="tariffContract">
					<tr>
						<td>${tariffContract.idContract}</td>
						<td>${tariffContract.idTariff}</td>
						<td id="smallTd"><a class="mod"
							href="controller?command=removeService&idTariff=${tariffContract.idTariff}&idContract=${tariffContract.idContract}">
								<img src="image/delete.png" width="40" height="40" />
						</a></td>
					</tr>
				</c:forEach>
			</table>

			<fmt:formatDate var="dob" value="${abonent.dob}" dateStyle="Full" />
			<c:out value="${dob}" />

			<table class="info">
				<tr>
					<th><fmt:message key="contract_jsp.id_contract" /></th>
					<th colspan="2"><fmt:message key="contract_jsp.id_abonent" /></th>
					<th><fmt:message key="contract_jsp.number" /></th>
					<th><fmt:message key="contract_jsp.start" /></th>
					<th><fmt:message key="contract_jsp.end" /></th>
				</tr>
				<c:forEach items="${contracts}" var="contracts">
					<tr>
						<td>${contracts.id}</td>
						<td>${contracts.idAbonent}</td>
						<td id="info" onclick="window.location.href='controller?command=findAbonentById&idAbonent=${contracts.idAbonent}'">i</td>
						<td>${contracts.number}</td>
						<fmt:formatDate var="start" value="${contracts.startDate}" dateStyle="Full" />
						<td><c:out value="${start}" /></td>
						<fmt:formatDate var="end" value="${contracts.endDate}"
							dateStyle="Full" />
						<td><c:out value="${end}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</div>


</body>
</html>