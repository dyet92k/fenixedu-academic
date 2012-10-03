package net.sourceforge.fenixedu.domain.teacher;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import net.sourceforge.fenixedu.domain.ExecutionCourse;
import net.sourceforge.fenixedu.domain.ExecutionSemester;
import net.sourceforge.fenixedu.domain.Lesson;
import net.sourceforge.fenixedu.domain.Professorship;
import net.sourceforge.fenixedu.domain.RootDomainObject;
import net.sourceforge.fenixedu.domain.Shift;
import net.sourceforge.fenixedu.domain.SupportLesson;
import net.sourceforge.fenixedu.domain.Teacher;
import net.sourceforge.fenixedu.domain.exceptions.DomainException;
import net.sourceforge.fenixedu.util.BundleUtil;
import net.sourceforge.fenixedu.util.CalendarUtil;
import net.sourceforge.fenixedu.util.WeekDay;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.joda.time.DateTime;

import pt.ist.fenixWebFramework.services.Service;

public class TeacherService extends TeacherService_Base {

    public TeacherService(Teacher teacher, ExecutionSemester executionSemester) {
	super();
	if (teacher == null || executionSemester == null) {
	    throw new DomainException("arguments can't be null");
	}
	TeacherService teacherService = teacher.getTeacherServiceByExecutionPeriod(executionSemester);
	if (teacherService != null) {
	    throw new DomainException("error.teacherService.already.exists.one.teacherService.in.executionPeriod");
	}
	setRootDomainObject(RootDomainObject.getInstance());
	setTeacher(teacher);
	setExecutionPeriod(executionSemester);
    }

    public void delete() {
	if (getServiceItems().isEmpty()) {
	    removeTeacher();
	    removeExecutionPeriod();
	    removeRootDomainObject();
	    deleteDomainObject();
	} else {
	    throw new DomainException("There are service items associated to this Teacher Service");
	}
    }

    @Service
    public static TeacherService getTeacherService(Teacher teacher, ExecutionSemester executionPeriod) {
	TeacherService teacherService = teacher.getTeacherServiceByExecutionPeriod(executionPeriod);
	if (teacherService == null) {
	    teacherService = new TeacherService(teacher, executionPeriod);
	}
	return teacherService;
    }

    public DegreeTeachingService getDegreeTeachingServiceByShiftAndProfessorship(final Shift shift,
	    final Professorship professorship) {
	return (DegreeTeachingService) CollectionUtils.find(getDegreeTeachingServices(), new Predicate() {
	    public boolean evaluate(Object arg0) {
		DegreeTeachingService degreeTeachingService = (DegreeTeachingService) arg0;
		return (degreeTeachingService.getShift() == shift) && (degreeTeachingService.getProfessorship() == professorship);
	    }
	});
    }

    public List<DegreeTeachingService> getDegreeTeachingServiceByProfessorship(final Professorship professorship) {
	return (List<DegreeTeachingService>) CollectionUtils.select(getDegreeTeachingServices(), new Predicate() {
	    public boolean evaluate(Object arg0) {
		DegreeTeachingService degreeTeachingService = (DegreeTeachingService) arg0;
		return degreeTeachingService.getProfessorship() == professorship;
	    }
	});
    }

    public TeacherMasterDegreeService getMasterDegreeServiceByProfessorship(Professorship professorship) {
	for (TeacherMasterDegreeService masterDegreeService : getMasterDegreeServices()) {
	    if (masterDegreeService.getProfessorship() == professorship) {
		return masterDegreeService;
	    }
	}
	return null;
    }

    public Double getCredits() throws ParseException {
	double credits = getMasterDegreeServiceCredits();
	credits += getTeachingDegreeCredits();
	credits += getOtherServiceCredits();
	credits += getTeacherAdviseServiceCredits();
	return round(credits);
    }

    public Double getTeachingDegreeHours() {
	double hours = 0;
	for (DegreeTeachingService degreeTeachingService : getDegreeTeachingServices()) {
	    if ((!degreeTeachingService.getProfessorship().getExecutionCourse().isDissertation())
		    && (!degreeTeachingService.getProfessorship().getExecutionCourse().getProjectTutorialCourse())) {
		hours += degreeTeachingService.getHours();
	    }
	}
	return round(hours);
    }

