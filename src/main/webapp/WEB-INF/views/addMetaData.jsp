<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<html>
<head>
    <title>Add Metadata Form</title>
    <style>
    .error
    {
        color: #ff0000;
        font-weight: bold;
    }
    </style>

    <script>

    </script>
</head>
 
<body>
    <h2><spring:message  text="Add New Metadata" /></h2>
    <br/>
    <form:form method="post" modelAttribute="metadata">
        <table>
            <tr>
                <td><spring:message text="Metadata Name" /></td>
                <td><form:input path="name" /></td>
                <td><form:errors path="name" cssClass="error" /></td>
            </tr>
            <tr>
                <td><spring:message text="Metadata Description" /></td>
                <td><form:input path="description" /></td>
                <td><form:errors path="description" cssClass="error" /></td>
            </tr>
            <tr>
                <td><spring:message text="Metadata Label" /></td>
                <td><form:input path="label" /></td>
                <td><form:errors path="label" cssClass="error" /></td>
            </tr>
            <tr>
                <td colspan="3"><input type="submit" value="Add Metadata"/></td>
            </tr>
        </table>

        <table>
            <tr>
                <th>Field Name</th>
                <th>Field Type</th>
            </tr>
            <c:forEach items="${metadata.fields}" var="field" varStatus="tagStatus">
                <tr>
                    <td><form:input path="fields[${tagStatus.index}].name" value="${field.name}" readonly="true"/></td>
                    <td><form:input path="fields[${tagStatus.index}].fieldType" value="${field.fieldType}" readonly="true"/></td>
                </tr>
            </c:forEach>
        </table>
    </form:form>
</body>
</html>