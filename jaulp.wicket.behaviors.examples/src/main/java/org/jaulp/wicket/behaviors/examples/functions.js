$(window).load(function(){
	$("a[class^='navbarlink']").click(function () {
		$(this).siblings().removeClass('navbarlink-underline');
		$(this).toggleClass('navbarlink-underline');
	});
});

function setFocus(comp) {
	comp.focus();
	comp.select();
}

function alertnow(){

}

