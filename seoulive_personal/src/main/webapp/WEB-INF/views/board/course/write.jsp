<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Course_Write</title>

<link rel="stylesheet" href="https://kit.fontawesome.com/889f069cfd.css" crossorigin="anonymous">

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/course_write_copy.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/reset.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout/footer.css">

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script src="https://kit.fontawesome.com/889f069cfd.js" crossorigin="anonymous"></script>

<style>
        #map {

            width: 380px;
            height: 500px;

        }
</style>

</head>


<body>

	<div id="header">
		<div class="wrap">
			<h1 class="logo">
				<a href="#"><img
					src="${pageContext.request.contextPath}/resources/img/img-logo.png"
					width="240" height="60"></a>
			</h1>
			<ul class="bn">
				<a href="#" class="fl">
					<li>여행지</li>
				</a>
				<a href="#" class="fl">
					<li>일정</li>
				</a>

				<a href="#" class="fl">
					<li>자유게시판</li>
				</a>

			</ul>

			<ul class="bn">

				<a href="#" class="fr">
					<li>회원가입</li>
				</a>
				<a href="#" class="fr">
					<li>로그인</li>
				</a>

			</ul>

		</div>

	</div>

	<!-- ===================================================================================== -->

	
		<!-- seq, WRITE_DATE는 디폴트 값 -->
		<input type="hidden" name="WRITER" value="NICKNAME">
		<div class="write">
			<div>
				<div>
					제목: <input type="text" name="TITLE">
				</div>
				<div class="write_func">
					<label for="birthday">출발일자:</label> 
					<input type="date" name="DURATION_START" onchange="updateEndDate()" id="startDateInput"> 
					<input class="day_plus" type="button" value="Day추가" onclick="addWriteDay()">
				</div>
				<input type="hidden" name="DURATION_END" id="durationEnd" value="">
				<div>
					내용: <input type="text" name="REVIEW">
				</div>
			</div>
			<ul id="day_ul">
				<li>
					<div class="write_day" id="day1">
						
						<div class="write_day_left">
							<span class="day_text">1일차</span>
							<input type="hidden" class="deleteButton" onclick="deleteElement(event)" value="삭제">
						</div>
						<div class="write_day_right">
							<ul class="dec_ul" id="list1">
								<li>
									<div class="write_day_top">
										<span class="day_text2">Day1. </span><span id="selectedDate1"></span> 
									</div>
								</li>

								<li>
								    <div class="write_day_dec_search" id="travel1_1">
								        <input type="search" class="keyword" placeholder="검색어를 입력해주세요.">
								        <input type="button" value="검색" onclick="search(event)">
								        <input type="button" class="deleteButton" onclick="deleteElement(event)" value="삭제">
								    </div>
								    <div class="searchResult" id="result1_1">
								    </div>
								</li>
							</ul>
							<ul>
								<li>
									<div id="dec_plus" class="dec_plus">
										<input type="button" value="여행지 추가">
									</div>
								</li>
							</ul>
						</div>
						
					    <div id="map"></div>
					    
					</div>
				</li>
			</ul>
			<div class="write_day">
				<div id="day_plus" class="day_plus">
					<input type="button" value="Day 추가">
				</div>
			</div>
			<div class="write_button">
				<input type="button" value="등록" onclick="sendData()">
			</div>
		</div>


	<!-- script -->
	<script src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=1e94da7a1ab1c55879fcde4cfe8d086d&libraries=services"></script>
    <script>

	    var mapContainer = document.getElementById('map');
        var options = {
            center: new kakao.maps.LatLng(37.5665, 126.9780), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };
        var map = new kakao.maps.Map(mapContainer, options); // 지도 생성
	    
	    function initMap() {
	    	var mapContainer = document.find('.map')[0];
	    	var options = {
	    	        center: new kakao.maps.LatLng(37.5665, 126.9780), // 지도의 중심좌표
	    	        level: 3 // 지도의 확대 레벨
	    	    };

    	    var map = new kakao.maps.Map(mapContainer, options); // 지도 생성

    	    // mapContainer 내부의 내용을 모두 비우고, 생성된 지도 컨테이너를 삽입
    	    mapContainer.appendChild(map.getContainer()); // 생성된 지도 컨테이너 삽입
	    }
	    
    </script>
	
	<script>
		// =====================================
		// Day와 여행지 개수 추가하는 J-Query
		// =====================================
		$(document).on('click', '.dec_plus', function() {
			var dayId = $(this).closest('.write_day').attr('id');
			var dayCount = parseInt(dayId.slice(3));
		    var searchCount = $('#list' + dayCount + ' .write_day_dec_search').length + 1; // 기존 write_day_dec_search 클래스 개수
		    var searchId = 'travel' + dayCount + '_' + searchCount; // 고유한 id 값 생성
		    var resultId = 'result' + dayCount + '_' + searchCount;
		    
		    $(this).closest('.write_day').find('.dec_ul').append(
		        '<li>\
		            <div class="write_day_dec_search" id="' + searchId + '">\
		                <input type="search" class="keyword" placeholder="검색어를 입력해주세요.">\
		                <input type="button" value="검색" onclick="search(event)">\
		                <input type="button" class="deleteButton" onclick="deleteElement(event)" value="삭제">\
		            </div>\
		            <div class="searchResult" id="' + resultId + '">\
		            </div>\
		        </li>');
		    

		});
		
		$(document).on('click', '.day_plus', function() {
		    var dayCount = $('.write_day').length; // 기존 write_day 클래스 개수에 1을 더한 값
		    var searchCount = $('#list' + dayCount + ' .write_day_dec_search').length + 1; // 기존 write_day_dec_search 클래스 개수
		    var searchId = 'travel' + dayCount + '_' + searchCount; // 고유한 id 값 생성
		    var resultId = 'result' + dayCount + '_' + searchCount;

		    $("#day_ul").append(
		        '<li>\
		            <div class="write_day" id="day' + dayCount + '">\
		                <div class="write_day_left">\
		                    <span class="day_text">' + dayCount + '일차</span>\
		                    <input type="button" class="deleteButton" onclick="deleteElement(event)" value="삭제">\
		                </div>\
		                <div class="write_day_right">\
		                    <ul class="dec_ul" id="list' + dayCount + '">\
		                        <li>\
		                            <div class="write_day_top"> <span class="day_text2">Day' + dayCount + '. </span><span id="selectedDate' + dayCount + '"></span>\
		                            </div>\
		                        </li>\
		                        <li>\
		                            <div class="write_day_dec_search" id="' + searchId + '">\
		                                <input type="search" class="keyword" placeholder="검색어를 입력해주세요.">\
		                                <input type="button" value="검색" onclick="search(event)">\
		                                <input type="button" class="deleteButton" onclick="deleteElement(event)" value="삭제">\
		                            </div>\
		                            <div class="searchResult" id="' + resultId + '">\
		                            </div>\
		                        </li>\
		                    </ul>\
		                    <ul>\
		                        <li>\
		                            <div id="dec_plus_' + dayCount + '" class="dec_plus">\
		                                <input type="button" value="여행지 추가">\
		                            </div>\
		                        </li>\
		                    </ul>\
		                </div>\
		                <div class="map"></div>\
		            </div>\
		        </li>');
		    updateEndDate();
		    
		    
		    var startDateInput = document.getElementById('startDateInput');
		    var startDate = new Date(startDateInput.value);
		    var selectedDate = new Date(startDate);
		    selectedDate.setDate(selectedDate.getDate() + dayCount - 1); // 증가된 날짜 계산
		    var selectedDateFormatted = selectedDate.toISOString().split("T")[0]; // YYYY-MM-DD 형식으로 변환
		    var selectedDateElement = document.getElementById("selectedDate"+dayCount);
		    selectedDateElement.textContent = "[" + selectedDateFormatted + "]";
		});

		// =====================================
		// ======여행지 선택하는 스크립트=======
		// =====================================
		
		
		function search(event) {
		  var keywordInput = event.target.parentNode.querySelector('.keyword');
		  var keyword = keywordInput.value.trim(); // 검색어 가져오기
		
		  // 검색어가 비어 있는 경우 처리
		  if (keyword === "") {
		    alert("검색어를 입력해주세요.");
		    return;
		  }
	
		  var resultId = event.target.parentNode.nextElementSibling.id;
		  
		  // AJAX를 사용하여 검색 결과 요청
		  $.ajax({
		    type: "GET",
		    url: "/board/course/travel_search",
		    data: { keyword: keyword,
		    		resultId: resultId},
		    success: function(response) {
		      var newWindow = window.open("", "_blank", "width=600,height=400");
		      newWindow.document.write(response);
		
		      // 검색 완료 후 입력란 초기화
		      keywordInput.value = "";
		      
		     
		    },
		    error: function(xhr, status, error) {
		      console.error(xhr.responseText);
		    }
		  });
		}
		  // 선택된 데이터 처리
		function handleSelectedData(seq, category, title, address, resultId) {
		
			var li = $("#" + resultId);
			
			// 검색 결과를 표시하는 부분 숨기기
			var searchDiv = $(".write_day_dec_search");
			searchDiv.each(function() {
			  var targetId = $(this).attr("id");
			  var targetSubstring = targetId.substring(6); // 맨 앞 6자리를 제외한 부분
			
			  var resultSubstring = resultId.substring(6); // 맨 앞 6자리를 제외한 부분
			
			  if (targetSubstring === resultSubstring) {
			    $(this).css("display", "none");
			  }
			});
			
			var seqInput = $("<input>").attr("type", "hidden")
						         .attr("name", "SEQ")
						         .val(seq);
			li.append(seqInput);
			
			var imgDiv = $("<div>").addClass("dec_img");
			var img = $("<img>").attr("src", "../../../resources/img/seoul_forest.png")
			                     .attr("alt", "")
			                     .css("width", "80px")
			                     .css("height", "50px");
			imgDiv.append(img);
			li.append(imgDiv);
			
			var titleCategoryDiv = $("<div>").addClass("dec_title")
			                                 .text("여행지 : " + title + " / 테마 : " + category);
			li.append(titleCategoryDiv);
			
			li.append("<br><br>");
			
			var addressDiv = $("<div>").addClass("dec_address")
			                           .text("주소 : " + address);
			li.append(addressDiv);
			
			
			
			// 입력하는 데이터 변수 초기화
			$(".keyword").val("");
			
			// 삭제 버튼 추가
			var deleteButton = $("<input>").attr("type", "button")
			                               .addClass("deleteButton")
			                               .attr("onclick", "deleteElement(event)")
			                               .val("삭제");
			li.append(deleteButton);
			li.append("<br>");
			
			// 사진 변경 추가
			var fileInput = $("<input>").attr("type", "file")
			                            .attr("name", "file")
			                            .addClass("fileInput");
			li.append(fileInput);
			
			var reviewInput = $("<input>").attr("type", "text")
						         .attr("name", "TRAVEL_REVIEW");
			li.append(reviewInput);
		
		} // handleSelectedData
		  
		// =====================================
		// ======여행지 삭제하는 스크립트=======
		// =====================================
		function deleteElement(event) {
		  var elementId = $(event.target).parent().attr("id");
		
		  $(".write_day_dec_search").each(function() {
		    var searchId = $(this).attr("id");
		    if (searchId === elementId) {
		    	
		     	$(this).css("display", "none");
		    }
		  });
		
		  $(".searchResult").each(function() {
		    var resultId = $(this).attr("id");
		    if (resultId === elementId) {
		     	$(this).css("display", "none");
		    }
		  });
		  
		  var elementId2 = $(event.target).parent().parent().attr("id");
		  
		  $(".write_day .write_day_left").each(function() {
			  var dayId = $(this).parent().attr("id");
			  if (dayId === elementId2) {
			    var dayText = $(this).find(".day_text");
			    var currentDay = parseInt(dayText.text().split("일차")[0]);
			    $(this).parent().remove();

			    var writeDayElements = $(".write_day");
			    for (var i = 0; i < writeDayElements.length; i++) {
			      var eachDayText = $(writeDayElements[i]).find(".day_text");
			      var eachCurrentDay = parseInt(eachDayText.text().split("일차")[0]);
			      var eachDayText2 = $(writeDayElements[i]).find(".day_text2");
			      if (currentDay < eachCurrentDay) {
			        eachDayText.text((eachCurrentDay - 1) + "일차");
			        eachDayText2.text("Day" + (eachCurrentDay - 1)+".");
			      } // if
			    } // for
			    return false; // 루프 종료
			  } // if
		  });
		}
		
		// =====================================
		// ======종료일자 구하는 스크립트=======
		// =====================================
		
		function updateEndDate() {
			var startDateInput = document.getElementById('startDateInput');
			var startDate = new Date(startDateInput.value);
			var endDate = new Date(startDate);
			var dayElements = document.getElementsByClassName('write_day');
			var hiddenDayElements = document.querySelectorAll('.write_day[style*="display: none"]');
			var visibleDayCount = dayElements.length - hiddenDayElements.length - 2; // 숨겨진 write_day 클래스 개수를 제외한 일수 계산
			endDate.setDate(endDate.getDate() + visibleDayCount);
			var endDateFormatted = endDate.toISOString().split("T")[0]; // YYYY-MM-DD 형식으로 변환
			
			var endDateInput = document.getElementById("durationEnd");
			endDateInput.value = endDateFormatted;
			
			var selectedDateElement;
			  for (var i = 0; i < dayElements.length; i++) {
			    var selectedDate = new Date(startDate);
			    selectedDate.setDate(selectedDate.getDate() + i);
			    var selectedDateFormatted = selectedDate.toISOString().split("T")[0]; // YYYY-MM-DD 형식으로 변환
			    selectedDateElement = document.getElementById("selectedDate" + (i + 1));
			    selectedDateElement.textContent = "[" + selectedDateFormatted + "]";
			  }
		}
		
	
		// =====================================
		// ====입력 데이터 전송하는 스크립트====
		// =====================================
			
		function sendData() {
		  // 전송할 데이터 추출
		  var writer = $('input[name="WRITER"]').val();
		  var title = $('input[name="TITLE"]').val();
		  var durationStartStr = $('input[name="DURATION_START"]').val();
		  var durationStart = new Date(durationStartStr);
		  var durationEndStr = $('input[name="DURATION_END"]').val(); // 날짜 값으로 받은 문자열
		  var durationEnd = new Date(durationEndStr);
		  var review = $('input[name="REVIEW"]').val();
		  
		  
		  $('.searchResult').each(function() {
			  if ($(this).css('display') !== 'none') {
				  var id = $(this).attr('id');
				  var dataId = id.substring(6); // 'result' 제외한 부분 추출
				  var travelReview = $(this).find('input[name="TRAVEL_REVIEW"]').val();
				  var travelSEQ = $(this).find('input[name="SEQ"]').val();
				
				  // 개별적으로 Ajax 요청 전송
				  $.ajax({
				    url: '/board/course/registerTravel',
				    method: 'POST',
				    data: {
				      TRAVEL_SEQ: travelSEQ,
				      USER_REVIEW: travelReview,
				      ID: dataId
				    },
				    success: function(response) {
				      // 성공적으로 요청을 처리한 후 실행할 코드
				    },
				    error: function(xhr, status, error) {
				      // 요청 처리 중 오류가 발생한 경우 실행할 코드
				    }
				  });
				}
			  
			});
		  
		
		  // AJAX 요청
		  $.ajax({
		    url: '/board/course/register', 
		    method: 'POST',
		    data: {
		    	WRITER: writer,
			    TITLE: title,
			    DURATION_START: durationStart,
			    DURATION_END: durationEnd,
			    REVIEW: review
		    },
		    success: function(response) {
		      // 요청이 성공한 경우 실행될 코드
		      console.log('데이터 전송 성공:', response);
		      // 여기서 필요한 추가 처리를 수행할 수 있습니다.
		      window.location.href = "/board/course/list";
		    },
		    error: function(xhr, status, error) {
		      // 요청이 실패한 경우 실행될 코드
		      console.error('데이터 전송 실패:', error);
		      // 실패 처리를 수행할 수 있습니다.
		    }
		  });
		}
		
		
	</script>

	<!-- ===================================================================================== -->

	<aside>
		<div class="quick_box">
			<p>QUICK LINK</p>
			<ul>
				<li><a href="#a">날씨</a></li>
				<li><a href="#a">문의</a></li>
				<li class="top"><a href="#a"><i class="fas fa-caret-up"></i></a></li>
				<li class="bottom"><a href="#a"><i
						class="fas fa-caret-down"></i></a></li>
			</ul>
		</div>

	</aside>

	<footer>
		<div class="footer_wrap">
			<div class="footer_box">
				<div class="logo_box">
					<a href="#a"><img
						src="/resources/static/img/img-footer-logo.png" /></a>
				</div>
				<div>
					<ul>
						<li><a href="#a">이용약관</a></li>
						<li><a href="#a">개인정보처리방침</a></li>
						<li><a href="#a">사이트맵</a></li>
					</ul>
					<p class="copyright">Copyright © seoullive. All Rights Reserved</p>
				</div>
			</div>
		</div>
	</footer>

</body>

</html>