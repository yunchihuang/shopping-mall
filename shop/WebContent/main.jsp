<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="/icd_meta.jsp" />
<title>电器城-首页</title>
<jsp:include page="/icd_link.jsp" />
</head>
<body>
	<jsp:include page="/icd_top.jsp">
		<jsp:param value="index" name="tag" />
	</jsp:include>

	<!-- 轮播图+公告 -->
	<div class="wrapper row">
		<div class="col-xs-9">
			<div id="myNiceCarousel" class="carousel slide" data-ride="carousel">
				<!-- 圆点指示器 -->
				<ol class="carousel-indicators">
					<c:forEach items="${tops}" var="top" varStatus="vs">
						<li data-target="#myNiceCarousel" data-slide-to="${vs.index}"
							${vs.index == 0 ? "class='active'" : ""}></li>
					</c:forEach>
				</ol>
				<!-- 轮播项目 -->
				<div class="carousel-inner">
					<c:forEach items="${tops}" var="top" varStatus="vs">
						<div class='item ${vs.index == 0 ? "active" : ""}'>
							<a href="${ctx}/news/detail?id=${top.id}" target="_blank"><img
								alt="${top.title}" src="${pic_base}${top.thumbnail}"></a>
						</div>
					</c:forEach>
				</div>

				<!-- 项目切换按钮 -->
				<a class="left carousel-control" href="#myNiceCarousel"
					data-slide="prev"> <span class="icon icon-chevron-left"></span>
				</a> <a class="right carousel-control" href="#myNiceCarousel"
					data-slide="next"> <span class="icon icon-chevron-right"></span>
				</a>
			</div>
		</div>

		<div id="news" class="col-xs-3" style="padding: 0">
			<div class="panel">
				<div class="panel-heading">
					<span style="font-size: 16px">优惠快讯</span> <a
						href="${ctx}/news/list" class="pull-right" style="margin-top: 4px">更多
						<i class="icon-angle-right"></i>
					</a>
				</div>
				<div class="panel-body">
					<ul>
						<c:forEach items="${news}" var="n" varStatus="vs">
							<li class="text-ellipsis"><a
								${vs.index==0 ? 'class="hot"' : ""} target="_blank"
								href="${ctx}/news/detail?id=${n.id}">${n.title}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- /轮播图+公告 -->

	<!-- 商品列表 -->
	<div class="wrapper">

		<div class="list">
			<header style="border: 0">
				<strong><i class="icon panel-icon icon-heart"
					style="color: #0000CC"></i> 猜你喜欢</strong>
				<div class="pull-right">
					<a href="${ctx}/product/list?level=hots">更多</a>
				</div>
			</header>
			<section class="cards cards-condensed row" style="margin: 0">
				<c:forEach items="${hots}" var="h">
					<div class="col-xs-2">
						<div class="card">
							<a href="${ctx}/product_detail?id=${h.id}" target="_blank"><img
								src="${pic_base}${h.thumbnail}" alt=""></a>
							<div class="card-heading">
								<span class="pull-right price"><fmt:formatNumber
										value="${h.sale_price}" pattern="￥#,##0.00" /> </span> <a
									href="${ctx}/product_detail?id=${h.id}" target="_blank">${h.name}</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</section>
		</div>

		<!-- 第二行 -->
		<div class="list">
			<header style="border: 0">
				<strong><i class="icon-tablet" style="color: #0000CC"></i>
					热门手机</strong>
				<div class="pull-right">
					<a href="${ctx}/product/list?id=1">更多</a>
				</div>
			</header>
			<section class="cards cards-condensed row" style="margin: 0">
				<c:forEach items="${phones}" var="h">
					<div class="col-xs-2">
						<div class="card">
							<a href="${ctx}/product_detail?id=${h.id}" target="_blank"><img
								src="${pic_base}${h.thumbnail}" alt=""></a>
							<div class="card-heading">
								<span class="pull-right price"><fmt:formatNumber
										value="${h.sale_price}" pattern="￥#,##0.00" /> </span> <a
									href="${ctx}/product_detail?id=${h.id}" target="_blank">${h.name}</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</section>
		</div>
		<!-- /第2行 -->

		<!-- 第3行 -->
		<div class="list" style="border: 0">
			<header>
				<strong><i class="icon-desktop" style="color: #0000CC"></i>
					电脑办公</strong>
				<div class="pull-right">
					<a href="${ctx}/product/list?id=2">更多</a>
				</div>
			</header>
			<section class="cards cards-condensed row" style="margin: 0">
				<c:forEach items="${computers}" var="h">
					<div class="col-xs-2">
						<div class="card">
							<a href="${ctx}/product_detail?id=${h.id}" target="_blank"><img
								src="${pic_base}${h.thumbnail}" alt=""></a>
							<div class="card-heading">
								<span class="pull-right price"><fmt:formatNumber
										value="${h.sale_price}" pattern="￥#,##0.00" /> </span> <a
									href="${ctx}/product_detail?id=${h.id}" target="_blank">${h.name}</a>
							</div>
						</div>
					</div>
				</c:forEach>

			</section>
		</div>
		<!-- /第3行 -->
		<!-- 广告 -->
		<div class="list">
			<a target="_blank" href="${ctx}/product_detail?id=4"><img
				src="./img/banner_ad.jpg" alt=""></a>
		</div>
		<!-- /广告 -->
	</div>

	<!-- /商品列表 -->

	<jsp:include page="/icd_bottom.jsp"></jsp:include>

	<script src="${ctx}/zui/lib/jquery/jquery.js"></script>
	<script src="${ctx}/js/jquery.scrollUp.min.js"></script>
	<script src="${ctx}/zui/js/zui.js"></script>
	<script src="${ctx}/js/my.js"></script>
</body>
</html>