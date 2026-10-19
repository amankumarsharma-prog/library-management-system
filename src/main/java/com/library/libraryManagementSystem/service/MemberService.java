package com.library.libraryManagementSystem.service;

import com.library.libraryManagementSystem.modal.Members;
import com.library.libraryManagementSystem.exception.DuplicateFoundException;
import com.library.libraryManagementSystem.exception.ResourceNotFoundException;
import com.library.libraryManagementSystem.repository.MemberRepository;
import com.library.libraryManagementSystem.dto.MemberRequestDto;
import com.library.libraryManagementSystem.dto.MemberResponseDto;
import com.library.libraryManagementSystem.dto.UpdateMemberResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MemberService{

    private MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberResponseDto createMember(MemberRequestDto memberRequest){

      Members responseEntity = mapToEntity(memberRequest);

      if(existEmail(responseEntity)){
          throw new DuplicateFoundException("Email " + responseEntity.getEmail() + " IS ALREADY EXIST.");
      }

       Members saveResponse = memberRepository.save(responseEntity);
       return  mapToDto(saveResponse);
    }

    public MemberResponseDto getMember(Long id){
        Members existMember = memberRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("ID "+ id + " IS NOT EXIST"));
        return mapToDto(existMember);
    }

    public List<MemberResponseDto> getAllMember(){
        List<Members> allMembers = memberRepository.findAll();
        return allMembers.stream().map(this::mapToDto).toList();
    }

    public UpdateMemberResponseDto updateMember(Long id, MemberRequestDto memberRequest){
       Members existMember = memberRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("ID "+ id + " IS NOT EXIST"));

       existMember.setName(memberRequest.getName());
       existMember.setEmail(memberRequest.getEmail());
       existMember.setPhone(memberRequest.getPhone());
       existMember.setUpdateAt(LocalDateTime.now());

       Members saveResponse = memberRepository.save(existMember);
       return mapToUpdateDto(saveResponse);
    }

    public void deleteMember(Long id ){
        Members existMember = memberRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("ID "+ id + " IS NOT EXIST"));
        memberRepository.delete(existMember);
    }

    private Members mapToEntity(MemberRequestDto memberRequest){
        Members membersToSave = new Members();

        membersToSave.setName(memberRequest.getName());
        membersToSave.setEmail(memberRequest.getEmail());
        membersToSave.setPhone(memberRequest.getPhone());
        membersToSave.setCurrentAt(LocalDateTime.now());
        membersToSave.setUpdateAt(LocalDateTime.now());

        return membersToSave;
    }

    private MemberResponseDto mapToDto(Members saveResponse){

        MemberResponseDto memberToResponse = new MemberResponseDto();

        memberToResponse.setId(saveResponse.getId());
        memberToResponse.setName(saveResponse.getName());
        memberToResponse.setEmail(saveResponse.getEmail());
        memberToResponse.setPhone(saveResponse.getPhone());
        memberToResponse.setCurrentAt(saveResponse.getCurrentAt());

        return memberToResponse;
    }

    private UpdateMemberResponseDto mapToUpdateDto(Members saveResponse){
        UpdateMemberResponseDto updateMemberResponse = new UpdateMemberResponseDto();

        updateMemberResponse.setId(saveResponse.getId());
        updateMemberResponse.setName(saveResponse.getName());
        updateMemberResponse.setEmail(saveResponse.getEmail());
        updateMemberResponse.setPhone(saveResponse.getPhone());
        updateMemberResponse.setUpdateAt(saveResponse.getUpdateAt());

        return updateMemberResponse;
    }

    private Boolean existEmail(Members responseEntity){
        return memberRepository.existsByEmail(responseEntity.getEmail());
    }
}
