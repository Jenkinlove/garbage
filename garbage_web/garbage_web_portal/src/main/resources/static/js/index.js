new Vue({
    el: '#app',
    data: {
        cityList: [],
        selectedEntity: {
            cityId: 3
        },
        categoryList: [],
        category: {},
        categoryMap: {},
        garbageList: [],
        garbage: {}
    },
    methods: {
        getAllCities: function () {
            let _this = this;
            axios.get("/api/portal/city/all").then(function (response) {
                if (response.data.success) {
                    _this.cityList = response.data.result;
                } else {
                    alert(response.data.error);
                }
            }).catch(function (reason) {
                console.log(reason);
            })
        },
        getCategoryByCity: function (cityId) {
            let _this = this;
            axios.get("/api/portal/category/city?cityId=" + cityId).then(function (response) {
                if (response.data.success) {
                    _this.categoryList = response.data.result;
                    _this.category = {};
                    for (let i = 0; i < _this.categoryList.length; i++) {
                        let cat = _this.categoryList[i];
                        _this.categoryMap[cat.id] = cat.name;
                    }
                    console.log(_this.categoryMap);
                } else {
                    alert(response.data.error);
                }
            }).catch(function (reason) {
                console.log(reason);
            })
        },
        getDetailCategory: function (id) {
            let _this = this;
            axios.get("/api/portal/category/single?id=" + id).then(function (response) {
                if (response.data.success) {
                    _this.category = response.data.result;
                    $(".cardShow").fadeIn('slow');
                } else {
                    alert(response.data.error);
                }
            }).catch(function (reason) {
                console.log(reason);
            })
        },
        searchGarbage: function () {
            if (this.selectedEntity.name == null) {
                alert("搜索内容不能为空");
                return;
            }
            let _this = this;
            axios.get("/api/portal/garbage/search", {params: _this.selectedEntity}).then(function (response) {
                if (response.data.success) {
                    _this.garbageList = response.data.result;
                    if (_this.garbageList.length > 0) {
                        for (let i = 0; i < _this.garbageList.length; i++) {
                            if ($.trim(_this.garbageList[i].name) === $.trim(_this.selectedEntity.name)) {
                                _this.garbage = _this.garbageList[i];
                                _this.garbageList.splice(i, 1);
                                break;
                            }
                        }
                    }
                    console.log(_this.garbage);
                    $(".mask").fadeIn('slow');
                    $(".toDisplay").fadeIn('slow');
                    _this.selectedEntity.name = null;
                } else {
                    alert(response.data.error);
                }
            }).catch(function (reason) {
                console.log(reason);
            })
        },
        close: function () {
            $(".mask").fadeOut('slow');
            $(".toDisplay").fadeOut('slow');
            this.selectedEntity.name = null;
            this.garbageList = [];
            this.garbage = {};
        },
        checkObj: function (obj) {
            if (JSON.stringify(obj) == '{}') {
                return false;
            } else {
                return true;
            }
        }
    },
    created: function () {
        this.getAllCities();
        this.getCategoryByCity(3);
    }
});