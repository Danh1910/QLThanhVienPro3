
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

savedTable = $('#dataTable').DataTable().rows().data().toArray()

// Hàm cập nhật bảng với dữ liệu mới từ kết quả Ajax
function updateTable(data) {
	console.log(data.length);
    var table = $('#dataTable').DataTable();
    table.clear().rows.add(data).draw();
}
$(document).ready(function() {
    $('#reloadbtn').click(function() {
        reloadTable();
    });
});
function reloadTable() {
    var table = $('#dataTable').DataTable();
    table.clear().rows.add(savedTable).draw();
}