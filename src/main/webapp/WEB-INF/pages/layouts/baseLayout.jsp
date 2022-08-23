<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.loctitle.name.welcome"
	var="welcome" />
<fmt:message bundle="${loc}" key="local.loctitle.name.management"
			 var="management" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${management}</title>

<link rel="stylesheet" type="text/css" href="styles/newsStyle.css">

</head>
<body>
	<div class="page">
		<div class="header">
			<c:import url="/WEB-INF/pages/tiles/header.jsp" />
		</div>

		<div class="base-layout-wrapper">
			<div class="menu">

				<c:if test="${not (sessionScope.user eq 'active')}">
				   ${welcome}
					<br/>

					 	</c:if>
				<c:if test="${sessionScope.user eq 'active'}">
					<c:import url="/WEB-INF/pages/tiles/menu.jsp" />
				</c:if>
			</div>

			<div class="content">
				<c:if test="${not (param.reg eq 'reg')}">
					<c:if test="${not (sessionScope.user eq 'active')}">
						<c:import url="/WEB-INF/pages/tiles/guestInfo.jsp" />
					</c:if>
					<c:if test="${sessionScope.user eq 'active'}">
						<c:import url="/WEB-INF/pages/tiles/body.jsp" />
					</c:if>
				</c:if>
				<c:if test="${param.reg eq 'reg'}">
					<c:import url="/WEB-INF/pages/tiles/registration.jsp" />
				</c:if>
				<c:if test="${addnews eq 'active'}">
					<c:import url="/WEB-INF/pages/tiles/addNews.jsp" />
				</c:if>
				<c:if test="${editnews eq 'active'}">
					<c:import url="/WEB-INF/pages/tiles/editNews.jsp" />
				</c:if>

			</div>
		</div>

		<div class="footer">

			<c:import url="/WEB-INF/pages/tiles/footer.jsp" />
		</div>
	</div>
</body>
</html>