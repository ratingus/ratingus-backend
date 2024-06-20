package ru.dnlkk.ratingusbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.api.dtos.NoteDto;
import ru.dnlkk.ratingusbackend.api.dtos.TeacherDto;
import ru.dnlkk.ratingusbackend.api.dtos.diary.LessonDto;
import ru.dnlkk.ratingusbackend.api.dtos.diary.DayLessonsDto;
import ru.dnlkk.ratingusbackend.model.*;
import ru.dnlkk.ratingusbackend.repository.LessonRepository;
import ru.dnlkk.ratingusbackend.repository.ScheduleRepository;
import ru.dnlkk.ratingusbackend.repository.StudentLessonRepository;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final StudentLessonRepository diaryRepository;
    private final LessonRepository lessonRepository;
    private final ScheduleRepository scheduleRepository;


    public List<DayLessonsDto> getWeekLessons(UserRole userRole, Integer week) {
        Timestamp startDate = getAcademicDateByWeek(week);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_WEEK, 6);
        Timestamp endDate = new Timestamp(calendar.getTimeInMillis());

        var userClass = userRole.getRoleClass();

        List<StudentLesson> studentLessons = diaryRepository.findByStudentAndLessonDateBetween(userRole, startDate, endDate);
        List<Lesson> lessons = lessonRepository.findByScheduleScheduleForClassIdAndDateBetween(userClass.getId(), startDate, endDate);
        List<Schedule> schedules = scheduleRepository.findByScheduleForClassId(userClass.getId());

        List<DayLessonsDto> dayLessonsDtoList = new ArrayList<>();

        for (Schedule schedule : schedules) {
            LessonDto lessonDto = new LessonDto();
            lessonDto.setScheduleId(schedule.getId());
            lessonDto.setTimetableNumber(schedule.getLessonNumber());
            lessonDto.setTeacherSubjectId(schedule.getSubject().getId());
            lessonDto.setSubject(schedule.getSubject().getSubject().getName());
            lessonDto.setTeacher(new TeacherDto(
                    schedule.getSubject().getTeacher().getId(),
                    schedule.getSubject().getTeacher().getName(),
                    schedule.getSubject().getTeacher().getSurname(),
                    schedule.getSubject().getTeacher().getPatronymic()
            ));
            var timetableStartTime = startDate.toLocalDateTime()
                    .plusHours(
                                    schedule.getTimetable().getStartTime().toLocalDateTime().getHour() -
                                            startDate.toLocalDateTime().getHour())
                    .plusMinutes(
                            schedule.getTimetable().getStartTime().toLocalDateTime().getMinute() -
                                    startDate.toLocalDateTime().getMinute())
                    .plusDays(
                            schedule.getDayOfWeek() -
                                    startDate.toLocalDateTime().getDayOfWeek().getValue());
            var timetableEndTime = timetableStartTime
                    .plusHours(
                            schedule.getTimetable().getEndTime().toLocalDateTime().getHour() -
                                    timetableStartTime.getHour())
                            .plusMinutes(
                                    schedule.getTimetable().getEndTime().toLocalDateTime().getMinute() -
                                            timetableStartTime.getMinute());
            lessonDto.setStartTime(new Timestamp(timetableStartTime.toInstant(ZoneOffset.UTC).toEpochMilli()));
            lessonDto.setEndTime(new Timestamp(timetableEndTime.toInstant(ZoneOffset.UTC).toEpochMilli()));

            Lesson matchingLesson = lessons.stream()
                    .filter(lesson -> lesson.getSchedule().equals(schedule))
                    .findFirst()
                    .orElse(null);

            if (matchingLesson != null) {
                lessonDto.setLessonId(matchingLesson.getId());
                lessonDto.setHomework(matchingLesson.getHomework());
            }

            StudentLesson matchingStudentLesson = studentLessons.stream()
                    .filter(studentLesson -> studentLesson.getLesson().equals(matchingLesson))
                    .findFirst()
                    .orElse(null);

            if (matchingStudentLesson != null) {
                lessonDto.setStudentLessonId(matchingStudentLesson.getId());
                lessonDto.setMark(matchingStudentLesson.getMark());
                lessonDto.setAttendance(matchingStudentLesson.getAttendance());
                lessonDto.setNote(matchingStudentLesson.getNote());
            }

            DayLessonsDto dayLessonsDto = dayLessonsDtoList.stream()
                    .filter(dayLessons -> dayLessons.getDayOfWeek() == schedule.getDayOfWeek())
                    .findFirst()
                    .orElse(null);

            if (dayLessonsDto == null) {
                dayLessonsDto = new DayLessonsDto();
                dayLessonsDto.setDayOfWeek(schedule.getDayOfWeek());
                dayLessonsDto.setDateTime(new Timestamp(startDate.toLocalDateTime().plusDays(schedule.getDayOfWeek() - 1)
                        .toInstant(ZoneOffset.UTC).toEpochMilli()));
                dayLessonsDto.setStudies(new ArrayList<>());
                dayLessonsDtoList.add(dayLessonsDto);
            }

            dayLessonsDto.getStudies().add(lessonDto);
        }

        dayLessonsDtoList.sort(Comparator.comparing(DayLessonsDto::getDayOfWeek));
        dayLessonsDtoList.forEach(dayLessonsDto -> dayLessonsDto.getStudies().sort(Comparator.comparing(LessonDto::getTimetableNumber)));
        return dayLessonsDtoList;
    }

    public static Timestamp getAcademicDateByWeek(int weekOfYear) {
        Timestamp date = getAcademicDate(new Timestamp(System.currentTimeMillis()));
        int difference = (weekOfYear - 1) * 7;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
        calendar.add(Calendar.DAY_OF_YEAR, difference);
        calendar.setWeekDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.WEEK_OF_YEAR), Calendar.MONDAY);
        return new Timestamp(calendar.getTimeInMillis());
    }

    public static int getAcademicWeekOfYear(Timestamp date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 3, 0, 0);
        int startOfYearWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTimeInMillis(getAcademicDate(date).getTime());
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 3, 0, 0);
        int academicStartWeek = calendar.get(Calendar.WEEK_OF_YEAR);

        int weekOfYear = startOfYearWeek - academicStartWeek;
        if (academicStartWeek > startOfYearWeek) {
            weekOfYear = 52 - academicStartWeek + startOfYearWeek;
        }
        return weekOfYear;
    }

    public static Timestamp getAcademicDate(Timestamp date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 3, 0, 0);
        calendar.set(calendar.get(Calendar.YEAR), Calendar.SEPTEMBER, 1);
        calendar.setWeekDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.WEEK_OF_YEAR), Calendar.MONDAY);

        if (calendar.getTimeInMillis() > date.getTime()) {
            calendar.add(Calendar.YEAR, -1);
            calendar.setWeekDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.WEEK_OF_YEAR), Calendar.MONDAY);
        }

        return new Timestamp(calendar.getTimeInMillis());
    }

    public void createNote(UserRole userRole, NoteDto noteDto) {
        StudentLesson studentLesson = noteDto.getLessonStudentId() == null ? null : diaryRepository.findById(noteDto.getLessonStudentId()).orElse(null);
        if (studentLesson == null) {
            studentLesson = new StudentLesson();
            studentLesson.setStudent(userRole);

            Lesson lesson = noteDto.getLessonId() == null ? null : lessonRepository.findById(noteDto.getLessonId()).orElse(null);
            if (lesson == null) {
                lesson = new Lesson();
                lesson.setDate(noteDto.getDate());

                Schedule schedule = noteDto.getScheduleId() == null ? null : scheduleRepository.findById(noteDto.getScheduleId()).orElse(null);
                lesson.setSchedule(schedule);
                lessonRepository.save(lesson);
            }
            studentLesson.setLesson(lesson);
        }
        studentLesson.setNote(noteDto.getText());
        diaryRepository.save(studentLesson);
    }
}
