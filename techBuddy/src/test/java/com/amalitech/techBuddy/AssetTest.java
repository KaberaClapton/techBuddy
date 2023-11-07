package com.amalitech.techBuddy;

import com.amalitech.techBuddy.Dao.AssetDao;
import com.amalitech.techBuddy.dto.UserDto;
import com.amalitech.techBuddy.entity.Asset;
import com.amalitech.techBuddy.service.AssetService;
import com.amalitech.techBuddy.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RequiredArgsConstructor
public class AssetTest {

    private final AssetService assetService;
    private final UserService userService;
    private final AssetDao assetDao;

    @Test
    public void saveAssetTest(){
        log.info("Saving asset to DB");
        Asset asset = new Asset();

        UserDto userDto = new UserDto();
        userDto.setUsername("TestUser");
        userDto.setPassword("test123");
        userService.registerUser(userDto);

        asset.setId(Long.parseLong("1"));
        asset.setDescription("Test Description");
        asset.setFilePath("testFilePath");
        asset.setOwner(userService.findByUsername(userDto.getUsername()));

        assertNotNull(assetDao.save(asset));
    }

    @Test
    public void allAssetsTest(){
        List<Asset> assets = assetDao.findAll();
        assertNotNull(assets);
    }
}
