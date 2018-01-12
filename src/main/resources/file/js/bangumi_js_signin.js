/**
 * 如果用户已经登录的动作
 */

//获取动画的ID
var theid = $('#inputofanimeid').val()

$('#addbangumitouser').click(function() {
    $.ajax({
        url: "/bangumi/" + theid,
        type: "POST",
        data: theid,
        success: function(data) {
            if (data == "true") {
                $('#addbangumitouser').html('已定阅')
                Materialize.toast('订阅成功，记得来更新！', 10000)
            }
        },
        error: function() {
            Materialize.toast('网络出错，请稍后重试！', 10000)
        }
    })
})

// _blank
// 浏览器总在一个新打开、未命名的窗口中载入目标文档。

// _self
// 这个目标的值对所有没有指定目标的 <a> 标签是默认目标，它使得目标文档载入并显示在相同的框架或者窗口中作为源文档。这个目标是多余且不必要的，除非和文档标题 <base> 标签中的 target 属性一起使用。

// _parent
// 这个目标使得文档载入父窗口或者包含来超链接引用的框架的框架集。如果这个引用是在窗口或者在顶级框架中，那么它与目标 _self 等效。

// _top
// 这个目标使得文档载入包含这个超链接的窗口，用 _top 目标将会清除所有被包含的框架并将文档载入整个浏览器窗口。
$("#animeedit").click(function() {
    window.open("/bangumi/1044", "_self")
});

$("#dingyueed").click(function() {
    Materialize.toast('已经订阅啦！', 5000)
});