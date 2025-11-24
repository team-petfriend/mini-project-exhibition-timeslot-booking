package org.example.exhibitiontimeslotbooking.controller.exhibition;


import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.constants.ApiMappingPattern;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiMappingPattern.Exhibitions.ROOT)
@RequiredArgsConstructor
public class ExhibitionController {
}
