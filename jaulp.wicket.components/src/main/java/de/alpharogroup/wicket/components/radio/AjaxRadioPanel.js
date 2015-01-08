$('.image-radio-container').on('change click', 'label', function() {
	var $selectedRadioId = $(this).attr("for");
	var $selectedRadio = $('.image-radio-container').find("#"+$selectedRadioId);
	var $radiogroupname = $selectedRadio.attr("name");
	var $radios = $('.image-radio-container input[type="radio"]');
    if($radios.is(':checked') === true) {
    	$('input:radio[name='+ $radiogroupname +']')
    		.each(function () { 
    			$(this).prop('checked', false).removeAttr('checked'); 
    		});
    }
    $selectedRadio.prop('checked',true).attr('checked',true);
});