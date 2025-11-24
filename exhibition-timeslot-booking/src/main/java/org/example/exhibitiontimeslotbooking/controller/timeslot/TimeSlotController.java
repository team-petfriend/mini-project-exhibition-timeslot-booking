package org.example.exhibitiontimeslotbooking.controller.timeslot;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.constants.ApiMappingPattern;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiMappingPattern.Timeslots.ROOT)
@RequiredArgsConstructor
public class TimeSlotController {
}
