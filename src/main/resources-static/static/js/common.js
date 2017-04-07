var dlgs = new Array();
function showLoading(msg) {
    dlgs.push(bootbox.dialog({ message: '<div class="text-center"><i class="fa fa-spin fa-spinner"></i>' + msg + '</div>', closeButton: false}))
}

function closeLoading() {
    dlgs.pop().modal('hide');
}

function toFixed2(num) {
    return parseFloat(+num.toFixed(2));
}

function randNum(minnum, maxnum){
    return Math.floor(minnum + Math.random() * (maxnum - minnum));
}

// e.g.: http://domain.com/path/to/picture.jpg?size=1280脳960 -> picture.jpg
function getImageName(url) {
    var name = isString(url) ? url.replace(/^.*\//, '').replace(/[\?&#].*$/, '') : '';
    name = name.replace(/^p_/, '').replace(/^\d{13}/, '');
    return name;
}

function isString(s) {
    return typeof s === 'string';
}

//重写viewer image url加载方法。
if (typeof $.fn.viewer == 'function') {
    $.fn.viewer.Constructor.prototype.initList = function() {
        var options = this.options;
        var $this = this.$element;
        var $list = this.$list;
        var list = [];
        this.$images.each(function (i) {
            var url = options.url;
            if (isString(url)) {
              url = this.getAttribute(url);
            } else if ($.isFunction(url)) {
              url = url.call(this, this);
            }
            if (!url) {
                return;
            }
            var alt = this.alt || getImageName(url);
            list.push(
              '<li>' +
                '<img' +
                  ' src="' + url + '"' +
                  ' data-action="view"' +
                  ' data-index="' +  i + '"' +
                  ' data-original-url="' +  url + '"' +
                  ' alt="' +  alt + '"' +
                '>' +
              '</li>'
            );
        });

        $list.html(list.join('')).find('img').one('load.viewer', {
            filled: true
        }, $.proxy(this.loadImage, this));

        this.$items = $list.children();

        if (options.transition) {
            $this.one('viewed.viewer', function () {
              $list.addClass('viewer-transition');
            });
        }
    }
}