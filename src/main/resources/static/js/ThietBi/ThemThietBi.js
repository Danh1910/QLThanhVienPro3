function openFormAdd() {
    document.getElementById("addForm").style.display = "block";
}

function closeFormAdd() {
    document.getElementById("addForm").style.display = "none";
}


function saveAdd() {
    // Lấy phần tử input bằng id

    var deviceId = document.getElementById('adddeviceCode');
    var deviceName = document.getElementById('adddeviceName');
    var deviceDescription = document.getElementById('adddeviceDescription');
    if (deviceId && deviceName && deviceDescription) {

        const loaiTBIndex = deviceId.selectedIndex;
        const tenTB = deviceName.value; // Giá trị TenTB
        const moTaTB = deviceDescription.value; // Giá trị MoTaTB

        fetch('/ThietBi.html?LoaiTBIndex=' + loaiTBIndex + '&TenTB=' + tenTB + '&MoTaTB=' + moTaTB, {
            method: 'POST'
        })
        .then(response => {
            if (response.ok) {
                // // Sau khi lưu xong, bạn có thể đóng form bằng cách gọi hàm closeForm()
                closeFormAdd();

                alert("Thiết bị đã được thêm thành công");
                window.location.reload(); // Làm mới trang sau khi hiển thị thông báo
                
            } else {
                console.error('Lỗi khi thêm thiết bị');
            }
        })
        .catch(error => {
            console.error('Lỗi khi gửi yêu cầu: ', error);
        });
    }
}
var addButton = document.getElementById('addButton');

// Thêm trình nghe sự kiện cho sự kiện nhấp chuột
addButton.addEventListener('click', function(event) {
    openFormAdd();
});


