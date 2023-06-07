 function chk_form() {
    document.getElementById('frm').submit();
}

$(function (){
//summernote 생성
    $(".write-form").find("[name=content]").summernote({
        height:300, //기본 높이
        minHeight:300, //최소 높이
    });
});