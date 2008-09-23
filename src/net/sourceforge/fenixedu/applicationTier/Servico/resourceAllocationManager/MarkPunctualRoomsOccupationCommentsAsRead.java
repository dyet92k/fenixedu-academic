package net.sourceforge.fenixedu.applicationTier.Servico.resourceAllocationManager;

import net.sourceforge.fenixedu.applicationTier.FenixService;
import net.sourceforge.fenixedu.domain.PunctualRoomsOccupationRequest;

public class MarkPunctualRoomsOccupationCommentsAsRead extends FenixService {

    public void run(PunctualRoomsOccupationRequest request, boolean forTeacher) {
	if (request != null) {
	    if (forTeacher) {
		request.setTeacherReadComments(request.getCommentsCount());
	    } else {
		request.setEmployeeReadComments(request.getCommentsCount());
	    }
	}
    }
}
