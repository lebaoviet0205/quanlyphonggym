<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Login</title>
<link href="resources/css/login.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
</head>
<body>
	<h1>File Upload</h1>
	<form action="upload-file" method="post" enctype="multipart/form-data">
		<input type="file" name="profile" id="profile"/>
		<button>submit</button>
	</form>
</body>