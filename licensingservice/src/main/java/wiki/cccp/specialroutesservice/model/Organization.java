package wiki.cccp.specialroutesservice.model;

import lombok.Data;

import java.util.Date;

@Data
public class Organization {
    private String id;
    private String name;
    private Date createTime;
}
