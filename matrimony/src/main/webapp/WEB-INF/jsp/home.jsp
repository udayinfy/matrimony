<%@page pageEncoding="UTF8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<t:layout>
	<jsp:body>
	<c:set var="alias" value="matrimony" />
	<title>Trang chủ</title>
	<div class="dropdown">
  <button class="btn btn-default dropdown-toggle" type="button"
				id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="true">
    Dropdown
    <span class="caret"></span>
  </button>
  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
    <li><a href="#">Action</a></li>
    <li><a href="#">Another action</a></li>
    <li><a href="#">Something else here</a></li>
    <li><a href="#">Separated link</a></li>
  </ul>
</div>

        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <a href="/${alias }/${user.username}">${user.name}</a>
        DANH SACH GOI Y KET BAN sfsdfasdfds
        <c:forEach var="list" items="${listSuggest}">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Email</th>
                    <th>Gender</th>
                    <th>Request friend</th>
                </tr>
                <tr>
                    <td>${list.accountId}</td>
                    <td>${list.email}</td>
                    <td>${list.gender}</td>
                    <td><a
						href="/DemoSpringHiber/controller/sendRequest/${list.accountId}/${sessionScope.id}">SendRequest</a></td>
                </tr>
            </table>
        </c:forEach>
        DANH SACH LOI MOI KET BAN
        <c:forEach var="list" items="${listRequest}">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Email</th>
                    <th>Gender</th>
                    <th>Request friend</th>
                </tr>
                <tr>
                    <td>${list.accountId}</td>
                    <td>${list.email}</td>
                    <td>${list.gender}</td>
                    <td><a
						href="/DemoSpringHiber/controller/acceptRequest/${list.accountId}/${sessionScope.id}">Accept Request</a></td>
                    <td><a
						href="/DemoSpringHiber/controller/cancelRequest/${list.accountId}/${sessionScope.id}">Cancel Request</a></td>
                </tr>
            </table>
        </c:forEach>
        DANH SACH BAN BE
        <c:forEach var="list" items="${listFriend}">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Email</th>
                    <th>Gender</th>
                    <th>Request friend</th>
                </tr>
                <tr>
                    <td>${list.accountId}</td>
                    <td>${list.email}</td>
                    <td>${list.gender}</td>
                    <td><a
						href="/DemoSpringHiber/controller/removeFriend/${list.accountId}/${sessionScope.id}">Remove Friend List</a></td>
                </tr>
            </table>
        </c:forEach>
        <form action="/wedding_event_prj/logout" method="POST">
            <input type="submit" value="Đăng xuất" />
        </form>
	</jsp:body>
</t:layout>