new Vue({
    el: '#app',
    data: {
        user: {}
    },
    methods: {
        getLoginName: function () {
            let _this = this;
            axios.post("/login/loginName")
                .then(function (response) {
                    if (response.data.success) {
                        _this.user = response.data.result;
                    } else {
                        alert(response.data.error);
                    }
                }).catch(function (reason) {
                console.log(reason);
            })
        }
    },
    created: function () {
        this.getLoginName();
    }

})