
const dropdowns = document.querySelectorAll('.dropdown');

dropdowns.forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');
    const options = dropdown.querySelectorAll('.menu li');
    const selected = dropdown.querySelector('.selected');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });

    options.forEach(option => {
        option.addEventListener('click', () => {
            selected.innerText = option.innerText;
            select.classList.remove('select-clicked');
            caret.classList.remove('caret-rotate');
            menu.classList.remove('menu-open');

            // Remove 'active' class from all options
            options.forEach(opt => {
                opt.classList.remove('activecbx');
            });

            // Add 'active' class to the clicked option
            option.classList.add('activecbx');
            
            if (option.textContent === "Thời gian vào") {
				chonTGVao();
				console.log("Thời gian vào");
			}
            if (option.textContent === "Thời gian mượn") {
            	chonTGMuon();
            	console.log("Thời gian mượn");
			}
            if (option.textContent === "Thời gian trả") {
            	chonTGTra();
				console.log("Thời gian trả");
			}
            if (option.textContent === "Thời gian đặt chổ") {
            	chonTGDatCho();
				console.log("Thời gian đặt chổ");
			}
        });
    });
});


document.addEventListener('DOMContentLoaded', function () {
    // Get references to the input field and button
    var searchInput = document.getElementById('searchInput');
    var searchButton = document.getElementById('searchButton');
    

    // Add event listeners for input and button click
    searchInput.addEventListener('input', handleInput);
    searchButton.addEventListener('click', handleClick);

    function handleInput(event) {
        // This function will be called when the user enters text in the input field
        var searchText = event.target.value;
        //console.log('User entered:', searchText);
    }

    function handleClick() {
    	handleSearch();
    }
    function handleSearch() {
		var theogi = document.getElementById('khoahaynganh').textContent;
    	
        // This function will be called when the user clicks the search button
        var searchText = searchInput.value;
        var search = searchText;
        var url = ""
	
		if (theogi === 'Theo Khoa') {
			url = "/ThongKe.html?chon=khoa&search=";
		} else {
			url = "/ThongKe.html?chon=nganh&search=";
		}
		fetch(url + search, {
            method: 'PUT'
        })
        .then(response => {
			if (!response.ok) {
				alert("Không tìm thấy dữ liệu!")
      			throw new Error('Network response was not ok');
    		}
    		return response.json();
  		})
  		.then(data => {
		    console.log(data);
		    updateTable(data);
  		})
        .catch(error => {
            console.error('Lỗi ', error);
        });
	}
});

$('#dataTable').dataTable({
    "bPaginate": false
});

savedTable = $('#dataTable').DataTable().rows().data().toArray();
savedTable1 = $('#dataTable').DataTable().rows().data().toArray();


function hideColumn(columnIndex){
	$('#dataTable tbody tr td:nth-child(' + columnIndex + '), #dataTable thead th:nth-child(' + columnIndex + ')').hide();
}
function showColumn(columnIndex) {
    var cells = document.querySelectorAll('#dataTable tbody tr td:nth-child(' + columnIndex + '), #dataTable thead th:nth-child(' + columnIndex + ')');
    cells.forEach(function(cell) {
        cell.style.display = '';
    });
}

// Hàm cập nhật bảng với dữ liệu mới từ kết quả Ajax
function updateTable(data) {
	chonAll();
    var table = $('#dataTable').DataTable();
    table.clear().rows.add(data).draw();
    savedTable1 = table.rows().data().toArray();
    filterDateTG();
    var thoigianchoice = document.getElementById('thoigianchoice').textContent;
    if (thoigianchoice === "Thời gian vào") 
    	chonTGVao();
    if (thoigianchoice === "Thời gian mượn") 
    	chonTGMuon();
    if (thoigianchoice === "Thời gian trả") 
    	chonTGTra();
    if (thoigianchoice === "Thời gian đặt chổ") 
    	chonTGDatCho();
}

$(document).ready(function() {
    $('#reloadbtn').click(function() {
		chonAll();
		var searchInput = document.getElementById('searchInput');
		searchInput.value = "";
        var table = $('#dataTable').DataTable();
	    table.clear().rows.add(savedTable).draw();
	    savedTable1 = table.rows().data().toArray();
	    var thoigianchoice = document.getElementById('thoigianchoice').textContent;
	    if (thoigianchoice === "Thời gian vào") 
	    	chonTGVao();
	    if (thoigianchoice === "Thời gian mượn") 
	    	chonTGMuon();
	    if (thoigianchoice === "Thời gian trả") 
	    	chonTGTra();
	    if (thoigianchoice === "Thời gian đặt chổ") 
	    	chonTGDatCho();
	    
    });
});



$(document).ready(function() {
    $('#dateBtn').click(function() {
		filterDateTG();
    });      
});

