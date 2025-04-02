package ru.suman.feign.adapter;

import org.springframework.stereotype.Component;
import ru.suman.api.dto.OpenQuestionCardDto;
import ru.suman.api.mapper.QuestionDtoMapper;
import ru.suman.domain.model.OpenQuestionCard;
import ru.suman.domain.repo.QuestionRepository;
import ru.suman.feign.client.ProjectFeignClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProjectAdapter implements QuestionRepository {
    private final ProjectFeignClient feignClient;
    private final QuestionDtoMapper mapper;

    public ProjectAdapter(ProjectFeignClient feignClient, QuestionDtoMapper mapper) {
        this.feignClient = feignClient;
        this.mapper = mapper;
    }

    @Override
    public List<OpenQuestionCard> findAll() {
        List<OpenQuestionCardDto> projects = feignClient.list();
        return projects.stream()
                .map(mapper::mapToModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OpenQuestionCard> findById(Long id) {
        List<OpenQuestionCardDto> projects = feignClient.list();
        return projects.stream()
                .map(mapper::mapToModel)
                .filter(project -> project.getId().equals(id))
                .findAny();
    }

    @Override
    public void add(OpenQuestionCard project) {
        feignClient.add(mapper.mapToDto(project));
    }

    @Override
    public void update(OpenQuestionCard project) {
        feignClient.update(mapper.mapToDto(project));
    }

    @Override
    public void remove(Long id) {
        feignClient.remove(id);
    }
}
