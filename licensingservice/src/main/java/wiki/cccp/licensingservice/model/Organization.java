package wiki.cccp.licensingservice.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Organization implements Serializable {
    private String id;
    private String name;
    private Date createTime;
}
