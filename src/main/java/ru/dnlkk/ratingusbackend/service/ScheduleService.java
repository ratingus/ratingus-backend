package ru.dnlkk.ratingusbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.api.dtos.ScheduleDto;
import ru.dnlkk.ratingusbackend.api.dtos.SubjectDto;
import ru.dnlkk.ratingusbackend.api.dtos.UserDto;
import ru.dnlkk.ratingusbackend.model.Schedule;
import ru.dnlkk.ratingusbackend.model.Subject;
import ru.dnlkk.ratingusbackend.model.User;
import ru.dnlkk.ratingusbackend.repository.ScheduleRepository;
import ru.dnlkk.ratingusbackend.repository.SubjectRepository;
import ru.dnlkk.ratingusbackend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private UserRepository userRepository;
    private SubjectRepository subjectRepository;
    private ScheduleRepository scheduleRepository;


    public void addLessonToSchedule(UserDto userDto, SubjectDto subjectDto, ScheduleDto scheduleDto){
        Schedule schedule = new Schedule();

        schedule.setSubject(connectTeacherSubject(userDto, subjectDto));
        schedule.setDayOfWeek(scheduleDto.getDayOfWeek());
        schedule.setLessonNumber(scheduleDto.getLessonNumber());
        schedule.setTimetable(scheduleDto.getTimetable());

    }

    public Subject connectTeacherSubject(UserDto userDto, SubjectDto subjectDto){
        User user = userRepository.findUserById(userDto.getId());
        Subject subject = subjectRepository.findSubjectById(subjectDto.getId());

        subject.getTeachers().add(user);
        return subject;
    }

}
