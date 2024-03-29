$(document).ready(function(){
    function setCookie(cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
        var expires = "expires="+d.toUTCString();
        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    }

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


    var dataProductCart = {};

    var kt=0;




    var changeProductCart = function (pdInfo, amount) {
        NProgress.start();
        axios.get("/api/cart-product/detail/" + pdInfo).then(function(res) {
            NProgress.done();
            if (res.data.success) {
                dataProductCart.id = res.data.data.id;
                dataProductCart.productId = res.data.data.productId;
                dataProductCart.cartId = res.data.data.cartId;
                dataProductCart.amount = res.data.data.amount;
                dataProductCart.amount = amount;
                NProgress.start();
                var linkPost = "/api/cart-product/update/" + pdInfo;
                axios.post(linkPost, dataProductCart).then(function (res) {
                    NProgress.done();
                    location.reload();
                }, function (err) {
                    NProgress.done();
                    swal(
                        'Error',
                        'Some error when saving cart product',
                        'error'
                    );
                });
            }

        }, function(err){
            NProgress.done();
        });

    }

    var deleteProductCart = function (pdInfo) {
        NProgress.start();
        axios.get("/api/cart-product/detail/" + pdInfo).then(function(res){
            NProgress.done();
            if(res.data.success) {
                NProgress.start();
                var linkPost = "/api/cart-product/delete/" + pdInfo;
                axios.post(linkPost, dataProductCart).then(function(res){
                    NProgress.done();
                    location.reload();
                }, function(err){
                    NProgress.done();
                    swal(
                        'Error',
                        'Some error when saving product',
                        'error'
                    );
                });
            }

        }, function(err){
            NProgress.done();
        });
    };



    $(".checkout").attr("href","/checkout");



    $(".form-control").change(function () {
        var pdInfo = $(this).data("product");
        var amount = $(this).val();
        var productAmount = $(this).data("product1");
        if(parseInt(productAmount)<parseInt(amount)){
            swal(
                'Lỗi',
                'Số lượng món ăn bạn đặt phải nhỏ hơn số lượng hàng tồn trong kho',
                'error'
            ).then(function() {
                amount=1;
                changeProductCart(pdInfo, amount);
            });
        }else changeProductCart(pdInfo, amount);
    });

    $(".delete-order-product").on("click", function () {
        var pdInfo = $(this).data("product");
        deleteProductCart(pdInfo);
    });



});

function AutoRefresh( t ) {
    setTimeout("location.reload(true);", t);
}