<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %><%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %><div align="center">	<span class="error"><html:errors/></span>	<br/>	<h2 style="text-align:center">		<bean:message key="title.student.enrollment.simple"/>	</h2>			</div><div class="infoselected">
		<h2>Aten��o:</h2>		<ul>		<li><bean:message key="message.students.with.tutor"/></li>		<li><bean:message key="message.LEEC.students.tutor"/></li>		<li><bean:message key="message.LMAC.students"/></li>		<li><bean:message key="message.LEFT.students"/></li>		<li><bean:message key="message.LEIC.students"/></li>		<li><bean:message key="message.LESIM.students"/></li>
		</ul>
		<ul>
		<li><a href="@enrollment.faq.url@" target="_blank"><bean:message key="message.enrollment.instructions"/></a>	</li>		</ul></div>

<div><br/><br/>	<html:form action="/studentCurricularCoursesEnrollment.do">		<html:hidden property="method" value="prepareEnrollment"/>		<html:submit styleClass="inputbutton">			<bean:message key="button.continue.enrolment"/>		</html:submit>	</html:form></div>