function openForm() {
    document.getElementById("addForm").style.display = "block";
}

function closeForm() {
    document.getElementById("addForm").style.display = "none";
}

function saveChanges() {
    // Viết mã JavaScript để lưu thông tin thiết bị tại đây
    // Sau khi lưu xong, bạn có thể đóng form bằng cách gọi hàm closeForm()
    closeForm();
}
var addButton = document.getElementById('addButton');

// Thêm trình nghe sự kiện cho sự kiện nhấp chuột
addButton.addEventListener('click', function(event) {
    openForm();
});