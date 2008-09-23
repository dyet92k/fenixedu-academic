/*
 * Created on 28/Ago/2003
 *
 */
package net.sourceforge.fenixedu.applicationTier.Servico.student.onlineTests;

import java.util.Set;

import net.sourceforge.fenixedu.applicationTier.FenixService;
import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.FenixServiceException;
import net.sourceforge.fenixedu.domain.onlineTests.DistributedTest;
import net.sourceforge.fenixedu.domain.onlineTests.StudentTestLog;
import net.sourceforge.fenixedu.domain.onlineTests.StudentTestQuestion;
import net.sourceforge.fenixedu.domain.onlineTests.SubQuestion;
import net.sourceforge.fenixedu.domain.onlineTests.utils.ParseSubQuestion;
import net.sourceforge.fenixedu.domain.student.Registration;

/**
 * @author Susana Fernandes
 */
public class CleanSubQuestions extends FenixService {

    public void run(Registration registration, DistributedTest distributedTest, Integer exerciseCode, Integer itemCode,
	    String path) throws FenixServiceException {
	if (distributedTest == null) {
	    throw new FenixServiceException();
	}
	Set<StudentTestQuestion> studentTestQuestionList = StudentTestQuestion.findStudentTestQuestions(registration,
		distributedTest);
	for (StudentTestQuestion studentTestQuestion : studentTestQuestionList) {
	    if (studentTestQuestion.getQuestion().getIdInternal().equals(exerciseCode)) {
		ParseSubQuestion parse = new ParseSubQuestion();
		try {
		    parse.parseStudentTestQuestion(studentTestQuestion, path.replace('\\', '/'));
		} catch (Exception e) {
		    throw new FenixServiceException(e);
		}
		SubQuestion subQuestion = studentTestQuestion.getStudentSubQuestions().get(0);
		if (subQuestion.getItemId().equals(studentTestQuestion.getItemId())) {
		    // e a 1�
		    studentTestQuestion.setResponse(null);
		} else {
		    studentTestQuestion.delete();
		}
	    }
	}
	new StudentTestLog(distributedTest, registration, "Apagou resposta da pergunta/al�nea: " + itemCode);
	return;
    }

}