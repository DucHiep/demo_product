/**
 * Created by ManhNguyen on 1/17/18.
 */

$(document).ready(function() {
    var dataMaterial = {};

    $("#new-material").on("click", function () {
        dataMaterial = {};
        $('#input-material-name').val("");
        $("#input-material-price-test").val("");
    });

    $(".edit-material").on("click", function () {
        var pdInfo = $(this).data("material");
        console.log(pdInfo);
        NProgress.start();
        axios.get("/api/material/detail/" + pdInfo).then(function(res){
            NProgress.done();
            if(res.data.success) {
                dataMaterial.id = res.data.data.id;
                $("#input-material-name").val(res.data.data.name);
                $("#input-material-price-test").val(res.data.data.price);
            }else {
                console.log("ahihi");
            }
        }, function(err){
            NProgress.done();
        })
    });




    $(".btn-save-material").on("click", function () {
        if($("#input-material-name").val() === "" || $("#input-material-price-test").val()==="") {
            swal(
                'Error',
                'You need to fill all values',
                'error'
            );
            return;
        }


        dataMaterial.name = $('#input-material-name').val();
        dataMaterial.price = $("#input-material-price-test").val();
        if($("input-material-amount").val()===null){
            dataMaterial.amount = 0;
        }else dataMaterial.amount = $("#input-material-amount").val();
        NProgress.start();
        var linkPost = "/api/material/create";
        if(dataMaterial.id) {
            linkPost = "/api/material/update/" + dataMaterial.id;
        }

        axios.post(linkPost, dataMaterial).then(function(res){
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
                console.log(dataMaterial.price);
                console.log(dataMaterial.id);
                console.log(dataMaterial.name);
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