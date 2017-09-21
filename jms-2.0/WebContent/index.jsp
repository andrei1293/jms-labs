<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JMS 2.0</title>
</head>
<body>
	<div>
		<form action="./jms" method="post">
			<input type="hidden" name="service" value="sender" /> 
			<p>
				Text: <input type="text" name="text" />
			</p>
			<p>
				<input type="submit" value="Send" />
			</p>
		</form>
	</div>
	<div>
		<form action="./jms" method="post">
			<input type="hidden" name="service" value="receiver" />
			<p>
				<input type="submit" value="Receive" />
			</p>
		</form>
	</div>
	<p>
		<c:if test="${text ne null}">
			Received message: ${text}
		</c:if>
	</p>
	<p>
		<c:if test="${error ne null}">
			${error}
		</c:if>
	</p>
</body>
</html>