new Vue({
    el: '#app',
    data: {
        articleList: [],
        type: 1
    },
    methods: {
        getAllArticleByType: function (type) {
            let _this = this;
            axios.get("/api/portal/article/type?type=" + type).then(function (response) {
                if (response.data.success) {
                    _this.articleList = response.data.result;
                    for (let i = 0; i < _this.articleList.length; i++) {
                        let date = _this.articleList[i].createdAt;
                        _this.articleList[i].createdAt = date.substr(0, 10);
                    }
                } else {
                    alert(response.data.error);
                }
            }).catch(function (reason) {
                console.log(reason);
            })
        },
        GetQueryString: function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return (r[2]);
            return null;
        },
        jump: function (index) {
            //获取需要跳转到标签的top值
            var topLength = $(".conArticle").eq(index).offset().top;
            //动态跳转到指定位置（数值越大滚动速度越慢）
            $("html,body").animate({scrollTop: topLength}, 500);
            // 给点击的元素添加类名
            var $item = $(".list-group-item").eq(index);
            $item.addClass("blueColor");
            $item.siblings().removeClass("blueColor");
        }
    },
    mounted: function () {
        this.type = this.GetQueryString('type');
        this.getAllArticleByType(this.type);
    }

});