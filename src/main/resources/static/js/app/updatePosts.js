const Editor = toastui.Editor;
const editor = new Editor({
    el: document.querySelector("#editor"),
    height: '600px',
    initialEditType: 'wysiwyg',
    previewStyle: 'vertical',
    initialValue: $('#content').val()
});

var main = {

    init : function () {
        var _this = this;
        $('#btn-update').on('click', function (){
            _this.update();
        });
    },

    update : function () {
        var data = {
            title : $('#title').val(),
            content : editor.getMarkdown(),
            fileID : $('#file').val(),
            locID : $('#loc').val(),
            latitude : latitude,
            longtitude : longtitude,
            locationID : $('#locationID').val()
        };
        var id = $('#id').val();

        $.ajax({
            type : 'POST',
            url : '/api/posts/update/'+id,
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data),

            success : function () {
                alert('성공적으로 수정되었습니다.');
                // window.history.back();
                window.location.href = "/posts/view/" + $('#id').val();
            },
            error : function (error) {
                alert(JSON.stringify(error));
            }
        });
    },

    deleteFile : function (fileID) {

        let conf = confirm("첨부파일을 삭제하겠습니까?");
        if(conf==false){
            return;
        }

        $.ajax({
            type : 'GET',
            url : '/api/file/delete/' + fileID,
            dataType: 'text',

            success : function () {

                window.location.reload();
            },
            error : function (error) {
                alert(JSON.stringify(error));
            }
        });
    }
}

main.init();