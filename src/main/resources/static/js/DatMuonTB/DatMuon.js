
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

function save_dat_muon(){

    var deviceId = document.getElementById();
    var userId = document.getElementById();
    var timeDatCho = document.getElementById();

    if (deviceId && userId && timeDatCho) {

        const thietbiId = deviceId.selectedIndex;
        const thanhvienId = userId.selectedIndex; // Giá trị TenTB
        const tgianDatCho = timeDatCho.value; // Giá trị MoTaTB

        fetch('/MuonTB.html?LoaiTBIndex=' + thietbiId + '&TenTB=' + thanhvienId + '&MoTaTB=' + tgianDatCho, {
            method: 'PUT'
        })
        .then(response => {
            if (response.ok) {
                // // Sau khi lưu xong, bạn có thể đóng form bằng cách gọi hàm closeForm()
                closeFormAdd();

                alert("Thông tin sử dụng đã được thêm thành công");
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