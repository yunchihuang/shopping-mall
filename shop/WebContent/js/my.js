/*使用jQuery处理分类菜单的显示和隐藏*/
var cate = $("#categories");
if(cate[0]){
	$(".categories").hover(function(){
		$("#menu").show();
	}, function(){
		$("#menu").hide();
	});
}

/*使用jQuery来处理子分类菜单的显示和隐藏*/
$("#menu > .cate-list > li").hover(function(){
	$(".sub-list", $(this)).show();
},function(){
	$(".sub-list", $(this)).hide();
});


/*QQ客服鼠标悬停事件*/
$(".slidebar-qq").hover(function(){
	$(".qq", $(this)).show();
}, function(){
	$(".qq", $(this)).hide();
});

/*客服电话鼠标悬停事件*/
$(".slidebar-phone").hover(function(){
	$(".phone", $(this)).show();
}, function(){
	$(".phone", $(this)).hide();
});

/*公众号鼠标悬停事件*/
$(".slidebar-wx").hover(function(){
	$(".pic", $(this)).show();
}, function(){
	$(".pic", $(this)).hide();
});


/*给页面添加滚动到顶部的jQuery插件*/
$.scrollUp({scrollText:''});


/*计数组件 */
$('.icon-plus').parent().click(function(){//给 +号的父亲元素添加点击事件
    var countInput = $(this).prev('input'); //获取input元素
    var num = parseInt(countInput.val()) + 1; //获取input中的值并转化成整数再加上1
    countInput.val(num); //把值赋给input
    countInput.change(); //触发change事件
});

$('.icon-minus').parent().click(function(){ //给-号的父亲元素添加点击事件
    var countInput = $(this).next('input');
    if(countInput.val() <= 1) {
    	return false;
    }
    
    countInput.val(parseInt(countInput.val()) - 1);
    countInput.change(); //触发change事件
});

$('input[name=amount]').change(function(){ //数量输入框的值改变事件处理函数--->计算本行的总金额
	
	var amount = parseInt($(this).val()); //获取当前的数量并转化为整数
	var price = parseFloat($(this).parents('tr').find('#price').text());//获取当前表格行中的商品价格并转化为浮点数
    var sum = (amount * price).toFixed(2); //计算当前表格行的值，取两位小数
    $(this).parents('tr').find('#sum').text(sum);
    countSum(); //计算总价格
});

/*计算总数量及总价格*/
function countSum(){
    var amount = 0;
    var price = 0;
    $('#cart-list>tr').each(function(){ //循环表格的每一行
      amount += parseInt($("#amount", $(this)).val());
      price = (parseFloat(price) + parseFloat($("#sum", $(this)).text())).toFixed(2);
    })
    $('#amount-sum').text(amount);
    $('#price-sum').text(price);
}
