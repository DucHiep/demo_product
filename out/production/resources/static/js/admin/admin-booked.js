/**
 * Created by ManhNguyen on 1/17/18.
 */

$(document).ready(function() {

    $(".delete-booker").on("click", function () {
        var bInfo = $(this).data("booker");
        // console.log(bInfo);
        var linkPost = "/api/booker/delete/"+bInfo;
        axios.post(linkPost).then(function(res){
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