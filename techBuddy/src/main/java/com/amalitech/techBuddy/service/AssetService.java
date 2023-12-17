package com.amalitech.techBuddy.service;

import com.amalitech.techBuddy.dto.AssetDto;
import com.amalitech.techBuddy.entity.Asset;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface AssetService {
    String uploadAsset(MultipartFile multipartFile, AssetDto assetDto) throws IOException;
    List<Asset> getAssetsByUser();
    String getUsername();
    Asset getAssetById(Long id);
    Asset updateAsset(Asset asset);
    Asset getAssetsBySearch(String search);
}
