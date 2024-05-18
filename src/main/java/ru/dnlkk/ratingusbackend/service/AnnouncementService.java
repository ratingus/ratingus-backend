package ru.dnlkk.ratingusbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.api.dtos.AnnouncementDto;
import ru.dnlkk.ratingusbackend.exceptions.ForbiddenException;
import ru.dnlkk.ratingusbackend.exceptions.NotFoundException;
import ru.dnlkk.ratingusbackend.mapper.announcement.AnnouncementMapper;
import ru.dnlkk.ratingusbackend.model.Announcement;
import ru.dnlkk.ratingusbackend.model.Class;
import ru.dnlkk.ratingusbackend.model.School;
import ru.dnlkk.ratingusbackend.repository.AnnouncementRepository;
import ru.dnlkk.ratingusbackend.repository.ClassRepository;
import ru.dnlkk.ratingusbackend.repository.SchoolRepository;
import ru.dnlkk.ratingusbackend.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;
    private final ClassRepository classRepository;
    private final UserRepository userRepository;
    private final SchoolRepository schoolRepository;

    public List<AnnouncementDto> getAllAnnouncementsPagination(int offset, int limit, Integer classId, int schoolId) {
        if (classId != null) {
            Optional<Class> classById = classRepository.findById(classId);
            if (classById.isEmpty()) {
                throw new NotFoundException("Такого класса не существует");
            }
            Class c = classById.get();
            if (c.getSchool().getId() != schoolId) {
                throw new ForbiddenException("Нет доступа к этому классу");
            }
            List<Announcement> announcements = announcementRepository.findByClasses_Id(classId, PageRequest.of(offset, limit));
            return AnnouncementMapper.INSTANCE.toDtoList(announcements);
        }
        School school = schoolRepository.findById(schoolId).get();
        List<Class> classes = school.getClasses();
        List<Announcement> announcements = announcementRepository.getAnnouncementsByClassesIn(classes, PageRequest.of(offset, limit)).stream().toList();
        return AnnouncementMapper.INSTANCE.toDtoList(announcements);
    }

    public AnnouncementDto getAnnouncementById(int id, int schoolId) {
        Optional<Announcement> optionalAnnouncement = announcementRepository.findById(id);
        if (optionalAnnouncement.isEmpty()) {
            throw new NotFoundException("Такое объявление не найдено");
        }
        Announcement announcement = optionalAnnouncement.get();
        List<Class> classes = announcement.getClasses();
        for (Class c : classes) {
            if (c.getSchool().getId() == schoolId) {
                return AnnouncementMapper.INSTANCE.toDto(announcement);
            }
        }
        throw new ForbiddenException("Нет доступа к этому объявлению");
    }

    public AnnouncementDto createAnnouncement(AnnouncementDto announcementDto, int creatorId, int schoolId) {
        Announcement announcement = AnnouncementMapper.INSTANCE.toModel(announcementDto);
        List<Class> classes = announcement.getClasses();
        for (Class c : classes) {
            Optional<Class> classById = classRepository.findById(c.getId());
            if (classById.isEmpty()) {
                throw new NotFoundException("Не найден класс с id=" + c.getId());
            }
            Class cc = classById.get();
            if (cc.getSchool().getId() != schoolId) {
                throw new ForbiddenException("Нет прав, чтобы создать объявление класса c id=" + c.getId() );
            }
        }
        announcement.getCreator().setId(creatorId);
        Announcement announcementAfterSaving = announcementRepository.saveAndFlush(announcement);
        return AnnouncementMapper.INSTANCE.toDto(announcementAfterSaving);
    }

    public void deleteAnnouncementById(int id) {
        announcementRepository.deleteById(id);
    }
}
