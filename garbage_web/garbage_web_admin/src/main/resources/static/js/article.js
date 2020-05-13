var editor;

function _initEditor() {
    var E = window.wangEditor;
    editor = new E('#editor');
    // 配置服务器端地址
    editor.customConfig.uploadImgServer = '/upload/uploadEditor';
    editor.customConfig.uploadFileName = 'upfile';
    editor.create();
}

var a = new Vue({
    el: '#app',
    data: {
        page: 1,  //显示的是哪一页
        pageSize: 10, //每一页显示的数据条数
        total: 0, //记录总数
        maxPage: 9,

        article: {
            articleType: 1
        },
        articleList: [],

        articleTypes: {1: '政策', 2: '新闻资讯'},

        searchEntity: {
            articleType: -1
        },
        selectedIds: []

    },
    methods: {
        pageHandler: function (page) {
            let _this = this;
            axios.get("/api/admin/article/paging", {
                params: {
                    page: page,
                    pageSize: _this.pageSize,
                    name: _this.searchEntity.name,
                    articleType: _this.searchEntity.articleType
                }
            })
                .then(function (response) {
                    if (response.data.success) {
                        _this.articleList = response.data.result.rows;
                        _this.total = response.data.result.total;
                    } else {
                        alert(response.data.error);
                    }
                }).catch(function (reason) {

            })
        },
        close: function () {
            this.article = {
                articleType: 1
            };
            editor.txt.clear();
        },
        toEditArticle: function () {
            let url;
            if (this.article.id == null) {
                //新建
                url = "/api/admin/article/create";
            } else {
                //更新
                url = "/api/admin/article/update";
            }
            this.article.content = editor.txt.html();
            let _this = this;
            axios.post(url, _this.article).then(function (response) {
                if (response.data.success) {
                    _this.article = {
                        articleType: 1
                    };
                    _this.pageHandler(_this.page);
                } else {
                    alert(response.data.error);
                }
            }).catch(function (reason) {
                console.log(reason);
            })

        },
        findOne: function (id) {
            let _this = this;
            axios.get("/api/admin/article/single?id=" + id).then(function (response) {
                if (response.data.success) {
                    _this.article = response.data.result;
                    editor.txt.html(response.data.result.content);
                } else {
                    alert(response.data.error);
                }
            }).catch(function (reason) {
                console.log(reason);
            })
        },
        deleteSelect: function (event, id) {
            if (event.target.checked) {
                this.selectedIds.push(id);
            } else {
                let index = this.selectedIds.indexOf(id);
                this.selectedIds.splice(index, 1);
            }
        },
        deleteArticle: function () {
            if (this.selectedIds.length === 0) {
                alert("请选择一个或多个要删除的问题");
                return;
            }
            let _this = this;
            axios.post("/api/admin/article/delete", {ids: _this.selectedIds}).then(function (response) {
                if (response.data.success) {
                    _this.selectedIds = [];
                    window.location.reload();
                } else {
                    alert(response.data.error);
                }
            }).catch(function (reason) {
                console.log(reason);
            })
        }
    },
    created: function () {
        this.pageHandler(1);
    },
    mounted: function () {
        _initEditor();
    }

});