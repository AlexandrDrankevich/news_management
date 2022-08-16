<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="form-header">
	<h1>Registration</h1>
</div>
<div class="reg-form-body">
	<form action="controller" method="post">
		<input type="hidden" name="command" value="do_registration">
		<div>
			<label for="name">Name </label> 
			<input type="text" name="name" id="name" class="form-control" value="" required pattern="[A-Z a-z]+" />
		</div>
		<br />
		<div>
			<label for="surname">Surname</label> 
			<input type="text" name="surname" id="surname" class="form-control" value="" required
				pattern="[A-Z a-z]+" />
		</div>
		<br />
		<div>
			<label for="login">Login(Email) <font color="red">
			<c:out	value="${param.massage}">
					</c:out> </font>
			</label> <input type="text" name="login" id="login" class="form-control"
				value="" required pattern="[a-z 0-9]+@[a-z]+.[a-z]{2,3}" />
		</div>
		<br />
		<div>
			<label for="birthday">Birthday</label> <input type="date"
				name="birthday" id="birthday" class="form-control" min="1900-01-01" max="2030-01-01" required />
		</div>
		<br />
		<div>
			<label for="password">Password</label> 
			<input type="password" name="password" id="password" class="form-control" value="" required
				pattern="[A-Z a-z 0-9]+" maxlength="10" />
		</div>
		<br />
		<div>
			<input type="submit" class="btn" value="Registration" />
		</div>
	</form>
</div>
