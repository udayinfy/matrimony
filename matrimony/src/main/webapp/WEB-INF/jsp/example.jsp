<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
        <h1>THIS IS BODY</h1>
        ${empty sessionScope.abcxyz }
        <a href="#" class="btn btn-success">ngu nguoi</a>
    </jsp:body>
</t:layout>