$(function () {
    // 新闻页面跳转到指定位置
    $(".list-group-item").click(function () {
        var index = $(this).index();
        //获取需要跳转到标签的top值
        var topLength = $(".conArticle").eq(index).offset().top;
        //动态跳转到指定位置（数值越大滚动速度越慢）
        $("html,body").animate({scrollTop: topLength}, 500);
        // 给点击的元素添加类名
        $(this).addClass("blueColor");
        $(this).siblings().removeClass("blueColor");
    })
});