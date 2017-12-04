/**
 * 进行后台验证
 * 如果错误就返回错误信息
 * 正确就跳转
 */
var theusername = $("#username").val();
var thepassword = $("#password").val();
var theemail = $("#email").val();
var thepageusername = $("#pageusername").val();
var theurl = $("#url").val();

$("#sign_upbut").click(function(e) {
    $.ajax({
        type: "POST",
        url: "/sign_up",
        data: {
            "username": theusername,
            "password": thepassword,
            "email": theemail,
            "pageusername": thepageusername,
            "url": theurl
        },
        dataType: "JSON",
        success: function(response) {
            $.each(response, function(indexInArray, valueOfElement) {
                valueOfElement.indexInArray
            });
        }
    });
});