var main = {

    init : function () {
        var _this = this;

        $('#btn-saveFile').on('click', function(){
            _this.saveFile();
        })
    },

    saveFile : function() {

        var files = $('#files')[0].files;
        var data = new FormData();
        for(let i=0; i<files.length; i++){
            data.append('files', files[i]);
        }

        $.ajax({
            type : 'POST',
            enctype : 'multipart/form-data',
            url : '/api/file/upload',
            data : data,
            processData : false, // 서버로 전송될때 기본적으로 url에 query형식으로 key=value로 날아가는데, 파일 업로드는 이러면 안되니까 false 명시
            contentType : false, // 기본적으로 모든 문자들은 서버로 보내기전에 인코딩이 되는데, 파일이나 이미지를 전송할때는 인코딩하지 않아야하므로  false 명시
            cache : false,
            success : function (data) {
                alert(data);
            },
            error : function (error) {
                alert(JSON.stringify(error));
            }
        });
    }
}

main.init();