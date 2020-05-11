new Vue({
    el: '#app',
    data: {
        page: 1,  //显示的是哪一页
        pageSize: 10, //每一页显示的数据条数
        total: 0, //记录总数
        maxPage: 9,

        garbageList: [],

        searchEntity: {
            cityId: '',
            categoryId: '',
            name: ''
        },
        cityList: [{id: 1, name: '杭州'}, {id: 2, name: '上海'}],
        categoryList: [{id: 1, name: '有害垃圾'}, {id: 2, name: '湿垃圾'}],

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
        }
    },
    created: function () {
        this.pageHandler(1);
    }
});