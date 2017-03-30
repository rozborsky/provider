<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<jsp:include page="header.jsp" />
	<div class="row">
    	<div class="col-12" id="aboutMe">
            <p>score</p>
            ${user.getName()}</br>
            ${user.getSecondName()}</br>
            ${score.getMoney()}
            ${rate.getName()}

			<!--form:select id="hour" path="hour" label="rate" >
                <form:options items="${hours}" />
            </form:select>time</br-->
		</div>
	</div>
	<jsp:include page="footer.jsp" />