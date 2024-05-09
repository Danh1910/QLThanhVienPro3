function openFormAdd() {
    document.getElementById("addForm").style.display = "block";
}

function closeFormAdd() {
    document.getElementById("addForm").style.display = "none";
}

function ShowChooseFile(){

}

function saveAdd() {
    // Viết mã JavaScript để lưu thông tin thiết bị tại đây
    // Sau khi lưu xong, bạn có thể đóng form bằng cách gọi hàm closeForm()
    closeFormAdd();
}
var addButton = document.getElementById('addButton');

// Thêm trình nghe sự kiện cho sự kiện nhấp chuột
addButton.addEventListener('click', function(event) {
    openFormAdd();
});