function filterDateTG() {
	var table = $('#dataTable').DataTable();
	var thoigianchoice = document.getElementById('thoigianchoice').textContent;
	
	var startDateInput = document.getElementById("StartDateChoice").value;
	var endDateInput = document.getElementById("EndDateChoice").value;
	var theOptionNumber = 5;
	if (thoigianchoice === "Thời gian vào") 
		theOptionNumber = 5;
	if (thoigianchoice === "Thời gian mượn") 
		theOptionNumber = 6;
	if (thoigianchoice === "Thời gian trả") 
		theOptionNumber = 7;
	if (thoigianchoice === "Thời gian đặt chổ") 
		theOptionNumber = 8;
	
	chonAll();
	if (startDateInput !== "" || endDateInput !== "") {
		// Tạo một mảng mới để lưu các dòng thỏa mãn điều kiện
        var filteredData = [];

        for (var i = 0; i < savedTable1.length; i++) {
            var dateValue = savedTable1[i][theOptionNumber]; 
            // Kiểm tra xem giá trị của cột này có nằm trong khoảng startDate và endDate không
            if (startDateInput === "") {
	            if (isDateBetween(endDateInput, endDateInput, dateValue)) {
	                filteredData.push(savedTable1[i]);
	            }
           	} else if (endDateInput === "") {
			   	if (isDateBetween(startDateInput, startDateInput, dateValue)) {
	                filteredData.push(savedTable1[i]);
	            }
		   	} else {
			  	if (isDateBetween(startDateInput, endDateInput, dateValue)) {
	                filteredData.push(savedTable1[i]);
	            }
		   	}
        }
        // Thêm các hàng thỏa mãn điều kiện vào bảng
        table.clear().rows.add(filteredData).draw();	
        
        if (thoigianchoice === "Thời gian vào") {
			hideColumn(5);
			hideColumn(7);
			hideColumn(8);
			hideColumn(9);
		}
		
		if (thoigianchoice === "Thời gian mượn") {
			hideColumn(6);
			hideColumn(9);
		}
		if (thoigianchoice === "Thời gian trả") {
			hideColumn(6);
			hideColumn(9);
		}
		if (thoigianchoice === "Thời gian đặt chổ") {
			hideColumn(6);
			hideColumn(7);
			hideColumn(8);
		}
	} else 
		console.log("Không có ngày tìm thấy");
}

function isDateBetween(startDate, endDate, dateToCheck) {
    startDate = new Date(startDate);
    endDate = new Date(endDate);
    dateToCheck = new Date(dateToCheck);
    if (startDate.toDateString() === endDate.toDateString()) {
        // Nếu dateToCheck cũng là ngày đó thì trả về true, ngược lại trả về false
        return dateToCheck.toDateString() === startDate.toDateString();
    }
    // Tăng ngày của endDate lên 1
    endDate.setDate(endDate.getDate() + 1);
    // Kiểm tra xem dateToCheck có nằm trong khoảng giữa startDate và endDate không
    return dateToCheck >= startDate && dateToCheck <= endDate;
}

$(document).ready(function() {
    $('#reloadTGloc').click(function() {
		chonAll();
        var table = $('#dataTable').DataTable();
	    table.clear().rows.add(savedTable1).draw();
	    var thoigianchoice = document.getElementById('thoigianchoice').textContent;
	    if (thoigianchoice === "Thời gian vào") 
	    	chonTGVao();
	    if (thoigianchoice === "Thời gian mượn") 
	    	chonTGMuon();
	    if (thoigianchoice === "Thời gian trả") 
	    	chonTGTra();
	    if (thoigianchoice === "Thời gian đặt chổ") 
	    	chonTGDatCho();
    });
});

chonTGVao();

function chonTGVao(){
	chonAll();
	match_option(5);
	hideColumn(5);
	hideColumn(7);
	hideColumn(8);
	hideColumn(9);
}
function chonTGMuon(){
	chonAll();
	match_option(6);
	hideColumn(6);
	hideColumn(9);
}
function chonTGTra(){
	chonAll();
	match_option(7);
	hideColumn(6);
	hideColumn(9);
}
function chonTGDatCho(){
	chonAll();
	match_option(8);
	hideColumn(6);
	hideColumn(7);
	hideColumn(8);
}

function chonAll() {
	showColumn(5);
	showColumn(6);
	showColumn(7);
	showColumn(8);
	showColumn(9);
}

function match_option(option_cloc) {
	var filteredData = [];
	for (var i = 0; i < savedTable1.length; i++) {
        var dateValue = savedTable1[i][option_cloc];
        if (dateValue === null) 
        	break;
        // Kiểm tra xem giá trị của cột này có nằm trong khoảng startDate và endDate không
        if (dateValue.length > 0) {
            filteredData.push(savedTable1[i]);
       	}
    }
    // Thêm các hàng thỏa mãn điều kiện vào bảng
    $('#dataTable').DataTable().clear().rows.add(filteredData).draw();
}