myHover = function() {
	var sfEls = document.getElementById("nav").getElementsByTagName("LI");
	for (var i=0; i<sfEls.length; i++) {
		sfEls[i].onmouseover=function() {
			this.className+=" myhover";
		};
		sfEls[i].onmouseout=function() {
			this.className=this.className.replace(new RegExp(" myhover\\b"), "");
		};
	}
};
if (window.attachEvent) window.attachEvent("onload", myHover);