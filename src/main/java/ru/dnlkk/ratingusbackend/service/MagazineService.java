package ru.dnlkk.ratingusbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.api.dtos.*;
import ru.dnlkk.ratingusbackend.api.dtos.magazine.GradeDto;
import ru.dnlkk.ratingusbackend.api.dtos.magazine.LessonCreateDto;
import ru.dnlkk.ratingusbackend.api.dtos.magazine.LessonUpdateDto;
import ru.dnlkk.ratingusbackend.api.dtos.magazine.MagazineLessonDto;
import ru.dnlkk.ratingusbackend.model.*;
import ru.dnlkk.ratingusbackend.model.enums.Attendance;
import ru.dnlkk.ratingusbackend.repository.*;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MagazineService {
    private final ScheduleRepository scheduleRepository;
    private final SchoolRepository schoolRepository;
    private final LessonRepository lessonRepository;
    private final UserRoleRepository userRoleRepository;
    private final StudentLessonRepository studentLessonRepository;

    public MagazineDto getMagazineWithUsers(UserDetailsImpl user, Integer classId, Integer teacherSubjectId) {
        School school = schoolRepository.findSchoolById(user.getUserRole().getSchool().getId());

        List<UserRole> userRoles = userRoleRepository.findUserRolesBySchoolIdAndRoleClassId(school.getId(), classId);

        List<Schedule> schedules = scheduleRepository.findByScheduleForClassIdAndSubjectId(classId, teacherSubjectId);

        List<Integer> daysOfWeek = schedules.stream().map(Schedule::getDayOfWeek).distinct().toList();
        List<LocalDate> dates = generateDates(daysOfWeek);

        List<Timestamp> timestamps = new ArrayList<>();
        for (LocalDate date : dates) {
            timestamps.add(Timestamp.valueOf(date.atStartOfDay()));
        }
        List<Lesson> lessons = lessonRepository.findByDateIn(timestamps);

        MagazineDto magazineDto = new MagazineDto();
        magazineDto.setStudents(userRoles.stream().map(userRole -> {
            var studentDto = new StudentDto();
            studentDto.setId(userRole.getUser().getId());
            studentDto.setName(userRole.getName());
            studentDto.setSurname(userRole.getSurname());
            studentDto.setPatronymic(userRole.getPatronymic());

            return studentDto;
        }).toList());

        List<MonthLessonDayDto> monthDays = dates.stream()
                .collect(Collectors.groupingBy(LocalDate::getMonth,
                        Collectors.mapping(LocalDate::getDayOfMonth, Collectors.toList())))
                .entrySet().stream()
                .sorted(Comparator.comparingInt(e -> e.getKey().getValue()))
                .map(entry -> {
                    var dto = new MonthLessonDayDto();
                    var month = entry.getKey();
                    dto.setMonth(month.getValue());
                    var monthMarks = new ArrayList<MarkDto>();

                    List<LessonDayDto> lessonDays = new ArrayList<>();
                    Set<Integer> days = new HashSet<>(entry.getValue());
                    List<Integer> daysList = new ArrayList<>(days).stream().sorted(Comparator.naturalOrder()).toList();
                    for (int index = 0; index < days.size(); index++) {
                        int day  = daysList.get(index);
                        List<Lesson> lessonsForDay = lessons.stream().filter(
                                l -> l.getDate().toLocalDateTime().getDayOfMonth() == day && l.getDate().toLocalDateTime().getMonth().getValue() == month.getValue()
                        ).sorted((a, b) -> {
                            var dateDiff = a.getDate().compareTo(b.getDate());
                            if (dateDiff == 0) {
                                return b.getSchedule().getLessonNumber() - a.getSchedule().getLessonNumber();
                            }
                            return dateDiff;
                        }).toList();

                        List<Schedule> schedulesForDay = schedules.stream().filter(
                                l -> l.getDayOfWeek() == LocalDate.of(LocalDate.now().getYear(), month.getValue(), day).getDayOfWeek().getValue() - 1
                        ).sorted(Comparator.comparing(Schedule::getLessonNumber)).toList();

                        if (index == 0) {
                            magazineDto.getStudents().forEach(studentDto -> studentDto.getMarks().add(new ArrayList<>()));
                        }

                        lessonsForDay.forEach(lessonForDay -> {
                            List<StudentLesson> lessonStudents = lessonForDay.getStudentLessons();
                            magazineDto.getStudents().forEach(studentDto -> {
                                var marksDto = new MarkDto();
                                monthMarks.add(marksDto);
                                StudentLesson lessonStudent = lessonStudents.stream().filter(ls -> ls.getStudent().getId() == studentDto.getId()).findFirst().orElse(null);
                                if (lessonStudent != null) {
                                    marksDto.setMark(lessonStudent.getMark());
                                    marksDto.setAttendance(lessonStudent.getAttendance());
                                    studentDto.getMarks().getLast().add(marksDto);
                                }
                            });
                        });


                        List<MagazineLessonDto> lessonsDays = schedulesForDay.stream().map(schedule -> {
                            List<Lesson> lessonForDay = schedule.getLessons().stream().filter(l ->  l.getDate().toLocalDateTime().getDayOfMonth() == day && l.getDate().toLocalDateTime().getMonth().getValue() == month.getValue()).toList();
                            return new MagazineLessonDto(
                                    schedule.getId(),
                                    schedule.getLessonNumber(),
                                    !lessonForDay.isEmpty() ? lessonForDay.getFirst().getId() : null
                            );
                        }).toList();
                        lessonDays.add(new LessonDayDto(day, lessonsDays));
                    }

                    dto.setLessonDays(lessonDays);
                    return dto;

                })
                .toList();

        magazineDto.setMonthLessonDays(monthDays);
        return magazineDto;
    }

    public List<LocalDate> generateDates(List<Integer> daysOfWeek) {
        List<LocalDate> dates = new ArrayList<>();

        LocalDate start = LocalDate.of(LocalDate.now().getYear(), 1, 1);
        LocalDate end = LocalDate.of(LocalDate.now().getYear(), 5, 31);

        for (int dayOfWeek : daysOfWeek) {
            LocalDate next = start.with(TemporalAdjusters.nextOrSame(DayOfWeek.of(dayOfWeek + 1)));

            while (!next.isAfter(end)) {
                dates.add(next);
                next = next.with(TemporalAdjusters.next(DayOfWeek.of(dayOfWeek + 1)));
            }
        }

        return dates;
    }

    public void createUserGrade(GradeDto gradeDto) {
        StudentLesson studentLesson;
        if (gradeDto.getLessonStudentId() != null) {
            studentLesson = studentLessonRepository.findById(gradeDto.getLessonStudentId()).orElseThrow();
            placeMark(studentLesson, gradeDto.getMark(), gradeDto.getAttendance());
        } else if (gradeDto.getLessonId() != null) {
            Lesson lesson = lessonRepository.findById(gradeDto.getLessonId()).orElseThrow();
            studentLesson = new StudentLesson();
            studentLesson.setLesson(lesson);
            studentLesson.setStudent(userRoleRepository.findById(gradeDto.getStudentId()).orElseThrow());
            placeMark(studentLesson, gradeDto.getMark(), gradeDto.getAttendance());
        } else {
            Schedule schedule = scheduleRepository.findById(gradeDto.getScheduleId()).orElseThrow();
            Lesson lesson = new Lesson();
            lesson.setDate(gradeDto.getDate());
            lesson.setSchedule(schedule);
            lesson.setFinished(true);
            lessonRepository.save(lesson);

            studentLesson = new StudentLesson();
            studentLesson.setLesson(lesson);
            studentLesson.setStudent(userRoleRepository.findById(gradeDto.getStudentId()).orElseThrow());
            placeMark(studentLesson, gradeDto.getMark(), gradeDto.getAttendance());
        }
    }

    public void placeMark(StudentLesson studentLesson, String mark, Attendance attendance) {
        studentLesson.setMark(mark);
        studentLesson.setAttendance(attendance);
        studentLessonRepository.save(studentLesson);
    }

    public void createLesson(LessonCreateDto lessonCreateDto) {
        Schedule schedule = scheduleRepository.findById(lessonCreateDto.getScheduleId()).orElseThrow();

        Lesson lesson = new Lesson();
        lesson.setDate(lessonCreateDto.getDate());
        lesson.setFinished(lessonCreateDto.isFinished());
        lesson.setSchedule(schedule);
        lesson.setTheme(lessonCreateDto.getTheme());
        lesson.setHomework(lessonCreateDto.getHomework());

        lessonRepository.save(lesson);
    }

    public void updateLesson(LessonUpdateDto lessonUpdateDto) {
        Lesson lesson = lessonRepository.findById(lessonUpdateDto.getLessonId()).orElseThrow();
        if (lessonUpdateDto.getDate() != null) lesson.setDate(lessonUpdateDto.getDate());
        if (lessonUpdateDto.getFinished() != null) lesson.setFinished(lessonUpdateDto.getFinished());
        if (lessonUpdateDto.getTheme() != null) lesson.setTheme(lessonUpdateDto.getTheme());
        if (lessonUpdateDto.getHomework() != null) lesson.setHomework(lessonUpdateDto.getHomework());

        lessonRepository.save(lesson);
    }

    public List<LessonDto> getLessons(Integer classId, Integer teacherSubjectId) {
        List<Lesson> lessons = lessonRepository.findByScheduleScheduleForClassIdAndScheduleSubjectId(classId, teacherSubjectId).stream()
                .sorted((a, b) -> {
                    var dateDiff = a.getDate().compareTo(b.getDate());
                    if (dateDiff == 0) {
                        return a.getSchedule().getLessonNumber() - b.getSchedule().getLessonNumber();
                    }
                    return dateDiff;
                }).toList();

        return lessons.stream().map(lesson -> {
            var lessonDto = new LessonDto();
            lessonDto.setId(lesson.getId());
            lessonDto.setDate(lesson.getDate());
            lessonDto.setLessonNumber(lesson.getSchedule().getLessonNumber());
            lessonDto.setFinished(lesson.getFinished());
            lessonDto.setHomework(lesson.getHomework());
            lessonDto.setTheme(lesson.getTheme());
            return lessonDto;
        }).toList();
    }
}
