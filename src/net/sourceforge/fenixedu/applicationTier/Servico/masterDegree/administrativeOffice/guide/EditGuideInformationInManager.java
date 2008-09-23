package net.sourceforge.fenixedu.applicationTier.Servico.masterDegree.administrativeOffice.guide;

import net.sourceforge.fenixedu.applicationTier.FenixService;
import net.sourceforge.fenixedu.domain.DegreeCurricularPlan;
import net.sourceforge.fenixedu.domain.ExecutionDegree;
import net.sourceforge.fenixedu.domain.Guide;
import net.sourceforge.fenixedu.domain.transactions.PaymentType;
import net.sourceforge.fenixedu.persistenceTier.ExcepcaoPersistencia;

/**
 * 
 * @author <a href="mailto:shezad@ist.utl.pt">Shezad Anavarali </a>
 * 
 */
public class EditGuideInformationInManager extends FenixService {

    public void run(Integer guideID, Integer degreeCurricularPlanID, String executionYear, String newPaymentType) {
	Guide guide = rootDomainObject.readGuideByOID(guideID);

	DegreeCurricularPlan degreeCurricularPlan = rootDomainObject.readDegreeCurricularPlanByOID(degreeCurricularPlanID);
	ExecutionDegree cursoExecucao = ExecutionDegree.getByDegreeCurricularPlanAndExecutionYear(degreeCurricularPlan,
		executionYear);

	if (newPaymentType != null && newPaymentType.length() > 0) {
	    guide.setPaymentType(PaymentType.valueOf(newPaymentType));
	}
	guide.setExecutionDegree(cursoExecucao);

    }

}
