const swiper = new Swiper('.sample-slider', {
    loop: true,
    autoplay: true,
    effect: "cards",
    grabCursor: false,
    pagination: {
        el: '.swiper-pagination',
    },
    navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev",
    },
});