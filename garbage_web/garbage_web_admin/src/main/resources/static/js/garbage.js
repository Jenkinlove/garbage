new Vue({
    el: '#app',
    data: {
        page: 1,  //显示的是哪一页
        pageSize: 10, //每一页显示的数据条数
        total: 0, //记录总数
        maxPage: 0,

        searchEntity: {
            cityId: '',
            categoryId: '',
            name: ''
        },
        cityList: []
    },
    methods: {
        pageHandler: function (page) {
            let _this = this;
            axios.get().then(function (response) {

            }).catch(function (reason) {
                console.log(reason);
            })
        }
    },
    created: function () {
        this.pageHandler(1);
    }
});