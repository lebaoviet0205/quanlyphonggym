<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
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
            
            <div class="form-container" style="display: flex; justify-content: space-between; align-items: center;">
            	<h2>Doanh Thu</h2>
            	<h4 style="margin: 0;"><fmt:formatNumber value="${total}" type="currency"/></h4>
            </div>
	
			<div class="form-container">
				<h2 class="form-header">Thống kê</h2>
				<form action="statistic.htm" method="post" class="form form-statistic">
					<label class="form-group">
						Từ ngày
						<input type="date" name="from" class="from" placeholder="dd/mm/yyyy" value="${from}">
					</label>
					<label class="form-group">
						Đến ngày
						<input type="date" name="to" class="to" placeholder="dd/mm/yyyy" value="${to}">
					</label>
					
					<input type="submit" class="submit" value="Thống kê" style="margin: 15px 10px; align-self: end;">
				</form>
				<p class="error-message error-date"></p>
			</div>

			<div class="table-container">
				<table class="table">
	           		<thead>
	           			<tr>
	           				<th class="ta-start">Mã hóa đơn</th>
	           				<th class="ta-start">Họ và tên</th>
	           				<th class="ta-start">Ngày lập hóa đơn</th>
	           				<th class="ta-start">Tổng tiền</th>
	           				<th>Chi tiết</th>
	           			</tr>
	           		</thead>
	           		<tbody>
	           			<c:forEach var="p" items="${listHoaDon}" begin="0" end="${listHoaDon.size()}" varStatus="status">
						  	<tr>
		           				<td><p class="code" title="${p.getMaThe()}">${p.getMaThe()}</p></td>
		           				<td>${p.getFullName()}</td>
		           				<td>${p.getNgayLap()}</td>
		           				<td><fmt:formatNumber value="${p.getTotal()}" type="currency"/></td>
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

	<script>
		const from = document.querySelector('.from');
		const to = document.querySelector(".to");
		const submitBtn = document.querySelector(".submit");
		const errorDate = document.querySelector(".error-date");
		
		const validate = () => {
			if(to.value < from.value){
				errorDate.innerText = "Ngày không hợp lệ";
				submitBtn.disabled = true;
			} else {
				errorDate.innerText = "";
				submitBtn.disabled = false;
			}
		}
		
		from.addEventListener("change", validate)
		
		to.addEventListener("change", validate)
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