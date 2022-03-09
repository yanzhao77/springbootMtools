this.stringUtils = this.stringUtils || {
    lineHeight: 23,
    openSubWindow: function (value) {
        let lists = $('div').find('li');
        for (let i = 0; i < lists.length; i++) {
            let dom = $(lists[i]);
            if (dom && dom.attr('number') && dom.attr('number') == value) {
                let href = dom.find('a').attr('href') || dom.find('span').attr('href');
                if (href) {
                    window.open(href);
                }
            }
        }
    },

    // 获取URL中的 指定的参数值
    getQueryString: function (name) {
        var url = window.location.search; // 获取URL
        var pattern = new RegExp("[\?\&]" + name + "=([^\&]+)", "i"); // 正则匹配URL
        var matcher = pattern.exec(url);
        if (matcher == null || matcher.length < 1) {
            return "";
        }
        return decodeURIComponent(matcher[1]); // 输出指定的参数值 中文也可以
    },

    // 根据URL，读取json文件
    getJsonTextForUrl: function (url, method) {
        let result = [];
        $.ajax({
            url: url,
            type: method || "GET",
            async: false,
            dataType: "json",
            success: function (data) {
                result = data;
            },
            error: function (msg) {
                toastr.error(msg);
            }
        })
        return result;
    },

    //浮点数加法运算
    floatAdd: function (arg1, arg2) {
        let r1, r2, m;
        try {
            r1 = arg1.toString().split(".")[1].length
        } catch (e) {
            r1 = 0
        }
        try {
            r2 = arg2.toString().split(".")[1].length
        } catch (e) {
            r2 = 0
        }
        m = Math.pow(10, Math.max(r1, r2));
        return (arg1 * m + arg2 * m) / m;
    },

    /*
    *  设置 class为kpp-row-css样式的行 距离顶部的距离
    *  lineHeight (行间距，单位px，默认23px)
    *  isSetting: boolean (是否设置，默认为false)
    *  lineNum: number (页面行数)
    * */
    setLineHeight: function (lineHeight, isSetting, lineNum) {
        if (!lineNum) {
            lineNum = 24;
        }
        lineNum = lineNum + 2;
        document.body.style.zoom = document.body.style.zoom || 1;
        // var availHeight = window.innerHeight / document.body.style.zoom;
        var availHeight = 657 / document.body.style.zoom;
        var lineHeight = availHeight / lineNum;
        stringUtils.lineHeight = lineHeight;
        !!lineHeight ? lineHeight : lineHeight = stringUtils.lineHeight;
        if (!isSetting) {
            return;
        }
        var rows = document.getElementsByClassName('kpp-row-css');
        var header = document.getElementsByClassName('kpp-header');
        var content = document.getElementsByClassName('kpp-content');
        var footer = document.getElementsByClassName('kpp-footer');
        if (header) {
            $(header).css({
                "height": lineHeight + "px",
                "line-height": lineHeight + "px"
            });
        }

        $(content).css({"top": lineHeight + "px"});
        if (footer) {
            $(footer).css({
                "top": (lineNum - 1) * lineHeight + "px",
                "height": lineHeight + "px",
                "line-height": lineHeight + "px"
            });
        }
        if (!rows) {
            return;
        }
        for (var i = 0; i < rows.length; i++) {
            var lineNumber = rows[i].getAttribute('lineNumber') || 0;
            if (!!lineNumber) {
                $(rows[i]).css({"top": (lineNumber - 1) * lineHeight + "px"});
            } else {
                $(rows[i]).css({"top": i * lineHeight + "px"});
            }
        }
    },

    /**
     *  根据屏幕分辨率可用宽度设置字体的间距
     *
     *  columnNum:  number (页面列数)
     */

    setFontSpace: function (columnNum) {
        if (!columnNum) {
            columnNum = 80;
        }
        var innerWidth = window.innerWidth;// window.screen.availWidth;
        var spaceWith = innerWidth / columnNum;
        var a = document.body;
        a.setAttribute("style", "letter-spacing:" + spaceWith + "px;");

        var rows = document.getElementsByClassName('halfWith');
        if (!rows) {
            return;
        }
        for (var i = 0; i < rows.length; i++) {
            $(rows[i]).css({'letter-spacing': spaceWith / 2 + 'px'})
        }
    },

    /**
     *  columnNum:  number (页面列数)
     */
    setLeftWidth: function (columnNum) {
        if (!columnNum) {
            columnNum = 80;
        }
        var rows = document.getElementsByClassName('kpp-row-css');
        if (!rows) {
            return;
        }
        // var innerWidth = window.innerWidth;
        var innerWidth = 1280;
        for (var i = 0; i < rows.length; i++) {
            var chlldrenDivs = $(rows[i]).find('div');
            for (var j = 0; j < chlldrenDivs.length; j++) {
                var dom = chlldrenDivs[j];
                var left = dom.style.left;
                var ll = left.replace('px', '');
                $(dom).css({"left": (ll * (innerWidth / columnNum)) + "px"});
            }
            /* chlldrenDivs.map((key, dom) => {
                 var left = dom.style.left;
                 var ll = left.replace('px', '');
                 $(dom).css({"left": (ll * (innerWidth / columnNum)) + "px"});
             })*/
        }
    },

    /**
     * 页面加载完成后重新渲染页面比例
     */
    initHtml: function (columnNum, lineNum) {
        // stringUtils.setFontSpace();
        stringUtils.setLeftWidth(columnNum);
        window.onresize = function () {
            stringUtils.resizeHtml(lineNum);
        }

        $(document).ready(function () {
            stringUtils.resizeHtml(lineNum);
        });
    },
    resizeHtml: function (lineNum) {
        var t = window.devicePixelRatio;
        var wBro = window.innerWidth;
        var wInner = window.innerWidth;
        if (wBro != 1920 && wBro < 1366) {
            document.body.style.zoom = wInner / 1280;
        } else {
            document.body.style.zoom = 1;
        }
        stringUtils.setLineHeight(null, true, lineNum);
    }
}