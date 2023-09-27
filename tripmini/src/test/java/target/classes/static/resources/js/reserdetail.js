// 페이지 이동없이 내용 변경 
$(function() {
	$('.floor:first').show();
	$('.nav_tabs a').click(function(){
		$('.nav_tabs a').removeClass('active1');
		$(this).addClass('active1');
		$('.floor').hide();
		$('#f' + $(this).data('floor')).show();
	});
});

