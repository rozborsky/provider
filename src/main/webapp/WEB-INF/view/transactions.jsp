<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<jsp:include page="header.jsp" />
	<div class="row">
    	<div class="col-12" id="aboutMe">
			<p id="text">
				<p>transactions</p>
				<table>
				<tr>
                    <c:forEach items="${transactions}" var="transaction">
                        <tr>
                            <td>
                                ${transaction.getId()}
                            </td>
                            <td>
                                ${transaction.getIdUser()}
                            </td>
                            <td>
                                ${transaction.getTimestamp()}
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