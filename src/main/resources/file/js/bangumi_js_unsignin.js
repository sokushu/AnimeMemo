/**
 * 如果用户没有登录的动作
 */

$("#addbangumitouser").click(function() {
    var $toastContent = $('<span>需要登录才能订阅哦 0.0</span>').add($('<a class="btn-flat toast-action" href="/sign_in">登录</a>'));
    Materialize.toast($toastContent, 10000);
});

$("#animeedit").click(function() {
    var $toastContent = $('<span>需要登录才能编辑哦 0.0</span>').add($('<a class="btn-flat toast-action" href="/sign_in">登录</a>'));
    Materialize.toast($toastContent, 10000);

});