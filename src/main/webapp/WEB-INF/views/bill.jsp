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
            
            <div class="table-container">
            	<div class="table-header">
            		<h2 class="title">Khách hàng</h2>
            		<form action="search-bill.htm" method="post" class="search-bar">
            			<span>Tìm kiếm</span>
            			<input type="text" name="search" placeholder="Tên khách hàng..." value="${search}">
            			<button style="border: none; outline: none;"><i class="bi bi-search"></i></button>
            		</form>
            	</div>
            	
            	<table class="table">
            		<thead>
            			<tr>
            				<th class="ta-start">Mã hóa đơn</th>
            				<th class="ta-start">Họ và tên</th>
            				<th class="ta-start">Tổng tiền</th>
            				<th class="ta-start">Tên nhân viên</th>
            				<th class="ta-start">Ngày lập</th>
            				<th class="ta-start">Chi tiết</th>
            			</tr>
            		</thead>
            		<tbody>
            			<c:forEach var="p" items="${listHoaDon}" varStatus="status">
						  	<tr>
	            				<td><p class="code" title="${p.getMaHD()}">${p.getMaHD()}</p></td>
	            				<td>${p.getFullName()}</td>
	            				<td><fmt:formatNumber value="${p.getTotal()}" type="currency"/></td>
	            				<td>${p.getNhanVien().getHo()} ${p.getNhanVien().getTen()}</td>
	            				<td>${p.getNgayLap()}</td>
	            				<td><a href="bill-detail.htm?id=${p.getMaHD()}"><i class="bi bi-receipt-cutoff center"></i></a></td>
	            			</tr>        				
            			</c:forEach>
            		</tbody>
            	</table>
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