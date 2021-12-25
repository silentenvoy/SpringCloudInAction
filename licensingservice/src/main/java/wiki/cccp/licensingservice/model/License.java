package wiki.cccp.licensingservice.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@TableName(value = "license", excludeProperty = {"organization"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class License {
    @TableId("id")
    private String id;
    @TableField("name")
    private String name;
    @TableField("organization_id")
    private String organizationId;

    private Organization organization;
}
