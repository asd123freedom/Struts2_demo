(function($){


	$BODY = $('body');
	var show = $.fn.modal.Constructor.prototype.show;

	$.fn.modal.Constructor.prototype.show = function() {


		var zindex = 1040;

		$('.modal.in').each(function(i, elem) {
			var z = $(elem).css('z-index') - 0;
			if (z >= zindex)
				zindex = z + 10;
		});

		this.$element.css('z-index', zindex + 10);

		show.apply(this, arguments);
		console.log(this.$backdrop);
		this.$backdrop.css('z-index', zindex);
		
		

		
	}

	$.fn.modal.Constructor.prototype.enforceFocus = function() {
		var that = this
        $(document).on('focusin.modal', function (e) {

          if (that.$element[0] !== e.target && !that.$element.has(e.target).length) {

          	var $target = $(e.target);
          	if (!$target.hasClass('modal'))
          		$target = $target.parents('.modal:eq(0)');

          	if ($target.length) {
          		var z_order_cur_target = $target.css('z-index') - 0;
          		var z_order_that = that.$element.css('z-index') - 0;
          		if (z_order_cur_target > z_order_that)
          			return;
          	}

            that.$element.focus()
          }
        })
	}

}(jQuery));