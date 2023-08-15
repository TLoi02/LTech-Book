function handelLoginForm(){
    const container = document.getElementById('container');
    container.classList.toggle("right-panel-active");
}

function SliderProductPage(){
    const swiper = new Swiper('.swiper.swiper__product-category', {
        // Optional parameters
        direction: 'horizontal',
        loop: true,
      
        pagination: {
            el: '.swiper-pagination',
          },
        // Navigation arrows
        navigation: {
          nextEl: '.swiper-button-next',
          prevEl: '.swiper-button-prev',
        },
        autoplay: {
            delay: 5000,      
            disableOnInteraction: false, 
        },
      });
}

function start(){
    SliderProductPage();
}
start();