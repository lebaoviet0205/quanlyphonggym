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
            		<h2 class="title">Nhân viên</h2>
            		<form action="search-employee.htm" method="post" class="search-bar">
            			<span>Tìm kiếm</span>
            			<input type="text" name="search" placeholder="Tên nhân viên..." value="${search}">
            			<button style="border: none; outline: none;"><i class="bi bi-search"></i></button>
            		</form>
            	</div>
            	<div class="btns-container">
            		<a href="add-employee.htm" class="insert-btn">Thêm</a>
            	</div>
            	<table class="table">
            		<thead>
            			<tr>
            				<th class="ta-start">Mã nhân viên</th>
            				<th class="ta-start">Họ và tên</th>
            				<th class="ta-start">Giới tính</th>
            				<th class="ta-start">Email</th>
            				<th class="ta-start">SĐT</th>
            				<th class="ta-start">Ngày sinh</th>
            				<th class="ta-start">Chức vụ</th>
            				<th class="ta-start">Ngày nhận chức</th>
            				<th class="ta-start">Chỉnh sửa</th>
            				<th class="ta-start">Trạng thái</th>
            			</tr>
            		</thead>
            		<tbody>
            			<c:forEach var="p" items="${listNhanVien}" begin="0" end="${listNhanVien.size()}" varStatus="status">
						  	<c:if test="${p.getTaiKhoan().getPhanQuyen().getMaQuyen() != 0}">
						  		<tr>
		            				<td><p class="code" title="${p.getMaNV()}">${p.getMaNV()}</p></td>
		            				<td>${p.getFullname()}</td>
		            				<td>${p.getGioiTinh() == 1 ? 'Nam' : 'Nữ'}</td>
		            				<td><p class="code" title="${p.getEmail()}">${p.getEmail()}</p></td>
		            				<td>${p.getSDT()}</td>
		            				<td>${p.getNgaySinh()}</td>
		            				<td>${p.getTaiKhoan().getPhanQuyen().getChucVu()}</td>
		            				<td>${p.getNgayNhanChuc()}</td>
		            				<td><a href="edit-employee.htm?id=${p.getMaNV()}"><i class="bi bi-pencil-fill center"></i></a></td>
		            				<td><a href="change-account-status.htm?id=${p.getTaiKhoan().getUsername()}" class="change-status center"><div class="status"><span class="dot ${p.getTaiKhoan().getTrangThai() == 1 ? 'active-dot' : ''}"></span></div></a></td>
		            			</tr>  
						  	</c:if>				
            			</c:forEach>
            		</tbody>
            	</table>
            </div>
		</div>			
        <!-- Content End -->
		<div class="overlay hide"></div>

		<div class="confirm-dialog hide" id="change-status-dialog">
        	<div>
        		<h3>Xác nhận chỉnh sửa</h3>
	         	<p>Bạn có muốn thay đổi trạng thái tài khoản này?</p>
	         	<div>
	         		<button class="secondary-btn close-dialog" onclick="handleHideChangeStatusDialog()">Hủy</button>
	         		<a href="" class="change-status-btn primary-btn">Xác nhận</a>
	         	</div>
        	</div>
        </div>

		<script>
			const overlay = document.querySelector(".overlay");
			const confirmStatusDialog = document.querySelector("#change-status-dialog");
		
			const handleHideChangeStatusDialog = () => {
				overlay.classList.add("hide");
				confirmStatusDialog.classList.toggle("hide");
			}
			
	    	const changeStatusBtns = document.querySelectorAll(".change-status");
	    	changeStatusBtns.forEach((btn) => {
	    		btn.addEventListener("click", (e) => {
	    			e.preventDefault();
	    			let target = e.target;
	    			while(target.nodeName !== 'A'){
	    				target = target.parentElement;
	    			}
	    			
	    			overlay.classList.remove("hide");
	    			confirmStatusDialog.classList.remove("hide");
	    			
	    			const changeStatusBtn = document.querySelector(".change-status-btn");
	    			changeStatusBtn.href = target.href;
	    			
	    			changeStatusBtn.addEventListener("click", ()=>{
	    				const dot = target.querySelector(".dot");
	    				dot.classList.toggle("active-dot");
	    			})
	    		})
	    	});
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