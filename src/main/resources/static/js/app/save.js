var main = {

    init : function () {
        var _this = this;
        $('#btn-save').on('click', function (){
            _this.save();
        });
    },

    save: function () {
        var data = {
            title: $('#title').val(),
            clubID: $('#club').val(),
            fileID: $('#fileID').val(),
            locID: $('#locID').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: "POST",
            url: "/api/posts",
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),

            success: function () {
                alert('글이 등록되었습니다.');
                window.location.href = "/board";
            },
            error: function (error) {
                alert(JSON.stringify(error));
            }
        });
    }
}

main.init();