/*
 * Created on 2004/07/18
 * 
 */
package net.sourceforge.fenixedu.applicationTier.Servico.manager;

import net.sourceforge.fenixedu.applicationTier.FenixService;

/**
 * @author Luis Cruz
 * 
 */
public class ConfirmManagerIdentity extends FenixService {

    public ConfirmManagerIdentity() {
    }

    public Boolean run() {
	// Authentication is taken care of by the filters.
	return new Boolean(true);
    }

}