$(function () {
    //按钮点击事件
    $(".cardBtn").click(function () {
        //拿到当前点击按钮的索引
        var index = $(this).index(".cardBtn");
        //根据索引找到对应显示的卡片
        var $cardHide = $(".cardHide").eq(index);
        $cardHide.addClass("cardShow");
        //设置其他卡片隐藏
        $cardHide.siblings().removeClass("cardShow");
        return false;
    });
    //模态弹窗弹出
    $(".searchBtn").click(function () {
        $(".mask").fadeIn('slow');
        $(".toDisplay").fadeIn('slow');
        // alert("it999")
    });
    // 关闭搜索弹窗
    $("#maskDelete").click(function () {
        $(".mask").fadeOut('slow');
        $(".toDisplay").fadeOut('slow');
        $(".form-control").val("");
    });

});