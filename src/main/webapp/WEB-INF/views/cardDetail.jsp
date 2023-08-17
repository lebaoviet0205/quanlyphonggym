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
            
            <div class="table-container">
            	<div class="table-header">
            		<h2 class="title">Danh sách gói tập</h2>
            	</div>
            	<div class="btns-container">
            		<a 
            			href="card.htm?id=${the.getMaThe()}" 
            			class="insert-btn" 
            			${the.getTrangThai() == 0 ? 'style="pointer-events: none; opacity: 0.5;"' : ''}
           			>Thêm</a>
            	</div>
            	<table class="table">
            		<thead>
            			<tr>
            				<th class="ta-start">Mã gói tập</th>
            				<th class="ta-start">Tên gói tập</th>
            				<th class="ta-start">Ngày đăng ký</th>
            				<th class="ta-start">Ngày kết thúc</th>
            				<th class="ta-start">Trạng thái</th>
            			</tr>
            		</thead>
            		<tbody>
            			<c:forEach var="p" items="${listChiTietThe}" varStatus="status">
						  	<tr>
	            				<td><p class="code" title="${p.getGoiTap().getMaGoiTap()}">${p.getGoiTap().getMaGoiTap()}</p></td>
	            				<td>${p.getGoiTap().getTenGoiTap()}</td>
	            				<td>${p.getNgayDangKy()}</td>
	            				<td>${p.getNgayDangKy().plusDays(p.getGoiTap().getThoiHan())}</td>
	            				<td>
	            					<c:if test="${p.getNgayDangKy().plusDays(p.getGoiTap().getThoiHan()).isAfter(today) 
	            						&& bangDiemDanhService.countDiemDanh(p.getGoiTap().getMaGoiTap(), p.getThe().getMaThe(), p.getNgayDangKy()) < p.getGoiTap().getSoBuoiTap()}">
	            							<p style="color:var(--bs-success)">Còn hạn</p>
	            					</c:if>
	            					<c:if test="${!p.getNgayDangKy().plusDays(p.getGoiTap().getThoiHan()).isAfter(today) 
	            						|| bangDiemDanhService.countDiemDanh(p.getGoiTap().getMaGoiTap(), p.getThe().getMaThe(), p.getNgayDangKy()) >= p.getGoiTap().getSoBuoiTap()}">
	            						<a href="extend-package.htm?cardId=${p.getThe().getMaThe()}&packageId=${p.getGoiTap().getMaGoiTap()}" style="text-decoration: underline;">Gia hạn</a>
	            					</c:if>
            					</td>
	            			</tr>         				
            			</c:forEach>
            		</tbody>
            	</table>
            </div>
		</div>			
        <!-- Content End -->
        
        <div class="overlay ${errorMessage.length() > 0 ? '' :'hide' }"></div>
        <div class="success-message ${errorMessage.length() > 0 ? '' :'hide' }">
			<p>${errorMessage}</p>
			<button class="message-btn" onclick="hideMessage()">OK</button>
		</div>
        
        <script>
        	const hideMessage = () => {
        		document.querySelector(".success-message").classList.add("hide");
        		document.querySelector(".overlay").classList.add("hide");
        	}
        
        	const statusBtns = document.querySelectorAll(".status");
        	statusBtns.forEach((btn)=>{
        		if (btn)
	        		btn.addEventListener("click", ()=> {
	        			btn.querySelector(".dot").classList.toggle("active-dot");
	        		})
        	})
        </script>


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