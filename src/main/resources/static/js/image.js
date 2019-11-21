/**
 * Created by ManhNguyen on 3/1/18.
 */
$(document).ready(function () {

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $('#preview-product-img').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }


    $("#input-select-img-product").change(function() {
        console.log("ahihi");
        readURL(this);
        var formData = new FormData();
        NProgress.start();
        formData.append('file', $("#input-select-img-product")[0].files[0]);
        axios.post("/api/upload/upload-image", formData).then(function(res){
            NProgress.done();
            if(res.data.success) {
                $('#preview-product-img').attr('src', res.data.link);
                $("#avatar").val(res.data.link);
            }
        }, function(err){
            NProgress.done();
        });
    });


});