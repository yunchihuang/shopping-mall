<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="/icd_meta.jsp" />
<title>结算</title>
<jsp:include page="/icd_link.jsp" />
</head>
<body>
	<jsp:include page="/icd_top.jsp"></jsp:include>

	<!-- 主内容 -->
	<div class="wrapper" style="min-height: 500px;">
		<div class="panel" style="margin-top: 20px">
			<div class="panel-heading" style="font-size: 18px">
				<strong>结算</strong>
			</div>
			<form action="${ctx}/payment" method="post">
				<div class="panel-body">
					<div id="addressBox">
						<div id="addressTitle">
							<strong>收货地址</strong> <a href="${ctx}/member/address/list">添加新地址</a>
						</div>
						<div id="addressList">
							<c:forEach items="${addressList}" var="ad">
								<div class="item">
									<span> <input type="radio"
										${ad.default_value ? 'checked="checked"' : ""}
										name="address_id" value="${ad.id}" />
									</span> <strong>${ad.contact}</strong> <span>${ad.mobile}</span> <span
										class="text-muted">${ad.street}</span> <span
										class="text-muted">${ad.zipcode}</span>
								</div>
							</c:forEach>
						</div>
					</div>

					<br /> <br />
					<div class="form-group">
						<label class="col-xs-1 control-label required">买家留言</label>
						<div class="col-xs-11">
							<input type="text" name="remark" id="remark" class="form-control" />
						</div>
					</div>
				</div>
				<div class="panel-footer text-right">
					选择了 <strong class="text-danger">${curr_order.total_amount}</strong>
					件商品， 共计：<strong class="text-danger"><fmt:formatNumber
							value="${curr_order.payment_price}" pattern="￥#,##0.00" /> </strong> <input
						type="submit" id="submit" class="btn btn-primary" value="去支付" />
				</div>
			</form>
		</div>
	</div>
	<!-- /主内容 -->

	<jsp:include page="/icd_bottom.jsp"></jsp:include>

	<script src="${ctx}/zui/lib/jquery/jquery.js"></script>
	<script src="${ctx}/js/jquery.scrollUp.min.js"></script>
	<script src="${ctx}/zui/js/zui.js"></script>
	<script src="${ctx}/js/my.js"></script>
	<script>
    countSum();
    </script>
</body>
</html>