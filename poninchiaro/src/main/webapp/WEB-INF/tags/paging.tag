<%@ tag import="org.springframework.util.StringUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="pagedListHolder" required="true" type="org.springframework.beans.support.PagedListHolder" %>
<%@ attribute name="pagedLink" required="true" type="java.lang.String" %>

<c:if test="${pagedListHolder.pageCount > 1}">

    <c:if test="${pagedListHolder.firstPage}">
    	<a class="bck" href="javascript:void(0);">&lt;&lt;</a>
        <a class="bck2" href="javascript:void(0);">&lt;</a>
    </c:if>
    
    <c:if test="${!pagedListHolder.firstPage}">
    	<a class="bck" href="<%= StringUtils.replace(pagedLink, "~", "0") %>">&lt;&lt;</a>
        <a class="bck2" href="<%= StringUtils.replace(pagedLink, "~", String.valueOf(pagedListHolder.getPage()-1)) %>">&lt;</a>
    </c:if>
    
<%--     <c:if test="${pagedListHolder.firstLinkedPage > 1}">
        <span class="pagingDots">...</span>
    </c:if> --%>
    
    <c:forEach begin="${pagedListHolder.firstLinkedPage}" end="${pagedListHolder.lastLinkedPage}" var="i">
        <c:choose>
            <c:when test="${pagedListHolder.page == i}">
                <a class="active" href="javascript:void(0);">${i+1}</a>
            </c:when>
            <c:when test="${(pagedListHolder.page == i+1) or (pagedListHolder.page == i-1)}">
                <a href="<%= StringUtils.replace(pagedLink, "~", String.valueOf(jspContext.getAttribute("i"))) %>">${i+1}</a>
            </c:when>            
            <c:otherwise>
                <a class="no-mobile" href="<%= StringUtils.replace(pagedLink, "~", String.valueOf(jspContext.getAttribute("i"))) %>">${i+1}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    
<%--     <c:if test="${pagedListHolder.lastLinkedPage < pagedListHolder.pageCount - 2}">
        <span class="pagingDots">...</span>
    </c:if> --%>

    <c:if test="${pagedListHolder.lastPage}">
        <a class="fwd" href="javascript:void(0);">&gt;</a>
        <a class="fwd2" href="javascript:void(0);">&gt;&gt;</a>
    </c:if>
    
    <c:if test="${!pagedListHolder.lastPage}">
        <a class="fwd" href="<%= StringUtils.replace(pagedLink, "~", String.valueOf(pagedListHolder.getPage()+1)) %>">&gt;</a>
        <a class="fwd2" href="<%= StringUtils.replace(pagedLink, "~", String.valueOf(pagedListHolder.getPageCount()-1)) %>">&gt;&gt;</a>
    </c:if>
    
</c:if>