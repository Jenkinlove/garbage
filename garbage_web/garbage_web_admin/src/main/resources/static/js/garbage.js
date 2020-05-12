new Vue({
    el: '#app',
    data: {
        page: 1,  //显示的是哪一页
        pageSize: 10, //每一页显示的数据条数
        total: 0, //记录总数
        maxPage: 9,

        garbageList: [],

        searchEntity: {},
        cityList: [],
        cityMap: {},

        categoryList: [],
        categoryMap: {},

        selectIds: [],

        garbage: {}
    },
    methods: {
        pageHandler: function (page) {
            let _this = this;
            let obj = {};
            obj.page = page;
            obj.pageSize = this.pageSize;
            if (this.searchEntity.cityId == -1) {
                this.searchEntity.cityId = '';
            }
            obj.cityId = this.searchEntity.cityId;
            if (this.searchEntity.categoryId == -1) {
                this.searchEntity.categoryId = '';
            }
            obj.categoryId = this.searchEntity.categoryId;
            obj.name = this.searchEntity.name;
            axios.get("/api/admin/garbage/paging", {
                params: obj,
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                }
            }).then(function (response) {
                if (response.data.success) {
                    _this.garbageList = response.data.result.rows;
                    _this.total = response.data.result.total;
                } else {
                    alert(response.data.error);
                }
            }).catch(function (reason) {
                console.log(reason);
            })
        },
        getAllCities: function () {
            let _this = this;
            axios.get("/api/admin/city/all").then(function (response) {
                if (response.data.success) {
                    _this.cityList = response.data.result;
                    for (let i = 0; i < response.data.result.length; i++) {
                        let city = response.data.result[i];
                        _this.cityMap[city.id] = city.name;
                    }
                } else {
                    alert(response.data.error);
                }
            })
        },
        getAllCategories: function () {
            let _this = this;
            axios.get("/api/admin/category/all").then(function (response) {
                if (response.data.success) {
                    _this.categoryList = response.data.result;
                    for (let i = 0; i < response.data.result.length; i++) {
                        let category = response.data.result[i];
                        _this.categoryMap[category.id] = category.name;
                    }
                } else {
                    alert(response.data.error);
                }
            })
        },
        getCategoriesByCityId: function () {
            if (this.searchEntity.cityId == null || this.searchEntity.cityId == -1) {
                this.getAllCategories();
                return;
            }
            let _this = this;
            axios.get("/api/admin/category/city?cityId=" + this.searchEntity.cityId).then(function (response) {
                if (response.data.success) {
                    _this.categoryList = response.data.result;
                } else {
                    alert(response.data.error);
                }
            })

        },
        getCategoryByCityIdForEdit: function () {
            if (this.garbage.cityId == null || this.garbage.cityId == -1) {
                this.getAllCategories();
                return;
            }
            let _this = this;
            axios.get("/api/admin/category/city?cityId=" + this.garbage.cityId).then(function (response) {
                if (response.data.success) {
                    _this.categoryList = response.data.result;
                } else {
                    alert(response.data.error);
                }
            })
        },
        deleteSelection: function (event, id) {
            /*监听复选框按钮的点击*/
            //复选框选中
            if (event.target.checked) {
                //向数组中添加元素
                this.selectIds.push(id);
            } else {
                //从数组中移除
                var idx = this.selectIds.indexOf(id);
                this.selectIds.splice(idx, 1);
            }
        },
        close: function () {
            this.garbage = {};
        },
        toEditGarbage: function () {
            if (this.garbage.name == null) {
                alert("请填写垃圾的名称");
                return;
            }
            if (this.garbage.cityId == null || this.garbage.cityId == -1) {
                alert("请选择城市");
                return;
            }
            if (this.garbage.categoryId == null || this.garbage.categoryId == -1) {
                alert("请选择垃圾的分类");
                return;
            }
            let url;
            if (this.garbage.id == null) {
                //新建
                url = "/api/admin/garbage/create";
            } else {
                //更新
                url = "/api/admin/garbage/update";
            }
            let _this = this;
            axios.post(url, _this.garbage).then(function (response) {
                if (response.data.success) {
                    _this.garbage = {};
                    _this.pageHandler(_this.page);
                } else {
                    alert(response.data.error);
                }
            }).catch(function (reason) {
                console.log(reason);
            })
        },
        findById: function (id) {
            let _this = this;
            axios.get("/api/admin/garbage/single?id=" + id).then(function (response) {
                if (response.data.success) {
                    _this.garbage = response.data.result;
                    _this.getCategoryByCityIdForEdit();
                } else {
                    alert(response.data.error);
                }
            }).catch(function (reason) {
                console.log(reason);
            })
        },
        deleteGarbage: function () {
            if (this.selectIds.length === 0) {
                alert("请选择一个或多个要删除的垃圾");
                return;
            }
            let _this = this;
            axios.post("/api/admin/garbage/delete", {ids: _this.selectIds}).then(function (response) {
                if (response.data.success) {
                    _this.selectIds = [];
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
        this.getAllCities();
        this.getAllCategories();
    }
});