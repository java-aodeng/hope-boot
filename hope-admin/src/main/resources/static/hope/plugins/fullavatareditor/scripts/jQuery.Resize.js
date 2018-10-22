(function($){
	var doc = $(document),
		win = $(window),
		mouseDown = 'mousedown.resize',
		mouseMove = 'mousemove.resize',
		mouseUp = 'mouseup.resize',
		clsName = 'CursorResize',
		resize = function (e) {
			var o = this,
				a = o.wrapper.width('auto'),
				b = o.target,
				c = 0,
				d = 0,
				f = b.offset();
			if (a)
			{
				c = a.width() - b.width();
				d = a.height() - b.height();
				f = a.offset();
			}
			var m = $.extend({
					width  : win.width() - (f.left - doc.scrollLeft()) - c,
					height : win.height() - (f.top - doc.scrollTop()) - d
				}, o.max),
				w = Math.min(Math.max(e.pageX - o.x + o.w, o.min.width), m.width),
				h = Math.min(Math.max(e.pageY - o.y + o.h, o.min.height),m.height);
			b.css({ width : w, height : h });
			return false;
		},
		end = function () {
			doc.unbind(mouseMove + ' ' + mouseUp);
		},
		start = function (e) {
			var E = this,
				T = E.target;
			E.x = e.pageX;
			E.y = e.pageY;
			E.w = T.outerWidth()  - T.getPadding().w;
			E.h = T.outerHeight() - T.getPadding().h;
			if (E.min.width  === 0)
			{
				var MW = T.data('_mw');
				if (MW)
				{
					E.min.width = MW;
				}
				else
				{
					E.min.width  = T.outerWidth()  - T.getPadding().w;
					T.data('_mw', E.min.width);
				}
			}
			if (this.min.height === 0)
			{
				var MH = T.data('_mh');
				if (MH)
				{
					E.min.height = MH;
				}
				else
				{
					E.min.height = T.outerHeight() - T.getPadding().h;
					T.data('_mh', E.min.height);
				}
			}
			doc.bind(mouseMove, $.proxy(resize, E)).bind(mouseUp, end);
			return false;
		};
	$.fn.resize = function(o){
		o = $.extend({ min : { width : 0, height: 0 }}, o);
		/*
		options = {
			handler : null,
			wrapper : null,
			min : { width : 0, height: 0},
			max : { width : 0, height: 0}
		};
		*/
		return this.each(function(){
			var e = $(this),
				x = function () {
					this.target = e;
					this.handler = o.handler ? (typeof o.handler === 'string' ? $(o.handler, e[0]) : o.handler) : e;
					this.wrapper = o.wrapper;
					e.data('_h', this.handler);
					this.min = o.min;
					this.max = o.max;
					if (o.min) $.extend(this.min, o.min);
					return this;
				},
				X = new x();
			X.handler.addClass(clsName).unbind(mouseDown).bind(mouseDown, $.proxy(start, X));
		});
	};
	$.fn.unResize = function () {
		return this.each(function(){
			($(this).data('_h') || $()).removeClass(clsName).unbind(mouseDown);
		});
	};
	$.fn.getPadding = function () {
		var s = this[0].style,
			o =
			{
				w : parseInt(s.paddingLeft) + parseInt(s.paddingRight)  || 0,
				h : parseInt(s.paddingTop) +  parseInt(s.paddingBottom) || 0
			};
		return o;
	};
})($);
