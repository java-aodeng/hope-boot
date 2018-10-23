(function($){
	var O = 'opacity',
		C = 'CursorMove',
		M = 'mousemove.drag',
		U = 'mouseup.drag',
		D = 'mousedown.drag',
		W = $(window),
		A = $(document),
		timer = null,
		E = function () {
			this.w.css(O, 1);
			A.unbind(M+' '+U);
		},
		G = function (e) {
			var m = this,
				p = m.w.offset(),
				t = p.top,
				l = p.left,
				r = W.width() - m.w.outerWidth(),
				b = W.height() - m.w.outerHeight(),
				X = e.pageX,
				Y = e.pageY,
				x = m.p.left + (X - m.x) - W.scrollLeft(),
				y = m.p.top  + (Y - m.y) - W.scrollTop();
			//以下逻辑保证在可视范围内移动
			if (l <= 0 && X < m.x)
			{
				x = 0;
				m.x = Math.max(X, 0);
				m.p.left = 0;
			}
			if (t <= 0 && Y < m.y)
			{
				y = 0;
				m.y = Math.max(Y, 0);
				m.p.top = 0;
			}
			if (r <= l - A.scrollLeft() && X > m.x)
			{
				x = r;
				m.x = Math.min(X, r);
				m.p.left = r;
			}
			if (b <= t - A.scrollLeft() && Y > m.y)
			{
				y = b;
				m.y = Math.min(Y, b);
				m.p.top = b;
			}
			m.w.css({ left : x, top : y });
			return false;
		},
		S = function (e, m) {
			e.preventDefault();
			m = this;
			m.w.css(O, 0.8);
			m.p = m.w.offset();
			m.x = e.pageX;
			m.y = e.pageY;
			A.bind(M, $.proxy(G, m)).bind(U, $.proxy(E, m));
		};
	$.fn.Drag = function(o){
		return this.each(function () {
			var e = $(this),
				x = function () {
					this.h = o ? (typeof o === 'string' ? $(o, e[0]) : o) : e;
					this.w = e;
					return this;
				},
				X = new x();
			e.data('__h', X.h);
			X.h.addClass(C).unbind(D).bind(D, $.proxy(S, X));
		});
	};
	$.fn.unDrag = function () {
		return this.each(function(){
			($(this).data('__h') || $()).removeClass(C).unbind(D);
		});
	};
})($);
