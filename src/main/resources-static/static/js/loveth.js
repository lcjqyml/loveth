$(document).ready(function() {
    $("#th1314").bind("click", function() {
        checkClick("th1314");
    });
    $("#lth").bind("click", function() {
        checkClick("l");
    });
    $("#oth").bind("click", function() {
        checkClick("o");
    });
    $("#vth").bind("click", function() {
        checkClick("v");
    });
    $("#eth").bind("click", function() {
        checkClick("e");
    });
});
var clickRecord = "";
var finalPwd = "loveth1314";
function checkClick(cw) {
    clickRecord = clickRecord + cw;
    if (finalPwd == clickRecord) {
        window.location.href="toLogin.html"
    } else if (!finalPwd.startsWith(clickRecord)) {
        clickRecord = "";
    }
}

function reloadPics() {
    window.location.reload();
}