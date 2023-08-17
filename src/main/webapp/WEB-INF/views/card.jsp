<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
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
				<h2 class="form-header">Đăng ký gói tập</h2>
				<form action="card.htm" method="post" style="display: flex;">
					<label class="form-group" style="grid-area: card">
						Mã thẻ tập
						<input type="text" name="MaTheTap" readonly="readonly" value="${id}"/>
					</label>
					<label class="form-group" style="grid-area: customer">
						Mã khách hàng
						<input type="text" name="Ten" readonly="readonly"  value="${khachHang.getMaKH()}"/>
					</label>
				</form>
				<form:form style="display: flex;" action="load-package.htm?id=${id}" method="post" modelAttribute="lopDichVu">
					<label class="form-group" style="grid-area: class">
						Lớp dịch vụ
						<select name="MaLop">
							<option value="NONE" selected="selected"> --SELECT--</option>
							<c:forEach  var="p" items="${listLop}" begin="0" end="${listLop.size()}" varStatus="status">
								<option value="${p.getMaLop()}" ${lop != null && lop.getMaLop().equals(p.getMaLop()) ? 'selected' : ''}>${p.getTenLop()}</option>
							</c:forEach>
						</select>
					</label>
					<label class="form-group" style="grid-area: classBtn">
						<span style="color: transparent">A</span>
						<input type="submit" value="Tải gói tập" style="margin:0"/>
					</label>
				</form:form>
				<form:form action="add-package-to-cart.htm?id=${id}" method="post" modelAttribute="chiTietThe">
					<fieldset style="display:flex;" ${lop == null ? 'disabled' : ''}>
						<label class="form-group" style="grid-area: package">
							Tên gói tập
							<select name="GoiTap">
								<option value="NONE" selected="selected"> --SELECT--</option>
								<c:forEach  var="p" items="${lop.getListGoiTap()}" begin="0" end="${lop.getListGoiTap().size()}" varStatus="status">
									<c:if test="${p.getTrangThai() == 1 && p.getSoLuotDangKy() < p.getSoHocVien()}">
										<option value="${p.getMaGoiTap()}">${p.getTenGoiTap()} <fmt:formatNumber value="${p.getPrice()}" type="currency"/></option>
									</c:if>
								</c:forEach>
							</select>
						</label>
						<label class="form-group">
							<span style="color: transparent">A</span>
							<input type="submit" value="Thêm" style="margin:0;">
						</label>
					</fieldset>
				</form:form>
				<p class="error-message">${addPackageError}</p>
			</div>

			<div class="table-container">
				<h2 class="table-header">Danh sách gói tập</h2>
				<table class="table">
	           		<thead>
	           			<tr>
	           				<th class="ta-start">Mã gói tập</th>
	           				<th class="ta-start">Tên gói tập</th>
	           				<th class="ta-start">Lớp dịch vụ</th>
	           				<th class="ta-start">Giá</th>
	           				<th class="ta-start">Xóa</th>
	           			</tr>
	           		</thead>
	           		<tbody>
	           			<c:forEach  var="p" items="${listCTT}" begin="0" end="${listCTT.size()}" varStatus="status">
							<tr>
		           				<td><p class="code" title="${p.getGoiTap().getMaGoiTap()}">${p.getGoiTap().getMaGoiTap()}</p></td>
		           				<td>${p.getGoiTap().getTenGoiTap()}</td>
		           				<td>${p.getGoiTap().getLopDichVu().getTenLop()}</td>
		           				<td><fmt:formatNumber value="${p.getGoiTap().getPrice()}" type="currency"/></td>
		           				<td><a href="remove-package-from-cart.htm?id=${id}&packageId=${p.getGoiTap().getMaGoiTap()}"><i class="bi bi-trash"></i></a></td>
		           			</tr>
						</c:forEach>
	           		</tbody>
	           	</table>
	           	
				<div class="btns-container">
					<form action="card.htm?id=${id}" method="post">
						<input type="submit" value="Đăng ký" ${listCTT.isEmpty() || listCTT == null ? 'disabled' : ''}/>
					</form>
				</div>
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