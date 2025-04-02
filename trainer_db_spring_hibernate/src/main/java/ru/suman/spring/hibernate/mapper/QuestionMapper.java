package ru.suman.spring.hibernate.mapper;

import org.mapstruct.Mapper;
import ru.suman.domain.model.OpenQuestionCard;
import ru.suman.spring.hibernate.entity.OpenQuestionCardEntity;


import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    OpenQuestionCard mapToModel(OpenQuestionCardEntity entity);
    OpenQuestionCardEntity mapToEntity(OpenQuestionCard question);
    List<OpenQuestionCard> mapToModel(List<OpenQuestionCardEntity> entities);
    List<OpenQuestionCardEntity> mapToEntity(List<OpenQuestionCard> questions);
}
