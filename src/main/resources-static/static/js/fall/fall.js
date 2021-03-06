$(function() {
    $("#imgUploadInput").on("change", function() {
        if ($("#imgUploadInput").val() != "") {
            startUploadImages();
        }
    });

    $(".viewerImgs").viewer({
        url: 'data-original'
    });
})

function startUploadImages() {
    var files = $("#imgUploadInput")[0].files;
    var rsts  = new Array();
    for(var fIndex = 0; fIndex < files.length; fIndex++) {
        lrz(files[fIndex], {width: 900}).then(function(rst) {
            rsts.push(rst);
            if (rsts.length == files.length) {
                bootbox.confirm({
                    title: '上传确认',
                    message: buildReviewMsg(rsts),
                    size: 'large',
                    callback: function (result) {
                        if (result) {
                            showLoading('上传中...');
                            var uploaded = 0;
                            for (var rIndex = 0; rIndex < rsts.length; rIndex++) {
                                var rst = rsts[rIndex];
                                rst.formData.append('fileLen', rst.fileLen);
                                $.ajax({
                                    url: 'images/uploadImg.do', // 这个地址做了跨域处理，可以用于实际调试
                                    data: rst.formData,
                                    processData: false,
                                    contentType: false,
                                    type: 'POST',
                                    success: function (data) {
                                        if (++uploaded == rsts.length) {
                                            window.setTimeout(function() {
                                                closeLoading();
                                                window.location.reload();
                                            }, 3000);
                                        }
                                    }
                                });
                            }
                        }
                        $("#imgUploadInput").val("");
                    }
                });
            }
        });
    }
}

function buildReviewMsg(rsts) {
    var row = document.createElement("div");
    row.className = "row";
    for (var rIndex = 0; rIndex < rsts.length; rIndex++) {
        var rst = rsts[rIndex];
        var oriImg = rst.origin;
        var img = document.createElement('img'),
            div = document.createElement('div'),
            p = document.createElement('p'),
            sourceSize = toFixed2(oriImg.size / 1024),
            resultSize = toFixed2(rst.fileLen / 1024),
            scale = parseInt(100 - (resultSize / sourceSize * 100));

        p.style.fontSize = 13 + 'px';
        p.innerHTML      = '源文件：<span class="text-danger">' +
            sourceSize + 'KB' +
            '</span> <br />' +
            '压缩后传输大小：<span class="text-success">' +
            resultSize + 'KB (省' + scale + '%)' +
            '</span> ';

        div.className = 'col-sm-6 col-md-4';
        div.appendChild(img);
        div.appendChild(p);
        img.style.maxWidth = "280px";
        img.src = rst.base64;
        row.appendChild(div);
    }
    var notice = document.createElement("h3");
    notice.className = "col-md-12 text-center";
    notice.innerHTML = "是否上传以上图片？";
    notice.style.color = "black";
    row.appendChild(notice);
    return row.outerHTML;
}

function uploadImages() {
    $("#imgUploadInput").click();
}

function removeImg(imgName) {
    bootbox.confirm("是否删除？", function(result) {
        if (result) {
            showLoading('删除中...');
            $.ajax({
                url: 'images/deleteImg.do',
                type: 'post',
                data: {
                    imgName: imgName
                },
                success: function(data) {
                    if (data == 'success') {
                        closeLoading();
                        window.location.reload();
                    }
                }
            });
        }
    });
}

function showViewer(imgIndex) {
    $(".viewerImgs").on({
        'shown.viewer':  function (e) {
           $(".viewerImgs").viewer('view', imgIndex);
        }
    }).viewer('show');
}

function toViewerPage() {
    window.open("/love.html");
}

function resizeImgs() {
    var width = $("img.lazy")[0].width;
    $("img.lazy").each(function() {
        var $dom = $(this)
        var oriWidth = $dom.attr('width');
        var oriHeight = $dom.attr('height');
        var resultHeight = Math.floor(oriHeight * (width / oriWidth));
        $dom.attr('width', width);
        $dom.attr('height', resultHeight);
    });
}

/**
 *
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　 ┣┓
 * 　　　　┃　　　　 ┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 *
 */
