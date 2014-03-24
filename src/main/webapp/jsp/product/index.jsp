<head>
	<title>Product [index]</title>
</head>
<body>
	<h1>Listing Products</h1>

	<table>
		<tr>
			<th>Name</th>
			<th>Value</th>
			<th></th>
			<th></th>
			<th></th>
		</tr>

		<c:forEach items="${productList}" var="product">
			<tr>
				<td>${product.name}</td>
				<td>${product.value}</td>
				<td><a href="${pageContext.request.contextPath}/products/${product.id}">show</a></td>
				<td><a href="${pageContext.request.contextPath}/products/${product.id}/edit">edit</a></td>
				<td>
					<form action="${pageContext.request.contextPath}/products/${product.id}" method="post">
						<input type="hidden" name="_method" value="delete"/>
						<button type="submit" onclick="return confirm('Are you sure?')">destroy</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	<br />
	<a href="${pageContext.request.contextPath}/products/new">New Product</a> 
</body>