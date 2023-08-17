<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %> 
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>DASHMIN - Bootstrap Admin Template</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="resources/img_2/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">
    
    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="resources/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="resources/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

    <!-- Customized Bootstrap Stylesheet -->
    <link href="resources/css_2/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="resources/css_2/style.css" rel="stylesheet">
    <link href="resources/css_2/form.css" rel="stylesheet">
</head>

<body>
    <div class="container-xxl position-relative bg-white d-flex p-0">
        <!-- Sidebar Start -->
        <%@ include file="/resources/include/dashboard_sidebar.jsp" %>
        <!-- Sidebar End -->

        <!-- Content Start -->
        <div class="content">
            <!-- Navbar Start -->
            <%@ include file="/resources/include/dashboard_navbar.jsp" %>
            <!-- Navbar End -->
	
			<div class="form-container">
				<h2 class="form-header">Đăng ký</h2>
				<form:form class="form" action="register.htm" method="post" modelAttribute="khachHang" enctype="multipart/form-data">
					<label class="form-group" style="grid-area: first">
						Họ
						<form:input type="text" path="Ho" placeholder="Họ"/>
						<form:errors class="error-message" path="Ho"/>
					</label>
					<label class="form-group" style="grid-area: last">
						Tên
						<form:input type="text" path="Ten" placeholder="Tên"/>
						<form:errors class="error-message" path="Ten"/>
					</label>
					<label class="form-group" style="grid-area: email">
						Email
						<form:input type="text" path="Email" placeholder="abc@gmail.com"/>
						<form:errors class="error-message" path="Email"/>
						<span class="error-message">${emailError}</span>
					</label>
					<label class="form-group" style="grid-area: phone">
						Số điện thoại
						<form:input type="tel" path="SDT" placeholder="0123456789" pattern="[0-9]{4}[0-9]{3}[0-9]{3}"/>
						<form:errors class="error-message" path="SDT"/>
						<span class="error-message">${sdtError}</span>
					</label>
					<label class="form-group" style="grid-area: birth">
						Ngày sinh
						<form:input type="date" path="NgaySinh" placeholder="dd/mm/yyyy"/>
						<form:errors class="error-message" path="NgaySinh"/>
					</label>
					<label class="form-group" style="grid-area: address">
						Địa chỉ
						<form:input type="text" path="DiaChi" placeholder="Địa chỉ"/>
					</label>
					<label class="form-group" style="grid-area: gender">
						Giới tính
						<form:select path="GioiTinh">
							<form:option value="1">Nam</form:option>
							<form:option value="0">Nữ</form:option>
						</form:select>
					</label>
					<label class="form-group" style="grid-area: image">
						Ảnh
						<input type="file" name="profile"/>
					</label>
					<label class="form-group" style="grid-area: note">
						Ghi chú
						<form:input type="text" path="GhiChu" placeholder="Ghi chú"/>
					</label>
					<input type="submit" value="Lưu" style="grid-area: submit">
				</form:form>
			</div>
			<div class="overlay ${successMessage.length() > 0 ? '' :'hide' }"></div>
			<div class="success-message ${successMessage.length() > 0 ? '' :'hide' }">
				<p>${successMessage}</p>
				<a href="register.htm" class="message-btn">OK</a>
			</div>
		</div>			
        <!-- Content End -->


        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
    </div>

    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="resources/lib/chart/chart.min.js"></script>
    <script src="resources/lib/easing/easing.min.js"></script>
    <script src="resources/lib/waypoints/waypoints.min.js"></script>
    <script src="resources/lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="resources/lib/tempusdominus/js/moment.min.js"></script>
    <script src="resources/lib/tempusdominus/js/moment-timezone.min.js"></script>
    <script src="resources/lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

    <!-- Template Javascript -->
    <script src="resources/js_2/main.js"></script>
</body>

</html>