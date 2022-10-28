var main = {

    init : function () {
        var _this = this;
        $('#btn-delete').on('click', function (){
            _this.delete();
        });
    },

    delete : function () {
        var id = $('#postID').val();

        $.ajax({
            type: "POST",
            url: "/api/posts/delete/"+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(id),

            success : function () {
                alert('게시글이 삭제되었습니다.');
                window.history.back();
            },
            error : function (error){
                alert(JSON.stringify(error));
            }
        });
    }
}

main.init();