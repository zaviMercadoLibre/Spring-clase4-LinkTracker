package Springclase4.LinkTracker.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class LinkDto {
    private String url;
    private Integer count = 0;
    private Integer linkId;
    private String password;


    public void setCount() {
        this.count++;
    }

}
