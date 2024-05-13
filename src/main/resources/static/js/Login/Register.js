document.addEventListener('DOMContentLoaded', function() {
    // Lấy phần tử select của Khoa và Ngành
    var khoaSelect = document.getElementById('exampleKhoa');
    var nganhSelect = document.getElementById('exampleNganh');

    // Một đối tượng chứa các ngành tương ứng với mỗi khoa
    var nganhOptions = {
        "SP KHXH": ["Địa", "Sử", "Văn"],
        "SP KHTN": ["Lí", "Hóa", "Sinh"],
        "Ngoại Ngữ": ["Anh", "NNA"],
        "QTKD": ["QTKD"],
        "QLGD": ["TLH"],
        "Toán UD": ["Toán"],
        "CNTT": ["CNTT", "KTPM", "HTTT"]
    };

    // Thêm sự kiện change cho select của Khoa
    khoaSelect.addEventListener('change', function() {
        nganhSelect.innerHTML = '';

        var selectedKhoa = khoaSelect.value;

        var nganhs = nganhOptions[selectedKhoa];
        nganhs.forEach(function(nganh) {
            var option = document.createElement('option');
            option.textContent = nganh;
            option.value = nganh;
            nganhSelect.appendChild(option);
        });
    });
 })


 function saveAdd() {
     // Lấy phần tử input bằng id

         var personName = document.getElementById('exampleFullName');
         var personKhoa = document.getElementById('exampleKhoa');
         var personNganh = document.getElementById('exampleNganh');
         var personSDT = document.getElementById('exampleSDT');
         var personEmail = document.getElementById('exampleEmail');
         var personPassword = document.getElementById('examplePassword');


         if (personName && personKhoa && personNganh && personSDT && personEmail && personPassword) {
                 const tenTV = personName.value;
                 const khoa = personKhoa.options[personKhoa.selectedIndex].value;
                 const nganh = personNganh.options[personNganh.selectedIndex].value;
                 const sdt = personSDT.value;
                 const email = personEmail.value;
                 const password = personPassword.value;


                 fetch('/DangKy.html?&Ten=' + tenTV + '&Khoa=' + khoa + '&Nganh=' + nganh + '&SDT=' + sdt + '&Email=' + email + '&Password=' + password, {
                     method: 'POST'
                 })
         .then(response => {
             if (response.ok) {
                 // // Sau khi lưu xong, bạn có thể đóng form bằng cách gọi hàm closeForm()


                 Swal.fire({
                                                   title: "~Tuyệt~",
                                                   text: "Đăng ký thành công !!",
                                                   icon: "success"
                                                }).then((result) => {
                                                     if (result.isConfirmed) {
                                                         window.location.href = '/Login.html';
                                                     }
                                                  });

             } else {
                 Swal.fire({
                                                 icon: 'error',
                                                 title: 'Lỗi!',
                                                 text: 'Lỗi thêm thành viên !',
                                             });
             }
         })
         .catch(error => {
             console.error('Lỗi khi gửi yêu cầu: ', error);
         });

     }
 }
