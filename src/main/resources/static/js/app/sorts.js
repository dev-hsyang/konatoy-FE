var main = {

    init :function () {
        var _this = this;
        $('#select-sort').on('change', function (){
            _this.sort();
        });
    },

    sort : function () {
        var sort = $('#select-sort option:selected').val();
        var param = "?sortType=" + sort;

        $.ajax({
            type: "GET",
            url: '/board'+param,
            dataType: 'text',
            success: function (data) {
                window.location.href = "/board" + param;
            },
            error: function (error) {
                alert('failed');
            }
        });
    }
}

main.init();