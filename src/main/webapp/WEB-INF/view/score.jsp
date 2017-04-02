<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<jsp:include page="header.jsp" />
	<div class="row">
    	<div class="col-12" id="aboutMe">
            <p>score</p>
            <table id="scoreTable">
                <tr>
                    <th>name</th>
                    <th>second name</th>
                    <th>money on account</th>
                    <th>rate</th>
                </tr>
                <tr>
                    <td>
                        ${user.getSecondName()}
                    </td>
                    <td>
                        ${user.getName()}
                    </td>
                    <td>
                        ${score.getMoney()}
                    </td>
                    <td>
                        <form:form method="post" modelAttribute="score" action="${user.getId()}">
                            <form:select path="idRate">
                                <option selected disabled>${currentRate.getName()}</option>

                                <c:forEach items="${rateList}" var="rate">
                                    <option value="${rate.getId()}">
                                        ${rate.getName()}
                                    </option>
                                </c:forEach>

                            </form:select>
                            <input type="submit" value="change rate"/>
                        </form:form>
                    </td>
                </tr>
            </table>
		</div>
	</div>
	<jsp:include page="footer.jsp" />