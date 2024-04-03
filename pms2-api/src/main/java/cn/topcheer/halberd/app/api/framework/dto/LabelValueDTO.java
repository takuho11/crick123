package cn.topcheer.halberd.app.api.framework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zuowentao
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabelValueDTO {

    private String label;
    private String prop;
    private String unit;

    public LabelValueDTO(String label, String prop) {
        this.label = label;
        this.prop = prop;
    }
}
