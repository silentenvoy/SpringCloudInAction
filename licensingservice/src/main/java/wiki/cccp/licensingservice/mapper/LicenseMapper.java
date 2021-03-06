package wiki.cccp.licensingservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import wiki.cccp.licensingservice.model.License;

import java.util.List;

@Mapper
public interface LicenseMapper extends BaseMapper<License> {

    public List<License> queryOrganLicenses(@Param("organizationId") String organizationId);
}
