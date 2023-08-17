(function ($) {
    "use strict";

    // Spinner
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 1);
    };
    spinner();
    
    
    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 1500, 'easeInOutExpo');
        return false;
    });


    // Sidebar Toggler
    $('.sidebar-toggler').click(function () {
        $('.sidebar, .content').toggleClass("open");
        return false;
    });


    // Progress Bar
    $('.pg-bar').waypoint(function () {
        $('.progress .progress-bar').each(function () {
            $(this).css("width", $(this).attr("aria-valuenow") + '%');
        });
    }, {offset: '80%'});


    // Calender
    $('#calender').datetimepicker({
        inline: true,
        format: 'L'
    });


    // Testimonials carousel
    $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1000,
        items: 1,
        dots: true,
        loop: true,
        nav : false
    });
})(jQuery);

window.addEventListener("DOMContentLoaded", ()=>{
	const message = "<div class='clipboard-message'><p>Đã copy vào clipboard</p></div>"
	
	const codes = document.querySelectorAll(".code");
			
	codes.forEach((code)=>{
		code.addEventListener("click", ()=>{
			navigator.clipboard.writeText(code.innerText);
			
			let div = document.createElement("div");
			div.className = 'clipboard-message';
			div.innerHTML = "<p>Đã copy vào clipboard</p>";
			document.body.appendChild(div);
			
			setTimeout(()=>{
				div.remove();
			}, 1000)
		})
	})
})
