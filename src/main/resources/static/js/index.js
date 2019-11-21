$(document).ready(function(){
    function setCookie(cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
        var expires = "expires="+d.toUTCString();
        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    }

    var delete_cookie = function(name) {
        document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
    };

    function getCookie(name) {
        var nameEQ = name + "=";
        var ca = document.cookie.split(';');
        for(var i=0;i < ca.length;i++) {
            var c = ca[i];
            while (c.charAt(0)==' ') c = c.substring(1,c.length);
            if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
        }
        return null;
    }

    function checkCookie() {
        var user = getCookie("username");
        if (user != "") {
            alert("Welcome again " + user);
        } else {
            user = prompt("Please enter your name:", "");
            if (user != "" && user != null) {
                setCookie("username", user, 365);
            }
        }
    }

    // Add smooth scrolling to all links in navbar + footer link
    $(".navbar a, footer a[href='#myPage']").on('click', function(event) {
        // Make sure this.hash has a value before overriding default behavior
        if (this.hash !== "") {
            // Prevent default anchor click behavior
            event.preventDefault();

            // Store hash
            var hash = this.hash;

            // Using jQuery's animate() method to add smooth page scroll
            // The optional number (900) specifies the number of milliseconds it takes to scroll to the specified area
            $('html, body').animate({
                scrollTop: $(hash).offset().top
            }, 900, function(){

                // Add hash (#) to URL when done scrolling (default click behavior)
                window.location.hash = hash;
            });
        } // End if
    });

    $(window).scroll(function() {
        $(".slideanim").each(function(){
            var pos = $(this).offset().top;

            var winTop = $(window).scrollTop();
            if (pos < winTop + 600) {
                $(this).addClass("slide");
            }
        });
    });
//     $("#zoom_03").elevateZoom({gallery:'gallery_01', cursor: 'pointer', galleryActiveClass: 'active', imageCrossfade: true, loadingIcon: 'http://www.elevateweb.co.uk/spinner.gif'});
//
// //pass the images to Fancybox
//     $("#zoom_03").bind("click", function(e) {
//         var ez =   $('#zoom_03').data('elevateZoom');
//         $.fancybox(ez.getGalleryList());
//         return false;
//     });
    $("#zoom_01").elevateZoom();


    $(".contact-create").on("click", function () {
       dataContact = {};
       dataContact.comment = $(".contact-comment").val();
       dataContact.name = $(".contact-name").val();
       dataContact.email = $(".contact-email").val();

       console.log(dataContact.comment);
       console.log(dataContact.name);
       console.log(dataContact.email);

        NProgress.start();

        var linkPost = "/api/contact/create";

        axios.post(linkPost, dataContact).then(function(res){
            NProgress.done();
            if(res.data.success) {
                swal(
                    'Gửi thành công',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.reload();
                });
            } else {
                swal(
                    'Thất bại',
                    res.data.message,
                    'error'
                );
            }
        }, function(err){
            NProgress.done();
            swal(
                'Error',
                'Thất bại',
                'error'
            );
        });
    });


    var userName = getCookie("username");
    if(userName){
        $(".anonymous").attr("style","display: none");
        $(".username-logged").text(userName);
        $(".order-anonymous").attr("style","display: none");
    }else {
        $(".user-logged").attr("style","display: none");
    }

    $(".logout-user").on("click", function () {
        delete_cookie("username");
        delete_cookie("cartid");
        delete_cookie("guid");
    });

    $(document).ready(function () {
        $('a.login-window').click(function () {
            $('.login').fadeIn(300);
            $('#over').fadeIn(300);
            return false;
        });
        $(document).on('click',"#over,a.close",function () {
            $('.login, #over').fadeOut(300);
        });
        return false;
    });
});