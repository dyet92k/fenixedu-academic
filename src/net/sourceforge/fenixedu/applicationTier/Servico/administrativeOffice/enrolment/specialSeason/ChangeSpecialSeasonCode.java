package net.sourceforge.fenixedu.applicationTier.Servico.administrativeOffice.enrolment.specialSeason;

import net.sourceforge.fenixedu.applicationTier.FenixService;
import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.FenixServiceException;
import net.sourceforge.fenixedu.domain.ExecutionYear;
import net.sourceforge.fenixedu.domain.SpecialSeasonCode;
import net.sourceforge.fenixedu.domain.student.Registration;

public class ChangeSpecialSeasonCode extends FenixService {

    public void run(Registration registration, ExecutionYear executionYear, SpecialSeasonCode specialSeasonCode)
	    throws FenixServiceException {
	if (executionYear == null) {
	    throw new FenixServiceException("executionYear.invalid.argument");
	}

	registration.setSpecialSeasonCode(executionYear, specialSeasonCode);
    }

}
