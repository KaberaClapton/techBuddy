package com.amalitech.techBuddy.controller;

import com.amalitech.techBuddy.dto.AssetDto;
import com.amalitech.techBuddy.dto.UserDto;
import com.amalitech.techBuddy.service.AssetService;
import com.amalitech.techBuddy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AssetController {

    private final UserService userService;
    private final AssetService assetService;

    @PostMapping("/user/create-account")
    public String registerUser(@RequestBody UserDto userDto){
        return userService.registerUser(userDto);
    }

    @GetMapping("/asset/upload-asset")
    public String getUploadForm(){
        return "upload";
    }

    @PostMapping("/asset/upload-asset")
    public String uploadAsset(@RequestParam("assets") MultipartFile file,
                              @RequestParam("description") String assetDescription,
                              Model model) throws IOException {
        AssetDto assetDto = new AssetDto();
        assetDto.setDescription(assetDescription);

        if(file == null){
            return "No file or file description sent";
        }

        assetService.uploadAsset(file, assetDto);

        model.addAttribute("assets", assetService.getAssetsByUser());
        return "assets";
    }

    @GetMapping("/asset/my-assets")
    public String getMyAssets(Model model){
        model.addAttribute("assets", assetService.getAssetsByUser());
        return "assets";
    }
}
