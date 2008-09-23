/*
 * Created on Jan 20, 2005
 *
 */
package net.sourceforge.fenixedu.applicationTier.Servico.masterDegree.administrativeOffice.guide;

import java.sql.Timestamp;
import java.util.Calendar;

import net.sourceforge.fenixedu.applicationTier.IUserView;
import net.sourceforge.fenixedu.applicationTier.FenixService;
import net.sourceforge.fenixedu.domain.GratuitySituation;
import net.sourceforge.fenixedu.domain.Guide;
import net.sourceforge.fenixedu.domain.GuideEntry;
import net.sourceforge.fenixedu.domain.Person;
import net.sourceforge.fenixedu.domain.PersonAccount;
import net.sourceforge.fenixedu.domain.degree.DegreeType;
import net.sourceforge.fenixedu.domain.student.Registration;
import net.sourceforge.fenixedu.domain.transactions.GratuityTransaction;
import net.sourceforge.fenixedu.domain.transactions.TransactionType;
import net.sourceforge.fenixedu.persistenceTier.ExcepcaoPersistencia;

/**
 * @author <a href="mailto:shezad@ist.utl.pt">Shezad Anavarali </a>
 * 
 */
public class CreateGratuityTransaction extends FenixService {

    public void run(Integer guideEntryID, IUserView userView) {

	GuideEntry guideEntry = rootDomainObject.readGuideEntryByOID(guideEntryID);

	Guide guide = guideEntry.getGuide();
	Registration registration = guide.getPerson().readStudentByDegreeType(DegreeType.MASTER_DEGREE);
	GratuitySituation gratuitySituation = registration.readGratuitySituationByExecutionDegree(guide.getExecutionDegree());
	PersonAccount personAccount = guide.getPerson().getAssociatedPersonAccount();

	if (personAccount == null) {
	    personAccount = new PersonAccount(guide.getPerson());
	}

	Person responsible = Person.readPersonByUsername(userView.getUtilizador());

	Double value = new Double(guideEntry.getPrice().doubleValue() * guideEntry.getQuantity().intValue());

	new GratuityTransaction(value, new Timestamp(Calendar.getInstance().getTimeInMillis()), guideEntry.getDescription(),
		guide.getPaymentType(), TransactionType.GRATUITY_ADHOC_PAYMENT, Boolean.FALSE, responsible, personAccount,
		guideEntry, gratuitySituation);

	gratuitySituation.updateValues();

    }

}