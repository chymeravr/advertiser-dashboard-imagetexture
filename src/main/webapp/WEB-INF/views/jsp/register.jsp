<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<tiles:insertDefinition name="advertiser_anonymous">
	<tiles:putAttribute name="js">
		<script type="text/javascript">
			$(document).ready(function(){
				var debug = true;			
				
				initialize();
				function initialize(){
					$("#user_remember_me").attr("checked", true);
				}
			});
		</script>
	</tiles:putAttribute>
	<tiles:putAttribute name="maincontent">

		<div class="container" role="main">
			<div class="row" id="maincontent">
				<div class="hidden-xs hidden-sm col-md-2 col-lg-3"></div>
				<div class="col-xs-12 col-sm-12 col-md-8 col-lg-6 no-padding">
					<div class="panel panel-primary">
						<div class="panel-heading">Register</div>
						<div class="panel-body">
							<form role="form" id="login_form"
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
									placeholder="Password" required /> 
								<label for="repassword">Retype Password
								</label> 
								<input class="form-control' id="
									repassword" type="password" name="repassword"
									placeholder="Retype password" required />
								<button class="btn btn-md btn-primary btn-block" id="sign_in"
									type="submit">Register</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
