package ru.dnlkk.ratingusbackend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.api.dtos.announcement.AnnouncementCreateDto;
import ru.dnlkk.ratingusbackend.api.dtos.announcement.AnnouncementDto;
import ru.dnlkk.ratingusbackend.exceptions.ForbiddenException;
import ru.dnlkk.ratingusbackend.exceptions.LogicException;
import ru.dnlkk.ratingusbackend.exceptions.NotFoundException;
import ru.dnlkk.ratingusbackend.mapper.announcement.AnnouncementMapper;
import ru.dnlkk.ratingusbackend.model.*;
import ru.dnlkk.ratingusbackend.model.Class;
import ru.dnlkk.ratingusbackend.model.enums.Role;
import ru.dnlkk.ratingusbackend.repository.AnnouncementRepository;
import ru.dnlkk.ratingusbackend.repository.ClassRepository;
import ru.dnlkk.ratingusbackend.repository.SchoolRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;
    private final ClassRepository classRepository;
    private final SchoolRepository schoolRepository;

    private void checkIsUserRoleNull(UserDetailsImpl userDetails) {
        if (userDetails.getUserRole() == null) {
            throw new LogicException("Доступ запрещён");
        }
    }

    public void incrementViews(List<Announcement> announcements){
        for (Announcement announcement : announcements) {
            announcement.setViews(announcement.getViews() + 1);
        }
        announcementRepository.saveAll(announcements);
    }

    private void checkUserIsTeacherOrHigher(UserDetailsImpl userDetails) {
        Role roleOfUser = userDetails.getUserRole().getRole();
        if (roleOfUser != Role.LOCAL_ADMIN && roleOfUser != Role.MANAGER && roleOfUser != Role.TEACHER) {
            throw new ForbiddenException("Доступ запрещён");
        }
    }

    public List<AnnouncementDto> getAllAnnouncementsPagination(UserDetailsImpl userDetails, int offset, int limit, Integer classId) {
        checkIsUserRoleNull(userDetails);
        int schoolId = userDetails.getUserRole().getSchool().getId();
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
            incrementViews(announcements);
            return AnnouncementMapper.INSTANCE.toDtoList(announcements);
        }
        School school = schoolRepository.findById(schoolId).get();
        List<Class> classes = school.getClasses();
        List<Announcement> announcements = announcementRepository.getAnnouncementsByClassesIn(classes, PageRequest.of(offset, limit)).stream().toList();
        incrementViews(announcements);
        return AnnouncementMapper.INSTANCE.toDtoList(sortAnnouncementsByDateDesc(announcements));
    }

    public List<AnnouncementDto> getAnnouncementsByClassId(UserDetailsImpl userDetails, Integer classId) {
        Optional<Class> optionalClass = classRepository.findById(classId);
        if (optionalClass.isEmpty()) {
            throw new NotFoundException("Класс не найден");
        }
        Class clazz = optionalClass.get();
        List<Announcement> announcements = announcementRepository.getAnnouncementsByClassesIn(List.of(clazz), null).stream().toList();

        incrementViews(announcements);
        return AnnouncementMapper.INSTANCE.toDtoList(sortAnnouncementsByDateDesc(announcements));
    }

    public List<Announcement> sortAnnouncementsByDateDesc(List<Announcement> announcements) {
        return announcements.stream()
                .sorted(Comparator.comparing(Announcement::getCreateDate).reversed())
                .collect(Collectors.toList());
    }

    public void deleteAnnouncementById(UserDetailsImpl userDetails, int id) {
        checkIsUserRoleNull(userDetails);
        checkUserIsTeacherOrHigher(userDetails);
        announcementRepository.deleteById(id);
    }

    public AnnouncementDto createAnnouncement(UserDetailsImpl userDetails, AnnouncementCreateDto announcementCreateDto) {
        checkIsUserRoleNull(userDetails);
        checkUserIsTeacherOrHigher(userDetails);
        UserRole creator = userDetails.getUserRole();
        Announcement announcement = AnnouncementMapper.INSTANCE.toModel(announcementCreateDto);
        List<Class> classes = announcement.getClasses();
        for (Class c : classes) {
            Optional<Class> classById = classRepository.findById(c.getId());
            if (classById.isEmpty()) {
                throw new NotFoundException("Не найден класс с id=" + c.getId());
            }
            Class cc = classById.get();
            if (cc.getSchool().getId() != creator.getSchool().getId()) {
                throw new ForbiddenException("Нет прав, чтобы создать объявление класса c id=" + c.getId());
            }
        }
        announcement.setCreator(creator);
        Announcement announcementAfterSaving = announcementRepository.saveAndFlush(announcement);
        return AnnouncementMapper.INSTANCE.toDto(announcementAfterSaving);
    }
}
