$(function () {
    $(".enter").click(function () {
        $(".gameEnter").fadeOut("slow");
        $(".choice").fadeIn(2000);
        var a = true;
        var len = 60;
        if (a) {
            var $interval = $(".interval");
            $interval.innerHTML = len + 's';
            var time = setInterval(function () {
                $interval.innerHTML = parseFloat($interval.innerHTML) - 1 + 's';
                if ($interval.innerHTML === '5s') {
                    clearInterval(time);
                    a = true;
                }
            }, 1000);
            a = false;
        } else {
            return false;
        }
    });

    $(".choiceAnswer").click(function () {
        var $text = $(this).text();
        if ($.trim($text) === "干垃圾") {
            $(this).css("background-color", "  #cce5ff");
            $(this).children("span").addClass("icon-V");
        } else {
            $(this).css("background-color", "#fff3cd");
            $(this).children("span").addClass("icon-V1");
            var $right = $(".choiceAnswer:contains(\"干垃圾\")");
            $right.animate({
                width: "52%", height: "+=10px"
            }).css("background-color", "#cce5ff");
            $right.children("span").addClass("icon-V")
        }
        // 移除click
        $(".choiceAnswer").unbind("click");
    });

});