<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div align="left">
	<form action="controller" method="post">
		<input type="hidden" name="command" value="do_edit_news" />
		<input type="hidden" name="id" value="${news.idNews}" />
		<div>
			<label for=title>Title </label>
			 <input type="text" name="title" id="title" class="form-addnews" value="${news.title}"  required />
		</div>
		<br /> 
			<div>
			<label for="brief">Brief </label>
			 <input type="text" name="brief" id="brief" class="form-addnews" value="${news.briefNews}" required />
		</div>
		<br /> 
		<div>
			<label for="content">Content </label>
			 <textarea  name="content" id="content" class="form-content" required >${news.content}</textarea>
		</div>
		<br /> 
		<div >
		<input  type="submit"  value="Edit news" />
		</div>
	</form>
</div>
