const swiper = new Swiper('.swiper', {
    slidesPerView: "auto",
    direction: 'horizontal',
    loop: false,
//    autoplay: {
//        delay: 1000,
//        disableOnInteraction: false
//    },
    spaceBetween: 30,
//    pagination: {
//        el: ".swiper-pagination",
//        clickable: true,
//    }
// Navigation arrows
  navigation: {
    nextEl: '.swiper-button-next',
    prevEl: '.swiper-button-prev',
  }
});