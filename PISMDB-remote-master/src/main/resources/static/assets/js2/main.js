(function($) {
  'use strict';

  /*-------------------------------------------------------------------------------
  Preloader
    -------------------------------------------------------------------------------*/
  $(window).on('load', function() {
    $('.sigma_preloader').addClass('hidden');
  });

  /*-------------------------------------------------------------------------------
  Cart Trigger
  -------------------------------------------------------------------------------*/
  $(".sigma_cart-trigger").on('click', function(e) {
    e.preventDefault();
    $("body").toggleClass('cart-open');
  });

  /*-------------------------------------------------------------------------------
  Search Trigger
  -------------------------------------------------------------------------------*/
  $(".sigma_search-trigger").on('click', function(e) {
    e.preventDefault();
    $(".sigma_search-form-wrapper").toggleClass('open');
  });

  /*-------------------------------------------------------------------------------
  Aside Menu
  -------------------------------------------------------------------------------*/
  $(".aside-trigger-right").on('click', function() {
    var $el = $(".sigma_aside-right-panel");
    $el.toggleClass('open');
    if ($el.hasClass('open')) {
      setTimeout(function() {
        $el.find('.sidebar').fadeIn();
      }, 300);
    } else {
      $el.find('.sidebar').fadeOut();
    }
  });

  $(".aside-trigger-left").on('click', function() {
    $(".sigma_aside-left").toggleClass('open');
  });

  $(".sigma_aside .menu-item-has-children > a").on('click', function(e) {
    var submenu = $(this).next(".sub-menu");
    e.preventDefault();

    submenu.slideToggle(200);
  });

  /*-------------------------------------------------------------------------------
  Sticky Header
    -------------------------------------------------------------------------------*/
  var header = $(".can-sticky");
  var headerHeight = header.innerHeight();

  function doSticky() {
    if (window.pageYOffset > headerHeight) {
      header.addClass("sticky");
    } else {
      header.removeClass("sticky");
    }
  }
  doSticky();

  /*-------------------------------------------------------------------------------
  Tooltips
  -------------------------------------------------------------------------------*/
  $('[data-toggle="tooltip"]').tooltip();

  /*-------------------------------------------------------------------------------
  Magnific Popup
  -------------------------------------------------------------------------------*/
  $('.popup-youtube').magnificPopup({
    type: 'iframe'
  });
  $('.popup-vimeo').magnificPopup({
    type: 'iframe'
  });
  $('.popup-video').magnificPopup({
    type: 'iframe'
  });
  $('.gallery-thumb').magnificPopup({
    type: 'image',
    gallery: {
      enabled: true
    },
  });

  /*-------------------------------------------------------------------------------
  ion Range Sliders (Price filter)
  -------------------------------------------------------------------------------*/
  $(".js-range-slider").ionRangeSlider();

  $('.sigma_product-single-thumb')
    .wrap('<span style="display:inline-block" class="sigma_product-single-zoom"></span>')
    .css('display', 'block')
    .parent()
    .zoom();

  /*-------------------------------------------------------------------------------
  Countdown
  -------------------------------------------------------------------------------*/
  $(".sigma_countdown-timer").each(function() {
    var $this = $(this);
    $this.countdown($this.data('countdown'), function(event) {
      $(this).text(
        event.strftime('%D days %H:%M:%S')
      );
    });
  });

  /*-------------------------------------------------------------------------------
  Counter
  -------------------------------------------------------------------------------*/
  $('.counter').countTo({
    speed: 2000
  });

  /*-------------------------------------------------------------------------------
  Checkout Notices
  -------------------------------------------------------------------------------*/
  $(".sigma_notice a").on('click', function(e) {
    e.preventDefault();

    $(this).closest('.sigma_notice').next().slideToggle();
  });

  /*-------------------------------------------------------------------------------
  Testimonials slider
  -------------------------------------------------------------------------------*/
  $(".sigma_testimonial-slider").slick({
    slidesToShow: 2,
    slidesToScroll: 1,
    arrows: false,
    dots: true,
    autoplay: true,
    responsive: [{
        breakpoint: 575,
        settings: {
          slidesToShow: 1,
          autoplay: true,
        }
      },
    ]
  });

  /*-------------------------------------------------------------------------------
  Daily deals slider
  -------------------------------------------------------------------------------*/
  $(".sigma_grid-slider").slick({
    slidesToShow: 4,
    slidesToScroll: 1,
    arrows: false,
    dots: false,
    autoplay: true,
    responsive: [{
        breakpoint: 991,
        settings: {
          slidesToShow: 3,
          autoplay: true,
          arrows: false,
        }
      },
      {
        breakpoint: 768,
        settings: {
          slidesToShow: 2,
          arrows: false,
        }
      },
      {
        breakpoint: 575,
        settings: {
          slidesToShow: 1,
          arrows: false,
        }
      }
    ]
  });

  /*-------------------------------------------------------------------------------
  Other mentions slider
  -------------------------------------------------------------------------------*/
  $(".sigma_other-mentions-slider").slick({
    slidesToShow: 4,
    slidesToScroll: 1,
    arrows: true,
    dots: false,
    autoplay: false,
    responsive: [{
        breakpoint: 991,
        settings: {
          slidesToShow: 3,
        }
      },
      {
        breakpoint: 768,
        settings: {
          slidesToShow: 2,
        }
      },
      {
        breakpoint: 450,
        settings: {
          slidesToShow: 1,
        }
      }
    ]
  });

  /*-------------------------------------------------------------------------------
  Banner slider (Home v1)
  -------------------------------------------------------------------------------*/
  $(".banner-1 .sigma_banner-slider").slick({
    slidesToShow: 1,
    slidesToScroll: 1,
    arrows: false,
    dots: false,
    autoplay: true,
    responsive: [{
        breakpoint: 991,
        settings: {
          dots: false,
        }
      }
    ]
  });

  /*-------------------------------------------------------------------------------
  Banner slider (Home v2)
  -------------------------------------------------------------------------------*/
  $(".banner-2 .sigma_banner-slider").slick({
    slidesToShow: 1,
    slidesToScroll: 1,
    arrows: false,
    dots: false,
    autoplay: true,
  });

  /*-------------------------------------------------------------------------------
  Banner slider (Home v3)
  -------------------------------------------------------------------------------*/
  $(".banner-3 .sigma_banner-slider, .sigma_subheader-slider").slick({
    slidesToShow: 1,
    slidesToScroll: 1,
    arrows: true,
    prevArrow: $('.banner-3 .slider-prev'),
    nextArrow: $('.banner-3 .slider-next'),
    dots: false,
  });

  /*-------------------------------------------------------------------------------
  Service Slider
  -------------------------------------------------------------------------------*/
  $(".sigma_service-slider").slick({
    slidesToShow: 5,
    slidesToScroll: 1,
    arrows: true,
    dots: false,
    prevArrow: $('.service-section .slider-prev'),
    nextArrow: $('.service-section .slider-next'),
    autoplay: true,
    responsive: [{
        breakpoint: 1800,
        settings: {
          slidesToShow: 4,
        }
      },
      {
        breakpoint: 1400,
        settings: {
          slidesToShow: 3,
        }
      },
      {
        breakpoint: 991,
        settings: {
          slidesToShow: 2,
        }
      },
      {
        breakpoint: 575,
        settings: {
          slidesToShow: 1,
        }
      }
    ]
  });

  $(".sigma_service-slider-2").slick({
    slidesToShow: 1,
    slidesToScroll: 1,
    arrows: true,
    dots: false,
    prevArrow: $('.service-section .slider-prev'),
    nextArrow: $('.service-section .slider-next'),
    autoplay: true,
  });

  /*-------------------------------------------------------------------------------
  Upsells
  -------------------------------------------------------------------------------*/
  $(".sigma_upsells-slider").slick({
    slidesToShow: 1,
    slidesToScroll: 1,
    arrows: true,
    dots: false,
    autoplay: true,
    prevArrow: $('.sigma_upsells .slider-prev'),
    nextArrow: $('.sigma_upsells .slider-next'),
  });

  /*-------------------------------------------------------------------------------
  Blog
  -------------------------------------------------------------------------------*/
  $(".sigma_related-slider").each(function(){

    var $this = $(this);

    $this.slick({
      slidesToShow: 3,
      slidesToScroll: 1,
      arrows: true,
      dots: false,
      autoplay: true,
      responsive: [{
          breakpoint: 991,
          settings: {
            slidesToShow: 2,
          }
        },
        {
          breakpoint: 767,
          settings: {
            slidesToShow: 1,
          }
        }
      ]
    });

  });

  /*-------------------------------------------------------------------------------
  Causes
  -------------------------------------------------------------------------------*/
  $(".sigma_causes-slider").slick({
    slidesToShow: 1,
    slidesToScroll: 1,
    arrows: true,
    dots: false,
    infinite: false,
    autoplay: true,
    prevArrow: $('.sigma_causes-shortcode .slider-prev'),
    nextArrow: $('.sigma_causes-shortcode .slider-next'),
  });


  /*-------------------------------------------------------------------------------
  video-slider
  -------------------------------------------------------------------------------*/
  $(".sigma_video-slider").slick({
    slidesToShow: 3,
    slidesToScroll: 1,
    arrows: false,
    dots: false,
    autoplay: true,
  });

  /*-------------------------------------------------------------------------------
  event-slider
  -------------------------------------------------------------------------------*/
  $(".sigma_event-slider").slick({
    slidesToShow: 3,
    slidesToScroll: 1,
    arrows: true,
    dots: false,
    vertical: true,
    speed: 300,
    autoplay: true,
    autoplaySpeed: 2000,
    prevArrow: $('.sigma_event-box .slider-prev'),
    nextArrow: $('.sigma_event-box .slider-next'),
  });

  /*-------------------------------------------------------------------------------
  Masonry
  -------------------------------------------------------------------------------*/
  $('.masonry').imagesLoaded(function() {
    var isotopeContainer = $('.masonry');
    isotopeContainer.isotope({
      itemSelector: '.masonry-item',
    });
  });

  /*------------------------------------------------------------------------------
  Isotope
  ------------------------------------------------------------------------------*/

  var $portfolioGrid = $('.portfolio-filter').isotope({
    itemSelector: '.col-lg-4',
    percentPosition: true,
    masonry: {
      columnWidth: '.col-lg-4'
    }
  });

  $('.filter-items').on('click', '.portfolio-trigger', function() {
    var filterValue = $(this).attr('data-filter');
    $portfolioGrid.isotope({
        filter: filterValue
    });
  });

  $('.portfolio-trigger').on('click', function(e) {
      $(this).closest('.filter-items').find('.active').removeClass('active');
      $(this).addClass('active');
      event.preventDefault();
  });

  /*-------------------------------------------------------------------------------
  Add / Subtract Quantity
  -------------------------------------------------------------------------------*/
  $(".qty span").on('click', function() {
    var qty = $(this).closest('.qty').find('input');
    var qtyVal = parseInt(qty.val());
    if ($(this).hasClass('qty-add')) {
      qty.val(qtyVal + 1);
    } else {
      return qtyVal > 1 ? qty.val(qtyVal - 1) : 0;
    }
  })

  /*-----------------------------------
    Back to Top
    -----------------------------------*/
  $('.sigma_back-to-top').on('click', function() {
    $("html, body").animate({
      scrollTop: 0
    }, 600);
    return false;
  });

  // init wow js
  new WOW().init();

  //On scroll events
  $(window).on('scroll', function() {

    doSticky();

  });

  //On resize events
  $(window).on('resize', function() {


  });

})(jQuery);
