/*
 * Created on 13/Nov/2003
 *
 */
package net.sourceforge.fenixedu.domain.teacher;

import net.sourceforge.fenixedu.dataTransferObject.teacher.InfoProfessionalCareer;
import net.sourceforge.fenixedu.domain.Teacher;
import net.sourceforge.fenixedu.domain.exceptions.DomainException;

/**
 * @author Leonor Almeida
 * @author Sergio Montelobo
 * 
 */
public class ProfessionalCareer extends ProfessionalCareer_Base {

    public ProfessionalCareer() {
	super();
    }

    public ProfessionalCareer(Teacher teacher, Integer beginYear, Integer endYear, String function, String entity) {
	super();
	if (teacher == null)
	    throw new DomainException("The teacher should not be null!");
	if (endYear < beginYear) {
	    throw new DomainException("error.professionalcareer.endYearBeforeStart");
	}
	setTeacher(teacher);
	setBeginYear(beginYear);
	setEndYear(endYear);
	setFunction(function);
	setEntity(entity);
    }

    public ProfessionalCareer(Teacher teacher, InfoProfessionalCareer infoProfessionalCareer) {
	if (teacher == null)
	    throw new DomainException("The teacher should not be null!");
	setTeacher(teacher);
	setBasicProperties(infoProfessionalCareer);
    }

    public void edit(InfoProfessionalCareer infoProfessionalCareer) {
	setBasicProperties(infoProfessionalCareer);
    }

    private void setBasicProperties(InfoProfessionalCareer infoProfessionalCareer) {
	this.setBeginYear(infoProfessionalCareer.getBeginYear());
	this.setEndYear(infoProfessionalCareer.getEndYear());
	this.setEntity(infoProfessionalCareer.getEntity());
	this.setFunction(infoProfessionalCareer.getFunction());

    }
}
