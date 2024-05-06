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

// Lấy tất cả các phần tử có class "custom-button"
var buttons = document.querySelectorAll(".openFormButton");

// Lặp qua từng nút và thêm sự kiện click cho mỗi nút
buttons.forEach(function(button) {
    button.addEventListener("click", openForm);
});
