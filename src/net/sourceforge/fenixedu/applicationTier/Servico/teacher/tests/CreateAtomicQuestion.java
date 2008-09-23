package net.sourceforge.fenixedu.applicationTier.Servico.teacher.tests;

import net.sourceforge.fenixedu.applicationTier.FenixService;
import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.FenixServiceException;
import net.sourceforge.fenixedu.domain.tests.NewQuestion;
import net.sourceforge.fenixedu.domain.tests.NewQuestionGroup;
import net.sourceforge.fenixedu.domain.tests.NewQuestionType;

public class CreateAtomicQuestion extends FenixService {
    public NewQuestion run(NewQuestionGroup parentQuestionGroup, NewQuestionType questionType) throws FenixServiceException {
	return questionType.newInstance(parentQuestionGroup);
    }
}
