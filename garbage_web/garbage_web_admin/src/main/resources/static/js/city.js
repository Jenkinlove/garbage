new Vue({
    el: '#app',
    data: {
        page: 1,  //显示的是哪一页
        pageSize: 10, //每一页显示的数据条数
        total: 3, //记录总数
        maxPage: 9,

        searchEntity: {},
        cityList: [],
        city: {},
        selected: []
    },
    methods: {
        pageHandler: function (page) {
            let _this = this;
            axios.get("/api/admin/city/paging", {
                params: {
                    page: page,
                    pageSize: _this.pageSize,
                    name: _this.searchEntity.name
                }
            })
                .then(function (response) {
                    if (response.data.success) {
                        _this.cityList = response.data.result.rows;
                        _this.total = response.data.result.total;
                    }
                }).catch(function (reason) {
                console.log(reason);
            })
        },
        close: function () {
            this.city = {};
        },
        toEditCity: function () {
            let url;
            if (this.city.id == null) {
                //创建
                url = '/api/admin/city/create';
            } else {
                //更新
                url = '/api/admin/city/update';
            }
            let _this = this;
            axios.post(url, this.city).then(function (response) {
                if (response.data.success) {
                    _this.pageHandler(_this.page);
                    _this.city = {}
                } else {
                    alert(response.data.error);
                }
            }).catch(function (reason) {
                console.log(reason);
            })
        },
        findCityById: function (id) {
            let _this = this;
            axios.get("/api/admin/city/single?id=" + id).then(function (response) {
                if (response.data.success) {
                    _this.city = response.data.result;
                } else {
                    alert(response.data);
                }
            }).catch(function (reason) {
                console.log(reason);
            })
        },
        selectedCity: function (event, id) {
            if (event.target.checked) {
                this.selected.push(id);
            } else {
                let index = this.selected.indexOf(id);
                this.selected.splice(index, 1);
            }
        },
        deleteCities: function () {
            if (this.selected.length === 0) {
                alert("请选择一个或多个要删除的城市");
                return;
            }
            let _this = this;
            axios.post("/api/admin/city/delete", {ids: _this.selected}).then(function (response) {
                if (response.data.success) {
                    _this.selected = [];
                    _this.pageHandler(_this.page);
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