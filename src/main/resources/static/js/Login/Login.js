function login() {
    var maTVInput = document.getElementById('exampleInputMaTV');
    var passwordInput = document.getElementById('exampleInputPassword');

    if (maTVInput && passwordInput) {
        const maTV = parseInt(maTVInput.value); // Chuyển đổi thành số nguyên
        const password = passwordInput.value; // Giá trị password

        fetch('/login.html', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: 'maTV=' + encodeURIComponent(maTV) + '&password=' + encodeURIComponent(password)
        })
        .then(response => {
            if (response.ok) {
                // Đăng nhập thành công
                window.location.href = '/ThongTinTV.html?maTV=' + maTV; // Chuyển hướng đến trang thông tin thành viên
            } else {
                // Đăng nhập thất bại
                console.error('Mã thành viên hoặc mật khẩu không đúng');
                alert('Mã thành viên hoặc mật khẩu không đúng');
            }
        })
        .catch(error => {
            console.error('Lỗi khi gửi yêu cầu: ', error);
        });
    }
}
