/**
 * Created by ManhNguyen on 1/17/18.
 */

$(document).ready(function() {
    var dataCartProduct = {};
    $(".add-cart-product").on("click", function () {
        var pdInfo = $(this).data("product");
        var cartid = $(this).data("cartid");
       dataCartProduct.productId = pdInfo;
       dataCartProduct.cartId = cartid;
       dataCartProduct.amount = 1;

       var linkPost = "/api/cart-product/create";

        axios.post(linkPost, dataCartProduct).then(function(res){
            NProgress.done();
            if(res.data.success) {
                swal(
                    'Thành công',
                    "Thêm món ăn thành công",
                    'success'
                ).then(function() {
                    location.reload();
                });
            } else {
                swal(
                    'Lỗi',
                    "Thêm món ăn thất bại",
                    'error'
                );
            }
        }, function(err){
            NProgress.done();
            swal(
                'Lỗi',
                'Thêm món ăn thất bại',
                'error'
            );
        });
    });

});