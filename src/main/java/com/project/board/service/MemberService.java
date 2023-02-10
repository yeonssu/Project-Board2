package com.project.board.service;

import com.project.board.dto.MemberDto;
import com.project.board.entity.MemberEntity;
import com.project.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void savePro(MemberDto memberDto) {
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDto);
        memberRepository.save(memberEntity); //이건 JpaRepository에서 제공하는 method
    }

    public MemberDto loginPro(MemberDto memberDto) {
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDto.getMemberEmail());
        if (byMemberEmail.isPresent()) {
            //조회 결과가 있다
            MemberEntity memberEntity = byMemberEmail.get();
            if (memberEntity.getMemberPassword().equals(memberDto.getMemberPassword())) {
                // 비밀번호가 일치하는 경우
                MemberDto dto = MemberDto.toMemberDto(memberEntity);
                return dto;
            } else {
                //비밀번호가 일치하지 않는 경우
                return null;
            }
        } else {
            //조회 결과가 없다
            return null;
        }
    }

    public List<MemberDto> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDto> memberDtoList = new ArrayList<>();
        for (MemberEntity memberEntity : memberEntityList) {
            memberDtoList.add(MemberDto.toMemberDto(memberEntity));
        }
        return memberDtoList;
    }

    public MemberDto findbyId(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isPresent()) {
            return MemberDto.toMemberDto(optionalMemberEntity.get());
        }else{
            return null;
        }
    }

    public MemberDto updateForm(String myEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(myEmail);
        if (optionalMemberEntity.isPresent()){
            return MemberDto.toMemberDto(optionalMemberEntity.get());
        }else{
            return null;
        }
    }

    public void update(MemberDto memberDto) {
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDto));
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }
}
