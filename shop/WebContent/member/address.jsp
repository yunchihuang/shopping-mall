<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="/icd_meta.jsp" />
<title>会员-地址管理</title>
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
					<jsp:param value="address" name="tag" />
				</jsp:include>
			</div>
			<!-- /左边 -->

			<!-- 右边 -->
			<div class="col-xs-10">
				<div class="panel">
					<div class="panel-heading">
						<strong><i class="icon-map-marker"></i> 地址管理</strong>
						<div class="panel-actions">
							<button class="btn btn-primary" data-toggle="modal"
								data-target="#addAddressModal">添加新地址</button>
						</div>
					</div>
					<table class="table table-hover table-striped tablesorter">
						<thead>
							<tr class="text-center">
								<td style="width: 80px">收件人</td>
								<td style="width: 110px">电话</td>
								<td>详情地址</td>
								<td style="width: 70px">邮编</td>
								<td style="width: 80px">默认地址</td>
								<td style="width: 170px">操作</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="address">
								<tr class="text-center">
									<td>${address.contact}</td>
									<td class="text-left">${address.mobile}</td>
									<td class="text-left">${address.street}</td>
									<td>${address.zipcode}</td>
									<td>${address.default_value ? "是" : "否"}</td>
									<td><a
										href="${ctx}/member/address/default?id=${address.id}"
										${address.default_value ? 'class="disabled"' : ""}>设为默认</a>&nbsp;&nbsp;
										<a href="${ctx}/member/address/update?id=${address.id}"
										class="editHref">编辑</a>&nbsp;&nbsp; <a
										href="${ctx}/member/address/delete?id=${address.id}"
										class="deleteHref">删除</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<!-- /右边 -->
		</div>
	</div>
	<!-- /主内容 -->

	<jsp:include page="/icd_bottom.jsp"></jsp:include>

	<!-- 新增地址的对话框 -->
	<div class="modal fade" id="addAddressModal">
		<div class="modal-dialog">
			<form action="${ctx}/member/address/add" method="post"
				id="addressForm" class="form-horizontal">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
						</button>
						<h4 class="modal-title">新增地址</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label class="col-xs-2 control-label">收货人</label>
							<div class="col-xs-4 required">
								<input type="text" name="contact" id="contact"
									placeholder="收货人姓名" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-2 control-label">电话</label>
							<div class="col-xs-4 required">
								<input type="text" name="mobile" id="mobile" placeholder="手机号"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-2 control-label">地址</label>
							<div class="col-xs-9 required">
								<input type="text" name="street" id="street" placeholder="详细地址"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-2 control-label">邮编</label>
							<div class="col-xs-4 required">
								<input type="text" name="zipcode" id="zipcode"
									placeholder="邮政编码" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-2 control-label">默认地址</label>
							<div class="col-xs-4">
								<label class="radio-inline"><input type="radio"
									name="default_value" value="true" checked="checked" /> 是 </label> <label
									class="radio-inline"><input type="radio"
									name="default_value" value="false" /> 否 </label>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="reset" id="resetBtn" class="btn btn-default">重置</button>
						<button type="submit" id="submitBtn" class="btn btn-primary"
							style="min-width: 80px">保存</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- /新增地址的对话框 -->

	<!-- 编辑地址的对话框 -->
	<div class="modal fade" id="editAddressModal">
		<div class="modal-dialog">
			<form id="editAddressForm" action="" method="post"
				class="form-horizontal">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
						</button>
						<h4 class="modal-title">编辑地址信息</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label class="col-xs-2 control-label">收货人</label>
							<div class="col-xs-4 required">
								<input type="text" name="contact" id="contact"
									placeholder="收货人姓名" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-2 control-label">电话</label>
							<div class="col-xs-4 required">
								<input type="text" name="mobile" id="mobile" placeholder="手机号"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-2 control-label">地址</label>
							<div class="col-xs-9 required">
								<input type="text" name="street" id="street" placeholder="详细地址"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-2 control-label">邮编</label>
							<div class="col-xs-4 required">
								<input type="text" name="zipcode" id="zipcode"
									placeholder="邮政编码" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-2 control-label">默认地址</label>
							<div class="col-xs-4">
								<label class="radio-inline"><input type="radio"
									name="default_value" value="true" /> 是 </label> <label
									class="radio-inline"><input type="radio"
									name="default_value" value="false" /> 否 </label>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="reset" id="resetBtn" class="btn btn-default">重置</button>
						<button type="submit" id="submitBtn" class="btn btn-primary"
							style="min-width: 80px">保存</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- /编辑地址的对话框 -->

	<!-- 删除的提示对话框 -->
	<div class="modal fade" id="deleteModal">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">不可恢复删除，你确定吗？</h4>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" id="doDelete" class="btn btn-primary"
						style="min-width: 80px">确定</button>
				</div>
			</div>
		</div>
	</div>
	<!-- /删除的提示对话框  -->


	<script src="${ctx}/zui/lib/jquery/jquery.js"></script>
	<script src="${ctx}/js/jquery.scrollUp.min.js"></script>
	<script src="${ctx}/zui/js/zui.js"></script>
	<script src="${ctx}/js/my.js"></script>
	<script>
        /***********处理删除的问题*************/
        var url = null;
    	$(".deleteHref").click(function(){
    		//alert("(^_^)");
    		
    		//打开对话框
    		$('#deleteModal').modal('show');
    		
    		//获取当前超链接的地址
    		url = $(this).attr("href");
    		
    		return false;
    	});
    	
    	$("#doDelete").click(function(){
    		//执行操作
    		if(url){
    			location.assign(url);
    		}
    		$('#deleteModal').modal('hide');
    		
    		return false;
    	});
    	
    	/************处理编辑的问题************/
    	$(".editHref").click(function(){
    		var url = $(this).attr("href");
    		
    		//获取当前行的数据
    		var $tds = $("td", $(this).closest("tr"));
    		var contact = $($tds[0]).text();
    		var mobile = $($tds[1]).text();
    		var street = $($tds[2]).text();
    		var zipcode = $($tds[3]).text();
    		var dv = $($tds[4]).text() == '是' ? true : false;
    		
    		$("input[name='contact']", "#editAddressForm").val(contact);
    		$("input[name='mobile']", "#editAddressForm").val(mobile);
    		$("input[name='street']", "#editAddressForm").val(street);
    		$("input[name='zipcode']", "#editAddressForm").val(zipcode);
    		if(dv){
    			$("input[value='true']", "#editAddressForm").prop("checked", true);
    		}else{
    			$("input[value='false']", "#editAddressForm").prop("checked", true);
    		}
    		$("#editAddressForm").attr("action", url);
    		
    		//处理“重置”操作
    		$("#resetBtn", "#editAddressForm").unbind().click(function(){
    			$("input[name='contact']", "#editAddressForm").val(contact);
        		$("input[name='mobile']", "#editAddressForm").val(mobile);
        		$("input[name='street']", "#editAddressForm").val(street);
        		$("input[name='zipcode']", "#editAddressForm").val(zipcode);
        		if(dv){
        			$("input[value='true']", "#editAddressForm").prop("checked", true);
        		}else{
        			$("input[value='false']", "#editAddressForm").prop("checked", true);
        		}
        		
        		return false;
    		});
    		
    		//打开编辑的对话框
    		$('#editAddressModal').modal('show');
    		
    		return false;
    	});
    </script>
</body>
</html>