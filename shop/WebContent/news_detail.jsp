<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="/icd_meta.jsp" />
<title>卓尔商城-公告详情</title>
<jsp:include page="/icd_link.jsp" />
</head>
<body>
	<jsp:include page="/icd_top.jsp"></jsp:include>

	<!-- 公告详情 -->
	<div class="wrapper" style="min-height: 500px">
		<div class="panel" id="news_detail">
			<div class="panel-heading">
				<span>${news.title}</span>
				<p id="pubtime">
					时间：
					<fmt:formatDate value="${news.pub_time}"
						pattern="yyyy-MM-dd HH:mm:ss" />
				</p>
			</div>
			<div class="panel-body">${news.content}</div>
		</div>
	</div>
	<!-- /公告详情 -->

	<jsp:include page="/icd_bottom.jsp"></jsp:include>

	<script src="${ctx}/zui/lib/jquery/jquery.js"></script>
	<script src="${ctx}/js/jquery.scrollUp.min.js"></script>
	<script src="${ctx}/zui/js/zui.js"></script>
	<script src="${ctx}/js/my.js"></script>
</body>
</html>