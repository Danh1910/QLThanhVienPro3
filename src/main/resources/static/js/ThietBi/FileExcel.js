document.getElementById('excelFileInput').addEventListener('change', handleExcelFileSelection);

function handleExcelFileSelection() {
    var fileInput = document.getElementById('excelFileInput');
    
    if (fileInput.files.length > 0) {
        var selectedFile = fileInput.files[0];
        console.log('File được chọn:', selectedFile.path);
        // Thêm bất kỳ xử lý nào bạn muốn ở đây
    } else {
        console.log('Không có file nào được chọn.');
    }
}


function AddExcel(selectedFile){
    fetch('/ThietBi.html?FilePath=' + selectedFile, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(list_id_check) // Chuyển danh sách ID thành JSON và gửi đi
    })
    .then(response => {
        if (response.ok) {
           console.log("Gửi yêu cầu thành công")
        } else {
            console.error('Lỗi khi xóa thiết bị');
        }
        
    })
    .then(date =>{
        
    })
    .catch(error => {
    console.error('There was a problem with your fetch operation:', error);
    });
}