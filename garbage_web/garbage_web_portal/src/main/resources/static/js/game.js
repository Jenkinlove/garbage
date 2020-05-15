// $(function(){
//     var timer = null;
//     var re = null;
//     // 点击进入按钮
//     $(".enter").click(function () {
//         $(".gameEnter").fadeOut("slow");
//         $(".choice").fadeIn(2000);
//       var $num = 10;
//         cutDown($num);
//     });
//     // 倒数计时器
//     function cutDown($num){
//         timer = setInterval(function () {
//             var num=$num--;
//             $(".interval").text(num +"s");
//             if(num<=0){
//                 clearInterval(timer);
//                 document.location.reload();
//             }
//         },1000)
//     }
//     // 切换题目
//
//     // 选择按钮点击触发
//     $(".choiceAnswer").click(function () {
//       var $text = $(this).text();
//       if($.trim($text) ==="干垃圾"){
//           $(this).css("background-color","  #cce5ff");
//          $(this).children("span").addClass("icon-V");
//       }else{
//           $(this).css("background-color","#fff3cd");
//           $(this).children("span").addClass("icon-V1");
//            var $right = $(".choiceAnswer:contains(\"干垃圾\")");
//           $right.animate({
//               width:"52%",height:"+=10px"
//           }).css(  "background-color","#cce5ff");
//             $right.children("span").addClass("icon-V")
//       }
//         clearInterval(timer);
//       re =setInterval(function () {
//           document.location.reload();
//       },3000);
//
//         // 移除click
//         $(".choiceAnswer").unbind("click");
//
//     });
// });

var timer = null;
var re = null;
new Vue({
    el: '#app',
    data: {
        problemCount: 0,
        score: 0,
        answeredProblemIds: [],
        problem: {},
        rightResult: '',
        rightIndex: 0

    },
    methods: {
        getRandProblem: function () {
            let _this = this;
            if (re != null) {
                clearInterval(re);
                // 改
                $(".choiceAnswer").css({"backgroundColor": "", "width": "40%"});
                $(".choiceAnswer>span").attr("class", "");
                _this.problemCount = _this.problemCount + 1;
                if (_this.problemCount === 3) {
                    document.location.reload();
                    alert(_this.score);
                }
                console.log(_this.answeredProblemIds);
            }

            axios.post("/api/portal/problem/rand", {params: {ids: _this.answeredProblemIds}}).then(function (response) {
                if (response.data.success) {
                    _this.problem = response.data.result;
                    _this.answeredProblemIds.push(response.data.result.id);
                    //正确答案赋值,记录正确答案的坐标
                    if (response.data.result.rightAnswer === 'A') {
                        _this.rightResult = response.data.result.selectA;
                        _this.rightIndex = 0;
                    } else if (response.data.result.rightAnswer === 'B') {
                        _this.rightResult = response.data.result.selectB;
                        _this.rightIndex = 1;
                    } else if (response.data.result.rightAnswer === 'C') {
                        _this.rightResult = response.data.result.selectC;
                        _this.rightIndex = 2;
                    } else if (response.data.result.rightAnswer === 'D') {
                        _this.rightResult = response.data.result.selectD;
                        _this.rightIndex = 3;
                    }
                    $(".gameEnter").fadeOut("slow");
                    $(".choice").fadeIn(2000);
                    var $num = 10;
                    _this.cutDown($num);

                } else {
                    alert(response.data.error);
                }
            }).catch(function (reason) {
                console.log(reason);
            })
        },
        cutDown: function ($num) {
            let _this = this;
            timer = setInterval(function () {
                var num = $num--;
                $(".interval").text(num + "s");
                if (num <= 0) {
                    clearInterval(timer);
                    _this.score = _this.score - 10;
                    _this.choiceAnswer(_this.rightResult, _this.rightIndex);
                }
            }, 1000)
        },
        choiceAnswer: function (answer, index) {
            let _this = this;

            var $item = $('.choiceAnswer').eq(index);
            let rightAnswer = this.rightResult;
            if ($.trim(answer) == $.trim(rightAnswer)) {
                _this.score = _this.score + 10;
                $item.css("background-color", "  #cce5ff");
                $item.children("span").addClass("iconfont icon-V");
            } else {
                $item.css("background-color", "#fff3cd");
                $item.children("span").addClass("iconfont icon-V1");
                $("div").each((index, item) => {
                    if ($.trim($(item).text()) == $.trim(rightAnswer)) {
                        $(item).animate({
                            width: "52%"
                        }).css("background-color", "#cce5ff");
                        $(item).children("span").addClass("iconfont icon-V");
                    }
                });
            }
            clearInterval(timer);
            re = setInterval(function () {
                _this.getRandProblem();
            }, 3000);

            // 移除click
            $(".choiceAnswer").unbind("click");

        }
    }
});