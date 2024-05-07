
// Hiện form lên 
function openForm() {
    document.getElementById("editForm").style.display = "block";
}

// Tắt form
function closeForm() {
    document.getElementById("editForm").style.display = "none";
}

// Thực hiện thay đổi thông tin thiết bị
function saveChanges() {

    // Lấy phần tử input bằng id

    var deviceId = document.getElementById('deviceCode');
    var deviceName = document.getElementById('deviceName');
    var deviceDescription = document.getElementById('deviceDescription');

    
    if (deviceId && deviceName && deviceDescription) {

        const maTB = deviceId.value; // Giá trị MaTB
        const tenTB = deviceName.value; // Giá trị TenTB
        const moTaTB = deviceDescription.value; // Giá trị MoTaTB

        fetch('/ThietBi.html?MaTB=' + maTB + '&TenTB=' + tenTB + '&MoTaTB=' + moTaTB, {
            method: 'PUT'
        })
        .then(response => {
            if (response.ok) {
                alert("Dữ liệu đã được sửa thành công");
                window.location.reload(); // Làm mới trang sau khi hiển thị thông báo
            } else {
                console.error('Lỗi khi lưu thiết bị');
            }
        })
        .catch(error => {
            console.error('Lỗi khi gửi yêu cầu: ', error);
        });

        

        // // Sau khi lưu xong, bạn có thể đóng form bằng cách gọi hàm closeForm()
        closeForm();
    }
}

// thực hiện đổ dữ liệu lên 
document.addEventListener('DOMContentLoaded', function () {
    // Lấy tất cả các nút "Sửa"
    var editButtons = document.querySelectorAll('.openFormButton');

    // Lặp qua từng nút "Sửa"
    editButtons.forEach(function (button) {
        // Bắt sự kiện click
        button.addEventListener('click', function () {

            // Lấy ID của thiết bị từ thuộc tính data
            var deviceId = button.closest('tr').getAttribute('idtb');
            var deviceName = button.closest('tr').getAttribute('tentb');
            var deviceDescription = button.closest('tr').getAttribute('motatb');

            openForm();
            // Lấy phần tử input bằng id

            var deviceIdInput = document.getElementById('deviceCode');
            var deviceNameInput = document.getElementById('deviceName');
            var deviceDescriptionInput = document.getElementById('deviceDescription');

            // Kiểm tra xem phần tử có tồn tại không
            if (deviceIdInput && deviceNameInput && deviceDescriptionInput) {
                // Set giá trị mới cho trường input
                deviceIdInput.value = deviceId;
                deviceNameInput.value = deviceName;
                deviceDescriptionInput.value = deviceDescription;
            }
            
        });
    });
});


//
//// Lặp qua từng nút và thêm sự kiện click cho mỗi nút
//buttons.forEach(function(button) {
//    button.addEventListener("click", openForm);
//});
