$(function () {
    $("#login").click(function () {
        var username = $("#username").val();
        var pwd = $("#password").val();
        $.post("user/login", {username: username, pwd: pwd}, function (res) {
            console.log(res)
            if (res.code === 1) {
                // $('[input]').css(s)

            } else {
                console.log();
                let str = res.data;
                console.log(str)
                let split = str.split(",");
                $(split).each(function (index, value) {
                    let id = '#' + value
                    $(id).css("border-color", "red");
                })
            }
        });
    });
});