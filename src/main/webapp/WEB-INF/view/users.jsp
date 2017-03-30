<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<jsp:include page="header.jsp" />
	<div class="row">
    	<div class="col-12" id="aboutMe">
			<p id="text">
				<p>users</p>
                <table>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>
                                ${user.getId()}
                            </td>
                            <td>
                                ${user.getName()}
                            </td>
                            <td>
                                ${user.getSecondName()}
                            </td>
                            <td>
                                ${user.getAddress()}
                            </td>
                            <td>
                                <a href="score/${user.getId()}">
                                    <button>csore</button>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                <form:form method="post" modelAttribute="user" action="users">
                    <form:input path="name" type="text" />
                    <form:errors path="name" />
                    <form:input path="secondName" type="text" />
                    <form:errors path="secondName" />
                    <form:input path="address" type="text" />
                    <form:errors path="address" />
                    <input type="submit" value="add user"/>
                </form:form>
			</p>
		</div>
	</div>
	<jsp:include page="footer.jsp" />