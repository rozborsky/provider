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
                    <tr>
                    <form:form method="post" modelAttribute="user" action="users">
                        <td></td>
                        <td>
                             <form:input path="name" type="text" />
                             <form:errors path="name" />
                        </td>
                        <td>
                            <form:input path="secondName" type="text" />
                            <form:errors path="secondName" />
                        </td>
                        <td>
                            <form:input path="address" type="text" />
                            <form:errors path="address" />
                        </td>
                        <td>
                            <input type="submit" value="add user"/>
                        </td>
                        </form:form>
                    </tr>
                </table>
			</p>
		</div>
	</div>
	<jsp:include page="footer.jsp" />