    public Double getTeachingDegreeCredits() {
	double credits = 0;
	for (DegreeTeachingService degreeTeachingService : getDegreeTeachingServices()) {
	    if ((!degreeTeachingService.getProfessorship().getExecutionCourse().isDissertation())
		    && (!degreeTeachingService.getProfessorship().getExecutionCourse().getProjectTutorialCourse())) {
		credits += degreeTeachingService.calculateCredits();
	    }
	}
	return round(credits);
    }

    public Double getSupportLessonHours() {
	double hours = 0;
	for (SupportLesson supportLesson : getSupportLessons()) {
	    hours += supportLesson.hours();
	}
	return round(hours);
    }

    public Double getMasterDegreeServiceCredits() {
	double credits = 0;
	for (TeacherMasterDegreeService teacherMasterDegreeService : getMasterDegreeServices()) {
	    if (teacherMasterDegreeService.getCredits() != null) {
		credits += teacherMasterDegreeService.getCredits();
	    }
	}
	return round(credits);
    }

    public Double getTeacherAdviseServiceCredits() {
	double credits = 0;
	for (TeacherAdviseService teacherAdviseService : getTeacherAdviseServices()) {
	    credits = credits + ((teacherAdviseService.getPercentage().doubleValue() / 100) * (1.0 / 3));
	}
	return round(credits);
    }

    public Double getPastServiceCredits() {
	double credits = 0;
	TeacherPastService teacherPastService = getPastService();
	if (teacherPastService != null) {
	    credits = teacherPastService.getCredits();
	}
	return round(credits);
    }

    public Double getOtherServiceCredits() {
	double credits = 0;
	for (OtherService otherService : getOtherServices()) {
	    credits += otherService.getCredits();
	}
	return round(credits);
    }

    public Double getInstitutionWorkingHours() {
	double hours = 0;
	for (InstitutionWorkTime institutionWorkTime : getInstitutionWorkTimes()) {
	    hours += institutionWorkTime.getHours();
	}
	return round(hours);
    }

    public void verifyOverlappingWithInstitutionWorkingTime(Date startTime, Date endTime, WeekDay weekDay) {
	for (InstitutionWorkTime teacherInstitutionWorkTime : getInstitutionWorkTimes()) {
	    if (teacherInstitutionWorkTime.getWeekDay().equals(weekDay)) {
		Date startWorkTime = teacherInstitutionWorkTime.getStartTime();
		Date endWorkTime = teacherInstitutionWorkTime.getEndTime();
		if (CalendarUtil.intersectTimes(startTime, endTime, startWorkTime, endWorkTime)) {
		    throw new DomainException("message.overlapping.institution.working.period");
		}
	    }
	}
    }

    public void verifyOverlappingWithSupportLesson(Date startTime, Date endTime, WeekDay weekDay) {
	for (SupportLesson supportLesson : getSupportLessons()) {
	    if (WeekDay.getWeekDay(supportLesson.getWeekDay()).equals(weekDay)) {
		Date supportLessonStart = supportLesson.getStartTime();
		Date supportLessonEnd = supportLesson.getEndTime();
		if (CalendarUtil.intersectTimes(startTime, endTime, supportLessonStart, supportLessonEnd)) {
		    throw new DomainException("message.overlapping.support.lesson.period");
		}
	    }
	}
    }

    public void verifyOverlappingWithTeachingService(Date startTime, Date endTime, WeekDay weekDay) {
	for (DegreeTeachingService degreeTeachingService : getDegreeTeachingServices()) {
	    if (degreeTeachingService.getPercentage().doubleValue() == 100) {
		for (Lesson lesson : degreeTeachingService.getShift().getAssociatedLessons()) {
		    if (weekDay.equals(WeekDay.getWeekDay(lesson.getDiaSemana()))) {
			Date lessonStartTime = lesson.getBegin();
			Date lessonEndTime = lesson.getEnd();
			if (CalendarUtil.intersectTimes(startTime, endTime, lessonStartTime, lessonEndTime)) {
			    throw new DomainException("message.overlapping.lesson.period");
			}
		    }
		}
	    }
	}
    }

