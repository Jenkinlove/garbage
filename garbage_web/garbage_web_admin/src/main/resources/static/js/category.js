new Vue({
    el: '#app',
    data: {
        page: 1,  //显示的是哪一页
        pageSize: 10, //每一页显示的数据条数
        total: 16, //记录总数
        maxPage: 9,

        cityList: [{id: 1, name: '杭州'}, {id: 2, name: '上海'}],
    },
    methods: {}

});