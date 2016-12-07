package homo.efficio.toy.member.service;

import homo.efficio.toy.member.domain.Member;
import homo.efficio.toy.member.dto.MemberConverter;
import homo.efficio.toy.member.dto.MemberDto;
import homo.efficio.toy.member.exception.MemberNotFoundException;
import homo.efficio.toy.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by hanmomhanda on 2016-11-13.
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    public MemberServiceImpl(
            MemberRepository repository,
             MemberConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    @Transactional
    public MemberDto save(MemberDto memberDto) {

        Member member = converter.getMemberFrom(memberDto);
        Member persistedMember = repository.save(member);

        return converter.getMemberDtoFrom(persistedMember);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberDto findByUserName(String userName) {

        Optional<Member> optMember = repository.findByUserName(userName);

        Member member = optMember.orElseThrow(() -> new MemberNotFoundException());

        return converter.getMemberDtoFrom(member);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberDto findByEmail(String email) {

        Optional<Member> optMember = repository.findByEmail(email);

        Member member = optMember.orElseThrow(() -> new MemberNotFoundException());

        return converter.getMemberDtoFrom(member);
    }

    private MemberConverter converter;

    private MemberRepository repository;
}
