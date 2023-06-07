$(function(){
    // list faq 아코디언
    $(document).on('click','.topic .question', function (){
        $(this).each(function(){
            $(this).click(function(e){
                e.preventDefault(e);
                let elem = $(this).parents().next('p');
                if(elem.prev().hasClass('active')){
                    elem.prev().removeClass('active');
                    $(this).siblings('span').removeClass('faq-o').addClass('faq-t');
                    elem.stop().slideUp();
                } else{
                    elem.prev().addClass('active');
                    $(this).siblings('span').removeClass('faq-t').addClass('faq-o');
                    elem.stop().slideDown();
                }
            });
        });
    });

});

