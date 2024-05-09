
const list_id_check = []

function openFormEdit() {
    document.getElementById("editForm").style.display = "block";
}

function closeFormEdit() {
    document.getElementById("editForm").style.display = "none";
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


            openFormEdit();
            // Lấy phần tử input bằng id

            var deviceIdInput = document.getElementById('deviceCode');
            var deviceNameInput = document.getElementById('deviceName');


            // Kiểm tra xem phần tử có tồn tại không
            if (deviceIdInput && deviceNameInput) {
                // Set giá trị mới cho trường input
                deviceIdInput.value = deviceId;
                deviceNameInput.value = deviceName;
            }

        });
    });
});