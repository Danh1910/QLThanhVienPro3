
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
		searchTable("", 'xulyTable');
	} else if (currentContent === "Chưa xử lý"){
		searchTable("chưa", 'xulyTable');
	} else {
		searchTable("đã", 'xulyTable');
	}
	
}