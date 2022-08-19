<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Users</title>
    </head>
    <body>
        <c:if test="${message != null}">
            <div id="messageBox">
                <p> ${message} </p>
            </div>
            
        </c:if>
        <div >
            <h3> Add user</h3>
            
            <form method="post" action="users">
                <input placeholder="Email" type="email" required name="email"/>
                <br>
                <input placeholder="First Name" type="text" required name="firstname"/>
                <br>
                <input placeholder="Last Name" type="text" required name="lastname"/>
                <br>
                <input placeholder="Password" type="text" required name="password"/>
                <br>
                <input type="hidden" name="action" value="add"/>
                <select name="role">
                    <option value="1" <c:if test="{user.role.roleId == 1}"> selected </c:if>> System Admin </option>
                    <option value="1" <c:if test="{user.role.roleId == 2}"> selected </c:if>> Regular user </option>
                    <option value="1" <c:if test="{user.role.roleId == 3}"> selected </c:if>> Company admin </option>
                </select>
                <br>
                <input type="submit" value="save"/>              
            </form>
            
        </div>
                
                <div>
                    <h3> Manage users </h3>
                    <table>
                        <tr>
                            <th> Email </th>
                            <th> First name </th>
                            <th> Last name </th>
                            <th> Role </th>
                            <th> Edit </th>
                            <th> Delete </th>
                        </tr>
                        
                    <c:forEach var="user" items="${users}">
                               <tr>
                                   <td> ${user.email}</td>
                                   <td> ${user.firstName}</td>
                                   <td> ${user.lastName}</td>
                                   <td> ${user.role.roleNmae}</td>
                                   <c:url value="users" var="editurl">
                                       <c:param name="action" value="edit"/>
                                       <c:param name="email" value="${user.email}"/>
                                       </c:url>
                                   <td > <a href="${editurl}"> Edit </a> </td>
                                   
                                   <c:url value="users" var="deleteurl">
                                           <c:param name="action" value="delete"/>
                                           <c:param name="email" value="${user.email}"/>
                                               </c:url>
                                                   <td> <a href="${deleteur1}"> Delete </a> </td>                                          
                               </tr>
                        </c:forEach>
                    </table>
                </div>
                
                <h3> Edit user </h3>
                <form method="post" action="users">
                <input placeholder="First Name" type="text" required name="firstname" value="${edit.User.firstName}"/>
                <br>
                <input placeholder="First Name" type="text" required name="lastname" value="${edit.User.lastName}"/>
                <br>
                <input type="hidden" name="action" value="${editUser.email}"/>
                <input type="hidden" name="action" value="update"/>
                <select name="role">
                    <option value="1" <c:if test="{user.role.roleId == 1}"> selected </c:if>> System Admin </option>
                    <option value="1" <c:if test="{user.role.roleId == 2}"> selected </c:if>> Regular user </option>
                    <option value="1" <c:if test="{user.role.roleId == 3}"> selected </c:if>> Company admin </option>
                    
                </select>
                    <br>
                    <input type="submit" value="Update"/>
                    
                </form>
    </body>
</html>
