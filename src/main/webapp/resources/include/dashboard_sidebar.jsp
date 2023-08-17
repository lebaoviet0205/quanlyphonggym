<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<div class="sidebar pe-4 pb-3">
    <nav class="navbar bg-light navbar-light">
        <a href="index.html" class="navbar-brand mx-4 mb-3">
            <h3 class="text-primary">GYMSTER</h3>
        </a>
        <div class="d-flex align-items-center ms-4 mb-4">
            <div class="position-relative">
                <img class="rounded-circle" src="<c:url value="${sessionScope.currentEmployee.getAnh() == null ? '/resources/img_2/no_avatar.webp' : sessionScope.currentEmployee.getAnh()}"/>" alt="" style="width: 40px; height: 40px;">
                <div class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
            </div>
            <div class="ms-3">
                <h6 class="mb-0" style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 150px;">${sessionScope.currentEmployee.getHo()} ${sessionScope.currentEmployee.getTen()}</h6>
                <span>${sessionScope.currentAccess.getChucVu()}</span>
            </div>
        </div>
        <div class="navbar-nav w-100">
            <a href="check.htm" class="nav-item nav-link"><i class="bi bi-calendar-check-fill"></i><span class="dashboard-link">Điểm danh</span></a>
            <c:if test="${!sessionScope.currentAccess.getMaQuyen().equals('2')}">
            	<a href="dashboard.htm" class="nav-item nav-link active"><i class="bi bi-house-fill"></i><span class="dashboard-link">Trang chủ</span></a>
            </c:if>
            <a href="register.htm" class="nav-item nav-link"><i class="bi bi-pencil-fill"></i><span class="dashboard-link">Đăng ký</span></a>
            <a href="customer.htm" class="nav-item nav-link"><i class="bi bi-people-fill"></i><span class="dashboard-link">Khách hàng</span></a>
            <a href="cards.htm" class="nav-item nav-link"><i class="bi bi-card-heading"></i><span class="dashboard-link">Thẻ</span></a>
			<c:if test="${!sessionScope.currentAccess.getMaQuyen().equals('2')}">
            	<a href="dashboard-class.htm" class="nav-item nav-link"><i class="bi bi-table"></i><span class="dashboard-link">Lớp dịch vụ</span></a>
			</c:if>
			<c:if test="${!sessionScope.currentAccess.getMaQuyen().equals('2')}">
            	<a href="statistic.htm" class="nav-item nav-link"><i class="bi bi-bar-chart-line-fill"></i><span class="dashboard-link">Thống kê</span></a>
			</c:if>
            <a href="bill.htm" class="nav-item nav-link"><i class="bi bi-receipt-cutoff"></i><span class="dashboard-link">Hóa đơn</span></a>
			<c:if test="${!sessionScope.currentAccess.getMaQuyen().equals('2')}">
            	<a href="employee.htm" class="nav-item nav-link"><i class="bi bi-person-fill"></i><span class="dashboard-link">Nhân viên</span></a>
			</c:if>
        </div>
    </nav>
</div>

<script>
  	let navItems = document.querySelectorAll(".nav-item");
  	
  	let path = window.location.href;
  	navItems.forEach((item) => {
  		if (path === item.href){
		item.classList.add("active");
	} else {
		item.classList.remove("active");
	}
  	})
</script>