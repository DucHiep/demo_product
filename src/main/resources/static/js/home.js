$(document).ready(function(){

    dataBooker = {};

    $(".btn-lg").on("click", function () {


        dataBooker.name= $("#reservation_name").val();
        dataBooker.tableAmount = $("#table-amount").val();
        dataBooker.comment = $("#comments").val();
        dataBooker.phone = $("#reservation_phone").val();
        dataBooker.email=$("#reservation_email").val();
        dataBooker.timeBooked =$("#date").val()+" "+$("#time").val()+":00";

        console.log(dataBooker.comment);
        console.log(dataBooker.name);
        console.log(dataBooker.tableAmount);
        console.log(dataBooker.phone);
        console.log(dataBooker.email);
        console.log(dataBooker.timeBooked);

        NProgress.start();

        var linkPost = "/api/booker/create";

        axios.post(linkPost, dataBooker).then(function(res){
            NProgress.done();
            if(res.data.success) {
                swal(
                    'Đặt bàn thành công, chúng tôi sẽ liên lạc với bạn sớm nhất trong vòng 24h',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.reload();
                });
            } else {
                swal(
                    'Đặt bàn thất bại',
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



    });

});