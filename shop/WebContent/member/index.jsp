<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.itvk.cn/jsp/tags" prefix="q"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="/icd_meta.jsp" />
<title>会员首页</title>
<jsp:include page="/icd_link.jsp" />
</head>
<body>
	<jsp:include page="/icd_top.jsp"></jsp:include>

	<!-- 主内容 -->
	<div class="wrapper" style="min-height: 530px">

		<div class="row" style="padding: 20px 0px;">
			<!-- /左边 -->
			<div class="col-xs-2">
				<%-- JSP中的include中page路径里/代表的是项目的根目录 --%>
				<jsp:include page="/member/icd_menu.jsp">
					<jsp:param value="index" name="tag" />
				</jsp:include>
			</div>
			<!-- /左边 -->

			<!-- 右边 -->
			<div class="col-xs-10">
				<div class="panel">
					<div class="panel-heading">
						<strong><i class="icon-shopping-cart"> </i>订单管理</strong>
					</div>
					<table class="table table-hover table-striped tablesorter">
						<thead>
							<tr class="text-center">
								<td style="width: 60px">ID</td>
								<td class="text-left">商品信息</td>
								<td style="width: 80px">数量</td>
								<td style="width: 80px" class="text-right">金额</td>
								<td style="width: 200px">订单跟踪</td>
								<td style="width: 60px">状态</td>
								<td style="width: 100px">买家留言</td>
								<td style="width: 120px">操作</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.items}" var="ord">
								<tr>
									<td class="text-center">${ord.id}</td>
									<td class="text-left"><c:forEach items="${ord.items}"
											var="item" varStatus="vs">
											<c:if test="${vs.index >0}">
												<br />
											</c:if>
											<a href="${ctx}/product_detail?id=${item.product.id}">${item.product.name}</a> x ${item.amount}
				            </c:forEach></td>
									<td class="text-center">${ord.total_amount}</td>
									<td class="text-right"><fmt:formatNumber
											value="${ord.payment_price}" pattern="￥#,##0.00" /></td>
									<td class="text-center">下单时间：<fmt:formatDate
											value="${ord.create_time}" pattern="yyyy-MM-dd HH:mm" /> <c:if
											test="${!empty ord.delivery_time}">
											<br />发货时间：<fmt:formatDate value="${ord.delivery_time}"
												pattern="yyyy-MM-dd HH:mm" />
										</c:if> <c:if test="${!empty ord.end_time}">
											<br />完成时间：<fmt:formatDate value="${ord.end_time}"
												pattern="yyyy-MM-dd HH:mm" />
										</c:if>
									</td>
									<td class="text-center">
										<!-- 2已付款,3待发货,4已发货,5己收货,6已完成,-1已取消 --> <c:choose>
											<c:when test="${ord.status==2}">已付款</c:when>
											<c:when test="${ord.status==3}">待发货</c:when>
											<c:when test="${ord.status==4}">已发货</c:when>
											<c:when test="${ord.status==5}">己收货</c:when>
											<c:when test="${ord.status==6}">已完成</c:when>
											<c:when test="${ord.status==-1}">已取消</c:when>
										</c:choose>
									</td>
									<td class="text-right">${ord.remark}</td>
									<td class="text-center"><a
										href="${ctx}/member/orders/detail?id=${ord.id}">详情</a> <c:if
											test="${ord.status==4}" var="flag">
											<a href="${ctx}/member/orders/status?id=${ord.id}&status=5">确认收货</a>
										</c:if> <c:if test="${ord.status==3}" var="flag">
											<a href="${ctx}/member/orders/status?id=${ord.id}&status=-1">取消订单</a>
										</c:if>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="8"><q:pager
										totalElements="${page.totalElements}" number="${page.number}" />
								</td>
							</tr>
						</tfoot>
					</table>
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
</body>
</html>