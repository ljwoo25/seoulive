<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table>
  <thead>
    <tr>
      <th>SEQ</th>
      <th>CATEGORY</th>
      <th>TITLE</th>
      <th>ADDRESS</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${travelData}" var="travel">
      <tr>
        <td>${travel.SEQ}</td>
        <td>${travel.CATEGORY}</td>
        <td>${travel.TITLE}</td>
        <td>${travel.ADDRESS}</td>
        <td><button onclick="selectTravel('${travel.SEQ}', '${travel.CATEGORY}', '${travel.TITLE}', '${travel.ADDRESS}', '${resultId}')">선택</button></td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<script>
	function selectTravel(seq, category, title, address, resultId) {
		
	    // 원래 창으로 선택된 데이터를 전달합니다.
	    window.opener.handleSelectedData(seq, category, title, address, resultId);
	    
	    window.close();
	}
</script>