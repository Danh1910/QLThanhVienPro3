
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
        console.log('User entered:', searchText);
    }

    function handleClick() {
    	handleSearch();
    }
    function handleSearch() {
		var theogi = document.getElementById('khoahaynganh').textContent;
    	
        // This function will be called when the user clicks the search button
        var searchText = searchInput.value;
        console.log('User clicked search. Text:', searchText);
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

savedTable = $('#dataTable').DataTable().rows().data().toArray();
savedTable1 = $('#dataTable').DataTable().rows().data().toArray();

// Hàm cập nhật bảng với dữ liệu mới từ kết quả Ajax
function updateTable(data) {
	console.log(data.length);
    var table = $('#dataTable').DataTable();
    table.clear().rows.add(data).draw();
    savedTable1 = table.rows().data().toArray();
    filterDateTG();
}

$(document).ready(function() {
    $('#reloadbtn').click(function() {
		var searchInput = document.getElementById('searchInput');
		searchInput.value = "";
        var table = $('#dataTable').DataTable();
	    table.clear().rows.add(savedTable).draw();
	    savedTable1 = table.rows().data().toArray();
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
	var theOptionNumber = 3;
	if (thoigianchoice === "Thời gian vào") 
		theOptionNumber = 3;
	if (thoigianchoice === "Thời gian mượn") 
		theOptionNumber = 4;
	if (thoigianchoice === "Thời gian trả") 
		theOptionNumber = 5;
	if (thoigianchoice === "Thời gian đặt chổ") 
		theOptionNumber = 6;
	
	console.log(startDateInput + " " + endDateInput);
	
	if (startDateInput !== "" || endDateInput !== "") {
		// Tạo một mảng mới để lưu các dòng thỏa mãn điều kiện
        var filteredData = [];

        for (var i = 0; i < savedTable1.length; i++) {
            var dateValue = savedTable1[i][theOptionNumber]; 
            // Kiểm tra xem giá trị của cột này có nằm trong khoảng startDate và endDate không
            if (startDateInput === "") {
				console.log("startDateInput is empty");
	            if (isDateBetween(endDateInput, endDateInput, dateValue)) {
	                filteredData.push(savedTable1[i]);
	            }
           	} else if (endDateInput === "") {
				console.log("endDateInput is empty");
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
        var table = $('#dataTable').DataTable();
	    table.clear().rows.add(savedTable1).draw();
    });
});