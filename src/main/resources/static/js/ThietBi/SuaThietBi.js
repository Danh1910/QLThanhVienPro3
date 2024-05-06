function openForm() {
    document.getElementById("editForm").style.display = "block";
}

function closeForm() {
    document.getElementById("editForm").style.display = "none";
}

function saveChanges() {
    // Viết mã JavaScript để lưu thông tin thiết bị tại đây
    // Sau khi lưu xong, bạn có thể đóng form bằng cách gọi hàm closeForm()
    closeForm();
}

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
