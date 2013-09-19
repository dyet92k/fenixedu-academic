<%@ taglib uri="/WEB-INF/fenix-renderers.tld" prefix="fr"%>
<%@ taglib uri="/WEB-INF/app.tld" prefix="app"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<html:xhtml />

<%@ page
	import="net.sourceforge.fenixedu.presentationTier.Action.resourceAllocationManager.utils.PresentationConstants"%>

<em><bean:message key="label.person.main.title" /></em>
<h2>
	<bean:message key="oauthapps.label.manage.applications" bundle="APPLICATION_RESOURCES" />
</h2>

<html:link page="/externalApps.do?method=manageApplications">
		<bean:message key="label.back" bundle="APPLICATION_RESOURCES"/>
</html:link>


<logic:notEmpty name="application">
		<fr:view name="application" schema="oauthapps.view.app.details">
			<fr:layout name="tabular">
				<fr:property name="classes" value="tstyle4 thcenter thcenter"/>
			</fr:layout>
		</fr:view>
</logic:notEmpty>

<logic:empty name="application">
	<bean:message key="oauthapps.label.no.apps" bundle="APPLICATION_RESOURCES" />
</logic:empty>

<p>
	<html:link page="/externalApps.do?method=prepareEditApplication" paramId="appOid" paramName="application" paramProperty="externalId">
		<bean:message key="oauthapps.label.edit.application" bundle="APPLICATION_RESOURCES"/>
	</html:link>
	<html:link page="/externalApps.do?method=deleteApplication" paramId="appOid" paramName="application" paramProperty="externalId">
		<bean:message key="oauthapps.label.delete.application" bundle="APPLICATION_RESOURCES"/>
	</html:link>
</p>

<script type="text/javascript">
		$("table img").width("75px").height("75px");
		$("a[href*=deleteApplication]").click(function(e) {
			   answer = confirm('Deseja apagar a aplicação ?');
			   return answer;
			});
</script>
