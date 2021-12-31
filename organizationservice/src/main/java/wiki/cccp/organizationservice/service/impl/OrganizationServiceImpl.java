package wiki.cccp.organizationservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wiki.cccp.organizationservice.mapper.OrganizationMapper;
import wiki.cccp.organizationservice.model.Organization;
import wiki.cccp.organizationservice.service.OrganizationService;
import wiki.cccp.organizationservice.stream.SimpleSourceBean;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {
    @Autowired
    private SimpleSourceBean simpleSource;

    @Override
    public Organization getOrganization(String id) {
        System.out.println(Thread.currentThread().getId());
        simpleSource.publicOrgChange("QUERY", id);
        return baseMapper.selectById(id);
    }

}
