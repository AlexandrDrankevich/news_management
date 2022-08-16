<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="body-title">
	<a href="controller?command=go_to_news_list">News >> </a> Edit News
</div>
<br />
<div align="center">
	<form action="controller" method="post">
		<input type="hidden" name="command" value="do_edit_news" /> <input
			type="hidden" name="id" value="${news.idNews}" />
		<div>
			<label for=title>Title&nbsp</label> <input type="text" name="title"
				id="title" class="form-addnews" value="${news.title}"
				maxlength="100" required />
		</div>
		<br />
		<div>
			<label for=date>Date &nbsp</label> <input type="date" name="date"
				id="date" class="form-addnews" min="2022-01-01" max="2030-01-01" required />
		</div>
		<br /> 
			<p class="formfield">
			<label for="brief">Brief &nbsp</label>
			 <textarea  name="brief" id="brief" class="form-addnews" maxlength="500">${news.briefNews}</textarea>
		</p>
		<br /> 
		<p class="formfield">
			<label for="content">Content </label>
			<textarea name="content" id="content" class="form-content" maxlength="2048">${news.content}</textarea>
		<p />
		<br />
		<div class="first-view-button">
			<input type="submit" value="SAVE" />
		</div>
	</form>
</div>
<c:if test="${not (param.editView eq 'active')}">
	<div class="second-view-button">
		<a href="controller?command=go_to_news_list"><input type="submit"
			value="CANCEL" /></a>
	</div>
</c:if>
<c:if test="${param.editView eq 'active'}">
	<div class="second-view-button">
		<a href="controller?command=go_to_view_news&id=${news.idNews}"><input
			type="submit" value="CANCEL" /></a>
	</div>
</c:if>
</div>


