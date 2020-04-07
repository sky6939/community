$(function () {
    $(".questionMsg").hide();
    $("#question").submit(function () {
        if($("#title").val().trim() == "") {
            $(".questionMsg").show().html("标题不能为空！");
        } else if($("#description").val().trim() == "") {
            $(".questionMsg").show().html("问题描述不能为空！");
        } else if($("#tag").val().trim() == "") {
            $(".questionMsg").show().html("标签不能为空！");
        } else {
            $(".questionMsg").hide();
            return true;
        }
        return false;
    });
});