$(function () {
    var isClear = false;
    var dateDom = document.getElementsByClassName('date');
    if (dateDom && dateDom.length) {
        for (var i = 0; i < dateDom.length; i++) {
            dateDom[i].innerHTML = moment().format('YY/MM/DD');
        }
    }

    var timer = window.setInterval(function () {
        var dateTime = document.getElementsByClassName('dateTime');
        var timeDom = document.getElementsByClassName('time');
        if (dateTime && dateTime.length) {
            for (var i = 0; i < dateTime.length; i++) {
                dateTime[i].innerHTML = moment().format('YY/MM/DD HH:mm:ss');
            }
        } else if (timeDom && timeDom.length) {
            for (var i = 0; i < timeDom.length; i++) {
                timeDom[i].innerHTML = moment().format('HH:mm:ss');
            }
        } else {
            isClear = true;
        }
    }, 1000);

    if (isClear) {
        window.clearInterval(timer);
    }

    //屏蔽鼠标右键
    document.oncontextmenu = function () {
        return false;
    }

    $(document).ready(function () {
        $('.fa-sign-out').on('click',function () {
            window.open('/logout', '_self');
        })
    })
});