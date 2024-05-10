function updateDeviceName() {
    var selectedDeviceIndex = document.getElementById("device-id").selectedIndex;
    var deviceNameSelect = document.getElementById("device-name");
    
   
    
    // Find and select corresponding device name
    deviceNameSelect.selectedIndex = selectedDeviceIndex;
}

function updateDeviceId(){
    var selectedDeviceIndex = document.getElementById("device-name").selectedIndex;
    var deviceIDSelect = document.getElementById("device-id");
    
   
    
    // Find and select corresponding device name
    deviceIDSelect.selectedIndex = selectedDeviceIndex;
}

document.addEventListener('DOMContentLoaded', function (){
    // Lấy thẻ input theo ID
    var borrowDateInput = document.getElementById("borrow-date");


    // Lấy thời gian hiện tại theo múi giờ UTC
    var currentUTCTime = moment.utc();

    // Chuyển múi giờ UTC thành múi giờ Việt Nam
    var currentVNTime = currentUTCTime.utcOffset(7); // Điều chỉnh giờ cho múi giờ Việt Nam

    // Format thời gian thành chuỗi có định dạng phù hợp cho trường <input type="datetime-local">
    var formattedDate = currentVNTime.format("YYYY-MM-DDTHH:mm");

    // Gán giá trị ngày giờ vào trường input
    borrowDateInput.value = formattedDate;

    var button = document.getElementById("btnSubmit");

    button.addEventListener('click', function (){

        var MaTV = document.getElementById('inputMaTV').value;

        var MaTB = document.getElementById('device-id').value;

        var BorrowDate = document.getElementById('borrow-date').value;

        var ReturnDate = document.getElementById('return-date').value;


        fetch('/MuonThietBi.html?MaTV=' + MaTV + '&MaTB=' + MaTB + '&NgayMuon=' + BorrowDate + '&NgayTra=' + ReturnDate, {
            method: 'POST'
        })
        .then(response => {
            if (!response.ok) {
                alert ("Không được");
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(data => {

            alert(data);
            // Chuyển đến trang web mới
            window.location.href = 'http://localhost:8080/ThanhVien.html';
        })
        .catch(error => {
            console.error('There was a problem with your fetch operation:', error);
        });
        
    })
})

