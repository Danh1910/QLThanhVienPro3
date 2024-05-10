
// Lấy giá trị của trạng thái từ thẻ td và chuyển đổi thành "chưa xử lý" hoặc "đã xử lý"
// Lấy tất cả các phần tử có class là 'trang_thaixl'
var elements = document.getElementsByClassName("trang_thaixl");
// Lặp qua từng phần tử để xử lý
for (var i = 0; i < elements.length; i++) {
    var trangThai = elements[i].innerText;
    if (trangThai === "0") {
        elements[i].innerText = "Chưa xử lý";
    } else if (trangThai === "1") {
        elements[i].innerText = "Đã xử lý";
    } else {
        elements[i].innerText = "Không xác định";
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

            openFormEdit();
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

document.addEventListener('DOMContentLoaded', function() {
    // Lấy tất cả check box
    var checkbox = document.querySelectorAll(".checkbox_ID");

    console.log(checkbox.length)

    // Lặp qua từng checkbox
    checkbox.forEach(function (check){
        check.addEventListener('change', function(){

            var id = check.value

            if (check.checked){

                // Thêm id vào danh sách check
                list_id_check.push(id)

                console.log(list_id_check)


            }
            else{
                const indexToRemove = list_id_check.indexOf(id);

                if (indexToRemove !== -1) {
                    list_id_check.splice(indexToRemove, 1);
                }

                console.log(list_id_check)

            }

        });
    });


})

// thực hiện tạo thành động khi vừa load trang
document.addEventListener('DOMContentLoaded', function() {

    var checkAll = document.getElementById("checkAll");

    // Lấy tất cả check box
    var checkbox = document.querySelectorAll(".checkbox_ID");

    console.log(checkbox.length)

    checkAll.addEventListener('change', function() {


        // Xóa toàn bộ nội dung của mảng
        list_id_check.length = 0;

        if (this.checked) {
            console.log('CheckboxAll is checked');
            // Thực hiện các hành động khi checkbox được chọn


            checkbox.forEach(function (check) {

                check.checked = true

                var id = check.value

                list_id_check.push(id)

            });

        } else {
            console.log('CheckboxAll is unchecked');
            // Thực hiện các hành động khi checkbox không được chọn

            checkbox.forEach(function (check) {

                check.checked = false


            });

        }

        console.log(list_id_check)

    });

});