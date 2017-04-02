<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<jsp:include page="header.jsp" />
	<div class="row">
    	<div class="col-12" id="aboutMe">
			<p id="text">
				<p>transactions</p>
                <table>
                    <tr>
                        <form:form method="post" modelAttribute="filter" action="transactions">
                            <td>
                            </td>
                            <td>
                               <form:input path="surname" type="text" />
                            </td>
                            <td>
                                 <form:input path="name" type="text" />
                            </td>
                            <td>
                            </td>
                            <td>
                                <div class="input-daterange input-group" id="datepicker">
                                    <form:input path="startDate" type="text" class="input-sm form-control" name="from" placeholder="From date"/>
                                    <span class="input-group-addon">to</span>
                                    <form:input path="finishDate" type="text" class="input-sm form-control" name="to" placeholder="To date"/>
                                </div>
                            </td>
                            <td>
                                <input type="submit" value="get transactions"/>
                            </td>
                        </form:form>
                    </tr>
                    <tr>
                        <th>
                            id transaction
                        </th>
                        <th>
                            surname
                        </th>
                        <th>
                            name
                        </th>
                        <th>
                            id user
                        </th>
                        <th>
                            date
                        </th>
                        <th>
                            cash flow
                        </th>
                    </tr>
                    <c:forEach items="${transactions}" var="transaction">
                        <tr>
                            <td>
                                ${transaction.getId()}
                            </td>
                            <td>
                                ${transaction.getSurname()}
                            </td>
                            <td>
                                ${transaction.getName()}
                            </td>
                            <td>
                                ${transaction.getIdUser()}
                            </td>
                            <td>
                                ${transaction.getDate()}
                            </td>
                            <td>
                                ${transaction.getChange()}
                            </td>
                        </tr>
                    </c:forEach>
                </table>
			</p>
		</div>
	</div>
	<jsp:include page="footer.jsp" />