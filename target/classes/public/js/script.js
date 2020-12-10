/*

Style   : MobApp Script JS
Version : 1.0
Author  : Surjith S M
URI     : https://surjithctly.in/

Copyright © All rights Reserved 

*/
NUM_CONFETTI = 350
COLORS = [[85,71,106], [174,61,99], [219,56,83], [244,92,68], [248,182,70]]
PI_2 = 2*Math.PI


canvas = document.getElementById "world"
context = canvas.getContext "2d"
window.w = 0
window.h = 0

resizeWindow = ->
  window.w = canvas.width = window.innerWidth
  window.h = canvas.height = window.innerHeight

window.addEventListener 'resize', resizeWindow, false

window.onload = -> setTimeout resizeWindow, 0

range = (a,b) -> (b-a)*Math.random() + a

drawCircle = (x,y,r,style) ->
  context.beginPath()
  context.arc(x,y,r,0,PI_2,false)
  context.fillStyle = style
  context.fill()

xpos = 0.5

document.onmousemove = (e) ->
  xpos = e.pageX/w

window.requestAnimationFrame = do ->
  window.requestAnimationFrame       ||
  window.webkitRequestAnimationFrame ||
  window.mozRequestAnimationFrame    ||
  window.oRequestAnimationFrame      ||
  window.msRequestAnimationFrame     ||
  (callback) -> window.setTimeout(callback, 1000 / 60)


class Confetti

  constructor: ->
    @style = COLORS[~~range(0,5)]
    @rgb = "rgba(#{@style[0]},#{@style[1]},#{@style[2]}"
    @r = ~~range(2,6)
    @r2 = 2*@r
    @replace()

  replace: ->
    @opacity = 0
    @dop = 0.03*range(1,4)
    @x = range(-@r2,w-@r2)
    @y = range(-20,h-@r2)
    @xmax = w-@r
    @ymax = h-@r
    @vx = range(0,2)+8*xpos-5
    @vy = 0.7*@r+range(-1,1)

  draw: ->
    @x += @vx
    @y += @vy
    @opacity += @dop
    if @opacity > 1
      @opacity = 1
      @dop *= -1
    @replace() if @opacity < 0 or @y > @ymax
    if !(0 < @x < @xmax)
      @x = (@x + @xmax) % @xmax
    drawCircle(~~@x,~~@y,@r,"#{@rgb},#{@opacity})")


confetti = (new Confetti for i in [1..NUM_CONFETTI])

window.step = ->
  requestAnimationFrame(step)
  context.clearRect(0,0,w,h)
  c.draw() for c in confetti

step()

$(function() {
    "use strict";

    /*-----------------------------------
     * FIXED  MENU - HEADER
     *-----------------------------------*/
    function menuscroll() {
        var $navmenu = $('.nav-menu');
        if ($(window).scrollTop() > 50) {
            $navmenu.addClass('is-scrolling');
        } else {
            $navmenu.removeClass("is-scrolling");
        }
    }
    menuscroll();
    $(window).on('scroll', function() {
        menuscroll();
    });
    /*-----------------------------------
     * NAVBAR CLOSE ON CLICK
     *-----------------------------------*/

    $('.navbar-nav > li:not(.dropdown) > a').on('click', function() {
        $('.navbar-collapse').collapse('hide');
    });
    /* 
     * NAVBAR TOGGLE BG
     *-----------------*/
    var siteNav = $('#navbar');
    siteNav.on('show.bs.collapse', function(e) {
        $(this).parents('.nav-menu').addClass('menu-is-open');
    })
    siteNav.on('hide.bs.collapse', function(e) {
        $(this).parents('.nav-menu').removeClass('menu-is-open');
    })

    /*-----------------------------------
     * ONE PAGE SCROLLING
     *-----------------------------------*/
    // Select all links with hashes
    $('a[href*="#"]').not('[href="#"]').not('[href="#0"]').not('[data-toggle="tab"]').on('click', function(event) {
        // On-page links
        if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
            // Figure out element to scroll to
            var target = $(this.hash);
            target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
            // Does a scroll target exist?
            if (target.length) {
                // Only prevent default if animation is actually gonna happen
                event.preventDefault();
                $('html, body').animate({
                    scrollTop: target.offset().top
                }, 1000, function() {
                    // Callback after animation
                    // Must change focus!
                    var $target = $(target);
                    $target.focus();
                    if ($target.is(":focus")) { // Checking if the target was focused
                        return false;
                    } else {
                        $target.attr('tabindex', '-1'); // Adding tabindex for elements not focusable
                        $target.focus(); // Set focus again
                    };
                });
            }
        }
    });
    /*-----------------------------------
     * OWL CAROUSEL
     *-----------------------------------*/
    var $testimonialsDiv = $('.testimonials');
    if ($testimonialsDiv.length && $.fn.owlCarousel) {
        $testimonialsDiv.owlCarousel({
            items: 1,
            nav: true,
            dots: false,
            navText: ['<span class="ti-arrow-left"></span>', '<span class="ti-arrow-right"></span>']
        });
    }

    var $galleryDiv = $('.img-gallery');
    if ($galleryDiv.length && $.fn.owlCarousel) {
        $galleryDiv.owlCarousel({
            nav: false,
            center: true,
            loop: true,
            autoplay: true,
            dots: true,
            navText: ['<span class="ti-arrow-left"></span>', '<span class="ti-arrow-right"></span>'],
            responsive: {
                0: {
                    items: 1
                },
                768: {
                    items: 3
                }
            }
        });
    }

}); /* End Fn */