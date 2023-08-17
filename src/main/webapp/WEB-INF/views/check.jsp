<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
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
    <link href="resources/css_2/table.css" rel="stylesheet">
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
				<h2 class="form-header">Điểm danh</h2>
				<form action="get-card.htm" style="display:flex">
					<label class="form-group" style="grid-area: code">
						Mã Thẻ
						<input type="text" name="MaThe" placeholder="Mã thẻ" value="${maThe}" required="required">
						<span class="error-message">${errorMessage}</span>
					</label>
					<label class="form-group" style="grid-area: code">
						<span style="color:transparent;">p</span>
						<span style="display:flex;">
							<input type="submit" value="Tiếp theo" style="margin: 0 20px;">
							<button class="secondary-btn search-customer-btn">Tìm khách hàng</button>
						</span>
					</label>
				</form>
				<form action="check.htm?cardId=${the.getMaThe()}" method="post">
					<div style="display:flex;">
						<label class="form-group" style="grid-area: package">
							Gói tập
							<select name="GoiTap" required="required">
								<c:forEach var="p" items="${the.getListChiTietThe()}" begin="0" end="${the.getListChiTietThe().size()}" varStatus="status">
									<c:if test="${p.getNgayDangKy().plusDays(p.getGoiTap().getThoiHan()).isAfter(todayDateTime) 
										&& bangDiemDanhService.countDiemDanh(p.getGoiTap().getMaGoiTap(), p.getThe().getMaThe(), p.getNgayDangKy()) < p.getGoiTap().getSoBuoiTap()}">
										<option value="${p.getGoiTap().getMaGoiTap()},${p.getNgayDangKy()}">${p.getGoiTap().getTenGoiTap()}</option>
									</c:if>
								</c:forEach>
							</select>
						</label>
						<label class="form-group" style="grid-area: employee">
							Nhân viên
							<select name="NhanVien" required="required">
								<c:forEach var="p" items="${listNhanVien}" begin="0" end="${listNhanVien.size()}" varStatus="status">
									<option value="${p.getMaNV()}" ${nhanVien.getMaNV().equals(p.getMaNV()) ? 'selected' : ''}>${p.getHo()} ${p.getTen()}</option>
								</c:forEach>
							</select>
						</label>
					</div>
					<input type="submit" value="Điểm danh" style="grid-area: submit">
				</form>
			</div>

			<div class="table-container">
            	<div class="table-header">
            		<h2 class="title">Thông tin điểm danh</h2>
            		<form action="check-by-date.htm" method="post" class="search-bar">
            			<span>Ngày</span>
            			<input type="date" name="date-to-check" placeholder="yyyy-mm-dd" value="${today}">
            			<input type="submit" value="Tìm kiếm" style="width: auto;">
            		</form>
            	</div>
            	
            	<table class="table">
            		<thead>
            			<tr>
            				<th class="ta-start">Mã thẻ</th>
            				<th class="ta-start">Họ và tên</th>
            				<th class="ta-start">Gói tập</th>
            				<th class="ta-start">Ngày điểm danh</th>
            				<th class="ta-start">Số lần điểm danh</th>
            				<th class="ta-start">Số lần còn lại</th>
            				<th class="ta-start">Nhân viên</th>
            			</tr>
            		</thead>
            		<tbody>
            			<c:forEach var="p" items="${listDiemDanh}" begin="0" end="${listDiemDanh.size()}" varStatus="status">
						  	<tr>
	            				<td><p class="code" title="${p.getThe().getMaThe()}">${p.getThe().getMaThe()}</p></td>
	            				<td>${p.getThe().getKhachHang().getFullname()}</td>
	            				<td>${p.getGoiTap().getTenGoiTap()}</td>
	            				<td>${p.getNgayDiemDanh()}</td>
	            				<td>${bangDiemDanhService.countDiemDanh(p.getGoiTap().getMaGoiTap(), p.getThe().getMaThe(), p.getNgayDangKy())}</td>
	            				<td>${p.getGoiTap().getSoBuoiTap() - bangDiemDanhService.countDiemDanh(p.getGoiTap().getMaGoiTap(), p.getThe().getMaThe(), p.getNgayDangKy()) >= 0 ? p.getGoiTap().getSoBuoiTap() - bangDiemDanhService.countDiemDanh(p.getGoiTap().getMaGoiTap(), p.getThe().getMaThe(), p.getNgayDangKy()) : 0}</td>
	            				<td>${p.getNhanVien().getFullname()}</td>
            				</tr>          				
            			</c:forEach>
            		</tbody>
            	</table>
            </div>
            
            <div class="overlay ${maKH == null ? 'hide' : ''}"></div>
            
            <div class="table-container customer-modal-table ${maKH == null ? 'hide' : ''}" style="position:fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); z-index: 9999; min-width: 70%;">
            	<div style="display:flex; justify-content: flex-end;"><i class="bi bi-x-circle close-modal-btn" style="font-size: 24px;"></i></div>
            	<c:if test="${listThe == null}">
	            	<div class="table-header">
	            		<h2 class="title">Khách hàng</h2>
	            		<form action="load-customers.htm" class="search-bar">
	            			<span>Tìm kiếm</span>
	            			<input type="text" name="customer-name" placeholder="Tên khách hàng..." value="${hoTen}">
            				<input type="submit" value="Tìm kiếm" style="width: auto;">
	            		</form>
	            	</div>
	            	
	            	<table class="table">
	            		<thead>
	            			<tr>
	            				<th>Mã khách hàng</th>
	            				<th>Họ và tên</th>
	            				<th>Ngày sinh</th>
	            				<th>Giới tính</th>
	            				<th>Email</th>
	            				<th>Số điện thoại</th>
	            				<th>Danh sách thẻ</th>
	            			</tr>
	            		</thead>
	            		<tbody>	
	            			<c:forEach var="p" items="${listKhachHang}" begin="0" end="${listKhachHang.size()}" varStatus="status">
							  	<tr>
		            				<td><p class="code" title="${p.getMaKH()}">${p.getMaKH()}</p></td>
		            				<td>${p.getHo()} ${p.getTen()}</td>
		            				<td>${p.getNgaySinh()}</td>
		            				<td>${p.getGioiTinh() == 0 ? 'Nữ' : 'Nam'}</td>
		            				<td>${p.getEmail()}</td>
		            				<td>${p.getSDT()}</td>
		            				<td><a href="load-cards.htm?customerId=${p.getMaKH()}"><i class="bi bi-wallet center"></i></a></td>
		            			</tr>          				
	            			</c:forEach>
	            		</tbody>
	            	</table>
            	</c:if>
            	<c:if test="${listThe != null}">
	            	<div class="table-header">
	            		<h2 class="title">Danh sách thẻ</h2>
	            	</div>
            		<table class="table">
	            		<thead>
	            			<tr>
	            				<th class="ta-start">Mã thẻ</th>
	            				<th class="ta-start">Ngày tạo thẻ</th>
	            				<th class="ta-start">Chọn thẻ</th>
	            			</tr>
	            		</thead>
	            		<tbody>
	            			<c:forEach var="p" items="${listThe}" begin="0" end="${listThe.size()}" varStatus="status">
							  	<c:if test="${p.getTrangThai() == 1}">
							  		<tr>
			            				<td><p class="code">${p.getMaThe()}</p></td>
			            				<td>${p.getNgayTao()}</td>
			            				<td><a href="check.htm?cardId=${p.getMaThe()}">Chọn</a></td>
			            			</tr> 
							  	</c:if>  				
	            			</c:forEach>
	            		</tbody>
	            	</table>
            	</c:if>
            </div>
		</div>			
        <!-- Content End -->


        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
    </div>

	<script type="text/javascript">
		const searchCustomerBtn = document.querySelector(".search-customer-btn");
		const overlay = document.querySelector(".overlay");
		const customerModalTable = document.querySelector(".customer-modal-table");
		const closeModalBtn = document.querySelector(".close-modal-btn");
		
		if(window.location.search.includes("hoTen")) {
			overlay.classList.remove("hide");
			customerModalTable.classList.remove("hide");
		}
		
		const handleShowModal = (e) => {
			e.preventDefault();
			overlay.classList.toggle("hide");
			customerModalTable.classList.toggle("hide");
		}
		
		searchCustomerBtn.addEventListener("click", (e) => handleShowModal(e));
		closeModalBtn.addEventListener("click", (e) => handleShowModal(e));
	</script>

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