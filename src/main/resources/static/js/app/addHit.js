var main = {

    init: function () {
        var _this = this;
        $('.btn-view').on('click', function (){
            _this.addHit();
        });
    },

    addHit : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'POST',
            url: 'api/posts/view/addHit/'+id,
            dataType: 'text',
            success: function (data) {
                window.location.href = "/posts/view/" + id;
            },
            error: function (error){
                alert('failed');
            }
        });
    }
}

main.init();