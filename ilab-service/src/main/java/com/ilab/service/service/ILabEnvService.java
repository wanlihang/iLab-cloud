package com.ilab.service.service;

import com.ilab.service.dto.CourseChapterDTO;
import com.ilab.service.dto.LabEnvDTO;

public interface ILabEnvService {

    LabEnvDTO getLabEnv(CourseChapterDTO chapterDTO);
}
