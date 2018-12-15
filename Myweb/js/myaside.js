$(function() {
	$('.my-nav-dropdowm').click(function(){
		$(this).find('.my-dropdowm-menu').toggleClass('hidden');
		$(this).find('.icon-xiala2').add($(this).find('.icon-zuo')).toggleClass('icon-xiala2 icon-zuo')
		return false;
	})

})