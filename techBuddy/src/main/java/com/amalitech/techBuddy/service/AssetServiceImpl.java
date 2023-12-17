package com.amalitech.techBuddy.service;

import com.amalitech.techBuddy.Dao.AssetDao;
import com.amalitech.techBuddy.dto.AssetDto;
import com.amalitech.techBuddy.entity.Asset;
import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService{

    private final Cloudinary cloudinary;
    private final UserService userService;
    private final AssetDao assetDao;


    @Override
    public String uploadAsset(MultipartFile multipartFile, AssetDto assetDto) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();


        Asset asset = new Asset();
        asset.setDescription(assetDto.getDescription());
        asset.setFilePath(cloudinary.uploader()
                .upload(multipartFile.getBytes(),
                        Map.of("public_id", UUID.randomUUID().toString()))
                .get("url")
                .toString());
        asset.setOwner(userService.findByUsername(userDetails.getUsername()));

        assetDao.save(asset);
        return asset.getFilePath();
    }

    @Override
    public List<Asset> getAssetsByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return assetDao.findByOwner(userService.findByUsername(userDetails.getUsername()));
    }

    @Override
    public String getUsername(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return userDetails.getUsername();
    }

    @Override
    public Asset getAssetById(Long id) {
        Optional<Asset> assetToFind = assetDao.findById(id);
        if(assetToFind.isEmpty()){
            throw new IllegalArgumentException("Asset id not found");
        }
        return assetToFind.get();
    }

    @Override
    public Asset updateAsset(Asset asset) {
        return assetDao.save(asset);
    }

    @Override
    public Asset getAssetsBySearch(String search) {
        return assetDao.findByDescriptionLike(search);
    }
}
