<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="http://ua.nure.petrov.SummaryTask4/myTags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<font class="log"> <fmt:message key="login_jspf.login_as" /> :
</font>
<b> <fmt:message key="login_jspf.login" /> :
</b>
<c:out value="${user}" />
<b> <fmt:message key="login_jspf.role" /> :
</b>
<c:out value="${userRole}" />
[
<a href="controller?command=logout"> <fmt:message
		key="login_jspf.exit" />
</a>
]
