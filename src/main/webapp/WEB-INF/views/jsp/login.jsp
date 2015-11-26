<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<tiles:insertDefinition name="advertiser_anonymous">
	<tiles:putAttribute name="js">
		<script type="text/javascript">
			function initialize(){
				$("#user_remember_me").attr("checked", true);
			}
			function passwordMisMatchErrorDisplay(repass, pass, errorMsg){
				if(pass.val()!=repass.val()){
					$("<span>"+errorMsg+"</span>").insertAfter(repass).toggleClass("error");
					repass.toggleClass("error");
					pass.toggleClass("error");
				}
			}
			function emptyErrorDisplay(elem, errorMsg){
				if(!elem.val()){
					$("<span>"+errorMsg+"</span>").insertAfter(elem).toggleClass("error");
					elem.toggleClass("error");
				}
			}
			$("#registerSubmit").click(function(event){
				event.preventDefault();
				$("span.error").remove();
				$(".error").toggleClass("error");
				emptyErrorDisplay($('#username'), "Please enter a username");
				emptyErrorDisplay($('#password'), "Password cannot be empty");
				passwordMisMatchErrorDisplay($('#password'), $('#repassword'), "Password mismatch");
				
				if($(".error").length == 0){
					$("#registerForm").submit();
				}
			});
			$(document).ready(function(){
				var debug = true;	
				initialize();
			});
		</script>
	</tiles:putAttribute>
	<tiles:putAttribute name="maincontent">

		<div class="container" role="main">
				<div class="row" style="margin-top:40px;">
					<div  style="float: left;width: 450px;margin-left: -150px;">
						<h3>Login</h3>
						<hr/>
						<form class="form-signin" role="form" id="login_form"
							action="<c:url value='/j_spring_security_check' />" method="post"
							accept-charset="UTF-8">
							<label for="username">Username</label> <br /> <input
								class="form-control" id="j_username" name="j_username"
								placeholder="Username" type="tel" required autofocus /> <br />
	
							<label for="password">Password</label> <input class="form-control"
								id="j_password" type="password" name="j_password"
								placeholder="Password" required />
							<c:if test="${not empty error}">
								<span class="error">Username or password doesn't match the
									records</span>
							</c:if>
							<!--- <label class="checkbox"> -->
							<br/>
							<input id="user_remember_me" type="checkbox"
								name="_spring_security_remember_me" value="1" /> Remember Me <br />
							<br/>
							<button class="btn btn-md btn-primary btn-block" id="loginSubmit"
								type="submit">Sign in</button>
						</form>
					</div>
					<div style="float: left; width: 450px; margin-left: 300px;">
						<h3>Register</h3>
						<hr/>
						<form role="form" id="registerForm"
							action="<c:url value='/register' />" method="post"
							accept-charset="UTF-8">
							<label for="username">UserId</label> 
							<br /> 
							<input
								class="form-control" id="username" name="username"
								placeholder="Username" required autofocus /> <br /> 
							<label for="password">Password</label> 
							<input class="form-control"
								id="password" type="password" name="password"
								placeholder="Password" required /> <br/>
							<label for="repassword">Retype Password
							</label> 
							<input class="form-control" id="repassword" type="password" name="repassword"
								placeholder="Retype password" required /> <br/>
							<button class="btn btn-md btn-primary btn-block" id="registerSubmit"
								type="submit">Register</button>
						</form>
					</div>
				</div>
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>
