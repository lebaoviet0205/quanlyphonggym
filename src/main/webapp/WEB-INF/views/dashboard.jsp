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


            <!-- Sale & Revenue Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="row g-4">
                    <div class="col-sm-6 col-xl-3">
                        <div class="bg-light rounded d-flex align-items-center justify-content-between p-4">
                            <i class="fa fa-chart-line fa-3x text-primary"></i>
                            <div class="ms-3">
                                <p class="mb-2">Doanh thu trong ngày</p>
                                <h6><fmt:formatNumber value="${todayRevenue}" type="currency" /></h6>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-xl-3">
                        <div class="bg-light rounded d-flex align-items-center justify-content-between p-4">
                            <i class="fa fa-chart-bar fa-3x text-primary"></i>
                            <div class="ms-3">
                                <p class="mb-2">Doanh thu trong tháng</p>
                                <h6 class="mb-0"><fmt:formatNumber value="${thisMonthRevenue}" type="currency" /></h6>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-xl-3">
                        <div class="bg-light rounded d-flex align-items-center justify-content-between p-4">
                            <i class="fa fa-chart-area fa-3x text-primary"></i>
                            <div class="ms-3">
                                <p class="mb-2">Khách hàng mới trong ngày</p>
                                <h6 class="mb-0">${todayNewCustomer}</h6>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-xl-3">
                        <div class="bg-light rounded d-flex align-items-center justify-content-between p-4">
                            <i class="fa fa-chart-pie fa-3x text-primary"></i>
                            <div class="ms-3">
                                <p class="mb-2">Khách hàng mới trong tháng</p>
                                <h6 class="mb-0">${thisMonthNewCustomer}</h6>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Sale & Revenue End -->


            <!-- Sales Chart Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="row g-4">
                    <div class="col-sm-12 col-xl-6">
                        <div class="bg-light text-center rounded p-4">
                            <div class="d-flex align-items-center justify-content-between mb-4">
                                <h6 class="mb-0">Khách hàng mới</h6>
                                <a href="customer.htm">Chi tiết</a>
                            </div>
                            <canvas id="worldwide-sales"></canvas>
                        </div>
                    </div>
                    
                    <div class="col-sm-12 col-xl-6">
                        <div class="bg-light text-center rounded p-4">
                            <div class="d-flex align-items-center justify-content-between mb-4">
                                <h6 class="mb-0">Doanh thu</h6>
                                <a href="statistic.htm">Chi tiết</a>
                            </div>
                            <canvas id="salse-revenue"></canvas>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Sales Chart End -->


            <!-- Recent Sales Start -->
            <div class="container-fluid pt-4 px-4 recent-sales">
                <div class="bg-light text-center rounded p-4">
                    <div class="d-flex align-items-center justify-content-between mb-4">
                        <h6 class="mb-0">Hóa đơn gần đây</h6>
                        <a href="bill.htm">Chi tiết</a>
                    </div>
                    <div class="table-responsive">
                        <table class="table">
	           		<thead>
	           			<tr>
	           				<th class="ta-start">Mã thẻ</th>
	           				<th>Họ và tên</th>
	           				<th>Ngày lập hóa đơn</th>
	           				<th>Tổng tiền</th>
	           				<th>Chi tiết</th>
	           			</tr>
	           		</thead>
	           		<tbody>
	           			<c:forEach var="p" items="${listHoaDonThisMonth}" begin="0" end="${listHoaDonThisMonth.size() > 10 ? 10 : listHoaDonThisMonth.size()}" varStatus="status">
						  	<tr>
		           				<td><p class="code" title="${p.getMaThe()}">${p.getMaThe()}</p></td>
		           				<td>${p.getFullName()}</td>
		           				<td>${p.getNgayLap()}</td>
		           				<td><fmt:formatNumber value="${p.getTotal()}" type="currency" /></td>
		           				<td><a href="bill-detail.htm?id=${p.getMaHD()}"><i class="bi bi-receipt-cutoff center"></i></a></td>
		           			</tr>         				
            			</c:forEach>
	           		</tbody>
	           	</table>
                    </div>
                </div>
            </div>
            <!-- Recent Sales End -->
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
    
    <script>
    	// Worldwide Sales Chart
	    var ctx1 = document.querySelector("#worldwide-sales").getContext("2d");
	    var myChart1 = new Chart(ctx1, {
	        type: "line",
	        data: {
	            labels: ${listOfMonth},
	            datasets: [{
	                    label: "Khách hàng mới",
	                    data: ${listOfNewCustomerPerMonth},
	                    backgroundColor: "rgba(0, 156, 255, .7)",
	                    fill: true
	                }
	            ]
	            },
	        options: {
	            responsive: true
	        }
	    });

	 	// Salse & Revenue Chart
	    var ctx2 = document.querySelector("#salse-revenue").getContext("2d");
	    var myChart2 = new Chart(ctx2, {
	        type: "line",
	        data: {
	            labels: ${listOfMonth},
	            datasets: [{
	                    label: "Doanh thu",
	                    data: ${listOfRevenuePerMonth},
	                    backgroundColor: "rgba(0, 156, 255, .5)",
	                    fill: true
	                }
	            ]
	            },
	        options: {
	            responsive: true
	        }
	    });
    </script>
</body>

</html>