<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
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
				<h2 class="form-header">Thông tin hóa đơn</h2>
				<form class="form form-check" style="grid-template-rows: repeat(2, 1fr);">
					<label class="form-group" style="grid-area: code">
						Mã hóa đơn
						<input type="text" name="MaHoaDon" readonly="readonly" value="${hoaDon.getMaHD()}">
					</label>
					<label class="form-group" style="grid-area: package">
						Mã nhân viên
						<input type="text" name="MaNhanVien" readonly="readonly" value="${hoaDon.getNhanVien().getHo()} ${hoaDon.getNhanVien().getTen()}">
					</label>
					<label class="form-group" style="grid-area: date">
						Mã thẻ
						<input type="text" name="MaThe" readonly="readonly" value="${hoaDon.getMaThe()}">
					</label>
					<label class="form-group" style="grid-area: employee">
						Tên khách hàng
						<input type="text" name="Ten" readonly="readonly" value="${hoaDon.getFullName()}">
					</label>
				</form>
			</div>

			<div class="table-container">
            	<div class="table-header">
            		<h2 class="title">Chi tiết hóa đơn</h2>
            	</div>
            	
            	<table class="table">
            		<thead>
            			<tr>
            				<th class="ta-start">Mã gói tập</th>
            				<th class="ta-start">Tên gói tập</th>
            				<th class="ta-start">Thời hạn</th>
            				<th class="ta-start">Ngày đăng ký</th>
            				<th class="ta-start">Giá</th>
            			</tr>
            		</thead>
            		<tbody>
            			<c:forEach var="p" items="${hoaDon.getListChiTietThe()}" begin="0" end="${hoaDon.getListChiTietThe().size()}" varStatus="status">
            				<tr>
	            				<td><p class="code" title="${p.getGoiTap().getMaGoiTap()}">${p.getGoiTap().getMaGoiTap()}</p></td>
	            				<td>${p.getGoiTap().getTenGoiTap()}</td>
	            				<td>${p.getGoiTap().getThoiHan()}</td>
	            				<td>${hoaDon.getNgayLap()}</td>
	            				<td><fmt:formatNumber value="${p.getGoiTap().getPriceByDate(hoaDon.getNgayLap())}" type="currency"/></td>
	            			</tr>
            			</c:forEach>
            		</tbody>
            	</table>
            </div>
            
            <div class="form-container">
            	<div style="display: flex; justify-content: space-between; align-items: center;">
            		<h4>Tổng tiền:</h4>
            		<h3><fmt:formatNumber value="${hoaDon.getTotal()}" type="currency"/></h3>
            	</div>
            	<c:if test="${id.equals('new-bill')}">
            		<div  style="display: flex; justify-content: flex-end; padding: 20px 0">
            			<button class="secondary-btn exit-btn" style="margin-right: 20px;">Để sau</button>
	            		<form action="bill-detail.htm?id=${hoaDon.getMaHD()}" method="post">
		            		<input type="submit" class="primary-btn" value="Thanh toán" style="margin: 0;"/>
		            	</form>
            		</div>
            	</c:if>
            	<c:if test="${!id.equals('new-bill')}">
            		<p style="margin-bottom: 0; color: var(--bs-green); display: flex; justify-content: flex-end; align-items: center;">
            			<i class="bi bi-check" style="font-size: 50px;"></i>
            			Đã thanh toán
            		</p>
            	</c:if>
            </div>
		</div>			
        <!-- Content End -->

		<div class="overlay hide"></div>
        <div class="confirm-dialog hide">
        	<div>
        		<h3>Xác nhận hủy</h3>
	         	<p>Quá trình thanh toán chưa hoàn tất.<br/>Bạn có muốn thoát?</p>
	         	<div>
	         		<button class="secondary-btn close-dialog" onclick="handleHide()">Hủy</button>
	         		<a href="bill.htm" class="primary-btn">Xác nhận</a>
	         	</div>
        	</div>
        </div>

        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
    </div>

	<script>
		const exitBtn = document.querySelector(".exit-btn");
		const overlay = document.querySelector(".overlay");
		const confirmDialog = document.querySelector(".confirm-dialog");
		
		const handleHide = () => {
			overlay.classList.toggle("hide");
			confirmDialog.classList.toggle("hide");
		}
		
		exitBtn.addEventListener("click", (e) => {
			e.preventDefault();
			handleHide();
		})
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