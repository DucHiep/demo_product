/**
 * Created by ManhNguyen on 1/17/18.
 */

$(document).ready(function() {
    var dataCategory = {};

    $("#new-category").on("click", function () {
        dataCategory = {};
        $('#input-category-name').val("");
        $("#input-category-desc").val("");
    });

    $(".edit-category").on("click", function () {
        var pdInfo = $(this).data("category");
        // console.log(pdInfo);
        NProgress.start();
        axios.get("/api/product-category/detail/" + pdInfo).then(function(res){
            NProgress.done();
            if(res.data.success) {
                dataCategory.id = res.data.data.id;
                $("#input-category-name").val(res.data.data.name);
                $("#input-category-desc").val(res.data.data.shortDesc);
                // console.log(dataCategory.id);
            }else {
                console.log("ahihi");
            }
        }, function(err){
            NProgress.done();
        })
    });




    $(".btn-save-category").on("click", function () {
        if($("#input-category-name").val() === "") {
            swal(
                'Error',
                'You need to fill all values',
                'error'
            );
            return;
        }


        dataCategory.name = $('#input-category-name').val();
        dataCategory.shortDesc = $("#input-category-desc").val();
        NProgress.start();
        var linkPost = "/api/product-category/create";
        if(dataCategory.id) {
            linkPost = "/api/product-category/update/" + dataCategory.id;
        }

        axios.post(linkPost, dataCategory).then(function(res){
            NProgress.done();
            if(res.data.success) {
                swal(
                    'Good job!',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.reload();
                });
            } else {
                swal(
                    'Error',
                    res.data.message,
                    'error'
                );
            }
        }, function(err){
            NProgress.done();
            swal(
                'Error',
                'Some error when saving product',
                'error'
            );
        });
    });

});