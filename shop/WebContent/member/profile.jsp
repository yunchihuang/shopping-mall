<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="/icd_meta.jsp" />
<title>会员-个人资料</title>
<jsp:include page="/icd_link.jsp" />
</head>
<body>
	<jsp:include page="/icd_top.jsp"></jsp:include>

	<!-- 主内容 -->
	<div class="wrapper" style="min-height: 500px">
		<!--  -->
		<div class="row" style="padding: 20px 0px;">
			<!-- /左边 -->
			<div class="col-xs-2">
				<jsp:include page="/member/icd_menu.jsp">
					<jsp:param value="profile" name="tag" />
				</jsp:include>
			</div>
			<!-- /左边 -->

			<!-- 右边 -->
			<div class="col-xs-10">
				<div class="panel">
					<div class="panel-heading">
						<strong><i class="icon-user"></i> 修改资料</strong>
					</div>
					<div class="panel-body">
						<form action="${ctx}/member/profile" method="post"
							id="profileForm" role="form" class="form-horizontal">
							<div class="form-group">
								<label class="col-xs-2 control-label">当前会员</label>
								<div class="col-xs-4">${sessionScope.curr_mbr.mobile}</div>
							</div>
							<div class="form-group">
								<label class="col-xs-2 control-label">昵称</label>
								<div class="col-xs-4 required">
									<input type="text" name="nick_name" id="nick_name"
										value="${sessionScope.curr_mbr.nick_name}"
										class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-2 control-label">真实姓名</label>
								<div class="col-xs-4 required">
									<input type="text" name="real_name" id="real_name"
										value="${sessionScope.curr_mbr.real_name}"
										class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-2 control-label">性别</label>
								<div class="col-md-4">
									<c:if test="${sessionScope.curr_mbr.gender}">
										<label class="radio-inline"> <input type="radio"
											name="gender" value="true" checked="checked"> 男
										</label>
										<label class="radio-inline"> <input type="radio"
											name="gender" value="false"> 女
										</label>
									</c:if>
									<c:if test="${!sessionScope.curr_mbr.gender}">
										<label class="radio-inline"> <input type="radio"
											name="gender" value="true"> 男
										</label>
										<label class="radio-inline"> <input type="radio"
											name="gender" value="false" checked="checked"> 女
										</label>
									</c:if>
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-2 control-label">邮箱号</label>
								<div class="col-xs-4 required">
									<input type="text" name="email" id="email"
										value="${sessionScope.curr_mbr.email}" class="form-control" />
								</div>
							</div>

							<div class="form-group">
								<div class="col-xs-2"></div>
								<div class="col-xs-10">
									<button type="reset" id="resetBtn" class="btn btn-default">重置</button>
									&nbsp;&nbsp;
									<button type="submit" id="submitBtn" class="btn btn-primary"
										style="min-width: 80px">保存</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- /右边 -->
		</div>
	</div>
	<!-- /主内容 -->

	<jsp:include page="/icd_bottom.jsp"></jsp:include>

	<script src="${ctx}/zui/lib/jquery/jquery.js"></script>
	<script src="${ctx}/js/jquery.scrollUp.min.js"></script>
	<script src="${ctx}/zui/js/zui.js"></script>
	<script src="${ctx}/js/my.js"></script>
	<script>
    	$("#profileForm").submit(function(){

    		//==========使用jQuery来发送异步请求
    		var url = $(this).attr("action");
    		var param = $(this).serialize(); //获取表单中输入的数据
    		
    		$.post(url, param, function(txt){
    			if("ok" == txt){
    				//修改顶部的欢迎文本
    				$("#welcome").text("欢迎会员 " + $("#nick_name").val() + "！");
					    				
    				$.zui.messager.show('资料修改成功！', {type: 'success'});
    			}else{
    				$.zui.messager.show('资料修改失败！', {type: 'danger'});
    			}
    		});
    		
    		return false; //阻止表单默认的同步提交方式
    	});
    </script>
</body>
</html>