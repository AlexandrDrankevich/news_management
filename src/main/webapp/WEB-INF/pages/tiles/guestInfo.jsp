<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.loclink.name.news"
	var="news" />
<fmt:message bundle="${loc}" key="local.loclink.name.latest_news"
	var="latest_news" />
<fmt:message bundle="${loc}" key="local.loctitle.name.no_news"
	var="no_news" />
<div class="body-title">
	<a href="">${news} >> </a>${latest_news}
</div>

	<c:forEach var="news" items="${requestScope.news}">
		<div class="single-news-wrapper">
			<div class="single-news-header-wrapper">
				<div class="news-title">
					<c:out value="${news.title}" />
				</div>
				<div class="news-date">
					<c:out value="${news.newsDate}" />
				</div>

				<div class="news-content">
					<c:out value="${news.briefNews}" />
				</div>
			</div>
		</div>

	</c:forEach>

	<div class="no-news">
		<c:if test="${requestScope.news eq null}">
        ${no_news}
	</c:if>
	</div>


