package com.amalitech.techBuddy.dto;

import com.amalitech.techBuddy.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetDto {

    private String filePath;
    private String description;
    private User owner;
}
