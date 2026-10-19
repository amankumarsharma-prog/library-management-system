package com.library.libraryManagementSystem.controller;

import com.library.libraryManagementSystem.service.MemberService;
import com.library.libraryManagementSystem.dto.MemberRequestDto;
import com.library.libraryManagementSystem.dto.MemberResponseDto;
import com.library.libraryManagementSystem.dto.UpdateMemberResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<MemberResponseDto> createMember( @Valid @RequestBody MemberRequestDto memberRequest){
       MemberResponseDto memberResponse = memberService.createMember(memberRequest);
       return ResponseEntity.status(HttpStatus.CREATED).body(memberResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable Long id){
       MemberResponseDto memberResponse = memberService.getMember(id);
       return ResponseEntity.ok(memberResponse);
    }

    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> getAllMember(){
        List<MemberResponseDto> memberResponse = memberService.getAllMember();
        return ResponseEntity.ok(memberResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateMemberResponseDto> updateMember(
            @PathVariable Long id,  @Valid @RequestBody MemberRequestDto memberRequest){

        UpdateMemberResponseDto memberResponse =  memberService.updateMember(id, memberRequest);
        return ResponseEntity.ok(memberResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id){
        memberService.deleteMember(id);
        return ResponseEntity.ok("DELETED..");
    }
}
