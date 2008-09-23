package net.sourceforge.fenixedu.applicationTier.Servico.resourceAllocationManager;

/**
 * Servico LerSalas
 * 
 * @author tfc130
 * @version
 */

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.fenixedu.applicationTier.FenixService;
import net.sourceforge.fenixedu.dataTransferObject.InfoExecutionDegree;
import net.sourceforge.fenixedu.dataTransferObject.InfoExecutionYear;
import net.sourceforge.fenixedu.domain.ExecutionDegree;
import net.sourceforge.fenixedu.domain.ExecutionYear;
import net.sourceforge.fenixedu.persistenceTier.ExcepcaoPersistencia;

public class ReadExecutionDegreesByExecutionYear extends FenixService {

    public List run(InfoExecutionYear infoExecutionYear) {

	final List infoExecutionDegreeList = new ArrayList();
	final List<ExecutionDegree> executionDegrees = readExecutionDegrees(infoExecutionYear);

	for (ExecutionDegree executionDegree : executionDegrees) {
	    final InfoExecutionDegree infoExecutionDegree = InfoExecutionDegree.newInfoFromDomain(executionDegree);
	    infoExecutionDegreeList.add(infoExecutionDegree);
	}
	return infoExecutionDegreeList;
    }

    private List<ExecutionDegree> readExecutionDegrees(final InfoExecutionYear infoExecutionYear) {
	if (infoExecutionYear == null) {
	    return ExecutionDegree.getAllByExecutionYear(ExecutionYear.readCurrentExecutionYear().getYear());
	}
	return ExecutionDegree.getAllByExecutionYear(infoExecutionYear.getYear());
    }

}