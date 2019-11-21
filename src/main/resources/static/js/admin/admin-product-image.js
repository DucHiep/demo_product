/**
 * Created by ManhNguyen on 1/17/18.
 */

$(document).ready(function() {

    var dataProductImage = {};

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $('#preview-product-img').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $(".change-image").change(function() {
        var pdInfo = $(this).data("product");
        var pdImageId = $(this).data("product-image");
        readURL(this);
        var formData = new FormData();
        NProgress.start();
        formData.append('file', this.files[0]);
        axios.post("/api/upload/upload-image", formData).then(function(res){
            NProgress.done();
            if(res.data.success) {
                dataProductImage.image=res.data.link;
                dataProductImage.productid = pdInfo;
                var linkPost = "/api/product-image/update/"+pdImageId;
                axios.post(linkPost, dataProductImage).then(function(res){
                    NProgress.done();
                    if(res.data.success) {
                        swal(
                            'Thay ảnh thành công',
                            res.data.message,
                            'success'
                        ).then(function() {
                            location.reload();
                        });
                    } else {
                        swal(
                            'Thay ảnh thất bại',
                            res.data.message,
                            'error'
                        );
                    }
                }, function(err){
                    NProgress.done();
                    swal(
                        'Error',
                        'Đặt bàn thất bại',
                        'error'
                    );
                });

            }
        }, function(err){
            NProgress.done();
        })
    });


    $("#add-new-image").change(function () {
        var pdInfo = $(this).data("product");
        readURL(this);
        var formData = new FormData();
        NProgress.start();
        formData.append('file', this.files[0]);
        axios.post("/api/upload/upload-image", formData).then(function(res){
            NProgress.done();
            if(res.data.success) {
                dataProductImage.image=res.data.link;
                dataProductImage.productid = pdInfo;
                var linkPost = "/api/product-image/create";
                axios.post(linkPost, dataProductImage).then(function(res){
                    NProgress.done();
                    if(res.data.success) {
                        swal(
                            'Thêm ảnh thành công',
                            res.data.message,
                            'success'
                        ).then(function() {
                            location.reload();
                        });
                    } else {
                        swal(
                            'Thêm ảnh thất bại',
                            res.data.message,
                            'error'
                        );
                    }
                }, function(err){
                    NProgress.done();
                    swal(
                        'Error',
                        'Thêm ảnh thất bại',
                        'error'
                    );
                });
            }
        }, function(err){
            NProgress.done();
        });
    });

    $(".delete-product").on("click",function () {
        var pdImageId = $(this).data("product-image");

        var linkPost = "/api/product-image/delete/"+pdImageId;

        axios.post(linkPost).then(function(res){
            NProgress.done();
            if(res.data.success) {
                swal(
                    'Xóa ảnh thành công',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.reload();
                });
            } else {
                swal(
                    'Xóa ảnh thất bại',
                    res.data.message,
                    'error'
                );
            }
        }, function(err){
            NProgress.done();
            swal(
                'Error',
                'Xóa ảnh thất bại',
                'error'
            );
        });

    });


});