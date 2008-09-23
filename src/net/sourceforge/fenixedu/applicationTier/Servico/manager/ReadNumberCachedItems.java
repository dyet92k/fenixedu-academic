/*
 * Created on 2003/08/08
 * 
 */
package net.sourceforge.fenixedu.applicationTier.Servico.manager;

import net.sourceforge.fenixedu.applicationTier.FenixService;
import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.FenixServiceException;

import pt.ist.fenixframework.pstm.Transaction;

/**
 * @author Luis Cruz & Sara Ribeiro
 * 
 */
public class ReadNumberCachedItems extends FenixService {

    public Integer run() throws FenixServiceException {
	Integer numberCachedObjects = null;

	numberCachedObjects = Transaction.getCache().getNumberOfCachedItems();

	return numberCachedObjects;
    }

}
