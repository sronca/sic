<%@ tag import="org.springframework.util.StringUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="pagedListHolder" required="true" type="org.springframework.beans.support.PagedListHolder" %>
<%@ attribute name="pagedLink" required="true" type="java.lang.String" %>

<c:if test="${pagedListHolder.pageCount > 1}">
	<div class="sc-pager">
	    <c:if test="${pagedListHolder.firstPage}">
		    <div class="sc-pager-left">
		        <a href="javascript:void(0);" class="sc-pager-first">First</a>
		        <a href="javascript:void(0);" class="sc-pager-prev">Prev</a>
		    </div>
	    </c:if>
	    <c:if test="${!pagedListHolder.firstPage}">
		    <div class="sc-pager-left">
		        <a href="<%= StringUtils.replace(pagedLink, "~", "0") %>" class="sc-pager-first active">First</a>
		        <a href="<%= StringUtils.replace(pagedLink, "~", String.valueOf(pagedListHolder.getPage()-1)) %>" class="sc-pager-prev active">Prev</a>
		    </div>
		</c:if>
		<span>Pagina ${pagedListHolder.page + 1}/${pagedListHolder.pageCount}</span>
	    <c:if test="${pagedListHolder.lastPage}">
		    <div class="sc-pager-right">
		        <a href="javascript:void(0);" class="sc-pager-next">Next</a>
		        <a href="javascript:void(0);" class="sc-pager-last">Last</a>
		    </div>
	    </c:if>	
	    <c:if test="${!pagedListHolder.lastPage}">
		    <div class="sc-pager-right">
		        <a href="<%= StringUtils.replace(pagedLink, "~", String.valueOf(pagedListHolder.getPage()+1)) %>" class="sc-pager-next active">Next</a>
		        <a href="<%= StringUtils.replace(pagedLink, "~", String.valueOf(pagedListHolder.getPageCount()-1)) %>" class="sc-pager-last active">Last</a>
		    </div>	        
	    </c:if>
    </div>
</c:if>