    public List<DegreeTeachingService> getDegreeTeachingServices() {
	return (List<DegreeTeachingService>) CollectionUtils.select(getServiceItems(), new Predicate() {
	    public boolean evaluate(Object arg0) {
		return arg0 instanceof DegreeTeachingService;
	    }
	});
    }

    public List<TeacherMasterDegreeService> getMasterDegreeServices() {
	return (List<TeacherMasterDegreeService>) CollectionUtils.select(getServiceItems(), new Predicate() {
	    public boolean evaluate(Object arg0) {
		return arg0 instanceof TeacherMasterDegreeService;
	    }
	});
    }

    public TeacherPastService getPastService() {
	return (TeacherPastService) CollectionUtils.find(getServiceItems(), new Predicate() {
	    public boolean evaluate(Object arg0) {
		return arg0 instanceof TeacherPastService;
	    }
	});
    }

    public List<OtherService> getOtherServices() {
	return (List<OtherService>) CollectionUtils.select(getServiceItems(), new Predicate() {
	    public boolean evaluate(Object arg0) {
		return arg0 instanceof OtherService;
	    }
	});
    }

    public List<InstitutionWorkTime> getInstitutionWorkTimes() {
	return (List<InstitutionWorkTime>) CollectionUtils.select(getServiceItems(), new Predicate() {
	    public boolean evaluate(Object arg0) {
		return arg0 instanceof InstitutionWorkTime;
	    }
	});
    }

    public ReductionService getReductionService() {
	return (ReductionService) CollectionUtils.find(getServiceItems(), new Predicate() {
	    public boolean evaluate(Object arg0) {
		return arg0 instanceof ReductionService;
	    }
	});
    }

    public TeacherServiceNotes getTeacherServiceNotes() {
	return (TeacherServiceNotes) CollectionUtils.find(getServiceItems(), new Predicate() {
	    public boolean evaluate(Object arg0) {
		return arg0 instanceof TeacherServiceNotes;
	    }
	});
    }

    public List<SupportLesson> getSupportLessons() {
	List<SupportLesson> supportLessons = new ArrayList<SupportLesson>();
	for (Professorship professorship : getTeacher().getProfessorships()) {
	    ExecutionCourse executionCourse = professorship.getExecutionCourse();
	    if (executionCourse.getExecutionPeriod() == getExecutionPeriod()) {
		if (!executionCourse.isMasterDegreeDFAOrDEAOnly()) {
		    supportLessons.addAll(professorship.getSupportLessons());
		}
	    }
	}
	return supportLessons;
    }

    public List<TeacherAdviseService> getTeacherAdviseServices() {
	return (List<TeacherAdviseService>) CollectionUtils.select(getServiceItems(), new Predicate() {
	    public boolean evaluate(Object arg0) {
		return arg0 instanceof TeacherAdviseService;
	    }
	});
    }

    public List<TeacherServiceComment> getTeacherServiceComments() {
	return (List<TeacherServiceComment>) CollectionUtils.select(getServiceItems(), new Predicate() {
	    public boolean evaluate(Object arg0) {
		return arg0 instanceof TeacherServiceComment;
	    }
	});
    }

    private Double round(double n) {
	return Math.round((n * 100.0)) / 100.0;
    }

    public BigDecimal getReductionServiceCredits() {
	return getReductionService() == null ? BigDecimal.ZERO : getReductionService().getCreditsReduction();
    }

    public SortedSet<TeacherServiceLog> getSortedLogs() {
	return new TreeSet<TeacherServiceLog>(getTeacherServiceLogSet());
    }

    @Service
    public void lockTeacherCredits() {
	setTeacherServiceLock(new DateTime());
	new TeacherServiceLog(this, BundleUtil.getStringFromResourceBundle("resources.TeacherCreditsSheetResources",
		"label.teacher.lockTeacherCredits", getExecutionPeriod().getQualifiedName()));
    }

    @Service
    public void unlockTeacherCredits() {
	setTeacherServiceLock(null);
	new TeacherServiceLog(this, BundleUtil.getStringFromResourceBundle("resources.TeacherCreditsSheetResources",
		"label.teacher.unlockTeacherCredits", getExecutionPeriod().getQualifiedName()));
    }

}
