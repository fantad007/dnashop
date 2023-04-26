package com.dna.shop.api;

import com.dna.shop.dto.ColorDto;
import com.dna.shop.entity.ColorEntity;
import com.dna.shop.repository.ColorRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/san-pham")
@AllArgsConstructor
public class ProductApi {
    private final ColorRepository colorRepository;

    @ResponseBody
    @GetMapping("/chi-tiet-san-pham/cap-nhat-size/{sizeId}")
    public List<ColorDto> getColorsBySizeId(@PathVariable("sizeId") long sizeId) {
        List<ColorDto> colorDtos = new ArrayList<>();
        List<ColorEntity> colors = colorRepository.getAllBySizeId(sizeId);
        for (ColorEntity color : colors) {
            ColorDto colorDto = new ColorDto();
            colorDto.setColorId(color.getId());
            colorDto.setColorName(color.getName());
            colorDtos.add(colorDto);
        }
        return colorDtos;
    }
}
