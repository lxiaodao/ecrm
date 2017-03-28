<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>File Uploading Form</title>
</head>
<body>
<h3>File Upload:</h3>
Select a file to upload: <br />

<!--  
<form action="${ctx}/upload/video.do" method="post"  enctype="multipart/form-data">
file1<input type="file" name="filename" size="50"  />
videoName<input type="text" name="videoName" />
<br />
<input type="submit" value="Upload File" />
</form>
-->
<form action="${ctx}/upload/uploadMultipleFiles.do?${_csrf.parameterName}=${_csrf.token}" method="post"  enctype="multipart/form-data">

file1<input type="file" name="filename"   />
id<input type="text" name="id" />
<br />
<input type="submit" value="Upload File" />

</form>
</body>
</html>