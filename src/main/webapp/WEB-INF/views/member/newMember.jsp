<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
		<title>JSP Page</title>
		<link href="<%= application.getContextPath() %>/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script src="<%= application.getContextPath() %>/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="<%= application.getContextPath() %>/resources/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
		
		<script type="text/javascript">
			function handlejoin(){
				var form = $("#form");
				var mid = $("#mid").val();
				var mname = $("#mname").val();
				var mpassword = $("#mpassword").val();
				var mage = $("#mage").val();
				
				
				if(mid==""){
					$("#mid").attr("placeholder", "아이디를 입력하셔야 합니다.");
					$("#mid").css("border-color", "red");
					return;
				}else if(mname==""){
					$("#mname").attr("placeholder", "이름을 입력하셔야 합니다.");
					$("#mname").css("border-color", "red");
					return;
				}else if(mpassword==""){
					$("#mpassword").attr("placeholder", "비밀번호를 입력하셔야 합니다.");
					$("#mpassword").css("border-color", "red");
					return;
				}else if(mtel==""){
					$("#mage").attr("placeholder", "폰번호를 입력하셔야 합니다.");
					$("#mage").css("border-color", "red");
					return;
				}else if(mbirth==""){
					$("#mage").attr("placeholder", "생년월일을 입력하셔야 합니다.");
					$("#mage").css("border-color", "red");
					return;
				}else if(memail==""){
					$("#mage").attr("placeholder", "이메일를 입력하셔야 합니다.");
					$("#mage").css("border-color", "red");
					return;
				}else{
					form.submit();
				}
			}
		</script>
		
	</head>
<body>
<h3 style="text-align: center; margin-right: 20%">가입해주셔서 감사합니다.</h3>
<h5 style="text-align: right; margin-right: 20%">*값은 필수 값입니다.</h5><br/>
<form method="post" id="form" style="padding: 20px 80px; width: 80%; text-align: center;" enctype="multipart/form-data">
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-user"></span>
					</span>
					<input type="text" class="form-control" placeholder="*아이디" name="mid" id="mid"/>
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-lock"></span>
					</span>
					<input type="password" class="form-control" placeholder="*비밀번호" name="mpassword" id="mpassword"/>
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-tag"></span>
					</span>
					<input type="text" class="form-control" placeholder="*이름" name="mname" id="mname"/>
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-tag"></span>
					</span>
					<input type="date" class="form-control" placeholder="*생년월일" name="mbirth" id="mbirth"/>
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-phone-alt"></span>
					</span>
					<input type="text" class="form-control" placeholder="*핸드폰번호" name="mtel" />
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-envelope"></span>
					</span>
					<input type="text" class="form-control" placeholder="*이메일" name="memail" />
				</div>
			</div>	
			
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-home"></span>
					</span>
					<input type="text" class="form-control" placeholder="주소" name="maddress" />
				</div>
			</div>	
						
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-picture"></span>
					</span>
					<input type="file" class="form-control" placeholder="선택" name="mattach" />
				</div>
			</div>

			<input onclick="handlejoin()" type="button" class="btn btn-info" value="가입 완료" />
		</form>
</body>
</html>