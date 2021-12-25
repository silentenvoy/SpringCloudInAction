package wiki.cccp.organizationservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import wiki.cccp.organizationservice.mapper.OrganizationMapper;
import wiki.cccp.organizationservice.model.Organization;
import wiki.cccp.organizationservice.service.OrganizationService;

@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {
}
