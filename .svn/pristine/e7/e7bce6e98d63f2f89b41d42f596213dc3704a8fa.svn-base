(function ($) {

    var LOADING_COVER_TEMPLATE = '<div class="loading-cover"></div>',
        LOADING_PANEL_TEMPLATE = '<p class="loading-panel"><img src="../../images/common/loading.gif" style="margin-bottom: -7px; " />正在加载请稍等...</p>';

    function initLoadingCover(statusText) {
        var $matchedEls = $(this);

        $matchedEls.each(function () {
            var el = this,
                $el = $(this),
                $cover = $(LOADING_COVER_TEMPLATE).insertAfter($el),
                $panel = $(LOADING_PANEL_TEMPLATE).insertAfter($el);

            $cover.css({
                    'left': $el.position().left,
                    'top': $el.position().top,
                    'width': $el.outerWidth(),
                    'height': $el.outerHeight()
                });

            if (statusText && statusText.length) {
                $panel.text(statusText);
            }

            $panel.css({
                    'left': $el.position().left + $cover.outerWidth() / 2,
                    'top': $el.position().top + $cover.outerHeight() / 2,
                    'margin-left': - $panel.outerWidth() / 2,
                    'margin-top': - $panel.outerHeight() / 2
                });

            if ($el.is('body')) {
                $panel.css({
                        'position': 'fixed',
                        'left': '50%',
                        'top': '50%',
                        'margin-left': - $panel.outerWidth() / 2,
                        'margin-top': - $panel.outerHeight() / 2
                    });

                $panel.appendTo($el);
                $cover.appendTo($el);
            }

            $(this).data('loading-cover', $cover);
            $(this).data('loading-panel', $panel);
        });

        return this;
    }

    function destroyCover() {
        var $matchedEls = $(this);

        $matchedEls.each(function () {
            var el = this,
                $el = $(this);

            if ($el.data('loading-cover') && $el.data('loading-cover').length) {
                $el.data('loading-cover').remove();
                $el.data('loading-panel').remove();
            }
        });

        return this;
    }

    var loadingExt = {
        add: function (arg0, arg1) {
            var settings = $(this).data('loading-settings');

            if (settings.direction === DIRECTIONS.BOTTOM_TO_TOP) {
                $(this).prepend($('<li />').html(arg1));
            } else {
                $(this).append($('<li />').html(arg1));
            }

            $(this).loading();

            return this;
        }
    };
    
    $.fn.startLoading = function (arg0) {

        return initLoadingCover.call(this, arg0);
    };

    $.fn.stopLoading = function (arg0) {

        return destroyCover.call(this, arg0);
    };

})(jQuery);