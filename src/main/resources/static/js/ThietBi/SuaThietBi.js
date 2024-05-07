
// Danh sách các id mà người dùng đã thực hiện check vào
const list_id_check = []

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
                // // Sau khi lưu xong, bạn có thể đóng form bằng cách gọi hàm closeForm()
                closeForm();

                alert("Dữ liệu đã được sửa thành công");
                window.location.reload(); // Làm mới trang sau khi hiển thị thông báo
                
            } else {
                console.error('Lỗi khi lưu thiết bị');
            }
        })
        .catch(error => {
            console.error('Lỗi khi gửi yêu cầu: ', error);
        });

        

        
    }
}


function deleteThietBi() {

    if (list_id_check.length == 0){
        alert("Vui lòng chọn thiết bị muốn xóa");

        return;
    }

    const confirmation = confirm("Bạn có chắc chắn muốn xóa không?");

    if (confirmation) {
        fetch('https://example.com/api/resources', {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(idsToDelete) // Chuyển danh sách ID thành JSON và gửi đi
        })
        .then(response => {
            if (response.ok) {

                alert("Đã xóa thành công");
                window.location.reload(); // Làm mới trang sau khi hiển thị thông báo
                
            } else {
                console.error('Lỗi khi lưu thiết bị');
            }
            
        })
        .catch(error => {
        console.error('There was a problem with your fetch operation:', error);
        });
    } else {
        console.log("Không xóa")
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
