package com.project.board.entity;

import com.project.board.dto.MemberDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="member_table")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    public static MemberEntity toMemberEntity(MemberDto memberDto){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDto.getMemberEmail());
        memberEntity.setMemberPassword(memberDto.getMemberPassword());
        memberEntity.setMemberName(memberDto.getMemberName());
        return memberEntity;
    }
    public static MemberEntity toUpdateMemberEntity(MemberDto memberDto){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDto.getId());
        memberEntity.setMemberEmail(memberDto.getMemberEmail());
        memberEntity.setMemberPassword(memberDto.getMemberPassword());
        memberEntity.setMemberName(memberDto.getMemberName());
        return memberEntity;
    }
}
