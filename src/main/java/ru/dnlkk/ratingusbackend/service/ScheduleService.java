package ru.dnlkk.ratingusbackend.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.api.dtos.ScheduleDto;
import ru.dnlkk.ratingusbackend.api.dtos.SubjectDto;
import ru.dnlkk.ratingusbackend.model.Lesson;
import ru.dnlkk.ratingusbackend.repository.LessonRepository;
import ru.dnlkk.ratingusbackend.repository.SubjectRepository;
import ru.dnlkk.ratingusbackend.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleService {

    private UserRepository userRepository;
    private SubjectRepository subjectRepository;
    private LessonRepository lessonRepository;



    public void addLesson(ScheduleDto scheduleDto, Lesson lesson, int lessonNumber){
        scheduleDto.getLessons().add(lessonNumber,lesson);
    }

    public List<Lesson> getLessonsByClass(int offset, int limit, String className){
        if (className != null) {
            return lessonRepository.findLessonsByClassName(className, PageRequest.of(offset, limit));
        }
        return lessonRepository.findAll(PageRequest.of(offset, limit)).stream().toList();
    }

    public SubjectDto getSubjByNameAndTeacher(){
        return null;
    }

}
