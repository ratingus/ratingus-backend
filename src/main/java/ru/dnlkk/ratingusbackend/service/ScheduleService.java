package ru.dnlkk.ratingusbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.api.dtos.TeacherDTO;
import ru.dnlkk.ratingusbackend.api.dtos.schedule.*;
import ru.dnlkk.ratingusbackend.model.*;
import ru.dnlkk.ratingusbackend.model.Class;
import ru.dnlkk.ratingusbackend.repository.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final SubjectRepository subjectRepository;
    private final UserRoleRepository teacherRepository;
    private final TeacherSubjectRepository teacherSubjectRepository;
    private final TimetableRepository timetableRepository;

    public void mergeSubjectWithTeacher(UserRole userRole, MergeSubjectWithTeacherRequestDTO mergeSubjectWithTeacherRequestDTO) {
        UserRole teacher = teacherRepository.findUserRoleByUserIdAndSchool(mergeSubjectWithTeacherRequestDTO.getTeacherId(), userRole.getSchool());
        Subject subject = subjectRepository.findById(mergeSubjectWithTeacherRequestDTO.getSubjectId()).orElseThrow();

        TeacherSubject teacherSubject = new TeacherSubject();
        teacherSubject.setSubject(subject);
        teacherSubject.setTeacher(teacher);
        teacherSubjectRepository.save(teacherSubject);
    }

    public List<ScheduleTeachersDTO> getTeachersWithSubject(int classId) {
        Set<TeacherSubject> teacherSubjects = scheduleRepository.findTeacherSubjectsByClassId(classId);
        Map<Integer, ScheduleTeachersDTO> scheduleTeachersDTOS = new HashMap<>();

        for (TeacherSubject teacherSubject : teacherSubjects) {
            var scheduleTeachersDTO = scheduleTeachersDTOS.get(teacherSubject.getSubject().getId());
            if (scheduleTeachersDTO == null) {
                scheduleTeachersDTO = new ScheduleTeachersDTO();
                scheduleTeachersDTO.setId(teacherSubject.getSubject().getId());
                scheduleTeachersDTO.setName(teacherSubject.getSubject().getName());
                scheduleTeachersDTO.setTeachers(new ArrayList<>());
                scheduleTeachersDTOS.put(teacherSubject.getSubject().getId(), scheduleTeachersDTO);
            }
            var teacherDTO = new TeacherDTO();
            teacherDTO.setId(teacherSubject.getTeacher().getId());
            teacherDTO.setName(teacherSubject.getTeacher().getName());
            teacherDTO.setSurname(teacherSubject.getTeacher().getSurname());
            teacherDTO.setPatronymic(teacherSubject.getTeacher().getPatronymic());
            scheduleTeachersDTO.getTeachers().add(teacherDTO);
        }

        return new ArrayList<>(scheduleTeachersDTOS.values());
    }

    public ScheduleDTO getSchedule(int classId) {
        Class clazz = new Class();
        clazz.setId(classId);
        List<Schedule> schedules = scheduleRepository.findByScheduleForClass(clazz);
        Map<Integer, List<Schedule>> groupedByDayOfWeek = schedules.stream()
                .collect(Collectors.groupingBy(Schedule::getDayOfWeek));

        List<ScheduleDayDTO> dayLessons = new ArrayList<>();

        for (Map.Entry<Integer, List<Schedule>> entry : groupedByDayOfWeek.entrySet()) {
            List<Schedule> schedulesForDay = entry.getValue();

            // Sort schedules for the day by lesson number
            schedulesForDay.sort(Comparator.comparingInt(Schedule::getLessonNumber));

            List<ScheduleItemDTO> lessons = new ArrayList<>();
            int maxLessonNumber = schedulesForDay.getLast().getLessonNumber();

            for (int i = 1; i <= maxLessonNumber; i++) {
                int finalI = i;
                Schedule schedule = schedulesForDay.stream()
                        .filter(s -> s.getLessonNumber() == finalI)
                        .findFirst()
                        .orElse(null);

                if (schedule != null) {
                    TeacherDTO teacherDTO = new TeacherDTO();
                    teacherDTO.setId(schedule.getSubject().getTeacher().getId());
                    teacherDTO.setName(schedule.getSubject().getTeacher().getName());
                    teacherDTO.setSurname(schedule.getSubject().getTeacher().getSurname());
                    teacherDTO.setPatronymic(schedule.getSubject().getTeacher().getPatronymic());

                    lessons.add(new ScheduleItemDTO(
                            schedule.getLessonNumber(),
                            schedule.getSubject().getSubject().getId(),
                            teacherDTO,
                            schedule.getSubject().getSubject().getName(),
                            schedule.getTimetable().getStartTime(),
                            schedule.getTimetable().getEndTime()
                    ));
                } else {
                    lessons.add(new ScheduleItemDTO(
                            i,
                            -1,
                            null,
                            "Окно",
                            null,
                            null
                    ));
                }
            }

            dayLessons.add(new ScheduleDayDTO(entry.getKey(), lessons));
        }

        return new ScheduleDTO(dayLessons);
    }

    public void changeSubjectInSchedule(UserRole userRole, ScheduleChangeDTO changeSubjectInScheduleRequestDTO, int classId) {
        Class clazz = new Class();
        clazz.setId(classId);
        Schedule schedulesFrom = scheduleRepository.findByScheduleForClassAndDayOfWeekAndTimetableLessonNumber(
                clazz,
                changeSubjectInScheduleRequestDTO.getFrom().getDayOfWeek(),
                changeSubjectInScheduleRequestDTO.getFrom().getLessonNumber()
        );
        Schedule schedulesTo = scheduleRepository.findByScheduleForClassAndDayOfWeekAndTimetableLessonNumber(
                clazz,
                changeSubjectInScheduleRequestDTO.getTo().getDayOfWeek(),
                changeSubjectInScheduleRequestDTO.getTo().getLessonNumber()
        );
        List<Timetable> timetables = timetableRepository.findTimetablesBySchoolId(userRole.getSchool().getId());

        if (schedulesFrom != null) {
            schedulesFrom.setDayOfWeek(changeSubjectInScheduleRequestDTO.getTo().getDayOfWeek());
            schedulesTo.setTimetable(timetables.get(changeSubjectInScheduleRequestDTO.getTo().getLessonNumber() - 1));
            scheduleRepository.save(schedulesFrom);
        }
        if (schedulesTo != null) {
            schedulesTo.setDayOfWeek(changeSubjectInScheduleRequestDTO.getFrom().getDayOfWeek());
            schedulesTo.setTimetable(timetables.get(changeSubjectInScheduleRequestDTO.getFrom().getLessonNumber() - 1));
            scheduleRepository.save(schedulesTo);
        }
    }

    public void removeSubjectFromSchedule(UserRole userRole, ScheduleActionDTO removeSubjectFromScheduleRequestDTO, int classId) {
        Class clazz = new Class();
        clazz.setId(classId);
        Schedule schedule = scheduleRepository.findByScheduleForClassAndDayOfWeekAndTimetableLessonNumber(
                clazz,
                removeSubjectFromScheduleRequestDTO.getDayOfWeek(),
                removeSubjectFromScheduleRequestDTO.getLessonNumber()
        );

        scheduleRepository.delete(schedule);
    }

    public void addSubjectInSchedule(UserRole userRole, ScheduleActionDTO addSubjectInScheduleRequestDTO, int classId) {
        Class clazz = new Class();
        clazz.setId(classId);
        List<Schedule> schedules = scheduleRepository.findByScheduleForClassAndDayOfWeek(clazz, addSubjectInScheduleRequestDTO.getDayOfWeek());

        schedules.sort(Comparator.comparingInt(Schedule::getLessonNumber));

        int lessonNumber = addSubjectInScheduleRequestDTO.getLessonNumber();

        TeacherSubject teacherSubject = teacherSubjectRepository.findById(addSubjectInScheduleRequestDTO.getStudyWithTeacherId()).orElseThrow();
        List<Timetable> timetables = timetableRepository.findTimetablesBySchoolId(userRole.getSchool().getId());

        Schedule newSchedule = new Schedule();
        newSchedule.setSubject(teacherSubject);
        newSchedule.setScheduleForClass(clazz);
        newSchedule.setDayOfWeek(addSubjectInScheduleRequestDTO.getDayOfWeek());

        for (int i = 0; i < schedules.size(); i++) {
            Schedule schedule = schedules.get(i);

            if (schedule.getLessonNumber() == lessonNumber) {
                int diff = -1;
                boolean safeFlag = true;

                for (int j = i; j < schedules.size(); j++) {
                    if (schedules.size() - 1 == j) {
                        if (safeFlag) {
                            Schedule schedule1 = schedules.get(j);
                            Timetable timetable = timetables.get(schedule1.getLessonNumber());
                            schedule1.setTimetable(timetable);
                            scheduleRepository.save(schedule1);
                        }
                        break;
                    }
                    Schedule schedule1 = schedules.get(j);
                    Schedule schedule2 = schedules.get(j + 1);
                    if (safeFlag) {
                        if (schedule1.getLessonNumber() - schedule2.getLessonNumber() != diff) {
                            safeFlag = false;
                        }
                        Timetable timetable = timetables.get(schedule1.getLessonNumber());
                        schedule1.setTimetable(timetable);
                        scheduleRepository.save(schedule1);
                    } else {
                        break;
                    }
                }
                break;
            }
        }

        Timetable newSubjectTimetable = timetables.get(lessonNumber - 1);
        newSchedule.setTimetable(newSubjectTimetable);
        scheduleRepository.save(newSchedule);
    }
}
