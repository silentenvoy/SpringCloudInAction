package wiki.cccp.organizationservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import wiki.cccp.organizationservice.model.Organization;

@Mapper
public interface OrganizationMapper extends BaseMapper<Organization> {
}
