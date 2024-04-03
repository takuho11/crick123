package cn.topcheer.halberd.app.api.minidev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ModelAndTemplateDTO {
    ModelDTO model;
    TemplateDTO template; 
}
