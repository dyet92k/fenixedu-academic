package net.sourceforge.fenixedu.applicationTier.Servico.accounting;

import net.sourceforge.fenixedu.applicationTier.FenixService;
import net.sourceforge.fenixedu.domain.ExecutionSemester;
import net.sourceforge.fenixedu.domain.StudentCurricularPlan;

public class CreateEnrolmentOutOfPeriodEvent extends FenixService {

    public CreateEnrolmentOutOfPeriodEvent() {
	super();
    }

    public void run(final StudentCurricularPlan studentCurricularPlan, final ExecutionSemester executionSemester,
	    final Integer numberOfDelayDays) {
	studentCurricularPlan.getRegistration().getStudent().createEnrolmentOutOfPeriodEvent(studentCurricularPlan,
		executionSemester, numberOfDelayDays);
    }

}