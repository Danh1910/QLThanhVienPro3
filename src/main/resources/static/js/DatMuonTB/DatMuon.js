
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


            openFormEdit();
            // Lấy phần tử input bằng id

            var deviceIdInput = document.getElementById('MaTB');


            // Kiểm tra xem phần tử có tồn tại không
            if (deviceIdInput) {
                // Set giá trị mới cho trường input
                deviceIdInput.value = deviceId;
            }

        });
    });
});

function AddThongTin() {
    // Lấy phần tử input bằng id

    var MaTV = document.getElementById('MaTV');
    var MaTB = document.getElementById('MaTB');
    var TGianDatCho = document.getElementById('tgianDatCho');


    if (MaTV && MaTB && TGianDatCho) {

        const matv = MaTV.value;
        const matb = MaTB.value; 
        const tgiandatcho = TGianDatCho.value; 


        fetch('/MuonTB.html?MaTV=' + matv + '&MaTB=' + matb + '&TGianDatCho=' + tgiandatcho, {
            method: 'POST'
        })
        .then(response => {
            if (response.ok) {
                // // Sau khi lưu xong, bạn có thể đóng form bằng cách gọi hàm closeForm()
                closeFormEdit();

                alert("Thông tin sử dụng đã được thêm thành công");
                window.location.reload(); // Làm mới trang sau khi hiển thị thông báo
                
            } else {
                console.error('Lỗi khi thêm thông tin sử dụng');
            }
        })
        .catch(error => {
            console.error('Lỗi khi gửi yêu cầu: ', error);
        });
    }
}
