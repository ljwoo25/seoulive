// view JavaScript


// var dto = "${dto.seq}";
//
//
//
// $(document).ready(function (){
//     console.log(dto);
// });
//
// const loadList = () => {
//     //서버와 통신: 게시판 리스트 가져오기
//     $.ajax({
//         url : "comment/"+1,
//         type : "get",
//         dataType : "json",
//         success : makeView,
//         error : function () {alert("error");}
//     })
// }
//
// const makeView = (data) => {
//     console.log(data);
//
//     $.each(data,function (index,obj) {
//         var listHtml = "<div class='board-view'>";
//         listHtml+="<div class='board-comment-title'>내용</div>";
//         listHtml+="<div class='board-info'>";
//         listHtml+="<div class='board-info-lists'>";
//         listHtml+="<div class='board-info-list'>"+obj.writer+"</div>";
//         listHtml+="<div class='board-info-num'>"+obj.seq+"</div>";
//         listHtml+="</div>";
//         listHtml+="<div class='board-info-lists'>";
//         listHtml+="<div class='board-info-list'>날자</div>";
//         listHtml+="<div class='board-info-num'>"+obj.write_date+"</div>";
//         listHtml+="</div>";
//         listHtml+="</div>";
//         listHtml+="<div class='board-comment-info'>";
//         listHtml+="<div class='button_wrap'>";
//         listHtml+="<ul class='button_box'>";
//         listHtml+="<li><a href='#'>삭제</a></li>";
//         listHtml+="<li><a href='#'>수정</a></li>";
//         listHtml+="</ul>";
//         listHtml+="</div>";
//         listHtml+="</div>";
//         listHtml+="</div>";
//
//
//     })
//     var listHtml = "<div class='board-view'>";
//     listHtml+="<div class='board-comment-title'>내용</div>";
//     listHtml+="<div class='board-info'>";
//     listHtml+="<div class='board-info-lists'>";
//     listHtml+="<div class='board-info-list'>글쓴이</div>";
//     listHtml+="<div class='board-info-num'>번호</div>";
//     listHtml+="</div>";
//     listHtml+="<div class='board-info-lists'>";
//     listHtml+="<div class='board-info-list'>날짜</div>";
//     listHtml+="<div class='board-info-num'>2022-05-13</div>";
//     listHtml+="</div>";
//     listHtml+="</div>";
//     listHtml+="<div class='board-comment-info'>";
//     listHtml+="<div class='button_wrap'>";
//     listHtml+="<ul class='button_box'>";
//     listHtml+="<li><a href='#'>삭제</a></li>";
//     listHtml+="<li><a href='#'>수정</a></li>";
//     listHtml+="</ul>";
//     listHtml+="</div>";
//     listHtml+="</div>";
//     listHtml+="</div>";
//
//
//     $("#comment_area").html(listHtml);
//
//
// }
