package shop.users.services;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import shop.common.PagedResult;
import shop.users.model.User;
import shop.users.model.UserTransferObject;
import shop.web.PagedResultTransferObject;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "emailAddress", target = "email")
    User toUser(UserTransferObject userTransferObject);

    @Mapping(source = "email", target = "emailAddress")
    UserTransferObject toUserTransferObject(User user);

    @IterableMapping(elementTargetType = UserTransferObject.class)
    List<UserTransferObject> toUserTransferObjects(List<User> users);

    PagedResultTransferObject<UserTransferObject> toUserTransferObjectsPage(PagedResult<User> usersPage);

}
