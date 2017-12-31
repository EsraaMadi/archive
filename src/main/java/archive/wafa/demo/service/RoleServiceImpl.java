package archive.wafa.demo.service;


import archive.wafa.demo.command.UserRolesCommand;
import archive.wafa.demo.convertor.UserRolesToUserRolesCommand;
import archive.wafa.demo.model.UserRoles;
import archive.wafa.demo.repository.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RoleServiceImpl implements RoleService {

    private UserRolesRepository roleRepository;
    private final UserRolesToUserRolesCommand userRolesCommand;

    public RoleServiceImpl(UserRolesRepository roleRepository, UserRolesToUserRolesCommand userRolesCommand) {
        this.roleRepository = roleRepository;
        this.userRolesCommand = userRolesCommand;
    }

    @Override
    public List<?> listAll() {
        List<UserRoles> roles = new ArrayList<>();
        roleRepository.findAll().forEach(roles::add);
        return roles;
    }

    @Override
    public UserRoles getById(Long id) {
        Optional<UserRoles> roleOptional =  roleRepository.findById(id);
        return roleOptional.get();

    }

    @Override
    public UserRoles saveOrUpdate(UserRoles domainObject) {
        return roleRepository.save(domainObject);
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Set<UserRolesCommand> listAllRoles() {
        return StreamSupport.stream(roleRepository.findAll()
                .spliterator(), false)
                .map(userRolesCommand::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public void deletRole(Long userId, Long roleId) {
        roleRepository.deletRole(userId,roleId);
    }
}
