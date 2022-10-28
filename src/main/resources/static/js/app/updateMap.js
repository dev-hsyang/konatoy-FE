var latitude = $('#latitude').val();
var longtitude = $('#longtitude').val();
var mapContainer = document.getElementById('map'),
    mapOption = {
        center: new kakao.maps.LatLng(latitude, longtitude),
        level: 3
    };
var map = new kakao.maps.Map(mapContainer, mapOption);
var marker = new kakao.maps.Marker({
    map: map,
    position: new kakao.maps.LatLng(latitude, longtitude)
});
var infowindow = new kakao.maps.InfoWindow({
    zIndex: 1
});
var geocoder = new kakao.maps.services.Geocoder();

searchAddrFromCoords(displayCenterInfo);


searchDetailAddrFromCoords(function(result, status) {
    if(status === kakao.maps.services.Status.OK) {
        var detailAddr = !!result[0].road_address ? '<div>도로명주소: ' + result[0].road_address.address_name + '</div>' : '';
        detailAddr += '<div>지번 주소: ' + result[0].address.address_name + '</div>';
        var content = '<div class="bAddr">' + '<span class="title">법정동 주소정보</span>' + detailAddr + '</div>'

        infowindow.setContent(content);
        infowindow.open(map, marker);
    }
});

function searchDetailAddrFromCoords(callback) {
    geocoder.coord2Address(longtitude, latitude, callback);
}

function searchAddrFromCoords(callback) {
    geocoder.coord2RegionCode(longtitude, latitude, callback);
}

function displayCenterInfo(result, status) {
    if(status === kakao.maps.services.Status.OK) {
        var infoDiv = document.getElementById('centerAddr');
        for(var i=0; i<result.length; i++){
            if(result[i].region_type ==='H') {
                infoDiv.innerHTML = result[i].address_name;
                break;
            }
        }
    }
}