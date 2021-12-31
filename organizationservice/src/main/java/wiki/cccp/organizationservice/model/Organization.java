package wiki.cccp.organizationservice.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@TableName("organization")
public class Organization {
    @TableId("id")
    private String id;
    @TableField("name")
    private String name;
    @TableField("create_time")
    private Date createTime;

}
