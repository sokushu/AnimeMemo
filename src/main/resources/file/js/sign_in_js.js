/**
 * 进行后台验证
 * 如果错误就返回错误信息
 * 正确就跳转
 */
$(document).ready(function() {
    //开启弹出框
    $('.modal').modal();
    $('#textarea1').val('');
    $('#textarea1').trigger('autoresize');
});

$("#sendinfo").click(function() {
    var findusername = $("#findusername").val();
    var findemail = $("#findemail").val();
    var info = $("#textarea1").val();

    $.ajax({
        type: "POST",
        url: "urlkjkjkj",
        data: {
            "findusername": findusername,
            "findemail": findemail,
            "info": info
        },
        dataType: "JSON",
        success: function(response) {
            Materialize.toast('发送成功！请等待一段时间，我收到后就会发送密码到您的邮箱。', 10000);
        },
        error: function() {
            Materialize.toast('网络似乎有问题，发送失败！您可以稍等一会重新尝试发送。', 10000);
        }
    });

});

$('#login').click(function() {

    var username = $('#username').val().trim()
    var password = $('#password').val().trim()
    if (username == "" || password == "") {
        $('#showmessage').html("请完整填写用户名和密码")
        return false;
    }
    $.ajax({
        url: "/ajaxs/sign_in",
        type: "POST",
        data: {
            username: username,
            password: password
        },
        success: function(data) {
            if (data == true) {
                $('#form_sign_in').submit()
            } else {
                $('#showmessage').html("用户或密码错误")
                return false;
            }
        }
    })
})