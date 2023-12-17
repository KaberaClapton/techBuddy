package com.amalitech.techBuddy.controller;

import com.amalitech.techBuddy.dto.AssetDto;
import com.amalitech.techBuddy.dto.UserDto;
import com.amalitech.techBuddy.entity.Asset;
import com.amalitech.techBuddy.service.AssetService;
import com.amalitech.techBuddy.service.MailSenderService;
import com.amalitech.techBuddy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class AssetController {

    private final UserService userService;
    private final AssetService assetService;
    private final MailSenderService mailSender;

    @GetMapping("/")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/api/v1/login")
    public String login(){
        return "login";
    }

    @GetMapping("/api/v1/user/create-account")
    public String registerUser(Model model){

        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "create_account";
    }
    @PostMapping("/api/v1/user/create-account")
    public String registerUser(@ModelAttribute("user") UserDto userDto,
                             BindingResult result,
                             Model model){
        if(!userService.registerUser(userDto)){
            result.rejectValue("username", null,
                    "Account already exists.");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "create_account";
        }

        userService.registerUser(userDto);
        return "redirect:/api/v1/user/create-account?success";
    }

    @GetMapping("/api/v1/asset/upload-asset")
    public String getUploadForm(Model model){
        model.addAttribute("username", assetService.getUsername());
        return "upload";
    }

    @PostMapping("/api/v1/asset/upload-asset")
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

    @PostMapping("/api/v1/asset/update/{id}")
    public String updateAsset(@PathVariable String id,
                              @RequestParam("description") String description,
                              Model model){
        Long idToFind = Long.parseLong(id);
        Asset asset = assetService.getAssetById(idToFind);
        if(asset == null){
            return "Asset does not exist";
        }

        asset.setDescription(description);
        assetService.updateAsset(asset);
        model.addAttribute("assets", assetService.getAssetsByUser());
        return "assets";
    }

    @GetMapping("/api/v1/asset/my-assets")
    public String getMyAssets(Model model){
        model.addAttribute("username", assetService.getUsername());
        model.addAttribute("assets", assetService.getAssetsByUser());
        return "assets";
    }

    @GetMapping("/api/v1/asset-details/{id}")
    public String getAssetDetails(@PathVariable String id, Model model){
        model.addAttribute("username", assetService.getUsername());
        model.addAttribute("asset", assetService.getAssetById(Long.parseLong(id)));
        return "details";
    }

    @PostMapping("/subscribe")
    public String subscribeToNewsletter(@RequestParam("email") String email){
        mailSender.sendEmail(email,
                "Welcome to the TechBuddy Newsletter! Hooray!",
                "Thank you very much for trusting us to manage your" +
                        "content assets with the help of the Cloudinary platform.");

        return "redirect:?subscribed";
    }

    @PostMapping("/api/v1/searchBy")
    public String searchAsset(@RequestParam("search") String search, Model model){
        model.addAttribute("username", assetService.getUsername());
        model.addAttribute("assets", assetService.getAssetsBySearch(search));
        return "assets";
    }
}
