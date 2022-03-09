
function getMsg() {
	$.ajax({
		type: "post",
		url: "/getCount",
		dataType: "json",
		contentType: "application/json",
		success: function(res) {
			if (res > 0) {
				$("#count").html(res)
				$("#count").show()
			} else {
				$("#count").html("")
				$("#count").hide()
			}


		}
	})
}

window.setInterval(function() {
	getMsg()

}, 6000)

function add_info() {
	//var form = document.getElementById('updateform');
	var form = $("#updateform").serializeObject()

	if (form.msgData == null || form.msgData == '') {
		alert("内容不能爲空")
		return
	} else if (form.msgConsumer == null) {
		alert("接收方不能爲空")
		return
	} else if (form.msgLevel == null) {
		alert("信息級別不能為空")
		return
	}

	$.ajax({
		type: "post",
		url: "/sendMsg",
		data: JSON.stringify(form),
		dataType: "json",
		contentType: "application/json",
		success: function(res) {
		}
	})


	$('#myModal').modal('hide')

}