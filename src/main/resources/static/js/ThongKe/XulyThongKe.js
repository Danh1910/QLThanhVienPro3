
function searchTable(filter, tableId) {
    const table = document.getElementById(tableId);
    const tr = table.getElementsByTagName('tr');
    
    for (let i = 1; i < tr.length; i++) {
        const tdArray = tr[i].getElementsByTagName('td');
        let found = false;
        for (let j = 0; j < tdArray.length; j++) {
            if (tdArray[j]) {
                const txtValue = tdArray[j].textContent || tdArray[j].innerText;
                if (txtValue.toLowerCase().indexOf(filter) > -1) {
                    found = true;
                    break;
                }
            }
        }
        tr[i].style.display = found ? '' : 'none';
    }
}

document.getElementById('searchTable2').addEventListener('keyup', function() {
    const input = document.getElementById('searchTable2');
    const filter = input.value.toLowerCase();
    searchTable(filter, 'xulyTable');
});

function handleContentChange_trangthai() {
    var selectedSpan = document.getElementById("choiceXL");
    var currentContent = selectedSpan.innerText;
    //console.log("Nội dung thay đổi thành: " + currentContent);
    if (currentContent === "All"){
		loadDataToTable("/loadXulyData");
	} else if (currentContent === "Đã xử lý"){
		loadDataToTable("/loadDaXulyData")
	}
	else {
		loadDataToTable("/loadChuaXulyData")
	}
	
}

function loadDataToTable(urll) {
    $.ajax({
        url: urll,
        type: "GET",
        success: function(data) {
            // Xử lý dữ liệu nhận được từ controller
            // Đẩy dữ liệu vào bảng
            var tableBody = $("#xulyTable tbody");
            tableBody.empty(); // Xóa dữ liệu cũ trong bảng trước khi thêm dữ liệu mới

            $.each(data, function(index, xulyData) {
                var row = "<tr>" +
                          "<td>" + xulyData[0] + "</td>" +
                          "<td>" + xulyData[1] + "</td>" +
                          "<td>" + xulyData[2] + "</td>" +
                          "<td>" + xulyData[3] + "</td>" +
                          "<td>" + xulyData[4] + "</td>" +
                          "<td>" + xulyData[5] + "</td>" +
                          "<td>" + xulyData[6] + "</td>" +
                          "<td>" + xulyData[7] + "</td>" +
                          "</tr>";
                tableBody.append(row);
            });
        },
        error: function(xhr, status, error) {
            console.error("Error loading data: " + error);
        }
    });
}

