<div align="left">
	<form action="controller" method="post">
		<input type="hidden" name="command" value="do_add_news" />
		<div>
			<label for=title>Title </label>
			 <input type="text" name="title" id="title" class="form-addnews" value="" required />
		</div>
		<br /> 
			<div>
			<label for="brief">Brief </label>
			 <input type="text" name="brief" id="brief" class="form-addnews" value="" required />
		</div>
		<br /> 
		<div>
			<label for="content">Content </label>
			 <textarea  name="content" id="content" class="form-content" required ></textarea>
		</div>
		<br /> 
		<div >
		<input  type="submit"  value="Add news" />
		</div>
	</form>
</div>


