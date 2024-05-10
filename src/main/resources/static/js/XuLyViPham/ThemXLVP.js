function openFormAdd() {
    document.getElementById("addXLVP").style.display = "block";
}

function closeFormAdd() {
    document.getElementById("addXLVP").style.display = "none";
}
var addButton = document.getElementById('openFormButton');

// Thêm trình nghe sự kiện cho sự kiện nhấp chuột
addButton.addEventListener('click', function(event) {
    openFormAdd();
});