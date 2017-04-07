function toPage(page) {
    if (page == 'fall') {
        $("#breadcrumb .title").html("图片管理");
        $("#breadcrumb .active").html("瀑布管理");
        $('#fallFrame').attr('src', "images/fall.html");
    }
}

function refresh() {
    $('#fallFrame').attr('src', $('#fallFrame').attr('src'));
}

function openInNew() {
    window.open($('#fallFrame').attr('src'));
}

function logout() {
    window.location.href="/toLogin.html"
}