new Vue({
    el: '#app',
    data: {
        page: 1,  //显示的是哪一页
        pageSize: 10, //每一页显示的数据条数
        total: 0, //记录总数
        maxPage: 9,

        categoryList: [],
        cityList: [],
        cityMap: {},

        category: {},

        searchEntity: {},
        selectedCategories: [],

        imageUrl: ''
    },
    methods: {
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
        pageHandler: function (page) {
            let _this = this;
            axios.get("/api/admin/category/paging", {
                params: {
                    page: page,
                    pageSize: _this.pageSize,
                    cityId: _this.searchEntity.cityId,
                    name: _this.searchEntity.name
                }
            })
                .then(function (response) {
                    if (response.data.success) {
                        _this.categoryList = response.data.result.rows;
                        _this.total = response.data.result.total;
                    }
                }).catch(function (reason) {
                console.log(reason);
            })
        },
        close: function () {
            this.category = {};
            this.imageUrl = '';
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
                    _this.imageUrl = response.data.result;
                } else {
                    alert(response.data.error);
                }
            }).catch(function (reason) {
                console.log(reason);
            })
        },
        deleteImage: function () {
            if (this.imageUrl == '') {
                alert("图片为空");
                return;
            }
            let _this = this;

            axios.post("/upload/delete", Qs.stringify({'url': this.imageUrl})).then(function (response) {
                if (response.data.success) {
                    _this.imageUrl = '';
                    _this.category.image = ''
                } else {
                    alert(response.data.error);
                }
            }).catch(function (reason) {
                console.log(reason);
            })
        },
        toEditCategory: function () {
            let url;
            if (this.category.id == null) {
                //新建
                url = "/api/admin/category/create";
            } else {
                //更新
                url = "/api/admin/category/update";
            }
            let _this = this;
            if (this.category.cityId == -1) {
                this.category.cityId = null;
            }
            this.category.image = this.imageUrl;
            axios.post(url, _this.category).then(function (response) {
                if (response.data.success) {
                    _this.category = {};
                    _this.imageUrl = '';
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
            axios.get("/api/admin/category/single?id=" + id).then(function (response) {
                if (response.data.success) {
                    _this.category = response.data.result;
                    _this.imageUrl = response.data.result.image;
                } else {
                    alert(response.data.error);
                }
            }).catch(function (reason) {
                console.log(reason);
            })
        },
        selected: function (event, id) {
            if (event.target.checked) {
                this.selectedCategories.push(id);
            } else {
                let index = this.selectedCategories.indexOf(id);
                this.selectedCategories.splice(index, 1);
            }
        },
        deleteCategories: function () {
            if (this.selectedCategories.length === 0) {
                alert("请选择一个或多个要删除的分类");
                return;
            }
            let _this = this;
            axios.post("/api/admin/category/delete", {ids: _this.selectedCategories}).then(function (response) {
                if (response.data.success) {
                    _this.selectedCategories = [];
                    _this.pageHandler(_this.page);
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
        this.getAllCities();
        this.pageHandler(1);
    }

});