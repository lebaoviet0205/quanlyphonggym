<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
				<h2 class="form-header">Thông tin lớp</h2>
				<form:form class="form form-class ${id == null ? '' :'form-class-edit'}" action="${id == null ? 'new-class.htm' : 'edit-class.htm'}" method="post" modelAttribute="lopDichVu">
					<c:if test="${id != null}">
						<label class="form-group" style="grid-area: id">
							Mã lớp
							<form:input type="text" path="MaLop" placeholder="Mã lớp" value="${id}" readonly="true"/>
						</label>
					</c:if>
					<label class="form-group" style="grid-area: classname">
						Tên lớp
						<form:input type="text" path="TenLop" placeholder="Tên lớp" value="${className}"/>
						<form:errors class="error-message" path="TenLop"/>
					</label>
					<label class="form-group" style="grid-area: editbtn">
						<span style="color: transparent;">Edit</span>
						<input class="save-class-btn" style="margin: 0;" disabled type="submit" value="Lưu"/>
					</label>
				</form:form>
				<h2 class="form-header" style="border-top: 1px solid #00000040; padding-top: 20px; margin-bottom: 0;">Thêm gói tập</h2>
				<form action="add-package.htm?id=${id}" method="post">
					<fieldset ${id == null ? 'disabled' : ''}  class="form form-class-package">
						<label class="form-group" style="grid-area: packagename">
							Tên gói tập
							<input type="text" name="TenGoiTap" placeholder="Tên gói tập" value="${tenGoiTap}">
							<span class="error-message">${tenGoiTapError}</span>
						</label>
						<label class="form-group" style="grid-area: price">
							Giá
							<input type="number" name="Gia" placeholder="Giá" value="${gia}">
							<span class="error-message">${giaError}</span>
						</label>
						<label class="form-group" style="grid-area: numberofdays">
							Số buổi tập
							<input type="number" name="SoBuoiTap" placeholder="Số buổi tập"  value="${soBuoiTap}">
							<span class="error-message">${soBuoiTapError}</span>
						</label>
						<label class="form-group" style="grid-area: duration">
							Thời hạn
							<input type="number" name="ThoiHan" placeholder="Thời hạn"  value="${thoiHan}">
							<span class="error-message">${thoiHanError}</span>
						</label>
						<label class="form-group" style="grid-area: numberofstudents">
							Số học viên
							<input type="number" name="SoHocVien" placeholder="Số học viên"  value="${soHocVien}">
							<span class="error-message">${soHocVienError}</span>
						</label>
						<input type="submit" value="Thêm" style="grid-area: submit">
					</fieldset>
				</form>
			</div>
			
			<div class="table-container">
            	<div class="table-header">
            		<h2 class="title">Danh sách gói tập</h2>
            	</div>
            	
            	<table class="table">
            		<thead>
            			<tr>
            				<th class="ta-start">Mã gói tập</th>
            				<th class="ta-start">Tên gói tập</th>
            				<th class="ta-start">Số buổi tập</th>
            				<th class="ta-start">Thời hạn</th>
            				<th class="ta-start">Số học viên</th>
            				<th class="ta-start">Số lượt đăng ký</th>
            				<th class="ta-start">Giá</th>
            				<th>Chỉnh sửa</th>
            				<th>Trạng thái</th>
            			</tr>
            		</thead>
            		<tbody>
            			<c:forEach var="p" items="${listGoiTap}" begin="0" end="${listGoiTap.size()}" varStatus="status">
            				<tr>
	            				<td><p class="code" title="${p.getMaGoiTap()}">${p.getMaGoiTap()}</p></td>
	            				<td>${p.getTenGoiTap()}</td>
	            				<td>${p.getSoBuoiTap()}</td>
	            				<td>${p.getThoiHan()} Ngày</td>
	            				<td>${p.getSoHocVien()}</td>
	            				<td>${p.getSoLuotDangKy()}</td>
	            				<td><fmt:formatNumber value="${p.getPrice()}" type="currency"/></td>
	            				<td><a href="edit-package.htm?id=${id}&package=${p.getMaGoiTap()}" onClick="(e) => showModal(e)"><i class="bi bi-pencil-fill center"></i></a></td>
	            				<td><a href="change-package-status.htm?id=${id}&package=${p.getMaGoiTap()}" class="change-status center"><span class="status"><span class="dot ${p.getTrangThai() == 0 ? '' : 'active-dot'}"></span></span></a></td>
	            			</tr>
            			</c:forEach>
            		</tbody>
            	</table>
            </div>
		</div>			
        <!-- Content End -->
        
        <div class="overlay ${goiTap.getMaGoiTap() == null ? 'hide' : ''}"></div>

		<div class="modal-form ${goiTap.getMaGoiTap() == null ? 'hide' : ''}">
			<h2 class="form-title">Thông tin gói tập</h2>
			<form action="edit-package.htm?id=${id}" method="post" class="center" style="flex-direction: column;">
				<label class="form-group">
					Mã gói tập
					<input type="text" name="MaGoiTap" value="${goiTap.getMaGoiTap()}" readonly="readonly"/>
				</label>
				<label class="form-group">
					Tên gói tập
					<input type="text" value="${goiTap.getTenGoiTap()}" readonly="readonly"/>
				</label>
				<label class="form-group">
					Giá gói tập
					<input type="number" name="Gia" id="Gia" required="required" value="${goiTap.getPrice()}"/>
					<span class="error-message"></span>
				</label>
				<label class="form-group">
					Số buổi tập
					<input type="number" name="SoBuoiTap" id="SoBuoiTap" required="required" value="${goiTap.getSoBuoiTap()}" ${goiTap.getSoLuotDangKy() > 0 ? 'readonly' : ''}/>
					<span class="error-message"></span>
				</label>
				<label class="form-group">
					Thời hạn
					<input type="number" name="ThoiHan" id="ThoiHan" required="required" value="${goiTap.getThoiHan()}" ${goiTap.getSoLuotDangKy() > 0 ? 'readonly' : ''}/>
					<span class="error-message"></span>
				</label>
				<label class="form-group">
					Số học viên
					<input type="number" name="SoHocVien" id="SoHocVien" required="required" value="${goiTap.getSoHocVien()}"/>
					<span class="error-message"></span>
				</label>
				<div>
					<button class="cancel">Hủy</button>
					<input type="submit" id="submit-btn" value="Lưu thay đổi"/>
				</div>
			</form>
		</div>
		
		<div class="confirm-dialog hide" id="change-status-dialog">
         	<div>
         		<h3>Xác nhận chỉnh sửa</h3>
	          	<p>Bạn có muốn thay đổi trạng thái gói tập này?</p>
	          	<div>
	          		<button class="secondary-btn close-dialog" onclick="handleHideChangeStatusDialog()">Hủy</button>
	          		<a href="" class="change-status-btn primary-btn">Xác nhận</a>
	          	</div>
         	</div>
         </div>

        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
    </div>

	<script>
		const className = document.querySelector("input[name='TenLop']");
		const saveClassBtn = document.querySelector(".save-class-btn");
		const defaultValue = className.value;
		className.addEventListener("input", (e)=>{
			const value = e.target.value;
			if(value !== defaultValue){
				saveClassBtn.disabled = false;
			} else {
				saveClassBtn.disabled = true;
			}
		})
	</script>
	
	<script>
		const modalForm = document.querySelector(".modal-form");
		const overlay = document.querySelector(".overlay");
		const cancelBtn = document.querySelector(".cancel");
		
		const showModal = (e) => {
			e.preventDefault();
			if(modalForm.classList.contains("hide") && overlay.classList.contains("hide")){
				modalForm.classList.remove("hide");
				overlay.classList.remove("hide");
			}
		}
		
		cancelBtn.addEventListener("click", (e)=>{
			e.preventDefault();
			if(!modalForm.classList.contains("hide") && !overlay.classList.contains("hide")){
				modalForm.classList.add("hide");
				overlay.classList.add("hide");
				
			}
		})
	</script>
	
	<script>
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
	
	<script>
		const price = document.querySelector("#Gia");
		const numberOfStudent = document.querySelector("#SoHocVien");
		const numberOfDays = document.querySelector("#SoBuoiTap");
		const duration = document.querySelector("#ThoiHan");
		const submitBtn = document.querySelector("#submit-btn");
		
		price.addEventListener("change", (e)=>{
			if (e.target.value <= 0) {
				e.target.nextElementSibling.innerText = "Giá không hợp lệ";
				submitBtn.disabled = true;
			} else {
				e.target.nextElementSibling.innerText = "";
				submitBtn.disabled = false;
			}
		})
		
		numberOfStudent.addEventListener("change", (e)=>{
			if (e.target.value <= 0) {
				e.target.nextElementSibling.innerText = "Số học viên không hợp lệ";
				submitBtn.disabled = true;
			} else {
				e.target.nextElementSibling.innerText = "";
				submitBtn.disabled = false;
			}
		})
		
		numberOfDays.addEventListener("change", (e)=>{
			if (e.target.value <= 0) {
				e.target.nextElementSibling.innerText = "Số buổi tập không hợp lệ";
				submitBtn.disabled = true;
			} else {
				e.target.nextElementSibling.innerText = "";
				submitBtn.disabled = false;
			}
		})
		duration.addEventListener("change", (e)=>{
			if (e.target.value <= 0) {
				e.target.nextElementSibling.innerText = "Thời hạn không hợp lệ";
				submitBtn.disabled = true;
			} else {
				e.target.nextElementSibling.innerText = "";
				submitBtn.disabled = false;
			}
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