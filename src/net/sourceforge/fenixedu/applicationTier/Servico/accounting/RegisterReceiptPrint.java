package net.sourceforge.fenixedu.applicationTier.Servico.accounting;

import net.sourceforge.fenixedu.applicationTier.FenixService;
import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.FenixServiceException;
import net.sourceforge.fenixedu.domain.Employee;
import net.sourceforge.fenixedu.domain.accounting.Receipt;

public class RegisterReceiptPrint extends FenixService {

    public void run(final Receipt receipt, final Employee employee) throws FenixServiceException {
	receipt.registerReceiptPrint(employee);
    }
}