$('.image-checkbox-container').on('click', 'img', function(){
    if(!$(this).prev('input[type="checkbox"]').prop('checked')){
        $(this).prev('input[type="checkbox"]').prop('checked', true).attr('checked','checked');
        this.style.border = '4px solid #38A';
        this.style.margin = '0px';
    }else{
        $(this).prev('input[type="checkbox"]').prop('checked', false).removeAttr('checked');
        this.style.border = '0';
        this.style.margin = '4px';
    }
});
