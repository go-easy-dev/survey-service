package go.easy.userservice.mapper;

import go.easy.userservice.dto.userresult.bind.UserBindSurveyRequest;
import go.easy.userservice.dto.userresult.bind.UserSurveyResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserSurveyMapper {

    UserSurveyMapper INSTANCE = Mappers.getMapper(UserSurveyMapper.class);

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    UserSurveyResult map(UserBindSurveyRequest request);

}
