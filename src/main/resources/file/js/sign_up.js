/**
 * 进行后台验证
 * 如果错误就返回错误信息
 * 正确就跳转
 */

$("#sign_upbut").click(function(e) {

    var theusername = $("#username").val();
    var thepassword = $("#password").val();
    var theemail = $("#email").val();
    var thepageusername = $("#pagename").val();
    var theurl = $("#url").val();

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
            if (response == "true") {
                alert("jj")
                    // windows.location.href = "/sign_in";
            }
        },
        error: function(params) {
            // $("#").html(htmlString);
        }
    });
});