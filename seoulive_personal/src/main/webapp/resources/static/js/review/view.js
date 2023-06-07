// 버튼 요소를 가져옵니다.
const myButton = document.getElementById('myButton');

// 아이콘 요소를 가져옵니다.
const myIcon = document.getElementById('myIcon');

// 버튼 클릭 이벤트 핸들러를 등록합니다.
myButton.addEventListener('click', function() {
    // 아이콘 클래스를 변경합니다.
    if (myIcon.classList.contains('far')) {
        myIcon.classList.remove('far');
        myIcon.classList.add('fas');
    } else {
        myIcon.classList.remove('fas');
        myIcon.classList.add('far');
    }
});
// 좋아요 버튼 활성화
$('.fa-heart').click(function() {
    $(this).toggleClass('on');
})
// 좋아요 버튼 활성화
    var $heartIcon = $('.heartBtn .fa-heart');
$heartIcon.click(function() {
    $(this).toggleClass('on');
    alert("좋아요!");
})

const swiper = new Swiper(".swiper", {

    direction: "horizontal",
    loop: true,

    pagination: {
        el: ".swiper-pagination",
        type: "bullets",
        clickable: true,
    },

    navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev",
    },

    spaceBetween: 10,
    slidesPreView: "auto",
    grabCursor: true,
    centerdSlides: true,
    speed: 600,
    effect: "coverflow",
    coverflowEffect: {
        rotate: 0,
        stretch: -100,
        depth: 50,
        modifier: 1,
        slideShadows: false,
    },

});
const btnStart = document.querySelector(".btnStart");
const btnStop = document.querySelector(".btnStop");

// btnStart.addEventListener("click", ()=> {
//     swiper.autoplay.start();
// });
// btnStop.addEventListener("click", ()=> {
//     swiper.autoplay.stop();
// });
