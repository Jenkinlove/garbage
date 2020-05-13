new Vue({
    el: '#app',
    data: {
        page: 1,  //显示的是哪一页
        pageSize: 10, //每一页显示的数据条数
        total: 0, //记录总数
        maxPage: 9,

        searchEntity: {},

        problemList: [],
        problem: {
            problemType: 1,
            rightAnswer: '-1'
        },
        problemImageUrl: '',
        selectedIds: []

    },
    methods: {
        pageHandler: function (page) {
            let _this = this;
            axios.get("/api/admin/problem/paging", {
                params: {
                    page: page,
                    pageSize: _this.pageSize,
                    name: _this.searchEntity.name
                }
            })
                .then(function (response) {
                    if (response.data.success) {
                        _this.problemList = response.data.result.rows;
                        _this.total = response.data.result.total;
                    } else {
                        alert(response.data.error);
                    }
                }).catch(function (reason) {

            })
        },
        uploadImage: function () {
            let formData = new FormData();
            formData.append('file', file.files[0]);
            let instance = axios.create({
                withCredentials: true
            });
            let _this = this;
            instance.post("/upload/uploadFile", formData).then(function (response) {
                if (response.data.success) {
                    _this.problemImageUrl = response.data.result;
                } else {
                    alert(response.data.error);
                }
            }).catch(function (reason) {
                console.log(reason);
            })
        },
        deleteImage: function () {
            if (this.problemImageUrl == '') {
                alert("图片为空");
                return;
            }
            let _this = this;

            axios.post("/upload/delete", Qs.stringify({'url': this.problemImageUrl})).then(function (response) {
                if (response.data.success) {
                    _this.problemImageUrl = '';
                    _this.problem.image = ''
                } else {
                    alert(response.data.error);
                }
            }).catch(function (reason) {
                console.log(reason);
            })
        },
        close: function () {
            this.problem = {
                problemType: 1,
                rightAnswer: '-1'
            };
            this.problemImageUrl = '';
        },
        toEditProblem: function () {
            let url;
            if (this.problem.id == null) {
                //新建
                url = "/api/admin/problem/create";
            } else {
                //更新
                url = "/api/admin/problem/update";
            }
            let _this = this;
            if (this.problem.rightAnswer == -1) {
                this.problem.rightAnswer = null;
            }
            if (this.problem.problemType == 2) {
                this.problem.name = this.problemImageUrl;
            }
            axios.post(url, _this.problem).then(function (response) {
                if (response.data.success) {
                    _this.problem = {
                        problemType: 1,
                        rightAnswer: '-1'
                    };
                    _this.problemImageUrl = '';
                    _this.pageHandler(_this.page);
                } else {
                    alert(response.data.error);
                }
            }).catch(function (reason) {
                console.log(reason);
            })

        },
        findProblem: function (id) {
            let _this = this;
            axios.get("/api/admin/problem/single?id=" + id).then(function (response) {
                if (response.data.success) {
                    _this.problem = response.data.result;
                    if (_this.problem.problemType == 2) {
                        _this.problemImageUrl = response.data.result.name;
                    }
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
        deleteProblem: function () {
            if (this.selectedIds.length === 0) {
                alert("请选择一个或多个要删除的问题");
                return;
            }
            let _this = this;
            axios.post("/api/admin/problem/delete", {ids: _this.selectedIds}).then(function (response) {
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
    }
});