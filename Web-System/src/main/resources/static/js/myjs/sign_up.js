$("#sign_upbut").click(function() {

    var username = $("#username").val()
    var password = $("#password").val()
    var pass = $("#pass").val()
    var email = $("#email").val()
    var pagename = $("#pagename").val()
    var url = $("#url").val()

    if (isNull(username, 4)) {
        alert("NO-username")
        return;
    }
    if (isNull(password, 6)) {
        alert("NOpassword")
        return;
    }
    if (isNull(pass, 6)) {
        alert("NOpass")
        return;
    }
    if (isNull(email, 4)) {
        alert("NOemail")
        return;
    }
    if (isNull(pagename, 2)) {
        alert("NO")
        return;
    }
    if (isNull(url, 2)) {
        alert("NO")
        return;
    }

    $.ajax({
        type: "POST",
        url: "/sign_up",
        data: {
            "username": username,
            "password": password,
            "email": email,
            "url": url,
            "pageusername": pagename
        },
        dataType: "JSON",
        success: function(response) {
            alert("OK")
        }
    });
});

function isNull(params, len) {
    if (params == null) {
        return true
    } else {
        if (params.length > 0) {
            if (params.length > len) {
                return false
            } else {
                return true
            }
        } else {
            return true
        }
    }
}