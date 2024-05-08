document.getElementById('excelFileInput').addEventListener('change', handleExcelFileSelection);

    function handleExcelFileSelection() {
        var fileInput = document.getElementById('excelFileInput');
        
        if (fileInput.files.length > 0) {
            var selectedFile = fileInput.files[0];
            console.log('File được chọn:', selectedFile.name);
            // Thêm bất kỳ xử lý nào bạn muốn ở đây
        } else {
            console.log('Không có file nào được chọn.');
        }
    }