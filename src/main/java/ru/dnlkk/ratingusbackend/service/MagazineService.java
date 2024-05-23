package ru.dnlkk.ratingusbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.api.dtos.*;
import ru.dnlkk.ratingusbackend.model.*;
import ru.dnlkk.ratingusbackend.repository.*;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MagazineService {
    private final ScheduleRepository scheduleRepository;
    private final SchoolRepository schoolRepository;
    private final LessonRepository lessonRepository;
    private final UserRoleRepository userRoleRepository;

    public MagazineDto getMagazineWithUsers(UserDetailsImpl user, Integer classId, Integer teacherSubjectId) {
        School school = schoolRepository.findSchoolById(user.getUserRole().getSchool().getId());

        List<UserRole> userRoles = userRoleRepository.findUserRolesBySchoolIdAndRoleClassId(school.getId(), classId);

        List<Schedule> schedules = scheduleRepository.findByScheduleForClassIdAndSubjectId(classId, teacherSubjectId);

        List<Integer> daysOfWeek =schedules.stream().map(Schedule::getDayOfWeek).distinct().toList();
        List<LocalDate> dates = generateDates(daysOfWeek);

        List<Timestamp> timestamps = new ArrayList<>();
        for (LocalDate date : dates) {
            timestamps.add(Timestamp.valueOf(date.atStartOfDay()));
            System.out.println(date.atStartOfDay());
        }
        List<Lesson> lessons = lessonRepository.findByDateIn(timestamps);
        System.out.println(lessons);

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
                .collect(Collectors.groupingBy(date -> date.getMonth().name(),
                        Collectors.mapping(LocalDate::getDayOfMonth, Collectors.toList())))
                .entrySet().stream()
                .map(entry -> {
                    var dto = new MonthLessonDayDto();
                    dto.setMonth(entry.getKey());
                    var monthMarks = new ArrayList<MarkDto>();

                    List<LessonDayDto> lessonDays = new ArrayList<>();
                    Set<Integer> days = new HashSet<>(entry.getValue());
                    List<Integer> daysList = new ArrayList<>(days);
                    for (int index = 0; index < days.size(); index++) {
                        int day  = daysList.get(index);
                        List<Lesson> lessonsForDay = lessons.stream().filter(
                                l -> l.getDate().toLocalDateTime().getDayOfMonth() == day && l.getDate().toLocalDateTime().getMonth().name().equals(entry.getKey())
                        ).toList();

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

                        List<Integer> lessonsDays = lessonsForDay.stream().map(Lesson::getId).toList();
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
}
