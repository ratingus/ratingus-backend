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


    private void addLessonToSchedule(UserDto userDto, ScheduleDto scheduleDto, SubjectDto subjectDto){
        Schedule schedule = scheduleRepository.findScheduleById(scheduleDto.getId());

        User teacher = userRepository.findByFullName(userDto.getName(), userDto.getSurname(), userDto.getPatronymic());
        Subject subject = subjectRepository.findSubjectByName(subjectDto.getName());

        schedule.setLessonNumber(scheduleDto.getLessonNumber());
        schedule.setTeacher(teacher);
        schedule.setSubject(subject);
        schedule.setDayOfWeek(scheduleDto.getDayOfWeek());
        schedule.setTimetable(scheduleDto.getTimetable());

        scheduleRepository.save(schedule);
    }
}
